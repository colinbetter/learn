/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.client;

import com.hx.client.provider.ServiceProvider;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.DisposableBean;

import java.net.InetSocketAddress;

/**
 * Created by testuser on 16-12-14.
 */
public class ClientBase implements DisposableBean {
    private String metaspace;
    private String service;
    private ServiceProvider serviceProvider;
    private ManagedChannel channel;


    @Override
    public void destroy() throws Exception {
        if (channel != null) {
            channel.shutdown();
        }
    }


    public void afterPropertiesSet() throws Exception {

    }

    protected ManagedChannel getChannel() {
        if (channel == null) {
            InetSocketAddress address = serviceProvider.selector(metaspace, service);
            if (address != null) {
                channel = ManagedChannelBuilder.forAddress(address.getAddress().getHostAddress(), address.getPort())
                        .usePlaintext(true)
                        .build();
            }
        }
        return channel;
    }

    public void setMetaspace(String metaspace) {
        this.metaspace = metaspace;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
}
