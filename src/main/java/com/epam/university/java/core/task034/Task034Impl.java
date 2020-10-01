package com.epam.university.java.core.task034;

import org.xml.sax.helpers.DefaultHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Task034Impl implements Task034 {
    @Override
    public Person readWithSaxParser(DefaultHandler handler, String filepath) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;
        SaxHandlerImpl saxParser;
        try {
            parser = factory.newSAXParser();
            saxParser = new SaxHandlerImpl();
            File file = new File(getClass().getResource(filepath).toURI());
            parser.parse(file, saxParser);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
        return saxParser.getPerson();
    }

    @Override
    public Person readWithJaxbParser(String filepath) {
        try {
            File file = new File(getClass().getResource(filepath).toURI());
            JAXBContext jc = JAXBContext.newInstance(PersonJaxb.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            PersonJaxb personJaxb = (PersonJaxb) unmarshaller.unmarshal(file);
            return personJaxb.getPerson();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Person readWithStaxParser(XMLStreamReader streamReader) {
        Person person = new PersonImpl();
        List<PhoneNumber> list = new ArrayList<>();

        String localName = null;
        String text = null;

        try {
            while (streamReader.hasNext()) {
                final int event = streamReader.next();
                if (event == XMLStreamConstants.START_ELEMENT) {
                    localName = streamReader.getLocalName();
                    if (localName.equals("person")) {
                        person.setId(Integer.parseInt(streamReader
                                .getAttributeValue("", "id")));
                    }
                }

                if (event == XMLStreamConstants.CHARACTERS) {
                    text = streamReader.getText().trim();
                    if (text.length() == 0) {
                        continue;
                    }
                }

                if (event == XMLStreamConstants.END_ELEMENT) {
                    if (text == null || localName == null) {
                        continue;
                    }

                    if (localName.equals("first-name")) {
                        person.setFirstName(text);
                    }

                    if (localName.equals("last-name")) {
                        person.setLastName(text);
                    }

                    if (localName.equals("person-phone")) {
                        if (text.isEmpty()) {
                            continue;
                        }
                        list.add(new PhoneNumberImpl(text));
                    }

                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

        person.setPhoneNumbers(list);
        return person;
    }
}
