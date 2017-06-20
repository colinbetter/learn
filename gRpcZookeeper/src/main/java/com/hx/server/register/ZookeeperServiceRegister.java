/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.server.register;

import com.hx.exception.RegistException;
import com.hx.node.ComponentNode;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

/**
 * Created by testuser on 16-12-14.
 */
public class ZookeeperServiceRegister implements ServiceRegister, Closeable {
    private static final Logger LOG = LoggerFactory.getLogger(ZookeeperServiceRegister.class);

    private Pattern pattern = Pattern.compile("(\\d{1,3}\\.){3}\\d{1,3}:\\d+");

    private CuratorFramework zkClient;

    public ZookeeperServiceRegister() {

    }

    public void setZkClient(CuratorFramework zkClient) {
        this.zkClient = zkClient;
    }

    @Override
    public void register(String metaspace, String service, String ip, int port) {
        if (metaspace == null || service == null || metaspace.contains("/") || service.contains("/")) {
            throw new IllegalArgumentException("metaspace or service can not be null or contains \"/\"");
        } else {
            metaspace = metaspace.replaceAll(":", "");
            service = service.replaceAll(":", "");
        }
        if (!StringUtils.hasText(metaspace) || !StringUtils.hasText(service)) {
            throw new IllegalArgumentException("service or version can not be empty or just some \":\" ");
        }
        String serverPath = "/" + metaspace + "/" + service + "/" + ComponentNode.SERVER;
        buildPath(serverPath, ip + ":" + port);

    }

    @Override
    public void register(String metaspace, String service, String address) {
        if (metaspace == null || service == null || metaspace.contains("/") || service.contains("/")) {
            throw new IllegalArgumentException("metaspace or service can not be null or contains \"/\"");
        } else {
            metaspace = metaspace.replaceAll(":", "");
            service = service.replaceAll(":", "");
        }
        if (!StringUtils.hasText(metaspace) || !StringUtils.hasText(service)) {
            throw new IllegalArgumentException("service or version can not be empty or just some \":\" ");
        }

        if (!pattern.matcher(address).matches()) {
            LOG.error("the address can not be accepted");
            throw new RegistException("the address can not be accepted,the address is 'ip:port:weight'");
        }
        String serverPath = "/" + metaspace + "/" + service + "/" + ComponentNode.SERVER;
        buildPath(serverPath, address);

    }

    private void buildPath(String serverPath, String address) {
        try {
            zkClient.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath(serverPath + "/" + address);
        } catch (UnsupportedEncodingException e) {
            LOG.error("register service address to zookeeper exception:{}", e);
            throw new RegistException("register service address to zookeeper exception: address UnsupportedEncodingException", e);
        } catch (Exception e) {
            LOG.error("register service address to zookeeper exception:{}", e);
            throw new RegistException("register service address to zookeeper exception:{}", e);
        }
    }

    @Override
    public void close() throws IOException {
        if (zkClient != null) {
            zkClient.close();
        }
    }
}
