package com.alexis.userservice.service;

import com.alexis.userservice.config.RestTemplateConfig;
import com.alexis.userservice.entity.User;
import com.alexis.userservice.feignclients.MovieFeignClient;
import com.alexis.userservice.models.Movie;
import com.alexis.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MovieFeignClient movieFeignClient;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user){
        User userNew = userRepository.save(user);
        return userNew;
    }

    public List<Movie> getMovies(int userId){
        List<Movie> movies = restTemplate.getForObject("http://movie-service/movie/byUser/" + userId, List.class);
        return movies;
    }

    public Movie saveMovie(int userId, Movie movie){
        movie.setUserId(userId);
        Movie movieNew = movieFeignClient.save(movie);
        return movieNew;
    }

    public Map<String, Object> getUserAndMovies(int userId){
        Map<String, Object> result = new HashMap<>();
        User user= userRepository.findById(userId).orElse(null);

        if(user ==null ){
            result.put("mensaje","no existe el usuario");
            return result;
        }

        result.put("user",user);
        List<Movie> movies = movieFeignClient.getMovies(userId);
        if(movies.isEmpty()){
            result.put("movies", "ese user no tiene movies");
        }else{
            result.put("movies", movies);
        }
        return result;
    }

}
