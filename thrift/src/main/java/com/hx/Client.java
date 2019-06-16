package com.hx;

import com.hx.thrift.service.AdditionService;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.*;

import java.io.IOException;

/**
 * Created by testuser on 16-9-3.
 */
public class Client {
    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 8090;
    public static final int TIMEOUT = 30000;

    /**
     * @param args
     */
    public static void main1(String[] args) {
        Client client = new Client();
        client.startClient("amosli");

    }

    public static void main2(String[] args) throws TException {
        //设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
        TTransport transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
        // 协议要和服务端一致
        //HelloTNonblockingServer
        ////使用高密度二进制协议
        TProtocol protocol = new TCompactProtocol(transport);
        //HelloTHsHaServer
        ////使用二进制协议
        //TProtocol protocol = new TBinaryProtocol(transport);
        AdditionService.Client client = new AdditionService.Client(protocol);
        transport.open();
        int result = client.add(123, 435);
        System.out.println("result : " + result);
        //关闭资源
        transport.close();
    }

    public static void main3(String[] args) throws TException {
        //设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
        TTransport transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
        // 协议要和服务端一致
        //HelloTNonblockingServer
        ////使用高密度二进制协议
        TProtocol protocol = new TCompactProtocol(transport);
        //HelloTHsHaServer
        ////使用二进制协议
        //TProtocol protocol = new TBinaryProtocol(transport);
        TMultiplexedProtocol mp1 = new TMultiplexedProtocol(protocol, "userService");
        AdditionService.Client client = new AdditionService.Client(mp1);
        transport.open();
        int result = client.add(123, 435);
        System.out.println("result : " + result);
        //关闭资源
        transport.close();
    }

    public static void main(String[] args) throws TException {
        //设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
        TTransport transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
        // 协议要和服务端一致
        //HelloTNonblockingServer
        ////使用高密度二进制协议
        TProtocol protocol = new TCompactProtocol(transport);
        //HelloTHsHaServer
        ////使用二进制协议
        //TProtocol protocol = new TBinaryProtocol(transport);
        TMultiplexedProtocol mp1 = new TMultiplexedProtocol(protocol, "userService");
        AdditionService.AsyncClient client = null;
        try {
            client = new AdditionService.AsyncClient(new TCompactProtocol.Factory() {
                @Override
                public TProtocol getProtocol(TTransport trans) {
                    return new TMultiplexedProtocol(super.getProtocol(trans), "userService");
                    //return super.getProtocol(trans);
                }
            },
                    new TAsyncClientManager(), new TNonblockingSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
        transport.open();
        client.add(1, 2, new AsyncMethodCallback<Integer>() {
            @Override
            public void onComplete(Integer response) {
                if (response instanceof Integer) {
                    Integer result = (Integer) response;
                    System.out.println("result : " + result);
                }
                transport.close();
            }

            @Override
            public void onError(Exception exception) {
                System.out.println("exception : " + exception);
                exception.printStackTrace();
                transport.close();
            }
        });
        try {
            Thread.currentThread().sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //关闭资源

    }

    /**
     * @param userName
     */
    public void startClient(String userName) {
        TTransport transport = null;
        try {
            transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
            // 协议要和服务端一致
//			TProtocol protocol = new TBinaryProtocol(transport);
            TProtocol protocol = new TCompactProtocol(transport);
            // TProtocol protocol = new TJSONProtocol(transport);
            AdditionService.Client client = new AdditionService.Client(
                    protocol);
            transport.open();
            long time = System.currentTimeMillis();
            int result = client.add(100, 203);
            System.out.println("time:" + (System.currentTimeMillis() - time));
            time = System.currentTimeMillis();
            result = client.add(12, 23403);
            System.out.println("time:" + (System.currentTimeMillis() - time));
            time = System.currentTimeMillis();
            result = client.add(10230, 205433);
            System.out.println("time:" + (System.currentTimeMillis() - time));
            System.out.println("Thrift client result =: " + result);
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }


}
