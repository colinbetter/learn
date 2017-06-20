/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.reject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by testuser on 16-10-8.
 */
public class RejectedExceptionHandler {
    public static void main(String args[]) {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                1L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("在运行");
            }
        };
        Future<Integer> f = executorService.submit(r, 10);
        try {
            Thread.currentThread().sleep(2000);
            System.out.println(f.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
