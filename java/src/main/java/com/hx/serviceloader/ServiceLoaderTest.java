/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.serviceloader;

import java.util.ServiceLoader;

/**
 * Created by testuser on 16-12-1.
 */
public class ServiceLoaderTest {
    public static void main(String args[]) {
        ServiceLoader<A> serviceLoader = ServiceLoader.load(A.class, ServiceLoaderTest.class.getClassLoader());
        for (A a : serviceLoader) {
            System.out.println(a.get());
        }
    }

}

