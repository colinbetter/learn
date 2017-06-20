/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.springanalysis.property;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * Created by testuser on 17-2-17.
 */
public class ClasspathResourceTest {
    public static void main(String[] args) {
        Resource resource = new ClassPathResource("log4j.xml");
        try {
            resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
