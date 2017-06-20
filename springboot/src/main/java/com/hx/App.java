package com.hx;

import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySourcesPropertyResolver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Map<String, String> map1 = new ConcurrentHashMap<>(2, 3, 4);
        map1.put("123", "345");
        MutablePropertySources propertySources = new MutablePropertySources();
        Map<String, Object> map = new HashMap<>();
        map.put("encoding", "gbk");
        PropertySource propertySource1 = new MapPropertySource("mapProperties", map);
        propertySources.addFirst(propertySource1);
        PropertyResolver propertyResolver = new PropertySourcesPropertyResolver(propertySources);

        System.out.println(propertySources.get("mapProperties").getProperty("encoding"));
        System.out.println(propertyResolver.getProperty("encoding"));
    }
}
