package dao;

import entity.Person;

import java.util.List;

/**
 *  This interface contains methods
 * to realize for CRUD operations.
 */

public interface PersonDao {
    void addPerson(Person person);

    void editPerson(Person person, int personId);

    void deletePerson(int personId);

    Person find(int personId);

    List<Person> findAll();
}
