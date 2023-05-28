package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.models.Movie;
import com.example.demo.models.Person;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MovieRepository movieRepository;


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
        List<String> titles = person.getFavouriteMovies().stream().map(Movie::getTitle).collect(Collectors.toList());
        List<Movie> existingMovies = movieRepository.findByTitleIn(titles);

        if (!existingMovies.isEmpty()) {
            person.setFavouriteMovies(existingMovies);
        }

        Person savedPerson = personRepository.save(person);

        return savedPerson;
    }




    public Person updatePerson(long id, Person updatedPerson) {
        Optional<Person> optionalPerson = Optional.ofNullable(personRepository.findById(id).orElseThrow(() -> new NotFoundException("Persona no encontrada con id: " + id)));
        Person person = optionalPerson.get();
        BeanUtils.copyProperties(updatedPerson, person, "id");
        return personRepository.save(person);
    }

    public void deletePerson(long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);

        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            person.getFavouriteMovies().clear();
            personRepository.save(person);
            personRepository.deleteById(id);
        } else {
           throw new NotFoundException("person not found");
        }
    }
}