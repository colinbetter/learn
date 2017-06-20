package com.hx.cyclicbarrier.reset;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by testuser on 16-9-18.
 */
public class RestService {
    private CyclicBarrier cyclicBarrier;

    public RestService(CyclicBarrier cyclicBarrier) {
        super();
        this.cyclicBarrier = cyclicBarrier;
    }

    private void beginRun(int count) {
        try {
            System.out.println(Thread.currentThread().getName() + " arrive,waiting for others.");
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
