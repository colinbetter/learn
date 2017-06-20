/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.test;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 */
public class App {


    public static void test(String[] args) {
        System.out.println("Hello World!");
        JedisCluster jedisCluster = null;
        Jedis jedis = null;
        Pipeline pipeline = jedis.pipelined();
        Map<String, Response<String>> responseMap = new HashMap<>();
        List<String> keys = new ArrayList<>();
        for (String key : keys) {
            responseMap.put(key, pipeline.get(key));
        }
        pipeline.sync();
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, Response<String>> entry : responseMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue().get());
        }
        StringRedisTemplate stringRedisTemplate = null;
        stringRedisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.getNativeConnection();//获取原生的jedis或者jedisCluster等
                return null;
            }
        }, true);
    }
}
