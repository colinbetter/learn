/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.client;

import com.google.common.util.concurrent.ListenableFuture;
import com.hx.calculator.BinaryOperationReply;
import com.hx.calculator.BinaryOperationRequest;
import com.hx.calculator.SimpleCalculatorGrpc;
import io.grpc.ManagedChannel;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import static java.lang.System.currentTimeMillis;

/**
 * Created by testuser on 17-4-28.
 */
public class InProcessClient {
    private final ManagedChannel futureStubChannel;
    private final ManagedChannel blockingStubChannel;
    private final ManagedChannel stubChannel;
    private SimpleCalculatorGrpc.SimpleCalculatorFutureStub futureStub;
    private SimpleCalculatorGrpc.SimpleCalculatorBlockingStub blockingStub;
    private SimpleCalculatorGrpc.SimpleCalculatorStub stub;

    public InProcessClient(String host, int port) {
        futureStubChannel = InProcessChannelBuilder.forAddress(host, port).directExecutor()
                .usePlaintext(true)
                .build();
        futureStub = SimpleCalculatorGrpc.newFutureStub(futureStubChannel);//newBlockingStub();
        blockingStubChannel = InProcessChannelBuilder.forAddress(host, port).directExecutor()
                .usePlaintext(true)
                .build();
        blockingStub = SimpleCalculatorGrpc.newBlockingStub(blockingStubChannel);
        stubChannel = InProcessChannelBuilder.forAddress(host, port).directExecutor()
                .usePlaintext(true)
                .build();
        stub = SimpleCalculatorGrpc.newStub(stubChannel);
        //GreeterGrpc.newStub(channel).sayHello();
    }

    public static void main(String[] args) throws InterruptedException {
        InProcessClient client = new InProcessClient("127.0.0.1", 50051);
        for (int i = 0; i < 5; i++) {
            client.blockingGreet("world:" + i);
        }
        for (int i = 0; i < 5; i++) {
            client.greet("world:" + i);
        }
        Thread.currentThread().sleep(1000);
        for (int i = 0; i < 5; i++) {
            client.futureGreet("world:" + i);
        }
        Thread.currentThread().sleep(1000);


        //client.shutdown();
    }

    public void shutdown() throws InterruptedException {
        futureStubChannel.shutdown();
        blockingStubChannel.shutdown();
        stubChannel.shutdown();
    }

    public void futureGreet(String name) {
        BinaryOperationRequest request = BinaryOperationRequest.newBuilder().setNumber1(100).setNumber2(203).build();
        ListenableFuture<BinaryOperationReply> responseFuture = futureStub.add(request);
        long startTime = currentTimeMillis();
        responseFuture.addListener(() -> {
            try {
                BinaryOperationReply reply = responseFuture.get();
                long endTime = System.currentTimeMillis();
                System.out.println("future get add message:" + reply.getResult() + ":" + (endTime - startTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }, Executors.newSingleThreadExecutor());
    }

    public void blockingGreet(String name) {
        BinaryOperationRequest request = BinaryOperationRequest.newBuilder().setNumber1(100).setNumber2(203).build();
        long startTime = System.currentTimeMillis();
        BinaryOperationReply addResponse = blockingStub.add(request);
        long endTime = System.currentTimeMillis();
        System.out.println("blocking get add message:" + addResponse.getResult() + ":" + (endTime - startTime));

        long minusStartTime = System.currentTimeMillis();
        Iterator<BinaryOperationReply> responses = blockingStub.minus(request);
        long minusEndTime = System.currentTimeMillis();
        responses.forEachRemaining(response -> System.out.println("blocking get minus message:" + response.getResult() + ":" + (minusEndTime - minusStartTime)));
    }

    public void greet(String name) {
        BinaryOperationRequest request = BinaryOperationRequest.newBuilder().setNumber1(100).setNumber2(203).build();
        long addStartTime = currentTimeMillis();
        stub.add(request, new StreamObserver<BinaryOperationReply>() {
            @Override
            public void onNext(BinaryOperationReply reply) {
                long endTime = System.currentTimeMillis();
                System.out.println("stub get add message:" + reply.getResult() + ":" + (endTime - addStartTime));
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        });
        long startTime = currentTimeMillis();
        stub.minus(request, new StreamObserver<BinaryOperationReply>() {
            @Override
            public void onNext(BinaryOperationReply reply) {
                long endTime = System.currentTimeMillis();
                System.out.println("stub get minus message:" + reply.getResult() + ":" + (endTime - startTime));
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {

            }
        });
        long divideStartTime = currentTimeMillis();
        StreamObserver<BinaryOperationRequest> requestStreamObserver = stub.divide(new StreamObserver<BinaryOperationReply>() {
            @Override
            public void onNext(BinaryOperationReply reply) {
                long endTime = System.currentTimeMillis();
                System.out.println("stub get divide message:" + reply.getResult() + ":" + (endTime - divideStartTime));
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        });
        requestStreamObserver.onNext(request);
        requestStreamObserver.onCompleted();

    }
}
