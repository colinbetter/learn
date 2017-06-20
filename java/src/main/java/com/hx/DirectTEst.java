/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx;

import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by testuser on 16-12-12.
 */
public class DirectTEst {
    public static void clean(final ByteBuffer byteBuffer) {
        if (byteBuffer.isDirect()) {
            ((DirectBuffer) byteBuffer).cleaner().clean();
        }
    }

    public static void sleep(long i) {
        try {
            Thread.sleep(i);
        } catch (Exception e) {
          /*skip*/
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t = new Thread();
        System.out.println(System.getProperties());
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024 * 100);
        System.out.println("start");
        sleep(10000);
        clean(buffer);
        System.out.println("end");
        sleep(10000);
        SocketChannel sc = null;
        sc.socket().set
    }
}
