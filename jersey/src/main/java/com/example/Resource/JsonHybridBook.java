/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.Resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by testuser on 16-9-22.
 */
@XmlRootElement
public class JsonHybridBook {
    @SuppressWarnings("unused")
    @JsonProperty("bookIdNew")
    private String bookId;

    @SuppressWarnings("unused")
    @JsonProperty("bookNameNew")
    private String bookName;

    public JsonHybridBook() {
        bookId = "2";
        bookName = "Java Restful Web Services使用指南";
    }
}
