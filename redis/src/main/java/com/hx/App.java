

package com.hx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by testuser on 17-4-5.
 */
@ComponentScan(basePackages = {"com.hx.conf", "com.hx.dao", "com.hx.controller", "com.hx.listener"})
@EnableAutoConfiguration
public class App {
    public static void main(String args[]) {
        SpringApplication.run(App.class, args);
    }
}
