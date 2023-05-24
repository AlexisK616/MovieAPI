package com.example.demo.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private boolean hasInsurance;
    private List<Movie> favouriteMovies;

    // Constructor, getters, and setters

    // Constructor sin argumentos
    public Person() {
        this.favouriteMovies = new ArrayList<>();
    }

    // Constructor con argumentos
    public Person(long id, String firstName, String lastName, LocalDate birthdate, boolean hasInsurance, List<Movie> favouriteMovies) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.hasInsurance = hasInsurance;
        this.favouriteMovies = favouriteMovies;
    }

    // Agregar una película favorita
    public void addFavouriteMovie(Movie movie) {
        favouriteMovies.add(movie);
    }

    // Remover una película favorita
    public void removeFavouriteMovie(Movie movie) {
        favouriteMovies.remove(movie);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    public List<Movie> getFavouriteMovies() {
        return favouriteMovies;
    }

    public void setFavouriteMovies(List<Movie> favouriteMovies) {
        this.favouriteMovies = favouriteMovies;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", hasInsurance=" + hasInsurance +
                ", favouriteMovies=" + favouriteMovies +
                '}';
    }
}
