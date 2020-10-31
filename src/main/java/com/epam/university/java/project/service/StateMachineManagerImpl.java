package com.epam.university.java.project.service;

import com.epam.university.java.project.core.cdi.impl.io.XmlResource;
import com.epam.university.java.project.core.cdi.io.Resource;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinition;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinitionImpl;
import com.epam.university.java.project.core.state.machine.domain.StatefulEntity;
import com.epam.university.java.project.core.state.machine.manager.StateMachineManager;
import com.epam.university.java.project.domain.Book;
import com.epam.university.java.project.domain.BookEvent;
import com.epam.university.java.project.domain.BookStatus;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.logging.Handler;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class StateMachineManagerImpl implements StateMachineManager {
    private StateMachineDefinition stateMachineDefinition;
    BookStateMachineHandler bookStateMachineHandler = new BookStateMachineHandler();

    {
        final String config = getClass().getResource("/project/DefaultBookStateMachineDefinition.xml").getFile();
        loadDefinition(new XmlResource(config));
    }


    @Override
    public StateMachineDefinition<?, ?> loadDefinition(Resource resource) {
        try {
            JAXBContext context = JAXBContext.newInstance(StateMachineDefinitionImpl.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            stateMachineDefinition =
                    (StateMachineDefinition) unmarshaller.unmarshal(resource.getFile());

            return stateMachineDefinition;
        } catch (JAXBException e) {
            throw new IllegalArgumentException("JAXB could not create StateMachineDefinition by XML");
        }
    }

    @Override
    public <S, E> StatefulEntity<S, E> initStateMachine(StatefulEntity<S, E> entity, StateMachineDefinition<S, E> definition) {
        entity.setStateMachineDefinition(definition);
        return entity;
    }

    @Override
    public <S, E> StatefulEntity<S, E> handleEvent(StatefulEntity<S, E> entity, E event) {
        //Заходит книга, и че с ней сделать
        Book book = (Book) entity;
        BookEvent bookEvent = (BookEvent) event;
        bookStateMachineHandler.change(stateMachineDefinition, book,bookEvent);
        book.setState(book.getStateMachineDefinition().getStartState());
        return null;
    }
}
