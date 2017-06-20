/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.questions;

/**
 * Created by testuser on 17-4-11.
 */
public class Task {
    volatile int count = 0;
    private Object lock = new Object();

    public Task() {

    }

    public static void main(String args[]) {
        Task task = new Task();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                task.doSafely();
            }).start();
        }
    }

    public void doSafely() {
        synchronized (lock) {
            while (count >= 2) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                }
            }
            count++;
        }
        doSensitivePart();
        synchronized (lock) {
            count--;
            lock.notifyAll();
        }
    }

    private void doSensitivePart() {
        try {
            //代替复杂耗时逻辑
            System.out.println("开始执行复杂耗时逻辑 count=" + count);
            Thread.currentThread().sleep(2000);
            //System.out.println("结束执行复杂耗时逻辑 count="+count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
