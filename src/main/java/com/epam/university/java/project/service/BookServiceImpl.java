package com.epam.university.java.project.service;

import com.epam.university.java.project.core.cdi.impl.io.XmlResource;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinition;
import com.epam.university.java.project.core.state.machine.manager.StateMachineManager;
import com.epam.university.java.project.domain.Book;
import com.epam.university.java.project.domain.BookEvent;
import com.epam.university.java.project.domain.BookImpl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "bookService")
public class BookServiceImpl implements BookService {
    @XmlElement(name = "bookDao", type = BookDaoXmlImpl.class)
    private BookDao bookDao;
    @XmlElement(name = "stateMachineManager", type = StateMachineManagerImpl.class)
    private StateMachineManager stateMachineManager;
    private Map<String, User> users = new HashMap<>();

    private StateMachineDefinition stateMachineDefinition;


    @Override
    public Book createBook() {
        if (stateMachineDefinition == null) {
            final String config = getClass()
                    .getResource("/project/DefaultBookStateMachineDefinition.xml").getFile();
            stateMachineDefinition = stateMachineManager.loadDefinition(new XmlResource(config));
        }
        Book book = bookDao.createBook();
        stateMachineManager.initStateMachine(book,stateMachineDefinition);
        stateMachineManager.handleEvent(book, BookEvent.CREATE);
        return book;
    }

    @Override
    public Book getBook(int id) {
        Book book = bookDao.getBook(id);
        return book;
    }

    @Override
    public Collection<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public void remove(Book book) {
        bookDao.remove(book);
    }

    @Override
    public Book save(Book book) {
        return bookDao.save(book);
    }

    @Override
    public Book accept(Book book, String number) {
        if (users.containsKey(number)) {
            User user = users.get(number);
            user.addBook(book);
        } else {
            User user = new User(number);
            user.addBook(book);
            users.put(number, user);
        }
        stateMachineManager.handleEvent(book, BookEvent.ACCEPT);
        return book;
    }

    @Override
    public Book issue(Book book, LocalDate returnDate) {
        book.setReturnDate(returnDate);
        stateMachineManager.handleEvent(book, BookEvent.ISSUE);
        return book;
    }

    @Override
    public Book returnFromIssue(Book book) {
        book.setReturnDate(null);
        stateMachineManager.handleEvent(book, BookEvent.RETURN);
        return book;
    }

    private class User {
        private String number;
        private List<Book> hasBooks = new ArrayList<>();

        User(String number) {
            this.number = number;
        }

        void addBook(Book book) {
            hasBooks.add(book);
        }

        void returnBook(Book book) {
            hasBooks.remove(book);
        }
    }
}
