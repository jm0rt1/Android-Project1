package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String jsonFileString = Utils.getJSONFromAssets(getApplicationContext(), "recipes.json");
        //Log.i("data", jsonFileString);
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<Recipe>>() { }.getType();
        List<Recipe> recipes = gson.fromJson(jsonFileString, listUserType);
        for (int i = 0; i < recipes.size(); i++) {
            Log.i("data", "> Item " + i + "\n" + recipes.get(i));
        }
    }

}