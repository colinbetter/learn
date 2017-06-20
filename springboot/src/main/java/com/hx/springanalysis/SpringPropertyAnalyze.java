/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.springanalysis;

import com.hx.springanalysis.bean.MyAnnotation;
import com.hx.springanalysis.bean.Resolv;
import com.hx.springanalysis.bean.Resolvcy;
import com.hx.springanalysis.property.PropertyBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Created by testuser on 16-12-28.
 */
public class SpringPropertyAnalyze {
    public static void main(String args[]) {
        ClassPathXmlApplicationContext beanFactory = new ClassPathXmlApplicationContext("SpringPropertyAnalyzeApplication.xml") {
            @Override
            protected void prepareBeanFactory(ConfigurableListableBeanFactory beanFactory) {
                super.prepareBeanFactory(beanFactory);
                beanFactory.registerResolvableDependency(Resolv.class, new Resolv());
                //beanFactory.getBeanPostProcessorCount();
            }

        };
        PropertyBean hello = (PropertyBean) beanFactory.getBean("property");
        System.out.println(hello.getNameList());
        PropertyBean hello1 = (PropertyBean) beanFactory.getBean("property");
        System.out.println(hello1.getNameList());
        //((Resolvcy)beanFactory.getBean("resolvcy")).doSomething();
        Map<String, Object> maps = beanFactory.getBeansWithAnnotation(MyAnnotation.class);
        maps.forEach((key, object) -> {
            System.out.println(key);
            Resolvcy resolvcy = (Resolvcy) object;
            resolvcy.doSomething();
        });

    }
}
