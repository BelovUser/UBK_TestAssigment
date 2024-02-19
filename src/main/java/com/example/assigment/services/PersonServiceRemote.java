package com.example.assigment.services;

import com.example.assigment.exeptions.PersonNotFoundException;
import com.example.assigment.models.Person;

import javax.ejb.Remote;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Remote
public interface PersonServiceRemote {
    void createPerson(String name, String sex, String birthday) throws ParseException;
    void deletePerson(Long personId);
    Optional<Person> getPersonForName(String name) throws PersonNotFoundException;
    List<Person> getAllPersons();
    void updatePerson(Long personId, String name);
}
