package com.example.message;

import com.example.domain.Book;
import com.example.domain.Books;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * Created by testuser on 16-9-23.
 */
@Provider
public class BooksProvider implements MessageBodyReader<Books>, MessageBodyWriter<Books> {
    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        boolean b1 = Books.class.isAssignableFrom(type);
        boolean b2 = mediaType.toString().contains("json");
        return b1 && b2;
    }

    @Override
    public Books readFrom(Class<Books> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(entityStream, Charset.forName("utf-8")));
        String buffer = null;
        while ((buffer = br.readLine()) != null) {
            sb.append(buffer);
        }
        br.close();
        System.out.println(sb);
        ObjectMapper mapper = new ObjectMapper();
        JavaType jt = mapper.getTypeFactory().constructParametricType(type, Book.class);
        return mapper.readValue(sb.toString(), jt);
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return false;
    }

    @Override
    public long getSize(Books books, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return 0;
    }

    @Override
    public void writeTo(Books books, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {

    }
}
