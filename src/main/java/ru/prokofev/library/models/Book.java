package ru.prokofev.library.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class Book {

    private int id;
    @NotEmpty(message = "Название должно быть указано")
    private String name;
    @NotEmpty(message = "Автор должен быть указано")
    private String author;
    @Min(value = 1, message = "Год должен быть больше 0")
    private int year;
    private Integer person_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }
}
