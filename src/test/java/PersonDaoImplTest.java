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
    private Person Taras;
    private Person Petro;
    private Person Victor;

    @Before
    public void init() {
        context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        personService = (PersonService) context.getBean("personService");
        Taras = new Person();
        Petro = new Person();
        Victor = new Person();
        Taras.setAge(20).setFirstName("Taras").setLastName("Taras");
        Petro.setAge(21).setFirstName("Petro").setLastName("Ivanov");
        Victor.setAge(22).setFirstName("Victor").setLastName("Tytov");
    }

    @Test
    public void addPersonTest() {
        personService.addPerson(Taras);
        personService.addPerson(Petro);
        personService.addPerson(Victor);
        Assert.assertNotNull(personService.find(49));
    }

    @Test
    public void editPersonTest() {
        Taras.setFirstName("Taras").setLastName("Taras").setAge(20);
        personService.editPerson(Taras, 50);
        Assert.assertEquals(personService.find(50).getFirstName(),"Taras");
    }

    @Test
    public void deletePersonTest() {
        personService.deletePerson(49);
    }

    @Test
    public void findPersonTest() {
        Person testPerson = personService.find(50);
        Assert.assertEquals(testPerson.getFirstName(), "Petro");
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
