package ru.prokofev.library.models;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class Person {


    private int id;
    @NotEmpty(message = "ФИО должо быть указано")
    private String name;
    @Min(value = 1900, message = "Год рождения должен быть больше 1900 года")
    private int year;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
