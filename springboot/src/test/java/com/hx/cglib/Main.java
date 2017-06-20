package com.hx.cglib;

/**
 * Created by testuser on 16-9-7.
 */
public class Main {
    public static void main(String args[]) {
        CglibProxy cglibProxy = new CglibProxy();
        Service service = (Service) cglibProxy.getProxy(Service.class);
        service.doSomething();
    }
}
