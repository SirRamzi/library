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
import ru.prokofev.library.services.BookService;
import ru.prokofev.library.services.PersonService;
import ru.prokofev.library.util.PersonValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final BookDAO bookDAO;
    private final PersonValidator personValidator;
    private final PersonService personService;
    private final BookService bookService;

    @Autowired
    public PeopleController(BookDAO bookDAO, PersonValidator personValidator, PersonService personService, BookService bookService) {
        this.bookDAO = bookDAO;
        this.personValidator = personValidator;
        this.personService = personService;
        this.bookService = bookService;
    }

    @GetMapping()
    private String getIndexPage(Model model) {
        model.addAttribute("personList", personService.getPeople());
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
        personService.savePerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    private String getPersonPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.getPersonById(id));
        model.addAttribute("bookList", bookService.getBooksByPersonId(id));
        return "people/person";
    }

    @DeleteMapping("/{id}")
    private String delete(@PathVariable("id") int id) {
        personService.deletePersonById(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    private String getEditPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.getPersonById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    private String edit(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/edit";
        personService.updatePerson(id, person);
        return "redirect:/people/" + id;
    }
}
