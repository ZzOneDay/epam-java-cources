package com.epam.university.java.core.task055;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "dataset")
public class Houses {
    @XmlElement(name = "passports_houses", type = HouseDefinitionImpl.class)
    private List<HouseDefinition> houseDefinitionCollections = new LinkedList<>();

    public List<HouseDefinition> getHouseDefinitionCollections() {
        return houseDefinitionCollections;
    }
}
