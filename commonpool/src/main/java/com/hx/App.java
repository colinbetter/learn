package com.hx;

import com.hx.factory.SimpleObjectFactory;
import com.hx.object.SimpleObject;
import com.hx.pool.SimplePool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        SimpleObjectFactory simpleObjectFactory = new SimpleObjectFactory();
        GenericObjectPoolConfig conf = new GenericObjectPoolConfig();
        conf.setMaxIdle(2);
        conf.setTestOnBorrow(true);
        conf.setTestWhileIdle(true);
        SimplePool simplePool = new SimplePool(simpleObjectFactory, conf);
        SimpleObject mySimpleObject = null;
        try {
            for (int i = 100; i > 0; i--) {
                SimpleObject simpleObject = simplePool.borrowObject();
                if (mySimpleObject == null) {
                    mySimpleObject = simpleObject;
                } else {
                    System.out.println(mySimpleObject == simpleObject);
                }
                simplePool.returnObject(simpleObject);
                simplePool.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
