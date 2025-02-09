package com.codemeanslove.journalApp.controller;

import com.codemeanslove.journalApp.entity.Recipe;
import com.codemeanslove.journalApp.entity.User;
import com.codemeanslove.journalApp.repository.UserRepository;
import com.codemeanslove.journalApp.service.RecipeService;
import com.codemeanslove.journalApp.service.UserService;
import com.codemeanslove.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

//    @GetMapping //ek user sare users ko nhi dekh sakta h
//    public List<User> getAllUsers(){
//        return userService.getAll();
//    }

    //and update and delete pr user login ho jab hi chalana chahiye
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userindb = userService.findByUserName(userName);
        //user kabi null hoga hi nhi kyoki vo yha tak pahucha h
            userindb.setUserName(user.getUserName());
            userindb.setPassword(user.getPassword());
            userService.saveNewUser(userindb);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        userRepository.deleteByUserName(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Autowired
    WeatherService weatherService;

    @GetMapping("/{city}")
    public ResponseEntity<?> greeting(@PathVariable String city){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        String greet = "Dear " +userName+"\ntemperature feels like, "+ (weatherService.getWeather(city).main.getTemp()-273)+"\n" +
                weatherService.getWeather(city).getWeather().get(0).description;
        return new ResponseEntity<>(greet,HttpStatus.OK);
    }



}//closing of class

