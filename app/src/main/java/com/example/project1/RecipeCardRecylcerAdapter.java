package com.example.project1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecipeCardRecylcerAdapter extends RecyclerView.Adapter<RecipeCardViewHolder> {

    List<Recipe> data;
    Context c;

    public RecipeCardRecylcerAdapter(Context c, List<Recipe> list){
        data = list;
        this.c = c;
    }

    @Override
    public RecipeCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.recipe_card,parent,false);
        RecipeCardViewHolder soupViewHolder = new RecipeCardViewHolder(v);
        return soupViewHolder;

    }

    @Override
    public void onBindViewHolder(final RecipeCardViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.name.setText(data.get(position).Name);
        //TODO populate image data
        holder.image.setImageBitmap(data.get(position).image);

        holder.view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(c, RecipeInstructionsActivity.class);

                    ArrayList<String> a1 = new ArrayList<String>(data.get(position).Ingredients);
                    intent.putStringArrayListExtra("Ingredients",  a1);
                    ArrayList<String> a2 = new ArrayList<String>(data.get(position).Method);
                    intent.putStringArrayListExtra("Instructions",  a2);
                    c.startActivity(intent);
                } catch (Exception ex) {
                    Log.e("1", ex.toString());
                }


            }


        });
    }



    @Override
    public int getItemCount() {
        return data.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
