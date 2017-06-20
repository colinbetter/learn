package com.hx.cglib;

/**
 * Created by testuser on 16-9-7.
 */
public class Service {
    public void doSomething() {
        try {
            Thread.currentThread().sleep(5000L);
            System.out.print("do something");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
