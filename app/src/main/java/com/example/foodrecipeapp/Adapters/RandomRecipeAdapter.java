package com.example.foodrecipeapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipeapp.Listeners.RecipeClickListener;
import com.example.foodrecipeapp.Models.Recipe;
import com.example.foodrecipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * The RandomRecipeAdapter class is a RecyclerView adapter that binds data to the ViewHolder for displaying a list of random recipes.
 * It inflates the layout for each item in the list and binds the corresponding data to the views in the ViewHolder.
 */

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder>{

    Context context;
    List<Recipe> recipes;
    RecipeClickListener listener;

    /**
     * Constructs a RandomRecipeAdapter with the given context, list of recipes, and recipe click listener.
     *
     * @param context  The context in which the adapter is created.
     * @param recipes  The list of recipes to be displayed.
     * @param listener The listener for handling recipe item clicks.
     */
    public RandomRecipeAdapter(Context context, List<Recipe> recipes, RecipeClickListener listener) {
        this.context = context;
        this.recipes = recipes;
        this.listener = listener;
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new RandomRecipeViewHolder that holds the inflated view for a random recipe item.
     */
    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipe, parent, false));
    }


    /**
     * Called by RecyclerView to display the data at the specified position.
     * Binds the data to the views in the ViewHolder.
     *
     * @param holder   The ViewHolder that should be updated to represent the contents of the item at the given position.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.textView_title.setText(recipes.get(position).title);
        holder.textView_title.setSelected(true);

        holder.textView_likes.setText(recipes.get(position).aggregateLikes + " Likes");
        holder.textView_servings.setText(recipes.get(position).servings + " Servings");
        holder.textView_time.setText(recipes.get(position).readyInMinutes + " Minutes");

        Picasso.get().load(recipes.get(position).image).into(holder.imageView_food);

        holder.random_list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecipeClick(String.valueOf(recipes.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}

/**
 * The RandomRecipeViewHolder class represents the ViewHolder for a random recipe item in the RecyclerView.
 * It holds references to the views in the item layout and provides a way to access and update them.
 */
class RandomRecipeViewHolder extends RecyclerView.ViewHolder{
    CardView random_list_container;
    TextView textView_title, textView_servings, textView_likes, textView_time;
    ImageView imageView_food;

    /**
     * Constructs a RandomRecipeViewHolder with the given itemView.
     *
     * @param itemView The view for a random recipe item.
     */
    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        random_list_container = itemView.findViewById(R.id.random_list_container);
        textView_title = itemView.findViewById(R.id.textView_title);
        textView_servings = itemView.findViewById(R.id.textView_servings);
        textView_likes = itemView.findViewById(R.id.textView_likes);
        textView_time = itemView.findViewById(R.id.textView_time);
        imageView_food = itemView.findViewById(R.id.imageView_food);
    }
}