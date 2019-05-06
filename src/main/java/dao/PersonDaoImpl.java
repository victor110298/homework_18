package dao;

import lombok.extern.slf4j.Slf4j;
import entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This class allows the component scanning
 * to find and configure DAO.
 */
@Slf4j
@Repository
@Qualifier("personDao")
public class PersonDaoImpl implements PersonDao {
    private final static String INSERT = "INSERT INTO persons (first_name, last_name, age) VALUES (?, ?, ?)";
    private final static String UPDATE = "UPDATE persons SET first_name = ? , last_name = ? , age = ? WHERE id = ?";
    private final static String DELETE = "DELETE from persons WHERE id = ?";
    private final static String SELECT = "SELECT * FROM persons where id = ?";
    private final static String SELECT_ALL = "SELECT * FROM persons";
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addPerson(Person person) {
        jdbcTemplate.update(INSERT, person.getFirstName(), person.getLastName(), person.getAge());
        log.info("Person Added!!");
    }

    public void editPerson(Person person, int personId) {
        jdbcTemplate.update(UPDATE, person.getFirstName(), person.getLastName(), person.getAge(), personId);
        log.info("Person Updated!!");
    }

    public void deletePerson(int personId) {
        jdbcTemplate.update(DELETE, personId);
        log.info("Person Deleted!!");
    }

    public Optional<Person> find(int personId) {
        return Optional.of((Person) jdbcTemplate.queryForObject(SELECT, new Object[]{personId}, new BeanPropertyRowMapper(Person.class)));
    }

    public List<Person> findAll() {
        return jdbcTemplate.query(SELECT_ALL, new BeanPropertyRowMapper(Person.class));
    }
}
