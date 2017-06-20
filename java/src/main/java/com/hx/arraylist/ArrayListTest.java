/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.arraylist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by testuser on 17-4-19.
 */
public class ArrayListTest {
    public static void main(String[] args) {
        allocateMemory();
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {

        }

    }

    private static void allocateMemory() {
        List<byte[]> list = new ArrayList<>();
        int size = 1024 * 1024 * 480;
        int len = size / (20 * 1024);
        for (int i = 0; i < len; i++) {
            try {
                byte[] bytes = new byte[20 * 1024];
                list.add(bytes);
            } catch (java.lang.OutOfMemoryError e) {
            }
        }
    }
}
