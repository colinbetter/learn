/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.service;

import com.hx.calculator.BinaryOperationReply;
import com.hx.calculator.BinaryOperationRequest;
import com.hx.calculator.SimpleCalculatorGrpc;
import io.grpc.stub.StreamObserver;

/**
 * Created by testuser on 17-4-27.
 */
public class SimpleCalculatorImpl extends SimpleCalculatorGrpc.SimpleCalculatorImplBase {
    @Override
    public void add(BinaryOperationRequest request, StreamObserver<BinaryOperationReply> responseObserver) {
        System.out.println("the request is:" + request.getNumber1() + "+" + request.getNumber2());
        responseObserver.onNext(BinaryOperationReply.newBuilder().setResult(request.getNumber1() + request.getNumber2()).build());
        responseObserver.onCompleted();
    }

    @Override
    public void minus(BinaryOperationRequest request, StreamObserver<BinaryOperationReply> responseObserver) {
        System.out.println("the request is:" + request.getNumber1() + "-" + request.getNumber2());
        responseObserver.onNext(BinaryOperationReply.newBuilder().setResult(request.getNumber1() - request.getNumber2()).build());
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<BinaryOperationRequest> multiply(final StreamObserver<BinaryOperationReply> responseObserver) {
        return new StreamObserver<BinaryOperationRequest>() {
            @Override
            public void onNext(BinaryOperationRequest binaryOperationRequest) {
                System.out.println("the request is:" + binaryOperationRequest.getNumber1() + "X" + binaryOperationRequest.getNumber2());
                responseObserver.onNext(BinaryOperationReply.newBuilder().setResult(binaryOperationRequest.getNumber1() * binaryOperationRequest.getNumber2()).build());

            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
                responseObserver.onError(throwable);
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<BinaryOperationRequest> divide(StreamObserver<BinaryOperationReply> responseObserver) {
        return new StreamObserver<BinaryOperationRequest>() {
            @Override
            public void onNext(BinaryOperationRequest binaryOperationRequest) {
                System.out.println("the request is:" + binaryOperationRequest.getNumber1() + "%" + binaryOperationRequest.getNumber2());
                responseObserver.onNext(BinaryOperationReply.newBuilder().setResult(binaryOperationRequest.getNumber1() / binaryOperationRequest.getNumber2()).build());

            }

            @Override
            public void onError(Throwable throwable) {
                responseObserver.onError(throwable);
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
