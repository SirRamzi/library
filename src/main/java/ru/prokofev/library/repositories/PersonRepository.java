package ru.prokofev.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.prokofev.library.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
