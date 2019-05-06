package dao;

import entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final PersonService personService;

    @GetMapping("/list")
    public String listUser(Model model) {
        model.addAttribute("users", personService.findAll());
        return "list-user";
    }

    @GetMapping("/create")
    public String addUser(Model model) {
        Person person = new Person();
        model.addAttribute(person);
        return "show-user-form";
    }

    @GetMapping("/update")
    public String updateUser(Model model, @RequestParam("id") Integer id) {
        model.addAttribute("user", personService.find(id));
        return "show-user-form";
    }

    @PostMapping("/processform")
    public String processForm(Model model, @ModelAttribute("user") Person person) {
        personService.addPerson(person);
        model.addAttribute("users", personService.findAll());
        System.out.println("in process form");
        return "list-user";
    }

    @GetMapping("/delete")
    public String deleteUser(Model model, @RequestParam("id") Integer id) {
        personService.deletePerson(id);
        model.addAttribute("customers", personService.findAll());
        return "list-customer";
    }
}
