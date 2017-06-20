/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.forkjoin;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by testuser on 16-10-9.
 */
public class ForkjoinShutdown {
    public static void main(String args[]) throws InterruptedException {
        ThreadPoolExecutor f = new ThreadPoolExecutor(10, 10, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
        RecursiveTask<Integer> r = new RecursiveTask<Integer>() {
            @Override
            protected Integer compute() {
                this.fork();
                return 0;
            }
        };
        f.shutdown();
        Thread.currentThread().sleep(1000);
        System.out.println(f.isTerminating());
    }
}
