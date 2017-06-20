package com.hx.threadexception;

/**
 * Created by testuser on 16-9-18.
 */
public class Main {
    public static void main(String args[]) {
        Main m = new Main();
        ThreadGroup tg = m.new MyThreadGroup("group");

        Thread t = new Thread(tg, new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException();
            }
        }, "t");
        Thread t2 = new Thread(tg, new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException();
            }
        }, "t2");
        try {

            t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    System.out.println("UncaughtExceptionHandler catch Exception");
                    //e.printStackTrace();
                }
            });
            t.start();
            t2.start();
        } catch (Exception e) {
            System.out.println("catch Exception");
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end");
    }

    private class MyThreadGroup extends ThreadGroup {

        public MyThreadGroup(String name) {
            super(name);
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("uncaughtException method catch Exception from thread:" + t.getName());
        }
    }
}
