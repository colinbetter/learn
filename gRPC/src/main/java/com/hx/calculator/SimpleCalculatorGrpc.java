package com.hx.calculator;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * The greeting service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.21.0)",
    comments = "Source: calculator.proto")
public final class SimpleCalculatorGrpc {

  private SimpleCalculatorGrpc() {}

  public static final String SERVICE_NAME = "com.hx.calculator.SimpleCalculator";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.hx.calculator.BinaryOperationRequest,
      com.hx.calculator.BinaryOperationReply> getAddMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "add",
      requestType = com.hx.calculator.BinaryOperationRequest.class,
      responseType = com.hx.calculator.BinaryOperationReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.hx.calculator.BinaryOperationRequest,
      com.hx.calculator.BinaryOperationReply> getAddMethod() {
    io.grpc.MethodDescriptor<com.hx.calculator.BinaryOperationRequest, com.hx.calculator.BinaryOperationReply> getAddMethod;
    if ((getAddMethod = SimpleCalculatorGrpc.getAddMethod) == null) {
      synchronized (SimpleCalculatorGrpc.class) {
        if ((getAddMethod = SimpleCalculatorGrpc.getAddMethod) == null) {
          SimpleCalculatorGrpc.getAddMethod = getAddMethod = 
              io.grpc.MethodDescriptor.<com.hx.calculator.BinaryOperationRequest, com.hx.calculator.BinaryOperationReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.hx.calculator.SimpleCalculator", "add"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.hx.calculator.BinaryOperationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.hx.calculator.BinaryOperationReply.getDefaultInstance()))
                  .setSchemaDescriptor(new SimpleCalculatorMethodDescriptorSupplier("add"))
                  .build();
          }
        }
     }
     return getAddMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.hx.calculator.BinaryOperationRequest,
      com.hx.calculator.BinaryOperationReply> getMinusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "minus",
      requestType = com.hx.calculator.BinaryOperationRequest.class,
      responseType = com.hx.calculator.BinaryOperationReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.hx.calculator.BinaryOperationRequest,
      com.hx.calculator.BinaryOperationReply> getMinusMethod() {
    io.grpc.MethodDescriptor<com.hx.calculator.BinaryOperationRequest, com.hx.calculator.BinaryOperationReply> getMinusMethod;
    if ((getMinusMethod = SimpleCalculatorGrpc.getMinusMethod) == null) {
      synchronized (SimpleCalculatorGrpc.class) {
        if ((getMinusMethod = SimpleCalculatorGrpc.getMinusMethod) == null) {
          SimpleCalculatorGrpc.getMinusMethod = getMinusMethod = 
              io.grpc.MethodDescriptor.<com.hx.calculator.BinaryOperationRequest, com.hx.calculator.BinaryOperationReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.hx.calculator.SimpleCalculator", "minus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.hx.calculator.BinaryOperationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.hx.calculator.BinaryOperationReply.getDefaultInstance()))
                  .setSchemaDescriptor(new SimpleCalculatorMethodDescriptorSupplier("minus"))
                  .build();
          }
        }
     }
     return getMinusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.hx.calculator.BinaryOperationRequest,
      com.hx.calculator.BinaryOperationReply> getMultiplyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "multiply",
      requestType = com.hx.calculator.BinaryOperationRequest.class,
      responseType = com.hx.calculator.BinaryOperationReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.hx.calculator.BinaryOperationRequest,
      com.hx.calculator.BinaryOperationReply> getMultiplyMethod() {
    io.grpc.MethodDescriptor<com.hx.calculator.BinaryOperationRequest, com.hx.calculator.BinaryOperationReply> getMultiplyMethod;
    if ((getMultiplyMethod = SimpleCalculatorGrpc.getMultiplyMethod) == null) {
      synchronized (SimpleCalculatorGrpc.class) {
        if ((getMultiplyMethod = SimpleCalculatorGrpc.getMultiplyMethod) == null) {
          SimpleCalculatorGrpc.getMultiplyMethod = getMultiplyMethod = 
              io.grpc.MethodDescriptor.<com.hx.calculator.BinaryOperationRequest, com.hx.calculator.BinaryOperationReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.hx.calculator.SimpleCalculator", "multiply"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.hx.calculator.BinaryOperationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.hx.calculator.BinaryOperationReply.getDefaultInstance()))
                  .setSchemaDescriptor(new SimpleCalculatorMethodDescriptorSupplier("multiply"))
                  .build();
          }
        }
     }
     return getMultiplyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.hx.calculator.BinaryOperationRequest,
      com.hx.calculator.BinaryOperationReply> getDivideMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "divide",
      requestType = com.hx.calculator.BinaryOperationRequest.class,
      responseType = com.hx.calculator.BinaryOperationReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.hx.calculator.BinaryOperationRequest,
      com.hx.calculator.BinaryOperationReply> getDivideMethod() {
    io.grpc.MethodDescriptor<com.hx.calculator.BinaryOperationRequest, com.hx.calculator.BinaryOperationReply> getDivideMethod;
    if ((getDivideMethod = SimpleCalculatorGrpc.getDivideMethod) == null) {
      synchronized (SimpleCalculatorGrpc.class) {
        if ((getDivideMethod = SimpleCalculatorGrpc.getDivideMethod) == null) {
          SimpleCalculatorGrpc.getDivideMethod = getDivideMethod = 
              io.grpc.MethodDescriptor.<com.hx.calculator.BinaryOperationRequest, com.hx.calculator.BinaryOperationReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.hx.calculator.SimpleCalculator", "divide"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.hx.calculator.BinaryOperationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.hx.calculator.BinaryOperationReply.getDefaultInstance()))
                  .setSchemaDescriptor(new SimpleCalculatorMethodDescriptorSupplier("divide"))
                  .build();
          }
        }
     }
     return getDivideMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SimpleCalculatorStub newStub(io.grpc.Channel channel) {
    return new SimpleCalculatorStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SimpleCalculatorBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SimpleCalculatorBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SimpleCalculatorFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SimpleCalculatorFutureStub(channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class SimpleCalculatorImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void add(com.hx.calculator.BinaryOperationRequest request,
        io.grpc.stub.StreamObserver<com.hx.calculator.BinaryOperationReply> responseObserver) {
      asyncUnimplementedUnaryCall(getAddMethod(), responseObserver);
    }

    /**
     */
    public void minus(com.hx.calculator.BinaryOperationRequest request,
        io.grpc.stub.StreamObserver<com.hx.calculator.BinaryOperationReply> responseObserver) {
      asyncUnimplementedUnaryCall(getMinusMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.hx.calculator.BinaryOperationRequest> multiply(
        io.grpc.stub.StreamObserver<com.hx.calculator.BinaryOperationReply> responseObserver) {
      return asyncUnimplementedStreamingCall(getMultiplyMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.hx.calculator.BinaryOperationRequest> divide(
        io.grpc.stub.StreamObserver<com.hx.calculator.BinaryOperationReply> responseObserver) {
      return asyncUnimplementedStreamingCall(getDivideMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.hx.calculator.BinaryOperationRequest,
                com.hx.calculator.BinaryOperationReply>(
                  this, METHODID_ADD)))
          .addMethod(
            getMinusMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.hx.calculator.BinaryOperationRequest,
                com.hx.calculator.BinaryOperationReply>(
                  this, METHODID_MINUS)))
          .addMethod(
            getMultiplyMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.hx.calculator.BinaryOperationRequest,
                com.hx.calculator.BinaryOperationReply>(
                  this, METHODID_MULTIPLY)))
          .addMethod(
            getDivideMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.hx.calculator.BinaryOperationRequest,
                com.hx.calculator.BinaryOperationReply>(
                  this, METHODID_DIVIDE)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class SimpleCalculatorStub extends io.grpc.stub.AbstractStub<SimpleCalculatorStub> {
    private SimpleCalculatorStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SimpleCalculatorStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SimpleCalculatorStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SimpleCalculatorStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void add(com.hx.calculator.BinaryOperationRequest request,
        io.grpc.stub.StreamObserver<com.hx.calculator.BinaryOperationReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void minus(com.hx.calculator.BinaryOperationRequest request,
        io.grpc.stub.StreamObserver<com.hx.calculator.BinaryOperationReply> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getMinusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.hx.calculator.BinaryOperationRequest> multiply(
        io.grpc.stub.StreamObserver<com.hx.calculator.BinaryOperationReply> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getMultiplyMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.hx.calculator.BinaryOperationRequest> divide(
        io.grpc.stub.StreamObserver<com.hx.calculator.BinaryOperationReply> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getDivideMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class SimpleCalculatorBlockingStub extends io.grpc.stub.AbstractStub<SimpleCalculatorBlockingStub> {
    private SimpleCalculatorBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SimpleCalculatorBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SimpleCalculatorBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SimpleCalculatorBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public com.hx.calculator.BinaryOperationReply add(com.hx.calculator.BinaryOperationRequest request) {
      return blockingUnaryCall(
          getChannel(), getAddMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.hx.calculator.BinaryOperationReply> minus(
        com.hx.calculator.BinaryOperationRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getMinusMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class SimpleCalculatorFutureStub extends io.grpc.stub.AbstractStub<SimpleCalculatorFutureStub> {
    private SimpleCalculatorFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SimpleCalculatorFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SimpleCalculatorFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SimpleCalculatorFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.hx.calculator.BinaryOperationReply> add(
        com.hx.calculator.BinaryOperationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAddMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD = 0;
  private static final int METHODID_MINUS = 1;
  private static final int METHODID_MULTIPLY = 2;
  private static final int METHODID_DIVIDE = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SimpleCalculatorImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SimpleCalculatorImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD:
          serviceImpl.add((com.hx.calculator.BinaryOperationRequest) request,
              (io.grpc.stub.StreamObserver<com.hx.calculator.BinaryOperationReply>) responseObserver);
          break;
        case METHODID_MINUS:
          serviceImpl.minus((com.hx.calculator.BinaryOperationRequest) request,
              (io.grpc.stub.StreamObserver<com.hx.calculator.BinaryOperationReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_MULTIPLY:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.multiply(
              (io.grpc.stub.StreamObserver<com.hx.calculator.BinaryOperationReply>) responseObserver);
        case METHODID_DIVIDE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.divide(
              (io.grpc.stub.StreamObserver<com.hx.calculator.BinaryOperationReply>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SimpleCalculatorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SimpleCalculatorBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.hx.calculator.CalculatorProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SimpleCalculator");
    }
  }

  private static final class SimpleCalculatorFileDescriptorSupplier
      extends SimpleCalculatorBaseDescriptorSupplier {
    SimpleCalculatorFileDescriptorSupplier() {}
  }

  private static final class SimpleCalculatorMethodDescriptorSupplier
      extends SimpleCalculatorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SimpleCalculatorMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SimpleCalculatorGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SimpleCalculatorFileDescriptorSupplier())
              .addMethod(getAddMethod())
              .addMethod(getMinusMethod())
              .addMethod(getMultiplyMethod())
              .addMethod(getDivideMethod())
              .build();
        }
      }
    }
    return result;
  }
}
