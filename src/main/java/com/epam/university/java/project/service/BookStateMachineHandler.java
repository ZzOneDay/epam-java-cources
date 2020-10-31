package com.epam.university.java.project.service;

import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinition;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinitionImpl;
import com.epam.university.java.project.core.state.machine.domain.StateMachineEventHandler;
import com.epam.university.java.project.core.state.machine.domain.StateMachineState;
import com.epam.university.java.project.domain.Book;
import com.epam.university.java.project.domain.BookEvent;
import com.epam.university.java.project.domain.BookStatus;

import java.beans.EventHandler;
import java.lang.reflect.Method;
import java.util.Collection;

public class BookStateMachineHandler implements StateMachineEventHandler {
    void change(StateMachineDefinition definition, Book book, BookEvent event) {
        if (event.equals(BookEvent.CREATE)) {
            StateMachineDefinition stateMachineDefinition = definition;
            stateMachineDefinition.setStartEvent(BookEvent.CREATE);
            stateMachineDefinition.setStartState(BookStatus.DRAFT);
            book.setStateMachineDefinition(stateMachineDefinition);
        } else {
            Collection<StateMachineState<BookStatus, BookEvent>> states =
                    book.getStateMachineDefinition().getStates();
            for (StateMachineState state : states) {
                BookEvent thisEvent = (BookEvent) state.getOn();
                if (thisEvent.equals(event)) {
                    BookStatus fromStatus = (BookStatus) state.getFrom();
                    BookStatus toStatus = (BookStatus) state.getTo();
//                    try {
//                        Method method = book.getClass().getDeclaredMethod(state.getMethodToCall());
//                        method.invoke(book);
//                    } catch (Exception e) {
//                        throw new IllegalArgumentException("Problem with method by Call String " + state.getMethodToCall());
//                    }

                    StateMachineDefinition stateMachineDefinition = book.getStateMachineDefinition();
                    if (stateMachineDefinition.getStartState() != fromStatus) {
                        throw new IllegalArgumentException("StartStatus not correct for this method "
                                + stateMachineDefinition.getStartState() + " for " + event);
                    }
                    stateMachineDefinition.setStartState(toStatus);
                    stateMachineDefinition.setStartEvent(event);
                }
            }
        }
    }
}
