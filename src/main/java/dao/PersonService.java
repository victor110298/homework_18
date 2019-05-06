package dao;

import entity.Person;

import java.util.List;

/**
 * This interface contains methods
 * for CRUD operations.
 */

public interface PersonService {
    void addPerson(Person person);

    void editPerson(Person person, int personId);

    void deletePerson(int personId);

    Person find(int personId);

    List<Person> findAll();
}
