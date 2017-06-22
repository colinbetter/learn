package com.hx.thrift.server;

import com.hx.thrift.service.AdditionService;
import com.hx.thrift.service.AsyncAdditionServiceImpl;
import java.util.concurrent.Executors;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by colin on 17-6-20.
 *
 * AsyncProcessor了继承了TBaseAsyncProcessor，TBaseAsyncProcessor实现了TProcessor接口，
 * 所以TMultiplexedProcessor在调用registerProcessor注册AsyncProcessor不会报错。
 * 由于TMultiplexedProcessor在处理请求时只调用了TProcessor的process方法，AsyncProcessor没用重写这个
 * 方法，所以调用的就是TBaseAsyncProcessor中实现，这个方法只是简单的return false，就没做任何操作。所有
 * TMultiplexedProcessor不能注册AsyncProcessor
 */
public class AsyncMultiServer {
    private static final Logger LOG= LoggerFactory.getLogger(AsyncMultiServer.class);
    private static final int SERVER_PORT=8090;
    public static void main(String[] args){
        startTThreadedSelectorServer();
    }
    public static void startTNonblockingServer(){
        TMultiplexedProcessor multiplexedProcessor=new TMultiplexedProcessor();
        AdditionService.AsyncProcessor asyncProcessor =
                new AdditionService.AsyncProcessor(new AsyncAdditionServiceImpl());
        multiplexedProcessor.registerProcessor("additionService",asyncProcessor);
        TNonblockingServerSocket serverTransport=null;
        try {
            serverTransport = new TNonblockingServerSocket(SERVER_PORT);
        } catch (TTransportException e) {
            LOG.error("can not create TNonblockingServerSocket for "+e.getMessage(),e);
            return;
        }
        TNonblockingServer.Args tArgs = new TNonblockingServer.Args(serverTransport);
        tArgs.processor(multiplexedProcessor);
        //使用分段传输通道
        tArgs.transportFactory(new TFramedTransport.Factory());
        //使用高密度二进制协议
        tArgs.protocolFactory(new TCompactProtocol.Factory());
        // 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
        TServer server = new TNonblockingServer(tArgs);
        LOG.info("AsyncServer start....");
        server.serve(); // 启动服务
    }
    public static void startTThreadedSelectorServer(){
        TMultiplexedProcessor multiplexedProcessor=new TMultiplexedProcessor();
        AdditionService.AsyncProcessor asyncProcessor =
                new AdditionService.AsyncProcessor(new AsyncAdditionServiceImpl());
        TNonblockingServerSocket serverTransport=null;
        multiplexedProcessor.registerProcessor("additionService",asyncProcessor);
        try {
            serverTransport = new TNonblockingServerSocket(SERVER_PORT);
        } catch (TTransportException e) {
            LOG.error("can not create TNonblockingServerSocket for "+e.getMessage(),e);
            return;
        }
        TThreadedSelectorServer.Args args=new TThreadedSelectorServer.Args(serverTransport);
        args.processor(multiplexedProcessor);
        args.protocolFactory(new TCompactProtocol.Factory());
        args.transportFactory(new TFramedTransport.Factory());
        args.selectorThreads(5);
        args.workerThreads(5);
        args.executorService(Executors.newCachedThreadPool());
        TThreadedSelectorServer server=new TThreadedSelectorServer(args);
        LOG.info("TThreadedSelectorServer start....");
        server.serve();
    }
}
