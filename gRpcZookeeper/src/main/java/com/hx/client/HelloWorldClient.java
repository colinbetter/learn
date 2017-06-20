/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.client;

import com.hx.helloworld.GreeterGrpc;
import com.hx.helloworld.HelloReply;
import com.hx.helloworld.HelloRequest;

import static java.lang.System.currentTimeMillis;

/**
 * Created by testuser on 16-12-16.
 */
public class HelloWorldClient extends ClientBase {
    public void greet(String name) {
        GreeterGrpc.GreeterBlockingStub futureStub = GreeterGrpc.newBlockingStub(getChannel());
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        long startTime = currentTimeMillis();
        HelloReply response = futureStub.sayHello(request);
        long endTime = currentTimeMillis();
        System.out.println(response.getMessage() + ":" + (endTime - startTime));
        startTime = currentTimeMillis();
        response = futureStub.sayHello(request);
        endTime = currentTimeMillis();
        System.out.println(response.getMessage() + ":" + (endTime - startTime));
        startTime = currentTimeMillis();
        response = futureStub.sayHello(request);
        endTime = currentTimeMillis();
        System.out.println(response.getMessage() + ":" + (endTime - startTime));

        try {
            Thread.currentThread().sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startTime = currentTimeMillis();
        response = futureStub.sayHello(request);
        endTime = currentTimeMillis();
        System.out.println(response.getMessage() + ":" + (endTime - startTime));
        startTime = currentTimeMillis();
        response = futureStub.sayHello(request);
        endTime = currentTimeMillis();
        System.out.println(response.getMessage() + ":" + (endTime - startTime));
    }

}
