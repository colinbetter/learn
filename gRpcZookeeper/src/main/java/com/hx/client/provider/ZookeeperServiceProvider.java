/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.client.provider;

import com.hx.node.ComponentNode;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * Created by testuser on 16-12-14.
 */
public class ZookeeperServiceProvider implements ServiceProvider, DisposableBean {

    private static final Logger LOG = LoggerFactory.getLogger(ZookeeperServiceProvider.class);

    private static Random random = new Random();
    private final Map<String, List<InetSocketAddress>> serverMap = new ConcurrentHashMap<>();
    private final Map<String, PathChildrenCache> cachedPathMap = new ConcurrentHashMap<>();
    private CuratorFramework zkClient;

    //必须配置，Spring加载时会使用zkClient
    public void setZkClient(CuratorFramework zkClient) {
        this.zkClient = zkClient;
    }

    @Override
    public List<InetSocketAddress> findServerAddressList(String metaspace, String service) {
        if (!StringUtils.hasText(metaspace) || !StringUtils.hasText(service) || metaspace.contains("/") || service.contains("/")) {
            throw new IllegalArgumentException("metaspace or service can not be null or contains \"/\"");
        }
        String metaspaceServicePath = "/" + metaspace + "/" + service;
        List<InetSocketAddress> serverList = serverMap.get(metaspaceServicePath);
        if (serverList == null) {
            synchronized (serverMap) {
                serverList = serverMap.get(metaspaceServicePath);
                if (serverList == null) {
                    try {
                        innit(metaspaceServicePath);
                        serverList = serverMap.get(metaspaceServicePath);
                    } catch (Exception e) {
                        LOG.error("can not innit the metaspace " + metaspace + " with the service " + service, e);
                        return null;
                    }
                }
            }
        }
        return Collections.unmodifiableList(serverList);
    }


    @Override
    public InetSocketAddress selector(String metaspace, String service) {
        List<InetSocketAddress> serverList = findServerAddressList(metaspace, service);
        if (serverList != null && !serverList.isEmpty()) {
            return serverList.get(random.nextInt(serverList.size()));
        }
        return null;
    }


    private void innit(String metaspaceServicePath) throws Exception {
        assert zkClient != null;
        InetAddress inetAddress = InetAddress.getLocalHost();
        // 如果zk尚未启动,则启动
        if (zkClient.getState() == CuratorFrameworkState.LATENT) {
            zkClient.start();
        }
        //生成client节点
        if (cachedPathMap.get(metaspaceServicePath) == null) {
            zkClient.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath(metaspaceServicePath + "/" + ComponentNode.CLIENT + "/" + inetAddress.getHostAddress());
        }
        buildPathChildrenCache(zkClient, metaspaceServicePath, true);
    }

    private void buildPathChildrenCache(final CuratorFramework client, String metaspaceServicePath, Boolean cacheData) throws Exception {
        String listenPath = metaspaceServicePath + "/" + ComponentNode.SERVER;
        PathChildrenCache cachedPath = new PathChildrenCache(client, listenPath, cacheData);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        cachedPath.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                PathChildrenCacheEvent.Type eventType = event.getType();
                switch (eventType) {
                    case CONNECTION_RECONNECTED:
                        LOG.info("Connection is reconection.");
                        break;
                    case CONNECTION_SUSPENDED:
                        LOG.info("Connection is suspended.");
                        break;
                    case CONNECTION_LOST:
                        LOG.warn("Connection error,waiting...");
                        return;
                    case INITIALIZED:
                        //	countDownLatch.countDown();
                        LOG.warn("Connection init ...");
                    default:
                        //
                }
                // 任何节点的时机数据变动,都会rebuild,此处为一个"简单的"做法.
                cachedPath.rebuild();
                rebuild(metaspaceServicePath);
                countDownLatch.countDown();
            }

            protected void rebuild(String metaspaceServicePath) throws Exception {

                List<ChildData> children = cachedPath.getCurrentData();
                List<InetSocketAddress> serverList = serverMap.get(metaspaceServicePath);
                if (serverList == null) {
                    serverList = new ArrayList<InetSocketAddress>();
                    serverMap.put(metaspaceServicePath, serverList);
                }
                if (children == null || children.isEmpty()) {
                    // 有可能所有的server都与zookeeper断开了链接
                    // 但是,有可能,client与server之间的网络是良好的
                    // 因此此处是否需要清空container,是需要多方面考虑的.
                    LOG.warn("no server provide service ....");
                    serverList.clear();
                    return;
                }
                List<InetSocketAddress> current = new ArrayList<InetSocketAddress>();
                String address = null;
                for (ChildData data : children) {
                    address = data.getPath();
                    LOG.debug("get path:" + address);
                    address = address.substring(listenPath.length() + 1);
                    LOG.debug("get serviceAddress:" + address);
                    InetSocketAddress inetSocketAddress = transfer(address);
                    if (inetSocketAddress != null) {
                        current.add(inetSocketAddress);
                    }
                }
                Collections.shuffle(current);
                serverList.clear();
                serverList.addAll(current);
            }
        });
        cachedPath.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cachedPathMap.put(metaspaceServicePath, cachedPath);
        countDownLatch.await();
    }

    private InetSocketAddress transfer(String address) {
        String[] hostname = address.split(":");
        if (hostname.length != 2) {
            return null;
        }
        String ip = hostname[0];
        Integer port = Integer.valueOf(hostname[1]);
        return new InetSocketAddress(ip, port);
    }

    @Override
    public void destroy() {
        if (zkClient != null) {
            zkClient.close();
        }
        if (!cachedPathMap.isEmpty()) {
            cachedPathMap.forEach((key, cachedPath) -> {
                try {
                    cachedPath.close();
                } catch (IOException e) {
                    LOG.error("can not close PathChildrenCache for " + key, e);
                }
            });
        }
    }
}
