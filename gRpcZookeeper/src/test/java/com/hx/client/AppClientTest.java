/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.client;

import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by testuser on 16-12-16.
 */
public class AppClientTest {
    private static final Logger LOG = LoggerFactory.getLogger(AppClientTest.class);

    public static void main(String[] args) {
        try {
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:clientApplication.xml");
            HelloWorldClient helloWorldClient = applicationContext.getBean(HelloWorldClient.class);
            CuratorFramework zkClient = applicationContext.getBean(CuratorFramework.class);
            CuratorFramework zkClient2 = applicationContext.getBean(CuratorFramework.class);
            helloWorldClient.greet("huangxing");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
