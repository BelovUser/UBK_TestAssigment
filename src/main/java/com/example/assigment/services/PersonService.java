package com.example.assigment.services;

import com.example.assigment.enums.Sex;
import com.example.assigment.exeptions.PersonNotFoundException;
import com.example.assigment.models.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Stateless
public class PersonService {
    @PersistenceContext
    public EntityManager entityManager;

    public void createPerson(String name, String sex, String birthday) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("DD.MM.YYYY");
        Person person = new Person(name,Sex.valueOf(sex),dateFormat.parse(birthday));

        entityManager.persist(person);
    }

    public void deletePerson(Long personId) {
        Optional<Person> person = Optional.ofNullable(entityManager.find(Person.class, personId));
        if (person.isPresent()) {
            entityManager.remove(person.get());
        }
    }

    public Optional<Person> getPersonForName(String name) throws PersonNotFoundException {
        try {
            return Optional.of(entityManager
                    .createQuery("SELECT p FROM Person p WHERE p.name = :name", Person.class)
                    .setParameter("name", name)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (NonUniqueResultException e) {
            throw new PersonNotFoundException("Multiple persons found with name: " + name);
        }
    }

    public List<Person> getAllPersons() {

        return entityManager.createQuery("SELECT p FROM Person p", Person.class)
                .getResultList();
    }

    public void updatePerson(Long personId, String name) {
        Optional<Person> person = Optional.ofNullable(entityManager.find(Person.class, personId));
        if (person.isPresent()) {
            Person editedPerson = person.get();
            editedPerson.setName(name);
            entityManager.merge(editedPerson);
        }
    }
}
