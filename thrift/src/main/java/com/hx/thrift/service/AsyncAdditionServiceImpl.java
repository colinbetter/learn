package com.hx.thrift.service;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by testuser on 17-4-28.
 */
public class AsyncAdditionServiceImpl implements AdditionService.AsyncIface {
    private static final Logger LOG = LoggerFactory.getLogger(AsyncAdditionServiceImpl.class);

    @Override
    public void add(int n1, int n2, AsyncMethodCallback resultHandler) throws TException {
        LOG.info("get add request :" + n1 + " + " + n2);
        resultHandler.onComplete(n1 + n2);
    }

    @Override
    public void difference(int n1, int n2, AsyncMethodCallback resultHandler) throws TException {
        LOG.info("get difference request :" + n1 + " - " + n2);
        resultHandler.onComplete(n1 - n2);
    }
}
