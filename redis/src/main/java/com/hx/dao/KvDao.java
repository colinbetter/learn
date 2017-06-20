/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * Created by testuser on 17-4-5.
 */
@Repository("kvDao")
public class KvDao implements ApplicationContextAware {
    private static final Logger LOG = LoggerFactory.getLogger(KvDao.class);
    @Autowired
    private StringRedisTemplate redisTemplate;

    public void insert(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, 30, TimeUnit.SECONDS);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LOG.info(applicationContext.toString());
    }
}
