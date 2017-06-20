/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.springthrift.scanner;

import com.hx.springthrift.annotation.ThriftService;
import com.hx.springthrift.factory.ServiceBeanFactory;
import org.apache.thrift.TMultiplexedProcessor;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

/**
 * Created by testuser on 17-3-6.
 */
public class ThriftServiceScanner extends ClassPathBeanDefinitionScanner {
    TMultiplexedProcessor processors = new TMultiplexedProcessor();

    public ThriftServiceScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public ThriftServiceScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
    }

    public ThriftServiceScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment) {
        super(registry, useDefaultFilters, environment);
    }

    public ThriftServiceScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment, ResourceLoader resourceLoader) {
        super(registry, useDefaultFilters, environment, resourceLoader);
    }

    @Override
    public void registerDefaultFilters() {
        this.addIncludeFilter(new AnnotationTypeFilter(ThriftService.class));
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolderSet = super.doScan(basePackages);
        beanDefinitionHolderSet.forEach(beanDefinitionHolder -> {
            GenericBeanDefinition definition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
            definition.getPropertyValues().add("innerClassName", definition.getBeanClassName());
            definition.setBeanClass(ServiceBeanFactory.class);
        });
        return beanDefinitionHolderSet;
    }
}
