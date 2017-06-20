package com.hx.thrift.server;

import com.hx.thrift.service.AdditionService;
import com.hx.thrift.service.AsyncAdditionServiceImpl;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by colin on 17-6-20.
 */
public class AsyncServer {
    private static final Logger LOG= LoggerFactory.getLogger(AsyncServer.class);
    private static final int SERVER_PORT=8090;
    public static void main(String[] args){
        AdditionService.AsyncProcessor asyncProcessor =
                new AdditionService.AsyncProcessor(new AsyncAdditionServiceImpl());
        TNonblockingServerSocket serverTransport=null;
        try {
            serverTransport = new TNonblockingServerSocket(SERVER_PORT);
        } catch (TTransportException e) {
            LOG.error("can not create TNonblockingServerSocket for "+e.getMessage(),e);
            return;
        }
        TNonblockingServer.Args tArgs = new TNonblockingServer.Args(serverTransport);
        tArgs.processor(asyncProcessor);
        //使用分段传输通道
        tArgs.transportFactory(new TFramedTransport.Factory());
        //使用高密度二进制协议
        tArgs.protocolFactory(new TCompactProtocol.Factory());
        // 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
        TServer server = new TNonblockingServer(tArgs);
        LOG.info("AsyncServer start....");
        server.serve(); // 启动服务
    }
}
