package com.example.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by testuser on 16-9-20.
 */
@XmlRootElement(name = "books")
public class Books<T> implements Serializable {


    private List<T> bookList;

    public Books() {
    }

    public Books(List<T> bookList) {
        this.bookList = bookList;
    }

    @XmlElement(name = "book")
    @XmlElementWrapper
    public List<T> getBookList() {
        return bookList;
    }

    public void setBookList(List<T> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Books{" +
                "bookList=" + bookList +
                '}';
    }
}
