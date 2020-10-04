package com.epam.university.java.core.task035;

import com.epam.university.java.core.task034.PhoneNumber;
import com.epam.university.java.core.task034.PhoneNumberImpl;

import java.util.ArrayList;
import java.util.List;

public class PersonFromJson {
    private int id;
    private String firstName;
    private String lastName;
    private List<String> phones;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    /**
     * Cast to list PhoneNumbers.
     * <p>
     * This method for cast to phoneNumbers
     * for test, for task.
     * </p>
     *
     * @return new ArrayList this PhoneNumbers.
     */
    ArrayList<PhoneNumber> getPhoneNumbers() {
        ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
        for (String phone : phones) {
            phoneNumbers.add(new PhoneNumberImpl(phone));
        }
        return phoneNumbers;
    }
}
