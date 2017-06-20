package com.hx.executorserviceexception;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by testuser on 16-9-18.
 */
public class ExecutorRunnableException {
    public static void main(String args[]) {
        try {
            ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                    60L, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>(), new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                        @Override
                        public void uncaughtException(Thread t, Throwable e) {
                            System.out.println("!:UncaughtExtceptionHandler catch Exception");
                            //e.printStackTrace();

                        }
                    });
                    return t;
                }
            }) {
                @Override
                protected void beforeExecute(Thread t, Runnable r) {
                    super.beforeExecute(t, r);
                    t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                        @Override
                        public void uncaughtException(Thread t, Throwable e) {
                            System.out.println("UncaughtExceptionHandler catch Exception");
                            //重新跑出
                            t.setName("stop");
                            throw new RuntimeException("stop", e);
                        }
                    });
                }

                @Override
                protected void afterExecute(Runnable r, Throwable t) {
                    System.out.println("afterExecute got a Throwable:" + t);
                }
            };
            CountDownLatch latch = new CountDownLatch(1);
            Callable<Integer> t = new Callable<Integer>() {
                @Override
                public Integer call() {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    throw new RuntimeException("stop");

                }
            };
            List<Future<Integer>> futureList = new ArrayList<>();
            for (int i = 0; i < 1; i++) {
                //Thread t=new Thread(r);
                //t.setName(""+i);
                //executorService.execute(t);
                futureList.add(executorService.submit(t));
            }
            executorService.shutdown();

            latch.countDown();
        } catch (Exception e) {
            System.out.println("catch an exception");
            e.printStackTrace();
        }

        //while(Thread.activeCount()>100);

    }
}
