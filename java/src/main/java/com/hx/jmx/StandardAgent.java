/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.jmx;

import javax.management.Attribute;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

/**
 * Created by testuser on 16-12-8.
 */
public class StandardAgent {
    private MBeanServer mBeanServer = null;

    public StandardAgent() {
        mBeanServer = MBeanServerFactory.newMBeanServer();
    }

    public static void main(String[] args) {
        StandardAgent agent = new StandardAgent();
        MBeanServer mBeanServer = agent.getMBeanServer();
        String domain = mBeanServer.getDefaultDomain();
        String managedResourceClassName = "com.hx.jmx.Car";
        ObjectName objectName = agent.createObjectName(domain + ":type=" + managedResourceClassName);
        System.out.println("objectName: " + objectName);
        agent.createStandardBean(objectName, managedResourceClassName);

        try {
            Attribute colorAttribute = new Attribute("Color", "blue");
            mBeanServer.setAttribute(objectName, colorAttribute);
            System.out.println(mBeanServer.getAttribute(objectName, "Color"));
            mBeanServer.invoke(objectName, "drive", null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MBeanServer getMBeanServer() {
        return mBeanServer;
    }

    public ObjectName createObjectName(String name) {
        ObjectName objectName = null;
        try {
            objectName = new ObjectName(name);
        } catch (Exception e) {
        }
        return objectName;
    }

    private void createStandardBean(ObjectName objectName, String managedResourceClassName) {
        try {
            mBeanServer.createMBean(managedResourceClassName, objectName);
        } catch (Exception e) {
        }
    }
}
