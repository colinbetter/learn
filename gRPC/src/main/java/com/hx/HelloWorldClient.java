/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx;

import com.google.common.util.concurrent.ListenableFuture;
import com.hx.helloworld.GreeterGrpc;
import com.hx.helloworld.HelloReply;
import com.hx.helloworld.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.lang.System.currentTimeMillis;

/**
 * Created by testuser on 16-12-5.
 */
public class HelloWorldClient {
    private final ManagedChannel channel;
    //private final GreeterGrpc.GreeterBlockingStub blockingStub;
    private final GreeterGrpc.GreeterFutureStub futureStub;

    public HelloWorldClient(String host, int port) {
        channel = NettyChannelBuilder.forAddress(host, port).directExecutor()
                .usePlaintext(true)
                .build();
//        channel = ManagedChannelBuilder.forAddress(host,port).directExecutor()
//                .usePlaintext(true)
//                .build();
        futureStub = GreeterGrpc.newFutureStub(channel);//newBlockingStub();
        //GreeterGrpc.newStub(channel).sayHello();
    }

    public static void main(String[] args) throws InterruptedException {
        HelloWorldClient client = new HelloWorldClient("127.0.0.1", 50051);

        for (int i = 0; i < 5; i++) {
            client.greet("world:" + i);
        }
        Thread.currentThread().sleep(1000);


    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void greet(String name) {

        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        //HelloReply response = futureStub.sayHello(request);
        ListenableFuture<HelloReply> responseFuture = futureStub.sayHello(request);
        long startTime = currentTimeMillis();
        responseFuture.addListener(() -> {
            try {
                HelloReply reply = responseFuture.get();
                long endTime = System.currentTimeMillis();
                System.out.println(reply.getMessage() + ":" + (endTime - startTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }, Executors.newCachedThreadPool());

    }
}
