/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

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