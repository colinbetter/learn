package com.hx.cyclicbarrier.reset;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by testuser on 16-9-18.
 */
public class Main {
    public static void main(String args[]) {
        int parties = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(parties, new Runnable() {
            @Override
            public void run() {
                System.out.println(" all arrived!");
            }
        });
        final RestService restService = new RestService(cyclicBarrier);

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    restService.testA();
                }
            }).start();
        }
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cyclicBarrier.reset();
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    restService.testA();
                }
            }).start();
        }
    }
}
