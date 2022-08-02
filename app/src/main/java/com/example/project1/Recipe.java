package com.example.project1;

import java.util.List;

public class Recipe {
    String Name;
    String url;
    String Description;
    String Author;
    List<String> Ingredients;
    List<String> Method;


    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + Name + '\n' +
                ", url=" + url +
                ", Description=" + Description + '\n' +
                ", Author=" + Author + '\n' +
                ", Ingredients=" + Ingredients + '\n' +
                ", Method=" + Method + '\n' +
                '}';
    }
}


