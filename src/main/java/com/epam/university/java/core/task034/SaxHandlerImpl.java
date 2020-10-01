package com.epam.university.java.core.task034;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.ArrayList;

public class SaxHandlerImpl extends SaxHandler {
    private String elementName = "";
    private PersonImpl person = new PersonImpl();
    private ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();

    PersonImpl getPerson() {
        return person;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {
        elementName = qName;
        if (elementName.equals("person")) {
            int id = Integer.parseInt(attributes.getValue("id"));
            person.setId(id);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (person.getPhoneNumbers() == null) {
            person.setPhoneNumbers(phoneNumbers);
        }

        if (elementName.equals("first-name")) {
            person.setFirstName(new String(ch, start, length));
        }

        if (elementName.equals("last-name")) {
            person.setLastName(new String(ch, start, length));
        }

        if (elementName.equals("person-phone")) {
            phoneNumbers.add(new PhoneNumberImpl(new String(ch, start, length)));
        }
    }

    @Override
    public void endElement(String namespace,
                           String localName, String qName) throws SAXException {
        elementName = "";
    }
}
