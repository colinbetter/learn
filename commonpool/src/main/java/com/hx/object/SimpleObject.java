/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.object;

/**
 * Created by testuser on 17-3-3.
 */
public class SimpleObject {
    public SimpleObject() {
        System.out.println("create:" + System.currentTimeMillis());
    }

    public void destroy() {
        System.out.println("destroy:" + System.currentTimeMillis());
    }

    public void validate() {
        System.out.println("validate:" + System.currentTimeMillis());
    }

    public void active() {
        System.out.println("active:" + System.currentTimeMillis());
    }

    public void passivate() {
        System.out.println("passivate:" + System.currentTimeMillis());
    }
}
