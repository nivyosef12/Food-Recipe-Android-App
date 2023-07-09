package com.example.foodrecipeapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipeapp.Models.ExtendedIngredient;
import com.example.foodrecipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * The IngredientsAdapter class is a RecyclerView adapter that binds data to the ViewHolder for displaying a list of ingredients.
 * It inflates the layout for each item in the list and binds the corresponding data to the views in the ViewHolder.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder>{

    Context context;
    List<ExtendedIngredient> ingredients;

    /**
     * Constructs an IngredientsAdapter with the given context and list of ingredients.
     *
     * @param context     The context in which the adapter is created.
     * @param ingredients The list of ingredients to be displayed.
     */
    public IngredientsAdapter(Context context, List<ExtendedIngredient> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new IngredientsViewHolder that holds the inflated view for an ingredient item.
     */
    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_meal_ingredients, parent, false));
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * Binds the data to the views in the ViewHolder.
     *
     * @param holder   The ViewHolder that should be updated to represent the contents of the item at the given position.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        holder.textView_ingredients_name.setText(ingredients.get(position).name);
        holder.textView_ingredients_name.setSelected(true);

        holder.textView_ingredients_quantity.setText(ingredients.get(position).original);
        holder.textView_ingredients_quantity.setSelected(true);

        Picasso.get().load("http://spoonacular.com/cdn/ingredients_100x100/" + ingredients.get(position).image).into(holder.imageView_ingredients);

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }
}


/**
 * The IngredientsViewHolder class represents the ViewHolder for an ingredient item in the RecyclerView.
 * It holds references to the views in the item layout and provides a way to access and update them.
 */

class IngredientsViewHolder extends RecyclerView.ViewHolder{
    TextView textView_ingredients_quantity, textView_ingredients_name;
    ImageView imageView_ingredients;


    /**
     * Constructs an IngredientsViewHolder with the given itemView.
     *
     * @param itemView The view for an ingredient item.
     */
    public IngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_ingredients_quantity = itemView.findViewById(R.id.textView_ingredients_quantity);
        textView_ingredients_name = itemView.findViewById(R.id.textView_ingredients_name);
        imageView_ingredients = itemView.findViewById(R.id.imageView_ingredients);


    }
}
