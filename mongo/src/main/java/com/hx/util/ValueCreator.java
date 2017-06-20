

package com.hx.util;

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
