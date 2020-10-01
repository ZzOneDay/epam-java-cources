package com.epam.university.java.core.task034;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "person-phone")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhoneNumberImpl implements PhoneNumber {

    @XmlValue
    private String number;

    PhoneNumberImpl() {
        super();
    }

    public PhoneNumberImpl(String number) {
        this.number = number;
    }

    @Override
    public String getPhoneNumber() {
        return number;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        number = phoneNumber;
    }

    @Override
    public String toString() {
        return number;
    }
}
