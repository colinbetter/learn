/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.enumtets;

/**
 * Created by testuser on 17-3-22.
 */
public class EnumTest {
    public static final Enum anEnum = new Enum();
    public static Enum.Test test1 = Enum.Test.valueOf("hello");
    public static Enum.Test test2 = Enum.Test.valueOf("hello");

    public static void main(String args[]) {
        Enum.Test1 test11 = anEnum.new Test1();
        System.out.println(test1 == test2);
    }
}

class Enum {
    private int i = 0;

    Enum() {
        i = 100;
    }

    public enum Test {
        hello,
        second
    }

    public class Test1 {
    }
}
