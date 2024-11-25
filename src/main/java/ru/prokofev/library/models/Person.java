package ru.prokofev.library.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "ФИО должо быть указано")
    private String name;

    @Column(name = "year")
    @Min(value = 1900, message = "Год рождения должен быть больше 1900 года")
    private int year;

    @Column(name = "date_last_capture")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLastCapture;

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

    public Date getDateLastCapture() {
        return dateLastCapture;
    }

    public void setDateLastCapture(Date dateLastCapture) {
        this.dateLastCapture = dateLastCapture;
    }
}
