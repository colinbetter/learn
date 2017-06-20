/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.conf;

import com.hx.listener.KeyExpiredMessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by testuser on 17-4-5.
 */
@Configuration
public class RedisConfig {
    private static final Logger LOG = LoggerFactory.getLogger(RedisConfig.class);

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(StringRedisTemplate redisTemplate,
                                                                       KeyExpiredMessageListener keyExpiredMessageListener) {
        LOG.info("redisTemplate is " + (redisTemplate == null));
        LOG.info("keyExpiredMessageListener is " + (keyExpiredMessageListener == null));
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisTemplate.getConnectionFactory());
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        container.setTaskExecutor(executorService);
        Set<Topic> topicSet = new HashSet<>();
        topicSet.add(new ChannelTopic("__keyevent@0__:expired"));
        container.addMessageListener(keyExpiredMessageListener, topicSet);
        return container;
    }
}
