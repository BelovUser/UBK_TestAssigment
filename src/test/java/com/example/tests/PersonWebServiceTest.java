package com.example.tests;

import com.example.assigment.exeptions.PersonNotFoundException;
import com.example.assigment.models.Person;
import com.example.assigment.servers.PersonWebService;
import com.example.assigment.services.PersonService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonWebServiceTest {

    @Mock
    private PersonService personServiceMock;
    @InjectMocks
    private PersonWebService personWebServiceMock;

    @Test
    public void createPersonTest() throws ParseException {
        String name = "John";
        String sex = "MALE";
        String birthday = "02.02.2002";

        personWebServiceMock.createPerson(name, sex, birthday);

        Mockito.verify(personServiceMock, Mockito.times(1)).createPerson(name, sex, birthday);
    }

    @Test
    public void deletePersonTest() {
        long personId = 1L;

        personWebServiceMock.deletePerson(personId);

        Mockito.verify(personServiceMock, Mockito.times(1)).deletePerson(personId);
    }

    @Test
    public void getPersonForNameTest() throws PersonNotFoundException {
        String name = "John";
        Person person = new Person();
        person.setName(name);

        Mockito.when(personServiceMock.getPersonForName(name)).thenReturn(Optional.of(person));
        Optional<Person> result = personWebServiceMock.getPersonForName(name);

        Assert.assertEquals(name, result.get().getName());
    }

    @Test
    public void getAllPersonsTest() {
        List<Person> personList = Arrays.asList(new Person(), new Person());

        Mockito.when(personServiceMock.getAllPersons()).thenReturn(personList);
        List<Person> result = personWebServiceMock.getAllPersons();

        Assert.assertEquals(personList.size(), result.size());
    }

    @Test
    public void updatePersonTest() {
        long personId = 1L;
        String newName = "Jane";

        personWebServiceMock.updatePerson(personId, newName);

        Mockito.verify(personServiceMock, Mockito.times(1)).updatePerson(personId, newName);
    }
}
