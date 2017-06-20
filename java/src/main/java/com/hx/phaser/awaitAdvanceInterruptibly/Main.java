package com.hx.phaser.awaitAdvanceInterruptibly;

import java.util.concurrent.Phaser;

/**
 * Created by testuser on 16-9-18.
 */
public class Main {
    public static void main(String args[]) {
        Phaser phaser = new Phaser(2);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " A1 begin=" + System.currentTimeMillis());
                try {
                    phaser.awaitAdvanceInterruptibly(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " A1 end=" + System.currentTimeMillis());
            }
        };
        Thread t = new Thread(r);
        t.start();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        t.interrupt();

    }
}
