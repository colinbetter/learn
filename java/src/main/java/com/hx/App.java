package com.hx;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    //public static final String str="Hello World";
    public static void main(String[] args) throws IOException {
        String name = new String("Hello " + "World");
        String str = "Hello World";
        System.out.println(name.intern() == str);
        ServerSocket serverSocket = new ServerSocket(0); //读取空闲的可用端口
        int port = serverSocket.getLocalPort();
        System.out.println("系统分配的端口号 port=" + port);
        System.out.println("系统IP=" + InetAddress.getLocalHost().getHostAddress());

        List<? extends AA> as = new ArrayList<B>();
        Thread t = new Thread();
        AA[] t3 = new B[100];

    }

}

class AA {
    public static void a() {
    }
}

class B extends AA {
    public static void a() {
    }
}
