package com.hx.thrift.service;

import com.hx.springthrift.annotation.ThriftService;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

/**
 * Created by testuser on 16-9-3.
 */
@ThriftService(processorClass = AdditionService.AsyncProcessor.class)
public class AdditionServiceImpl implements AdditionService.AsyncIface, AdditionService.Iface {
    @Override
    public int add(int n1, int n2) throws TException {
        System.out.println("get the request" + n1 + ":" + n2);
        return n1 + n2;
    }

    @Override
    public int difference(int n1, int n2) throws TException {
        return n1 - n2;
    }

    @Override
    public void add(int n1, int n2, AsyncMethodCallback resultHandler) throws TException {
        resultHandler.onComplete(n1 + n2);
    }

    @Override
    public void difference(int n1, int n2, AsyncMethodCallback resultHandler) throws TException {
        resultHandler.onComplete(n1 - n2);
    }
}
