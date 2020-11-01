package com.epam.university.java.project.service;

import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinition;
import com.epam.university.java.project.core.state.machine.domain.StateMachineEventHandler;
import com.epam.university.java.project.core.state.machine.domain.StateMachineState;
import com.epam.university.java.project.domain.Book;
import com.epam.university.java.project.domain.BookEvent;
import com.epam.university.java.project.domain.BookStatus;

import java.util.Collection;

@SuppressWarnings("unchecked")
public class BookStateMachineHandler implements StateMachineEventHandler {
    void change(StateMachineDefinition definition, Book book, BookEvent event) {
        if (event.equals(BookEvent.CREATE)) {
            book.setStateMachineDefinition(definition);
            book.setState((BookStatus) definition.getStartState());
        } else {
            Collection<StateMachineState<BookStatus, BookEvent>> states =
                    book.getStateMachineDefinition().getStates();
            for (StateMachineState state : states) {
                BookEvent thisEvent = (BookEvent) state.getOn();
                if (thisEvent.equals(event)) {
                    BookStatus fromStatus = (BookStatus) state.getFrom();
                    BookStatus toStatus = (BookStatus) state.getTo();

                    StateMachineDefinition stateMachineDefinition =
                            book.getStateMachineDefinition();
                    if (book.getState() != fromStatus) {
                        throw new IllegalArgumentException("StartStatus not correct for this method"
                                + stateMachineDefinition.getStartState() + " for " + event);
                    }
                    book.setState(toStatus);
                    break;
                }
            }
        }
    }
}
