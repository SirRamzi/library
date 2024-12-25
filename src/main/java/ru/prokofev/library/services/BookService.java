package ru.prokofev.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.prokofev.library.models.Book;
import ru.prokofev.library.repositories.BookRepository;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks(Integer page, Integer booksPerPage, boolean isSort) {
        if (page == null || booksPerPage == null) {
            if (!isSort) {
                return bookRepository.findAll();
            } else {
                return bookRepository.findAll(Sort.by("year"));
            }
        } else {
            if (!isSort) {
                return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
            } else {
                return bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
            }
        }
    }

    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBookById(int id) {
        bookRepository.deleteById(id);
    }
}
