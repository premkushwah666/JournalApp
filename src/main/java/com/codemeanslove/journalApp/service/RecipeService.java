package com.codemeanslove.journalApp.service;

import com.codemeanslove.journalApp.entity.Recipe;
import com.codemeanslove.journalApp.entity.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class RecipeService {
    private static final String API = "7234bcfb94e0468daccd4a15c6f744f5";
    private static final String url = "https://api.spoonacular.com/recipes/findByIngredients";

    @Autowired
    private RestTemplate restTemplate;

    public List<Recipe> getRecipes(List<String> ingredients){

        // Build URL with Query Params
        String urlWithParams = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("ingredients",ingredients)
                .queryParam("number", 20)///////////////esko change krna h ** no of Recipes////////////////
                .queryParam("apiKey", API)
//                .queryParam("ranking",1)//having some problem
                .toUriString();

        ResponseEntity<List<Recipe>> response = restTemplate.exchange(urlWithParams, HttpMethod.GET,null, new ParameterizedTypeReference<List<Recipe>>() {});
        return response.getBody();
    }

//    public List<Recipe> getRecipesByIngredients(String ingredients, int number) {
//        String url = "https://api.spoonacular.com/recipes/findByIngredients?ingredients=" + ingredients +
//                "&number=" + number + "&apiKey=" + apiKey;
//
//        ResponseEntity<List<Recipe>> response = restTemplate.exchange(
//                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Recipe>>() {}
//        );

//        return response.getBody();
//    }
}

//////////////before query Param i have done this for ingredients

//        StringBuffer sb = new StringBuffer();
//        for(String s:ingredients)
//            sb.append(s+",");
//        .replace("{ingredients}",sb.toString())
