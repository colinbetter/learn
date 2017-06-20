/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.Resource;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

import javax.ws.rs.ext.ContextResolver;

/**
 * Created by testuser on 16-9-22.
 */

public class JsonContextProvider implements ContextResolver<ObjectMapper> {
    final ObjectMapper d;
    final ObjectMapper c;

    public JsonContextProvider() {
        d = createDefaultMapper();
        c = createCombinedMapper();
    }

    private static ObjectMapper createCombinedMapper() {
        AnnotationIntrospector ps = createIntrospector();
        ObjectMapper result = new ObjectMapper();
        //result.configure(Feature.WRAP_ROOT_VALUE, true);
        //result.configure(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE, true);
        //result.configure(result.getDeserializationConfig().with(ps),true);
        // result.configure(result.getSerializationConfig().with(ps));
        result.getDeserializationConfig().with(ps);
        result.getSerializationConfig().with(ps);
        return result;
    }

    private static ObjectMapper createDefaultMapper() {
        ObjectMapper result = new ObjectMapper();
        //result.configure(JsonParser.Feature.INDENT_OUTPUT, true);
        return result;
    }

    private static AnnotationIntrospector createIntrospector() {
        AnnotationIntrospector p = new JacksonAnnotationIntrospector();
        AnnotationIntrospector s = new JaxbAnnotationIntrospector();
        return AnnotationIntrospector.pair(p, s);
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        if (type == JsonHybridBook.class) {
            return c;
        } else {
            return d;
        }
    }
}