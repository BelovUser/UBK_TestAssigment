package com.example.assigment.servers;

import com.example.assigment.exeptions.PersonNotFoundException;
import com.example.assigment.models.Person;
import com.example.assigment.services.PersonService;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@WebService
public class PersonWebService {
    @EJB
    private PersonService personService;

    @WebMethod
    public void createPerson(String name, String sex, String birthday) throws ParseException {
        personService.createPerson(name, sex, birthday);
    }

    @WebMethod
    public void deletePerson(long personId) {
        personService.deletePerson(personId);
    }

    @WebMethod
    public Optional<Person> getPersonForName(String name) throws PersonNotFoundException {
        return personService.getPersonForName(name);
    }

    @WebMethod
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @WebMethod
    public void updatePerson(long personId, String name) {
        personService.updatePerson(personId, name);
    }

}