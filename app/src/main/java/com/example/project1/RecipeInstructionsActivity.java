package com.example.project1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecipeInstructionsActivity extends AppCompatActivity {
    public static final String TAG = "RecipeInstructionsActi";
    ArrayList<String> ingredients;
    ArrayList<String> instructions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_instructions);
        try {
            Intent intent = getIntent();
            ingredients =  intent.getStringArrayListExtra("Ingredients");
            instructions =  intent.getStringArrayListExtra("Instructions");

            Toolbar myToolbar = findViewById(R.id.my_toolbar);
            setSupportActionBar(myToolbar);
            ActionBar ab = getSupportActionBar();
            ab.setDisplayHomeAsUpEnabled(true);


            ArrayAdapter ingredient_adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.list_view_item, ingredients);
            ListView listView =(ListView) findViewById(R.id.ingredient_list);
            listView.setAdapter(ingredient_adapter);


            ArrayAdapter instruction_adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.list_view_item, instructions);
            ListView listView2 =(ListView) findViewById(R.id.instructions_list);
            listView2.setAdapter(instruction_adapter);
        }catch (Exception e) {
            Log.e(TAG,e.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recipe_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.like:
                Toast toast = Toast.makeText(getApplicationContext(),"Like Clicked", Toast.LENGTH_SHORT);
                toast.show();
                return true;
            case R.id.show_favorites:
                toast = Toast.makeText(getApplicationContext(),"two Clicked", Toast.LENGTH_SHORT);
                toast.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}