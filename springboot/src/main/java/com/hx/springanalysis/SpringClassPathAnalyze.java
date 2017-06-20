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
