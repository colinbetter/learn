

package com.hx.redisanalysis;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by testuser on 17-1-18.
 */
public class Test {
    public static void main(String args[]) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("redisClusterTest.xml");
        RedisTemplate redisTemplate = (RedisTemplate) beanFactory.getBean("redisTemplate");
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                Object object = connection.del("huangxing".getBytes());
                return object;
            }
        });

    }
}
