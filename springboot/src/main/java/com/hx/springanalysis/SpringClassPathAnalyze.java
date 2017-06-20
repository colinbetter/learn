/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.springanalysis;


import com.hx.springanalysis.bean.A;
import com.hx.springanalysis.bean.InstantiationBeanPostProcessorTest;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by testuser on 16-12-26.
 */
public class SpringClassPathAnalyze {
    public static void main(String args[]) {
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("SpringAnalysisApplication.xml"));
        beanFactory.addBeanPostProcessor(new InstantiationBeanPostProcessorTest());
        Object hello = beanFactory.getBean("hello");
        A a = (A) beanFactory.getBean("a");
        a.say();
    }
}
