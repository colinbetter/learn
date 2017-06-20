/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.server.register;

/**
 * Created by testuser on 16-12-14.
 */
public interface ServiceRegister {
    /**
     * 注册服务
     *
     * @param metaspace the metaspace
     * @param service   the service name
     * @param ip        the ip
     * @param port      the port
     */
    void register(String metaspace, String service, String ip, int port);

    /**
     * 发布服务接口
     *
     * @param metaspace the metaspace
     * @param service   服务接口名称，一个产品中不能重复
     * @param address   服务发布的地址和端口
     */
    void register(String metaspace, String service, String address);
}
