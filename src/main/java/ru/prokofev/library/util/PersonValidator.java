package ru.prokofev.library.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.prokofev.library.dao.PersonDAO;
import ru.prokofev.library.models.Person;
import ru.prokofev.library.services.PersonService;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;
    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonDAO personDAO, PersonService personService) {
        this.personDAO = personDAO;
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (personService.getPersonByName(person.getName()).isPresent()) {
            errors.rejectValue("name", "", "ФИО должно быть уникальным");
        }
    }
}
