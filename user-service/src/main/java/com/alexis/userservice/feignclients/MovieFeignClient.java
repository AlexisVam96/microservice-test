package com.alexis.userservice.feignclients;

import com.alexis.userservice.models.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "movie-service", url = "http://localhost:8082")
@RequestMapping("/movie")
public interface  MovieFeignClient {

    @PostMapping()
    public Movie save(@RequestBody Movie movie);

    @GetMapping("/byUser/{userId}")
    public List<Movie> getMovies(@PathVariable("userId") int userId);

}
