/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.thrift.service;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

/**
 * Created by testuser on 17-4-28.
 */
public class AsyncAdditionServiceImpl implements AdditionService.AsyncIface {
    @Override
    public void add(int n1, int n2, AsyncMethodCallback resultHandler) throws TException {
        resultHandler.onComplete(n1 + n2);
    }

    @Override
    public void difference(int n1, int n2, AsyncMethodCallback resultHandler) throws TException {
        resultHandler.onComplete(n1 - n2);
    }
}
