package com.example.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by testuser on 16-9-20.
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "tbl_hx_book")
public class Book {
    private static final int NAME_LENGTH = 50;
    private Long bookId;
    private String bookName;
    private String publisher;

    public Book() {
    }

    public Book(Long bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "EMP_SEQ")
    @SequenceGenerator(name = "EMP_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "EMP_SEQ_CONTENT")
    @Column(unique = true, nullable = false, name = "BOOKID")
    public Long getBookId() {
        return bookId;
    }


    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Column(length = Book.NAME_LENGTH, name = "BOOKNAME")
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Column(length = Book.NAME_LENGTH, name = "PUBLISHER")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
