/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.client.provider;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * Created by testuser on 16-12-14.
 */
public interface ServiceProvider {

    /**
     * 获取所有服务端地址
     *
     * @param metaspace the metaspace
     * @param service   the service
     * @return list list
     */
    List<InetSocketAddress> findServerAddressList(String metaspace, String service);

    /**
     * 选取一个合适的address,可以随机获取等'
     * 内部可以使用合适的算法.
     *
     * @param metaspace the metaspace
     * @param service   the service
     * @return inet socket address
     */
    InetSocketAddress selector(String metaspace, String service);

    /**
     * Destroy service provider.
     */
    void destroy();
}
