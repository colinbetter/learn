/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.invokeall;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by testuser on 16-10-8.
 */
public class InvokeAll {
    public static void main(String args[]) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor;
        threadPoolExecutor = new ThreadPoolExecutor(5, 5, 60l, TimeUnit.SECONDS, new ArrayBlockingQueue(100));
        Callable<String> a = () -> {
            try {
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException e) {

                System.out.print("AAAAAAAe.printStackTrace();");
            }
            System.out.println("AAAAAAA");
            if (1 == 1)
                throw new RuntimeException("AAA");
            return "AAAAA";
        };
        Callable<String> b = () -> {
            try {
                Thread.currentThread().sleep(3000);
            } catch (InterruptedException e) {

                System.out.print("BBBBBBB1e.printStackTrace();");
            }
            System.out.println("BBBBBBB1");
            return "BBBBB1";
        };
        Callable<String> bb = () -> {
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {

                System.out.print("BBBBBBB2e.printStackTrace();");
            }
            System.out.println("BBBBBBB2");
            return "BBBB2B";
        };
        List<Callable<String>> listR = new ArrayList<>();
        listR.add(a);
        listR.add(b);
        listR.add(bb);

        List<Future<String>> futureList = threadPoolExecutor.invokeAll(listR, 10, TimeUnit.SECONDS);
        try {
            for (Future<String> future : futureList) {
                System.out.println(future.get());
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        threadPoolExecutor.shutdown();
    }
}
