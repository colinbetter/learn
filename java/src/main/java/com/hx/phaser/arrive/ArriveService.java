package com.hx.phaser.arrive;

import java.util.concurrent.Phaser;

/**
 * Created by testuser on 16-9-18.
 */
public class ArriveService {
    private Phaser phaser;

    public ArriveService(Phaser phaser) {
        this.phaser = phaser;
    }

    public void methodA() {
        try {
            System.out.println(Thread.currentThread().getName() + " begin A1 " + System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " methodA() " + phaser.getArrivedParties());
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + " end A1 " + System.currentTimeMillis());

            System.out.println(Thread.currentThread().getName() + " begin A2 " + System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " methodA() " + phaser.getArrivedParties());
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + " end A2 " + System.currentTimeMillis());

            System.out.println(Thread.currentThread().getName() + " begin A3 " + System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " methodA() " + phaser.getArrivedParties());
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + " end A3 " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void methodB() {
        try {
            System.out.println(Thread.currentThread().getName() + " begin A1 " + System.currentTimeMillis());
            //Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " methodB() " + phaser.getArrivedParties());
            phaser.arrive();
            System.out.println(Thread.currentThread().getName() + " end A1 " + System.currentTimeMillis());

            System.out.println(Thread.currentThread().getName() + " begin A2 " + System.currentTimeMillis());
            //Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " methodB() " + phaser.getArrivedParties());
            phaser.arrive();
            System.out.println(Thread.currentThread().getName() + " end A2 " + System.currentTimeMillis());

            System.out.println(Thread.currentThread().getName() + " begin A3 " + System.currentTimeMillis());
            //Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " methodB() " + phaser.getArrivedParties());
            phaser.arrive();
            System.out.println(Thread.currentThread().getName() + " end A3 " + System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
