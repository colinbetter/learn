/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.redisanalysis;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisShardInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by testuser on 17-1-18.
 */

public class JedisConnectionFactoryFactoryBean implements FactoryBean<JedisConnectionFactory>, InitializingBean {

    private String[] hostPorts;

    private JedisConnectionFactory jedisConnectionFactory = null;

    @Override
    public void afterPropertiesSet() throws Exception {

        if (hostPorts != null && hostPorts.length > 0) {

            if (hostPorts.length > 1) {
                List<String> hostPortList = new ArrayList<>(hostPorts.length);
                for (int i = 0; i < hostPorts.length; i++) {
                    hostPortList.add(hostPorts[i]);
                }
                jedisConnectionFactory = new JedisConnectionFactory(new RedisClusterConfiguration(hostPortList));
            } else {
                String[] ipPort = hostPorts[0].split(":");
                if (ipPort.length != 2) {
                    throw new RuntimeException("the ip and port string must contain \":\" to split");
                }
                jedisConnectionFactory = new JedisConnectionFactory(
                        new JedisShardInfo(ipPort[0], Integer.valueOf(ipPort[1])));
            }
        }

    }

    @Override
    public JedisConnectionFactory getObject() throws Exception {
        return jedisConnectionFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return JedisConnectionFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String[] getHostPorts() {
        return hostPorts;
    }

    public void setHostPorts(String[] hostPorts) {
        this.hostPorts = hostPorts;
    }
}
