package ru.prokofev.library.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.prokofev.library.dao.BookDAO;
import ru.prokofev.library.dao.PersonDAO;
import ru.prokofev.library.models.Person;
import ru.prokofev.library.util.PersonValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDAO personDAO, BookDAO bookDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    private String getIndexPage(Model model) {
        model.addAttribute("personList", personDAO.getPersonList());
        return "people/index";
    }

    @GetMapping("/create")
    private String getCreatePage(@ModelAttribute("person") Person person) {
        return "people/create";
    }

    @PostMapping()
    private String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/create";
        personDAO.createPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    private String getPersonPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPerson(id));
        model.addAttribute("bookList", bookDAO.getBookListByPerson(id));
        return "people/person";
    }

    @DeleteMapping("/{id}")
    private String delete(@PathVariable("id") int id) {
        personDAO.deletePerson(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    private String getEditPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPerson(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    private String edit(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/edit";
        personDAO.updatePerson(id, person);
        return "redirect:/people/" + id;
    }
}
