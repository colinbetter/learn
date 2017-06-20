package com.hx.cyclicbarrier.timeout;

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
        final TimeOutService timeOutService = new TimeOutService(cyclicBarrier);

        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    timeOutService.testA();
                }
            }).start();
        }
    }
}
