package com.alexis.movieservice.service;

import com.alexis.movieservice.entity.Movie;
import com.alexis.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAll(){
        return movieRepository.findAll();
    }

    public Movie getById(int id){
        return movieRepository.findById(id).orElse(null);
    }

    public Movie save(Movie movie){
        Movie movieNew = movieRepository.save(movie);
        return movieNew;
    }

    public List<Movie> getUserById(int userId){
        return movieRepository.findByUserId(userId);
    }
}
