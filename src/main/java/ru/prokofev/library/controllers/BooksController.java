package ru.prokofev.library.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.prokofev.library.dao.BookDAO;
import ru.prokofev.library.dao.PersonDAO;
import ru.prokofev.library.models.Book;
import ru.prokofev.library.models.Person;
import ru.prokofev.library.services.BookService;
import ru.prokofev.library.services.PersonService;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BooksController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping()
    private String getIndexPage(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "books_per_page", required = false) Integer booksPerPage, @RequestParam(value = "sort_by_year", required = false) boolean isSort, Model model) {
        model.addAttribute("bookList", bookService.getBooks(page, booksPerPage, isSort));
        return "books/index";
    }

    @GetMapping("/search")
    private String getSearchPage(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("books", bookService.getBooksByNameStartingWith(name));
        return "books/search";
    }

    @GetMapping("/create")
    private String getCreatePage(@ModelAttribute("book") Book book) {
        return "books/create";
    }

    @PostMapping()
    private String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/create";
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    private String getEditPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    private String edit(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/edit";
        bookService.saveBook(book);
        return "redirect:/books/" + id;
    }

    @GetMapping("/{id}")
    private String getBookPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        Optional<Person> bookOwner = personService.getPersonByBookId(id);
        if (bookOwner.isPresent()) {
            model.addAttribute("person", bookOwner.get());
        } else {
            model.addAttribute("personList", personService.getPeople());
        }
        return "books/book";
    }

    @DeleteMapping("/{id}")
    private String deleteBook(@PathVariable("id") int id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }
}
