package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    public static final String TAG = "MainActivity";
    public static ArrayList<String> favorites = new ArrayList<>();

    ProgressDialog p;
    static Recipes recipes;
    LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.custom_toolbar));

        recyclerView = findViewById(R.id.recycler_view);

        // import the recipes
        recipes = new Recipes().fromJSONFile(getApplicationContext(), "christmas_recipes.json");

        getImages();
        try{
            loadFavorites("favorites.txt");
        } catch (Exception e){
            Log.e(TAG,"Failed to load favorites");
        }

        Log.i("new", "1");
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);




        final RecipeCardRecylcerAdapter adapter = new RecipeCardRecylcerAdapter(this,recipes.data);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.show_favorites:
                try{
                    Intent intent = new Intent(getApplicationContext(), FavoritesActivity.class);
                    startActivity(intent);
                } catch (Exception ex){
                    Log.e("favorites", ex.toString());
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private Thread[] getImages(){
        Thread[] threads = new Thread[recipes.data.size()];
        int size = recipes.data.size();
        for (int i =0; i<recipes.DATA_SIZE_CAP && i<size; i++){
            if (recipes.data.get(i).loadImage(getApplicationContext())){
                continue;
            }
            Thread T = getWebsite(i);
            threads[i] = T;
        }
        return threads;
    }


    private Thread getWebsite(int i) {
        String url = recipes.data.get(i).url;

        WebScraperRunnable r =  new WebScraperRunnable();
        r.setUrl(url);
        Thread thread = new Thread(r);
        thread.start();
        try {
            thread.join();
        } catch (Exception ex){
            Log.e("WEB", ex.toString());
        }

        recipes.data.get(i).setImage(r.bitmap);
        recipes.data.get(i).saveImage(getApplicationContext());

        recipes.data.get(i).image = null;

        return thread;
    }

    class WebScraperRunnable implements Runnable{
        Bitmap bitmap;
        String url;
        Recipes recipes;

        public void setUrl(String url){
            this.url = url;
        }

        @Override
        public void run() {
            final StringBuilder builder = new StringBuilder();
            try {
                Document doc = Jsoup.connect(url).get();
                Element __nextElement =  doc.getElementById("__next");
                Elements __main = __nextElement.getElementsByClass("js-site-main site-main body-background fluid-container");
                Elements div = __main.get(0).getElementsByClass("container post-header__container post-header__container--masthead-layout");
                Elements image= div.get(0).getElementsByTag("img");

                // Locate the src attribute
                String imgSrc = image.get(0).absUrl("src");
                // Download image from URL
                InputStream input = new java.net.URL(imgSrc).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);

                String title = doc.title();
            } catch (Exception e) {
                Log.e("WEB",e.toString());
            }
        }

    }
    public  void loadFavorites(String fileName)
    {
        final File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/folderName/" );

        if (!dir.exists())
        {
            if(!dir.mkdirs()){
                Log.e(TAG,"Failed to create the directories");
            }
        }
        final File myFile = new File(dir, fileName);
        Scanner s;
        try{
            s = new Scanner(myFile);
            ArrayList<String> list = new ArrayList<String>();
            while (s.hasNext()){
                list.add(s.next());
            }
            s.close();
        }catch (Exception e){
            Log.e(TAG,e.toString());
        }


    }

}




