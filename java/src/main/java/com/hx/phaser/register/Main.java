package com.hx.phaser.register;

import java.util.concurrent.Phaser;

/**
 * Created by testuser on 16-9-18.
 */
public class Main {
    public static void main(String args[]) {
        Phaser phaser = new Phaser(3);
        phaser.register();
        System.out.println("all" + phaser.getRegisteredParties());
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(phaser.getArrivedParties());
                phaser.arriveAndAwaitAdvance();
                System.out.println("end");
            }
        };
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(r);
            t.start();
        }
        try {
            System.out.println(phaser.getArrivedParties());
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        phaser.arriveAndAwaitAdvance();
    }
}
