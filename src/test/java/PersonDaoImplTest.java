import config.AppConfiguration;
import dao.PersonService;
import entity.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


public class PersonDaoImplTest {
    private AbstractApplicationContext context;
    private PersonService personService;
    private Person taras;
    private Person petro;
    private Person victor;

    @Before
    public void init() {
        context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        personService = (PersonService) context.getBean("personService");
        taras = new Person();
        petro = new Person();
        victor = new Person();
        taras.setAge(20).setFirstName("Taras").setLastName("Ivanov");
        petro.setAge(21).setFirstName("Petro").setLastName("Petrov");
        victor.setAge(22).setFirstName("Victor").setLastName("Tytov");
    }

    @Test
    public void addPersonTest() {
        personService.addPerson(taras);
        personService.addPerson(petro);
        personService.addPerson(victor);
        assertNotNull(personService.find(2));
    }

    @Test
    public void editPersonTest() {
        taras.setFirstName("Taras").setLastName("Ivanov").setAge(20);
        personService.editPerson(taras, 50);
        assertNotNull(personService.find(50));
    }

    @Test
    public void deletePersonTest() {
        personService.editPerson(victor, 3);
        personService.deletePerson(3);
        assertNull(personService.find(3));
    }

    @Test
    public void findPersonTest() {
        assertNotNull(personService.find(50));
    }

    @Test
    public void findAllPersonTest() {
        List<Person> personList = personService.findAll();
        assertEquals(personList.get(0).getLastName(), "Ivanov");
        assertEquals(personList.get(1).getLastName(), "Petrov");
        assertEquals(personList.get(2).getLastName(), "Tytov");
    }

    @After
    public void closeContext() {
        context.close();
    }
}
