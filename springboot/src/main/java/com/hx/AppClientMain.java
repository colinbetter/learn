/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by testuser on 16-12-7.
 */
@EnableTransactionManagement(proxyTargetClass = true)
@EnableAspectJAutoProxy
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AppClientMain {
    private static final Logger logger = LoggerFactory.getLogger(AppClientMain.class);

    public static void main(String args[]) {
        if (logger.isDebugEnabled()) {
            logger.debug("Service start ...");
        }
        SpringApplication.run(AppClientMain.class, args);
    }
}
