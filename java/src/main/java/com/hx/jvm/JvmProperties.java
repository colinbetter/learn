/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.jvm;

import sun.net.www.content.text.PlainTextInputStream;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by testuser on 16-12-1.
 */
public class JvmProperties {
    public static void main(String args[]) throws IOException {
        System.out.println(System.getProperty("xing.huang"));
        System.out.println(System.getenv("PATH"));
        System.out.println(System.getenv());
        PlainTextInputStream object = (PlainTextInputStream) JvmProperties.class.getClassLoader().getResource("com").getContent();
        System.out.println(JvmProperties.class.getClassLoader().getResource("com"));
        System.out.println(JvmProperties.class.getResource("").toString());
        System.out.println(JvmProperties.class.getClassLoader().getResource("/"));//null
        System.out.println(JvmProperties.class.getResource("/com").toString());
        System.out.println(ClassLoader.getSystemClassLoader());
        Integer i = new Integer(19);
        Integer j = 19;
        System.out.println(i == j);
        HashSet<String> set1 = new HashSet<>();
        set1.addAll(Arrays.asList("sdf", "sdf1", "sdf2", "s", "sdf5"));
        HashSet<String> set2 = new HashSet<String>();
        set2.addAll(Arrays.asList("sdf", "sdf1", "sdf2", "sdf3", "sdf4"));
        set1.retainAll(set2);
        System.out.println(set1);
        System.out.println(set2);
    }

}
