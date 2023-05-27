package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
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
        return Optional.ofNullable(personRepository.findById(id).orElseThrow(() -> new NotFoundException("No se encontró la persona con el ID: " + id)));

    }

    public List<Person> searchPersonsByName(String name) {
        List<Person> personList = personRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrderByLastNameAscFirstNameAsc(name, name);
        if(personList.isEmpty()){
            throw new NotFoundException("No se encontró la persona con el nombre: " + name);
        }
        return personList;
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Person updatePerson(long id, Person updatedPerson) {
        Optional<Person> optionalPerson = Optional.ofNullable(personRepository.findById(id).orElseThrow(() -> new NotFoundException("Persona no encontrada con id: " + id)));
        Person person = optionalPerson.get();
        BeanUtils.copyProperties(updatedPerson, person, "id");
        return personRepository.save(person);
    }

    public void deletePerson(long id) {
        personRepository.deleteById(id);
    }
}