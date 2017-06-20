/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.factory;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by testuser on 16-12-14.
 */
public class ZookeeperFactory implements FactoryBean<CuratorFramework> {
    private final static String ROOT = "rpc";
    private String zkHosts;
    // session超时
    private int sessionTimeout = 30000;
    private int connectionTimeout = 30000;
    // 共享一个zk链接
    private boolean singleton = true;
    private volatile CuratorFramework zkClient;

    public static CuratorFramework create(String zkHosts, int sessionTimeout, int connectionTimeout, String namespace) {
        return CuratorFrameworkFactory.builder().connectString(zkHosts).sessionTimeoutMs(sessionTimeout).connectionTimeoutMs(connectionTimeout)
                .canBeReadOnly(true).namespace(namespace).retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .defaultData(null).build();
    }

    public void setZkHosts(String zkHosts) {
        this.zkHosts = zkHosts;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    @Override
    public CuratorFramework getObject() throws Exception {
        if (singleton) {
            if (zkClient == null) {
                synchronized (ZookeeperFactory.class) {
                    if (zkClient == null) {
                        zkClient = create();
                        zkClient.start();
                    }
                }
            }
            return zkClient;
        }
        return create();
    }

    @Override
    public Class<?> getObjectType() {
        return CuratorFramework.class;
    }

    @Override
    public boolean isSingleton() {
        return singleton;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public CuratorFramework create() {
        return create(zkHosts, sessionTimeout, connectionTimeout, ROOT);
    }
}
