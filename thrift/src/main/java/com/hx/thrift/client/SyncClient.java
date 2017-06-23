package com.hx.thrift.client;

import com.hx.thrift.service.AdditionService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: colin
 * Date: 17-6-22
 * Time: 下午11:30
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class SyncClient {
    private static final Logger LOG = LoggerFactory.getLogger(SyncClient.class);
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 8090;
    private static final int CONNECT_TIMEOUT = 1000;
    private static final int SOCKET_TIMEOUT = 10000;

    public static void main(String[] args) {
        //设置传输通道，对于非阻塞服务，需要使用TFramedTransport，它将数据分块发送
        TTransport transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, SOCKET_TIMEOUT));
        // 协议要和服务端一致
        TProtocol protocol = new TCompactProtocol(transport);
        AdditionService.Client client = new AdditionService.Client(protocol);
        try {
            transport.open();
        } catch (TTransportException e) {
            LOG.error("the transport can not open for " + e.getMessage(), e);
            return;
        }
        int result = 0;
        try {
            result = client.add(123, 435);
        } catch (TException e) {
            LOG.error("fail to call the method \"add\" of client for " + e.getMessage(), e);
        } finally {
            transport.close();
        }
        LOG.info("get the result : " + result);
    }
}
