package com.example.project1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

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
                Toast toast = Toast.makeText(c.getApplicationContext(),"clicked "+data.get(position).Name, Toast.LENGTH_SHORT);
                toast.show();

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
