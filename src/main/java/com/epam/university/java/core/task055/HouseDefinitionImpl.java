package com.epam.university.java.core.task055;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "passports_houses")
@XmlAccessorType(XmlAccessType.FIELD)
public class HouseDefinitionImpl implements HouseDefinition {
    @XmlElement(name = "addr_district")
    private String address;

    @XmlElement(name = "address")
    private String fullAddress;

    @XmlElement(name = "comm_type")
    private String commType;

    private int year;

    @XmlElement(name = "data_buildingarea")
    private double area;

    @XmlElement(name = "data_buildingdate")
    private String yearString;

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    public String getYearString() {
        return yearString;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public String getCommType() {
        return commType;
    }
}
