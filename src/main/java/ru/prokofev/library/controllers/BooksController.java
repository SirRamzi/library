package ru.prokofev.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.prokofev.library.dao.BookDAO;
import ru.prokofev.library.models.Book;

import java.util.Arrays;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;

    @Autowired
    public BooksController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    private String getIndexPage(Model model) {
        model.addAttribute("bookList", bookDAO.getBookList());
        return "books/index";
    }

    @GetMapping("/create")
    private String getCreatePage(@ModelAttribute("book") Book book) {
        return "books/create";
    }

    @PostMapping()
    private String create(@ModelAttribute("book") Book book) {
        bookDAO.create(book);
        return "redirect:/books";
    }
}
