package com.codemeanslove.journalApp.controller;

import com.codemeanslove.journalApp.entity.Recipe;
import com.codemeanslove.journalApp.entity.User;
import com.codemeanslove.journalApp.service.RecipeService;
import com.codemeanslove.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
@CrossOrigin(origins = "http://localhost:5173")
public class PublicController {
    @Autowired
    UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        boolean ok = userService.saveNewUser(user);
        if(ok) return new ResponseEntity(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("/health-check")
    public String healthCheck(){
        return  "ok";
    }

    @Autowired
    RecipeService recipeService;

    @PostMapping("/recipes")
    public ResponseEntity<List<Recipe>> recipes(@RequestBody List<String> ingredients){
        System.out.println(ingredients);
        List<Recipe> recipes = recipeService.getRecipes(ingredients);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
//        return new ResponseEntity<>(null,HttpStatus.OK);
    }



}
