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

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder>{

    Context context;
    List<ExtendedIngredient> ingredients;

    public IngredientsAdapter(Context context, List<ExtendedIngredient> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_meal_ingredients, parent, false));
    }

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

class IngredientsViewHolder extends RecyclerView.ViewHolder{
    TextView textView_ingredients_quantity, textView_ingredients_name;
    ImageView imageView_ingredients;

    public IngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_ingredients_quantity = itemView.findViewById(R.id.textView_ingredients_quantity);
        textView_ingredients_name = itemView.findViewById(R.id.textView_ingredients_name);
        imageView_ingredients = itemView.findViewById(R.id.imageView_ingredients);


    }
}
