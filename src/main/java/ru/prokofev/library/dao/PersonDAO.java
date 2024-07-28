package ru.prokofev.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.prokofev.library.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(Person person) {
        jdbcTemplate.update("INSERT INTO people(name, age) values(?, ?)", person.getName(), person.getAge());
    }

    public List<Person> getPersonList() {
        return jdbcTemplate.query("SELECT * FROM people", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM people WHERE id = ?", new BeanPropertyRowMapper<>(Person.class), id).stream().findAny().orElse(null);
    }

    public void deletePerson(int id) {
        jdbcTemplate.update("DELETE FROM people WHERE id = ?", id);
    }

    public void updatePerson(int id, Person person) {
        jdbcTemplate.update("UPDATE people SET name = ?, age = ? WHERE id = ?", person.getName(), person.getAge(), id);
    }
}
