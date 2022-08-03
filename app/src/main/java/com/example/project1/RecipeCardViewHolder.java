package com.example.project1;

import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RecipeCardViewHolder extends RecyclerView.ViewHolder {

    TextView name;
    ImageView image;
    View view;

    public RecipeCardViewHolder(View view)
    {
        super(view);
        name = view.findViewById(R.id.name_recipe_card);
        image = view.findViewById(R.id.image_recipe_card);
        this.view = view;
    }
}
