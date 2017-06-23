package com.hx.thrift.service;

import com.hx.springthrift.annotation.ThriftService;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by testuser on 16-9-3.
 */
@ThriftService(processorClass = AdditionService.AsyncProcessor.class)
public class AdditionServiceImpl implements AdditionService.AsyncIface, AdditionService.Iface {
    private static final Logger LOG = LoggerFactory.getLogger(AdditionServiceImpl.class);

    @Override
    public int add(int n1, int n2) throws TException {
        LOG.info("get add request :" + n1 + " + " + n2);
        return n1 + n2;
    }

    @Override
    public int difference(int n1, int n2) throws TException {
        LOG.info("get difference request :" + n1 + " - " + n2);
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
