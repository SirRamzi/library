package ru.prokofev.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.prokofev.library.dao.PersonDAO;
import ru.prokofev.library.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    private String getPeoplePage(Model model) {
        model.addAttribute("personList", personDAO.getPersonList());
        return "people/index";
    }

    @GetMapping("/create")
    private String getCreatePage(@ModelAttribute("person") Person person) {
        return "people/create";
    }

    @PostMapping()
    private String create(@ModelAttribute("person") Person person) {
        personDAO.create(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    private String getPersonPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPerson(id));
        return "people/person";
    }

    @DeleteMapping("/{id}")
    private String deletePerson(@PathVariable("id") int id) {
        personDAO.deletePerson(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    private String getEditPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPerson(id));
        return "/people/edit";
    }

    @PatchMapping("/{id}")
    private String editPerson(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        personDAO.updatePerson(id, person);
        return "redirect:/people/" + id;
    }
}
