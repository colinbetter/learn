/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.springthrift.factory;

import com.hx.springthrift.annotation.ThriftService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by testuser on 17-3-6.
 */
public class ServiceBeanFactory<T> implements FactoryBean<T> {
    private String innerClassName;

    public void setInnerClassName(String innerClassName) {
        this.innerClassName = innerClassName;
    }

    @Override
    public T getObject() throws Exception {
        Class innerClass = Class.forName(innerClassName);
        if (innerClass.isAnnotationPresent(ThriftService.class)) {
            ThriftService thriftService = (ThriftService) innerClass.getAnnotation(ThriftService.class);
        } else {
            throw new RuntimeException("the class " + innerClassName + " does not annotation with ThriftService");
        }

        if (innerClass.isInterface()) {
            return (T) InterfaceProxy.newInstance(innerClass);
        } else {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(innerClass);
            enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
            enhancer.setCallback(new MethodInterceptorImpl());
            return (T) enhancer.create();
        }
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public static class InterfaceProxy implements InvocationHandler {
        public static <T> T newInstance(Class<T> innerInterface) {
            ClassLoader classLoader = innerInterface.getClassLoader();
            Class[] interfaces = new Class[]{innerInterface};
            InterfaceProxy proxy = new InterfaceProxy();
            return (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("ObjectProxy execute:" + method.getName());
            return method.invoke(proxy, args);
        }
    }

    public static class MethodInterceptorImpl implements MethodInterceptor {
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("MethodInterceptorImpl:" + method.getName());
            return methodProxy.invokeSuper(o, objects);
        }
    }
}
