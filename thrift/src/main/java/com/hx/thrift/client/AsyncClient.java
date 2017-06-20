package com.hx.thrift.client;

import com.hx.thrift.service.AdditionService;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TNonblockingSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by colin on 17-6-20.
 */
public class AsyncClient {
    private static final Logger LOG= LoggerFactory.getLogger(AsyncClient.class);
    private static final String SERVER_IP="127.0.0.1";
    private static final int SERVER_PORT=8090;
    private static final int CONNECT_TIMEOUT=1000;
    private static final int SOCKET_TIMEOUT=10000;
    private static final CountDownLatch latch=new CountDownLatch(1);
    public static void main(String[] args){
        TNonblockingSocket transport = null;
        AdditionService.AsyncClient client = null;
        try {
            transport = new TNonblockingSocket(SERVER_IP, SERVER_PORT, SOCKET_TIMEOUT);
            client = new AdditionService.AsyncClient(new TCompactProtocol.Factory(),
                    new TAsyncClientManager(), transport);
        } catch (IOException e) {
            LOG.error("can not connect to server:"+SERVER_IP+":"+SERVER_PORT,e);
            return ;
        }

        try {
            client.add(100, 200, new AsyncMethodCallback<AdditionService.AsyncClient.add_call>() {
                @Override
                public void onComplete(AdditionService.AsyncClient.add_call response) {
                    AdditionService.AsyncClient.add_call result = (AdditionService.AsyncClient.add_call) response;
                    try {
                        LOG.info("get correct response from server:"+ result.getResult());
                    } catch (TException e) {
                        LOG.error(e.getMessage(),e);
                    }finally {
                        latch.countDown();
                    }
                }
                @Override
                public void onError(Exception exception) {
                    LOG.error("get error response from server",exception);
                    latch.countDown();
                }
            });
        } catch (TException e) {
            LOG.error(e.getMessage(),e);
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
        }
    }
}
