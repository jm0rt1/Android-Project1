package com.example.project1;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;



//data set https://frosch.cosy.sbg.ac.at/datasets/json/recipes/-/blob/main/recipes.json
public class Recipes {
    List<Recipe> data;

    public Recipes() {
    }
    public Recipes fromJSONFile(Context c, String file_name) {
        String jsonFileString = Utils.getJSONFromAssets(c, "recipes.json");
        //Log.i("data", jsonFileString);
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<Recipe>>() {
        }.getType();
        data = gson.fromJson(jsonFileString, listUserType);
        for (int i = 0; i < data.size(); i++) {
            Log.i("data", "> Item " + i + "\n" + data.get(i));
        }
        return this;
    }

    public String[] getNames(){
        String[] names= new String[data.size()];
        for (int i=0; i<this.data.size(); i++){
            String name = this.data.get(i).Name;
            names[i] = name;
        }
        return names;
    }

    public String[] getUrls(){
        String[] urls= new String[data.size()];
        for (int i=0; i<this.data.size(); i++){
            String name = this.data.get(i).url;
            urls[i] = name;
        }
        return urls;
    }
}
