package ru.prokofev.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prokofev.library.models.Person;
import ru.prokofev.library.repositories.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPeople() {
        return personRepository.findAll();
    }
}
