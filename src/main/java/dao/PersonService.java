package dao;

import entity.Person;

import java.util.List;
import java.util.Optional;

/**
 * This interface contains methods
 * for CRUD operations.
 */

public interface PersonService {
    void addPerson(Person person);

    void editPerson(Person person, int personId);

    void deletePerson(int personId);

   Optional<Person> find(int personId);

    List<Person> findAll();
}
