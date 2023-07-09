package com.example.foodrecipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodrecipeapp.Adapters.IngredientsAdapter;
import com.example.foodrecipeapp.Listeners.RecipeDetailsListener;
import com.example.foodrecipeapp.Models.RecipeDetails;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * The RecipeDetailsActivity class displays the details of a recipe.
 * It retrieves and displays information such as the recipe name, source, summary, image, and ingredients.
 */
public class RecipeDetailsActivity extends AppCompatActivity {
    int id;
    TextView textView_meal_name, textView_meal_source, textView_meal_summary;
    ImageView imageView_meal_image;
    RecyclerView recycler_meal_ingredients;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        // find views by their IDs
        findViews();

        // get the recipe ID passed from the previous activity
        id = Integer.parseInt(getIntent().getStringExtra("id"));

        // initialize the RequestManager
        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener, id);

        // show a progress dialog while the details are being loaded
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading Details...");
        dialog.show();

    }

    /**
     * Find views by their IDs.
     */
    private void findViews() {
        textView_meal_name = findViewById(R.id.textView_meal_name);
        textView_meal_source = findViewById(R.id.textView_meal_source);
        textView_meal_summary = findViewById(R.id.textView_meal_summary);
        imageView_meal_image = findViewById(R.id.imageView_meal_image);
        recycler_meal_ingredients = findViewById(R.id.recycler_meal_ingredients);
    }


    /**
     * Listener for recipe details response.
     */
    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetails response, String message) {
            dialog.dismiss();

            // display recipe details in corresponding views
            textView_meal_name.setText(response.title);
            textView_meal_source.setText(response.sourceName);
            textView_meal_summary.setText(response.summary);
            Picasso.get().load(response.image).into(imageView_meal_image);

            // set up RecyclerView for displaying ingredients
            recycler_meal_ingredients.setHasFixedSize(true);
            recycler_meal_ingredients.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));

            // create and set the adapter for ingredients
            ingredientsAdapter = new IngredientsAdapter(RecipeDetailsActivity.this, response.extendedIngredients);
            recycler_meal_ingredients.setAdapter(ingredientsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}