/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by testuser on 17-3-29.
 */
public class ValueCreator {
    private static final char[] BYTES = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890".toCharArray();
    private static final int BYTES_LENGTH = BYTES.length;

    public static String getRandomString(int length) {
        if (length <= 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(BYTES[ThreadLocalRandom.current().nextInt(BYTES_LENGTH)]);
        }
        return sb.toString();
    }

    public static String getRandomIP() {
        StringBuilder sb = new StringBuilder();
        sb.append(ThreadLocalRandom.current().nextInt(256));
        for (int i = 0; i < 3; i++) {
            sb.append(".").append(ThreadLocalRandom.current().nextInt(256));
        }
        return sb.toString();
    }
}
