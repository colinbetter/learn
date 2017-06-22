package com.hx.thrift.client;

import com.hx.thrift.service.AdditionService;
import com.hx.thrift.service.AdditionService.AsyncClient.add_call;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by colin on 17-6-20.
 * 客户端可以使用AsyncMultiClient和TMultiplexedProtocol相结合，但是服务端不能是AsyncProcessor和
 * TMultiplexedProcessor的结合。只能是Processor和TMultiplexedProcessor的结合，而且定义AsyncClient
 * 时要重写TProtocol.Factory的getProtocol方法，在这个方法里面将父类返回的TProtocol转换成TMultiplexedProtocol
 *
 */
public class AsyncMultiClient {
    private static final Logger LOG = LoggerFactory.getLogger(AsyncMultiClient.class);
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 8090;
    private static final int CONNECT_TIMEOUT = 1000;
    private static final int SOCKET_TIMEOUT = 10000;
    private static final CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        TNonblockingSocket transport = null;
        AdditionService.AsyncClient client = null;
        try {
            transport = new TNonblockingSocket(SERVER_IP, SERVER_PORT, SOCKET_TIMEOUT);
            client = new AdditionService.AsyncClient(new TCompactProtocol.Factory() {
                @Override
                public TProtocol getProtocol(TTransport trans) {
                    return new TMultiplexedProtocol(super.getProtocol(trans), "additionService");
                }
            }, new TAsyncClientManager(), transport);
        } catch (IOException e) {
            LOG.error("can not connect to server:" + SERVER_IP + ":" + SERVER_PORT, e);
            return;
        }

        try {
            client.add(100, 200, new AsyncMethodCallback<add_call>() {
                @Override
                public void onComplete(AdditionService.AsyncClient.add_call response) {
                    AdditionService.AsyncClient.add_call result = (AdditionService.AsyncClient.add_call) response;
                    try {
                        LOG.info("get correct response from server:" + result.getResult());
                    } catch (TException e) {
                        LOG.error(e.getMessage(), e);
                    } finally {
                        latch.countDown();
                    }
                }

                @Override
                public void onError(Exception exception) {
                    LOG.error("get error response from server", exception);
                    latch.countDown();
                }
            });
        } catch (TException e) {
            LOG.error(e.getMessage(), e);
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
        }
        transport.close();
    }
}
