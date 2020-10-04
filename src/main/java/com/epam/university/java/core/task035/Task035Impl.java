package com.epam.university.java.core.task035;

import com.epam.university.java.core.task034.Person;
import com.epam.university.java.core.task034.PersonImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class Task035Impl implements Task035 {

    @Override
    public PersonImpl readWithJackson(ObjectMapper mapper, String jsonString) {
        try {
            PersonFromJson personFromJson = mapper.readValue(jsonString, PersonFromJson.class);
            return new PersonImpl(personFromJson.getId(),
                    personFromJson.getFirstName(),
                    personFromJson.getLastName(),
                    personFromJson.getPhoneNumbers());
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Person readWithGson(GsonBuilder builder, String jsonString) {
        Gson gson = builder.create();
        PersonFromJson personFromJson = gson.fromJson(jsonString, PersonFromJson.class);
        return new PersonImpl(personFromJson.getId(),
                personFromJson.getFirstName(),
                personFromJson.getLastName(),
                personFromJson.getPhoneNumbers());
    }
}
