package com.hx.phaser.onadvance;

import java.util.concurrent.Phaser;

/**
 * Created by testuser on 16-9-18.
 */
public class OnAdvanceService {
    private Phaser phaser;

    public OnAdvanceService(Phaser phaser) {
        super();
        this.phaser = phaser;
    }

    public void testMethod() {
        try {
            System.out.println("A begin ThreadName =" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            if (Thread.currentThread().getName().equals("B")) {
                Thread.currentThread().sleep(5000);
            }
            phaser.arriveAndAwaitAdvance();
            System.out.println("A end ThreadName =" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            System.out.println("B begin ThreadName =" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            if (Thread.currentThread().getName().equals("B")) {
                Thread.currentThread().sleep(5000);
            }
            phaser.arriveAndAwaitAdvance();
            System.out.println("B end ThreadName =" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            System.out.println("C begin ThreadName =" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            if (Thread.currentThread().getName().equals("B")) {
                Thread.currentThread().sleep(5000);
            }
            phaser.arriveAndAwaitAdvance();
            System.out.println("C end ThreadName =" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
