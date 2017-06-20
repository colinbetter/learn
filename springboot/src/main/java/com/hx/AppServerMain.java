

package com.hx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

/**
 * Created by testuser on 16-12-7.
 */
@EnableTransactionManagement(proxyTargetClass = true)
@EnableAspectJAutoProxy
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AppServerMain {
    private static final Logger logger = LoggerFactory.getLogger(AppServerMain.class);

    public static void main(String args[]) {
        if (logger.isDebugEnabled()) {
            logger.debug("Service start ...");
        }
        SpringApplication.run(AppServerMain.class, args);
        JedisCluster jedisCluster = new JedisCluster(new HostAndPort("172.27.12.124", 6379));
        jedisCluster.set("sdf", "sdf");
        Jedis jedis = new Jedis();
    }
}
