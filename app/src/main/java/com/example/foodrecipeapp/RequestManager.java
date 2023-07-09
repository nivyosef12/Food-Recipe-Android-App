package com.example.foodrecipeapp;

import android.content.Context;

import com.example.foodrecipeapp.Listeners.RandomRecipeResponseListener;
import com.example.foodrecipeapp.Listeners.RecipeDetailsListener;
import com.example.foodrecipeapp.Models.RandomRecipeApiResponse;
import com.example.foodrecipeapp.Models.RecipeDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * The RequestManager class handles network requests using the Retrofit library.
 * It provides methods for fetching random recipes and recipe details.
 */
public class RequestManager {
    Context context;
    Retrofit retrofit;

    /**
     * Constructs a RequestManager with the given context.
     *
     * @param context The application context.
     */
    public RequestManager(Context context) {
        this.context = context;

        // initialize Retrofit with base URL and Gson converter factory
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Fetches random recipes based on the provided tags.
     *
     * @param listener The listener for random recipe response.
     * @param tags     The list of tags for filtering recipes.
     */
    public void getRandomRecipes(RandomRecipeResponseListener listener, List<String> tags){
        // create an instance of the CallRandomRecipes interface using Retrofit
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);

        // make an asynchronous network call to fetch random recipes
        Call<RandomRecipeApiResponse> call = callRandomRecipes.callRandomRecipe(
                context.getString(R.string.api_key),
                "10",
                tags);

        // process the response in onResponse and onFailure callbacks
        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());

            }

            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    /**
     * Fetches recipe details for the given recipe ID.
     *
     * @param listener The listener for recipe details response.
     * @param id       The ID of the recipe to fetch details for.
     */
    public void getRecipeDetails(RecipeDetailsListener listener, int id){
        // create an instance of the CallRecipeDetails interface using Retrofit
        CallRecipeDetails callRecipeDetails = retrofit.create(CallRecipeDetails.class);

        // make an asynchronous network call to fetch recipe details
        Call<RecipeDetails> call = callRecipeDetails.callRecipeDetails(
                id,
                context.getString(R.string.api_key));

        // process the response in onResponse and onFailure callbacks
        call.enqueue(new Callback<RecipeDetails>() {
            @Override
            public void onResponse(Call<RecipeDetails> call, Response<RecipeDetails> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetails> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    /**
     * Interface for the random recipes API endpoint.
     */
    private interface CallRandomRecipes{
        @GET("recipes/random")
        Call<RandomRecipeApiResponse> callRandomRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags")List<String> tags
        );
    }

    /**
     * Interface for the recipe details API endpoint.
     */
    private interface CallRecipeDetails{
        @GET("recipes/{id}/information")
        Call<RecipeDetails> callRecipeDetails(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }
}
