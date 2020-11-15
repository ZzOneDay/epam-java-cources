package com.epam.university.java.core.task055;

import com.google.gson.internal.bind.util.ISO8601Utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Task055Impl implements Task055 {
    @Override
    public ProcessingContext createContext(String path) {
        Houses houses;
        try {
            File file = new File(getClass().getResource(path).toURI());
            JAXBContext context = JAXBContext.newInstance(Houses.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            houses = (Houses) unmarshaller.unmarshal(file);
        } catch (Exception e) {
            e.getMessage();
            throw new IllegalArgumentException("File parsing error");
        }
        return ProcessingContextImpl.getInstance(houses.getHouseDefinitionCollections());
    }
}
