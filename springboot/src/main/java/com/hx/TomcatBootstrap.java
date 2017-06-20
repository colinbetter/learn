

package com.hx;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 * Created by testuser on 16-12-23.
 */
public class TomcatBootstrap {
    public static final void main(String args[]) {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8888);
        try {
            tomcat.start();
            Thread.currentThread().sleep(10000);
        } catch (LifecycleException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
