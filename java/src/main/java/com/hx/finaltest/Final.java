/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.finaltest;

/**
 * Created by testuser on 17-4-27.
 */
public class Final {
    public static final Integer t = 10000;
    public static final Integer t4 = 10000;

    public static void main(String args[]) {
        System.out.println(A.t == B.t4);//false
        System.out.println(t == t4);//false
    }
}

class A {
    public static final Integer t = 10000;
}

class B {
    public static final Integer t4 = 10000;
}
