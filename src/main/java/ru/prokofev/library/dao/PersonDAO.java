package ru.prokofev.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.prokofev.library.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createPerson(Person person) {
        jdbcTemplate.update("INSERT INTO person(name, year) values(?, ?)", person.getName(), person.getYear());
    }

    public List<Person> getPersonList() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id = ?", new BeanPropertyRowMapper<>(Person.class), id).stream().findAny().orElse(null);
    }

    public void deletePerson(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id = ?", id);
    }

    public void updatePerson(int id, Person person) {
        jdbcTemplate.update("UPDATE person SET name = ?, year = ? WHERE id = ?", person.getName(), person.getYear(), id);
    }

    public Person getPersonByBook(int id) {
        return jdbcTemplate.query("SELECT person.id, person.name, person.year FROM person JOIN book ON person.id = book.person_id WHERE book.id = ?", new BeanPropertyRowMapper<>(Person.class), id).stream().findAny().orElse(null);
    }

    public Optional<Person> getPersonByName(String name) {
        return jdbcTemplate.query("SELECT * FROM person WHERE name = ? LIMIT 1", new BeanPropertyRowMapper<>(Person.class), name).stream().findAny();
    }

}
