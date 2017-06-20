/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by testuser on 16-12-15.
 */
public class HelloworldServiceTest {
    private static final Logger LOG = LoggerFactory.getLogger(HelloworldServiceTest.class);

    public static void main(String[] args) {
        try {
            new ClassPathXmlApplicationContext("classpath:serverApplication.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.currentThread().sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
