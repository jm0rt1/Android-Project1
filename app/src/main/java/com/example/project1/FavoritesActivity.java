package com.example.project1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
    LinearLayoutManager layoutManager;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);


        try{
            setSupportActionBar(findViewById(R.id.custom_toolbar));
            ActionBar ab = getSupportActionBar();
            ab.setDisplayHomeAsUpEnabled(true);
            recyclerView = findViewById(R.id.recycler_view);




            Log.i("new", "1");
            layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            layoutManager.scrollToPosition(0);
            recyclerView.setLayoutManager(layoutManager);

            Recipe[] favorites = new Recipe[MainActivity.favorites.size()];
            int counter = 0;
            for(int i=0; i<MainActivity.recipes.data.size() && counter < MainActivity.favorites.size(); i++){
                if (MainActivity.favorites.contains(MainActivity.recipes.data.get(i).Name)){

                    favorites[counter]= MainActivity.recipes.data.get(i);
                    counter++;
                }
            }
            List<Recipe> favorites2 = Arrays.asList(favorites);

            final RecipeCardRecylcerAdapter adapter = new RecipeCardRecylcerAdapter(this,favorites2);


            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());


        }catch (Exception ex){
            Log.e("favorites", ex.toString());
        }



    }

}