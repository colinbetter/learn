package com.hx.cyclicbarrier.broken;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by testuser on 16-9-18.
 */
public class MyService {
    private CyclicBarrier cyclicBarrier;

    public MyService(CyclicBarrier cyclicBarrier) {
        super();
        this.cyclicBarrier = cyclicBarrier;
    }

    private void beginRun(int count) {
        try {
            System.out.println(Thread.currentThread().getName() + " arrive,waiting for others.");
            if (Thread.currentThread().getName().equals("Thread-2")) {
                System.out.println("Thread-2 arrive");
                Thread.sleep(5000);
                Thread.currentThread().interrupt();
            }
            cyclicBarrier.await();//在这出异常才会产生 BrokenBarrierException
            System.out.println("ALL arrived");
            System.out.println(Thread.currentThread().getName() + " arrive and end " + count + " leg");
        } catch (InterruptedException e) {
            System.out.println(" in InterruptedException " + cyclicBarrier.isBroken());
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            System.out.println(" in BrokenBarrierException " + cyclicBarrier.isBroken());
            e.printStackTrace();
        }
    }

    public void testA() {
        for (int i = 0; i < 2; i++) {
            beginRun(i + 1);
        }
    }
}
