package com.alexis.userservice.controller;


import com.alexis.userservice.entity.User;
import com.alexis.userservice.models.Movie;
import com.alexis.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> users = userService.getAll();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id){
        User user = userService.getUserById(id);
        if(user == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user){
        User userNew = userService.save(user);
        return ResponseEntity.ok(userNew);
    }

    @GetMapping("/movie/{userId}")
    public ResponseEntity<List<Movie>> getMovies(@PathVariable("userId") int userId){
        User user = userService.getUserById(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }

        List<Movie> movies = userService.getMovies(user.getId());
        return ResponseEntity.ok(movies);
    }

    @PostMapping("/usermovie/{userId}")
    public ResponseEntity<Movie> saveMovie(@PathVariable("userId") int userId,@RequestBody Movie movie ){
        User user = userService.getUserById(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        Movie movieNew = userService.saveMovie(userId, movie);
        return  ResponseEntity.ok(movieNew);
    }

    @GetMapping("/getAll/{userId}")
    public ResponseEntity<Map<String,Object>> getAllMovies(@PathVariable("userId") int userId){
        Map<String, Object> result = userService.getUserAndMovies(userId);
        return ResponseEntity.ok(result);
    }


}
