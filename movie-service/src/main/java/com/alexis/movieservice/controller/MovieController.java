package com.alexis.movieservice.controller;

import com.alexis.movieservice.entity.Movie;
import com.alexis.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAll() {
        List<Movie> movies = movieService.getAll();
        if (movies.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(movies);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable("id") int id) {
        Movie movie = movieService.getById(id);
        if (movie == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(movie);
    }

    @PostMapping
    public ResponseEntity<Movie> save(@RequestBody Movie movie){
        Movie movieNew = movieService.save(movie);
        return ResponseEntity.ok(movieNew);
    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<Movie>> getByUserId(@PathVariable("userId") int userId){
        List<Movie> movies = movieService.getUserById(userId);
        return ResponseEntity.ok(movies);
    }
}






