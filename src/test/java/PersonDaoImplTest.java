import config.AppConfiguration;
import entity.Person;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import dao.PersonService;

import java.util.List;


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
        Assert.assertNotNull(personService.find(2));
    }

    @Test
    public void editPersonTest() {
        taras.setFirstName("Taras").setLastName("Ivanov").setAge(20);
        personService.editPerson(taras, 50);
        Assert.assertNotNull(personService.find(50));
    }

    @Test
    public void deletePersonTest() {
        personService.editPerson(victor, 3);
        personService.deletePerson(3);
        Assert.assertNull(personService.find(3));
    }

    @Test
    public void findPersonTest() {
        Assert.assertNotNull(personService.find(50));
    }

    @Test
    public void findAllPersonTest() {
        List<Person> personList = personService.findAll();
        Assert.assertEquals(personList.get(0).getLastName(), "Ivanov");
        Assert.assertEquals(personList.get(1).getLastName(), "Petrov");
        Assert.assertEquals(personList.get(2).getLastName(), "Tytov");
    }

    @After
    public void closeContext() {
        context.close();
    }
}
