package ru.prokofev.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prokofev.library.models.Person;
import ru.prokofev.library.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

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

    public Person getPersonById(int id) {
        return personRepository.findById(id).orElse(null);
    }

    public Optional<Person> getPersonByBookId(int id) {
        return personRepository.findByBooksId(id);
    }

    public void savePerson(Person person) {
        personRepository.save(person);
    }

    public void updatePerson(int id, Person person) {
        person.setId(id);
        personRepository.save(person);
    }

    public void deletePersonById(int id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> getPersonByName(String name) {
        return personRepository.findByName(name);
    }
}
