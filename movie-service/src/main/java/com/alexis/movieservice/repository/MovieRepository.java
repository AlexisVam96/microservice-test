package com.alexis.movieservice.repository;

import com.alexis.movieservice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findByUserId(int userId);
}
