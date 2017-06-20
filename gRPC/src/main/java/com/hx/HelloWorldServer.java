/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx;

import com.hx.helloworld.GreeterGrpc;
import com.hx.helloworld.HelloReply;
import com.hx.helloworld.HelloRequest;
import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.concurrent.Executors;

/**
 * Created by testuser on 16-12-5.
 */
public class HelloWorldServer {
    private int port = 50051;
    private Server server;

    public static void main(String[] args) throws IOException, InterruptedException {
        final HelloWorldServer server = new HelloWorldServer();
        server.start();
        server.blockUntilShutdown();
    }

    private void start() throws IOException {
//        server = ServerBuilder.forPort(port).executor(Executors.newCachedThreadPool())
//                .addService(new GreeterImpl())
//                .build()
//                .start();

        server = NettyServerBuilder.forPort(port).executor(Executors.newCachedThreadPool())
                .addService(new GreeterImpl())
                .build().start();
        System.out.println("service start...");
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                HelloWorldServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    // block 一直到退出程序
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    // 实现 定义一个实现服务接口的类
    private class GreeterImpl extends GreeterGrpc.GreeterImplBase {
        public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
            System.out.println("service:" + req.getName());
            long time = System.currentTimeMillis();
            HelloReply reply = HelloReply.newBuilder().setMessage(("Hello: " + req.getName())).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
            System.out.println("alltime:" + (System.currentTimeMillis() - time));
        }
    }
}
