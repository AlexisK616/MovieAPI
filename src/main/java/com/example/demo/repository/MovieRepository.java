package com.example.demo.repository;

import com.example.demo.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitleIn(List<String> movieTitles);
}
