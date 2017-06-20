/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hx.helloworld.GreeterGrpc;
import com.hx.helloworld.HelloReply;
import com.hx.helloworld.HelloRequest;

import io.grpc.stub.StreamObserver;

/**
 * Created by testuser on 16-12-14.
 */
public class HelloWorldService extends GreeterGrpc.GreeterImplBase {
    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldService.class);

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        System.out.println("service:" + req.getName());
        long time = System.currentTimeMillis();
        HelloReply reply = HelloReply.newBuilder().setMessage(("Hello: " + req.getName())).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        System.out.println("alltime:" + (System.currentTimeMillis() - time));
    }
}
