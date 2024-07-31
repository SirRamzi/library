package ru.prokofev.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.prokofev.library.models.Book;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createBook(Book book) {
        jdbcTemplate.update("INSERT INTO book(name, author, year) VALUES(?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
    }

    public List<Book> getBookList() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book getBook(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id = ?", new BeanPropertyRowMapper<>(Book.class), id).stream().findAny().orElse(null);
    }

    public void updateBook(Book book) {
        jdbcTemplate.update("UPDATE book SET name = ?, author = ?, year = ? WHERE id = ?", book.getName(), book.getAuthor(), book.getYear(), book.getId());
    }
}
