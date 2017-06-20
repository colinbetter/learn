/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by testuser on 16-10-8.
 */
public class ScheduleAtFixedRateTest {
    public static void main(String args[]) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        Runnable r1 = () -> {
            System.out.println("begin1" + System.currentTimeMillis());
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end1" + System.currentTimeMillis());
        };
        Runnable r2 = () -> {
            System.out.println("begin2" + System.currentTimeMillis());
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end2" + System.currentTimeMillis());
        };
        Runnable r3 = () -> {
            System.out.println("begin3" + System.currentTimeMillis());
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end3" + System.currentTimeMillis());
        };
        Runnable r4 = () -> {
            System.out.println("begin4" + System.currentTimeMillis());
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end4" + System.currentTimeMillis());
        };
        Runnable r5 = () -> {
            System.out.println("begin5" + System.currentTimeMillis());
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end5" + System.currentTimeMillis());
        };
        Runnable r6 = () -> {
            System.out.println("begin6" + System.currentTimeMillis());
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end6" + System.currentTimeMillis());
        };
        Runnable r7 = () -> {
            System.out.println("begin7" + System.currentTimeMillis());
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end7" + System.currentTimeMillis());
        };

        // executorService.scheduleAtFixedRate(r,1,2, TimeUnit.SECONDS);
        executorService.scheduleWithFixedDelay(r1, 1, 2, TimeUnit.SECONDS);


    }
}
