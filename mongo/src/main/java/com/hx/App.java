package com.hx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Hello world!
 */
@Configuration
@ComponentScan(basePackages = {"com.hx.dao", "com.hx.service", "com.hx.controller"})
@EnableAutoConfiguration
public class App implements CommandLineRunner {
    @Autowired
    private MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("#############################################");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("mongoTemplate==" + mongoTemplate == null);
    }
}
