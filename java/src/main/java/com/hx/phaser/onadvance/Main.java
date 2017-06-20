package com.hx.phaser.onadvance;

import java.util.concurrent.Phaser;

/**
 * Created by testuser on 16-9-18.
 */
public class Main {
    public static void main(String args[]) {
        Phaser phaser = new Phaser(2) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("protected boolean onAdvance(int phase, int registeredParties) is invoking [" + phase + "," + registeredParties + "]");
                return super.onAdvance(phase, registeredParties);
            }
        };
        OnAdvanceService service = new OnAdvanceService(phaser);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                service.testMethod();
            }
        };
        Thread a = new Thread(r);
        a.setName("A");
        Thread b = new Thread(r);
        b.setName("B");
        a.start();
        b.start();
    }
}
