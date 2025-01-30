package ru.prokofev.library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Название должно быть указано")
    private String name;

    @Column(name = "author")
    @NotEmpty(message = "Автор должен быть указано")
    private String author;

    @Column(name = "year")
    @Min(value = 1, message = "Год должен быть больше 0")
    private int year;

    @Column(name = "date_last_capture")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLastCapture;

    @Column(name = "person_id")
    private Integer person_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Person person;

    @Transient
    private boolean isExpired;

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

    public Date getDateLastCapture() {
        return dateLastCapture;
    }

    public void setDateLastCapture(Date dateLastCapture) {
        this.dateLastCapture = dateLastCapture;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public boolean isExpired() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -10);
        isExpired = dateLastCapture != null && dateLastCapture.before(calendar.getTime());
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }
}
