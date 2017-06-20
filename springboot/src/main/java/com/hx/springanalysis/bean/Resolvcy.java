package com.hx.springanalysis.bean;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by testuser on 17-3-6.
 */
@MyAnnotation
public class Resolvcy {
    @Autowired
    Resolv resolv;

    public void doSomething() {
        System.out.println("Resolvcy");
    }
}