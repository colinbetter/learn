package com.hx.phaser.arrive;

import java.util.concurrent.Phaser;

/**
 * Created by testuser on 16-9-18.
 */
public class Main {
    public static void main(String args[]) {
        Phaser phaser = new Phaser(3);
        ArriveService arriveService = new ArriveService(phaser);
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                arriveService.methodA();
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                arriveService.methodB();
            }
        };
        new Thread(r1).start();
        new Thread(r1).start();
        new Thread(r2).start();
    }
}
