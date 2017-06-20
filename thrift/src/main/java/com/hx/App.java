package com.hx;

import com.hx.thrift.service.AdditionService;
import com.hx.thrift.service.AdditionServiceImpl;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * Hello world!
 */
public class App {
    public static final int SERVER_PORT = 8090;

    /**
     * @param args
     */
    public static void main(String[] args) throws TTransportException {
        App server = new App();
        server.startMultiNioServer();
    }

    public void startServer() {
        try {
            System.out.println("HelloWorld TSimpleServer start ....");

//		    TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new  HelloWorldImpl());
            AdditionService.Processor<AdditionService.Iface> tprocessor =
                    new AdditionService.Processor<AdditionService.Iface>(new AdditionServiceImpl());
            // 简单的单线程服务模型，一般用于测试
            TServerSocket serverTransport = new TServerSocket(SERVER_PORT);

            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(tprocessor);
            //tArgs.transportFactory(trans)
//			tArgs.protocolFactory(new TBinaryProtocol.Factory());
            tArgs.protocolFactory(new TCompactProtocol.Factory());
            // tArgs.protocolFactory(new TJSONProtocol.Factory());
            TServer server = new TSimpleServer(tArgs);
            server.serve();

        } catch (Exception e) {
            System.out.println("Server start error!!!");
            e.printStackTrace();
        }
    }

    public void startNioServer() throws TTransportException {
        //处理器
        AdditionService.Processor<AdditionService.Iface> tprocessor =
                new AdditionService.Processor<AdditionService.Iface>(new AdditionServiceImpl());
        //TProcessor tprocessor = new HelloWorld.Processor<HelloWorld.Iface>(new HelloWorldImpl());
        // 传输通道 - 非阻塞方式
        TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(SERVER_PORT);
        //异步IO，需要使用TFramedTransport，它将分块缓存读取。
        TNonblockingServer.Args tArgs = new TNonblockingServer.Args(serverTransport);
        tArgs.processor(tprocessor);
        tArgs.transportFactory(new TFramedTransport.Factory());
        //使用高密度二进制协议
        tArgs.protocolFactory(new TCompactProtocol.Factory());
        // 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
        TServer server = new TNonblockingServer(tArgs);
        System.out.println("HelloTNonblockingServer start....");
        server.serve(); // 启动服务
    }

    public void startMultiNioServer() throws TTransportException {
        //处理器
        TMultiplexedProcessor processor = new TMultiplexedProcessor();
        AdditionService.Processor tprocessor =
                new AdditionService.Processor(new AdditionServiceImpl());
        processor.registerProcessor("userService", tprocessor);
        //TProcessor tprocessor = new HelloWorld.Processor<HelloWorld.Iface>(new HelloWorldImpl());
        // 传输通道 - 非阻塞方式
        TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(SERVER_PORT);
        //异步IO，需要使用TFramedTransport，它将分块缓存读取。
        TNonblockingServer.Args tArgs = new TNonblockingServer.Args(serverTransport);
        tArgs.processor(processor);
        tArgs.transportFactory(new TFramedTransport.Factory());
        //使用高密度二进制协议
        tArgs.protocolFactory(new TCompactProtocol.Factory());
        // 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
        TServer server = new TNonblockingServer(tArgs);
        System.out.println("HelloTNonblockingServer start....");
        server.serve(); // 启动服务
    }


}
