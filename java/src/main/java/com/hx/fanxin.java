/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx;

import java.util.List;

/**
 * Created by testuser on 17-5-3.
 */
public class fanxin {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        Long g1 = 1L;
        Long g2 = 2L;
        Long g3 = 3L;
        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));
        System.out.println(g == g3);
        System.out.println(g == (g1 + g2));
        System.out.println(g.equals(g1 + g2));

    }

}

class A {
    public void say(List<String> list) {
        return;
    }
}

class BBB extends A {
//    @Override
//    public void say(List<Integer> list){
//        return 1;
//    }
}