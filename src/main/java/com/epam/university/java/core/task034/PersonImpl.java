package com.epam.university.java.core.task034;

import java.util.Collection;

public class PersonImpl implements Person {
    private int id;
    private String firstName;
    private String lastName;
    private Collection<PhoneNumber> phoneNumbers;

    PersonImpl() {

    }

    /**
     * Object class Person.
     * <p>
     * Implemented interface Person.
     * </p>
     *
     * @param id           generated from Json file.
     * @param firstName    generated from Json file.
     * @param lastName     generated from Json file.
     * @param phoneNumbers generated from Json file.
     */
    public PersonImpl(int id, String firstName,
                      String lastName, Collection<PhoneNumber> phoneNumbers) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public Collection<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    @Override
    public void setPhoneNumbers(Collection<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
