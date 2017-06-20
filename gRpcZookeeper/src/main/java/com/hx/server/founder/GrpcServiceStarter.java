/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.server.founder;

import com.hx.server.register.ServiceRegister;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by testuser on 16-12-14.
 */
public class GrpcServiceStarter implements ServiceStarter, ServiceFounder,
        ApplicationListener<ContextRefreshedEvent>, DisposableBean {

    private static final Logger LOG = LoggerFactory.getLogger(GrpcServiceStarter.class);
    private ServiceRegister serviceRegister;
    private Map<String, BindableService> services;
    private List<Server> serverList = new ArrayList<>();
    private String metaspace;
    private String ip;


    private ExecutorService executorService;

    @Override
    public void start() {
        Map<String, BindableService> serviceMap = findServices();
        try {
            initServer(serviceMap);
        } catch (UnknownHostException e) {
            LOG.error("can not get IP of the localhost", e);
        }
    }

    public Map<String, BindableService> findServices() {
        return services != null && !services.isEmpty() ? services : null;
    }


    private void initServer(Map<String, BindableService> serviceMap) throws UnknownHostException {
        if (serviceMap == null || serviceMap.isEmpty()) {
            LOG.info("no service to start");
            return;
        }
        ip = InetAddress.getLocalHost().getHostAddress();
        if (executorService == null) {
            executorService = Executors.newCachedThreadPool();
        }
        serviceMap.forEach((key, service) -> {
            try {
                int port = getAvailablePort();
                LOG.info("the bind port is " + port);
                Server server = ServerBuilder.forPort(port).executor(executorService)
                        .addService(service)
                        .build()
                        .start();
                serverList.add(server);
                serviceRegister.register(metaspace, key, ip, port);
            } catch (IOException e) {
                LOG.error("the bean  of name " + key + " cannot be start", e);
            }
        });

    }

    private int getAvailablePort() {
        ServerSocket serverSocket = null; //读取空闲的可用端口
        int port = 0;
        try {
            serverSocket = new ServerSocket(0);
            port = serverSocket.getLocalPort();
            serverSocket.close();
        } catch (IOException e) {
        }
        return port;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        services = event.getApplicationContext().getBeansOfType(BindableService.class);
        start();
    }

    @Override
    public void destroy() throws Exception {
        if (!serverList.isEmpty()) {
            serverList.forEach(server -> server.shutdown());
        }
    }

    public ServiceRegister getServiceRegister() {
        return serviceRegister;
    }

    public void setServiceRegister(ServiceRegister serviceRegister) {
        this.serviceRegister = serviceRegister;
    }


    public String getMetaspace() {
        return metaspace;
    }

    public void setMetaspace(String metaspace) {
        this.metaspace = metaspace;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }
}
