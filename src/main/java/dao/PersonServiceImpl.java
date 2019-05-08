package dao;

import entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class implements PersonService
 * and realise this methods
 */

@Service("personService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonDao personDao;

    public void addPerson(Person person) {
        personDao.addPerson(person);
    }

    public void editPerson(Person person, int personId) {
        personDao.editPerson(person);
    }

    public void deletePerson(int personId) {
        personDao.deletePerson(personId);
    }

    public Person find(int personId) {
        return personDao.find(personId);
    }

    public List<Person> findAll() {
        return personDao.findAll();
    }
}
