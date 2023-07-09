package com.example.foodrecipeapp.Listeners;

import com.example.foodrecipeapp.Models.RecipeDetails;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetails response, String message);
    void didError(String message);
}
