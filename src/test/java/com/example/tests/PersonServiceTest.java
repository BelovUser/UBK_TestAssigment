package com.example.tests;

import com.example.assigment.exeptions.PersonNotFoundException;
import com.example.assigment.models.Person;
import com.example.assigment.services.PersonService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {
    @Mock
    private EntityManager entityManagerMock;
    @InjectMocks
    private PersonService personServiceMock;

    @Test
    public void createPersonTest() throws ParseException {
        String name = "John";
        String sex = "MALE";
        String birthday = "02.02.2002";

        personServiceMock.createPerson(name, sex, birthday);

        Mockito.verify(entityManagerMock, Mockito.times(1)).persist(ArgumentMatchers.any(Person.class));
    }

    @Test
    public void deletePersonTest() {
        Long personId = 1L;
        Person person = new Person();
        person.setId(personId);

        Mockito.when(entityManagerMock.find(Person.class, personId)).thenReturn(person);
        personServiceMock.deletePerson(personId);

        Mockito.verify(entityManagerMock, Mockito.times(1)).remove(person);
    }

    @Test
    public void getPersonForNameTest() throws PersonNotFoundException {
        String name = "John";
        Person person = new Person();
        person.setName(name);

        TypedQuery<Person> query = Mockito.mock(TypedQuery.class);
        Mockito.when(entityManagerMock.createQuery("SELECT p FROM Person p WHERE p.name = :name", Person.class)).thenReturn(query);
        Mockito.when(query.setParameter("name", name)).thenReturn(query);
        Mockito.when(query.getSingleResult()).thenReturn(person);

        Optional<Person> result = personServiceMock.getPersonForName(name);

        TestCase.assertTrue(result.isPresent());
        TestCase.assertEquals(name, result.get().getName());
    }

    @Test
    public void getAllPersonsTest() {
        List<Person> personList = Arrays.asList(new Person(), new Person());

        TypedQuery<Person> query = Mockito.mock(TypedQuery.class);
        Mockito.when(entityManagerMock.createQuery("SELECT p FROM Person p", Person.class)).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(personList);

        List<Person> result = personServiceMock.getAllPersons();

        TestCase.assertEquals(personList.size(), result.size());
    }

    @Test
    public void updatePersonTest() {
        Long personId = 1L;
        String newName = "Jane";

        Person person = new Person();
        person.setId(personId);
        person.setName("John");

        Mockito.when(entityManagerMock.find(Person.class, personId)).thenReturn(person);

        personServiceMock.updatePerson(personId, newName);

        TestCase.assertEquals(newName, person.getName());
        Mockito.verify(entityManagerMock, Mockito.times(1)).merge(person);
    }
}
