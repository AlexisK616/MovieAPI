package com.example.demo.repository;
import com.example.demo.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAllByOrderByLastNameAscFirstNameAsc();

    Optional<Person> findById(long id);

    List<Person> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrderByLastNameAscFirstNameAsc(String firstName, String lastName);
}