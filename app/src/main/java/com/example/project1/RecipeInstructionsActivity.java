package com.example.project1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class RecipeInstructionsActivity extends AppCompatActivity {
    public static final String TAG = "RecipeInstructionsActi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_instructions);
        try {

            Toolbar myToolbar = findViewById(R.id.my_toolbar);
            setSupportActionBar(myToolbar);
            ActionBar ab = getSupportActionBar();
            ab.setDisplayHomeAsUpEnabled(true);
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