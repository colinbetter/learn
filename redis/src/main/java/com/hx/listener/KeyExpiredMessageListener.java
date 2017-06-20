

package com.hx.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by testuser on 17-4-5.
 */
@Component("keyExpiredMessageListener")
public class KeyExpiredMessageListener implements MessageListener {
    private static final Logger LOG = LoggerFactory.getLogger(KeyExpiredMessageListener.class);
    private AtomicLong number = new AtomicLong();
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();
        String itemValue = (String) redisTemplate.getValueSerializer().deserialize(body);
        String itemChannel = redisTemplate.getStringSerializer().deserialize(channel);
        LOG.info("message body is " + itemValue + ",the message is from " + itemChannel);
        LOG.info("the value is" + redisTemplate.opsForValue().get(itemValue) + ", now is " + number.getAndIncrement());
    }
}
