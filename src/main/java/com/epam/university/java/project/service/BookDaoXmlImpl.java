package com.epam.university.java.project.service;

import com.epam.university.java.project.domain.Book;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "bookDao")
public class BookDaoXmlImpl implements BookDao {
    @Override
    public Book createBook() {
        return null;
    }

    @Override
    public Book getBook(int id) {
        return null;
    }

    @Override
    public Collection<Book> getBooks() {
        return null;
    }

    @Override
    public void remove(Book book) {

    }

    @Override
    public Book save(Book book) {
        return null;
    }
}
