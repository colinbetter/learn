/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.server;

import com.hx.service.SimpleCalculatorImpl;
import io.grpc.Server;
import io.grpc.inprocess.InProcessServerBuilder;

import java.io.IOException;
import java.util.concurrent.Executors;

/**
 * Created by testuser on 17-4-28.
 */
public class InProcessServer {
    private int port = 50051;
    private Server server;

    public static void main(String args[]) {
        InProcessServer server = new InProcessServer();
        try {
            server.start();
            server.blockUntilShutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() throws IOException {
        server = InProcessServerBuilder.forPort(port).executor(Executors.newCachedThreadPool())
                .addService(new SimpleCalculatorImpl())
                .build()
                .start();
        System.out.println("service start...");
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                InProcessServer.this.stop();
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
}
