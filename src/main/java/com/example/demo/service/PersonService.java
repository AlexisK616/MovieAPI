package com.example.demo.service;

import com.example.demo.models.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;


    public List<Person> getAllPersons() {
        return personRepository.findAllByOrderByLastNameAscFirstNameAsc();
    }

    public Optional<Person> getPersonById(long id) {
        return personRepository.findById(id);
    }

    public List<Person> searchPersonsByName(String name) {
        return personRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrderByLastNameAscFirstNameAsc(name, name);
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Person updatePerson(long id, Person updatedPerson) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            BeanUtils.copyProperties(updatedPerson, person, "id");
            return personRepository.save(person);
        }
        return null;
    }

    public void deletePerson(long id) {
        personRepository.deleteById(id);
    }
}