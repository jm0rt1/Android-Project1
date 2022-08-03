package com.example.project1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.InputStream;

//public class Content extends AsyncTask<Void, Void, Void> {
////
////    @Override
////    protected void onPreExecute() {
////        super.onPreExecute();
////    }
////
////    @Override
////    protected Void doInBackground(Void... voids) {
////        try {
////            //Connect to the website
////            //Document document = Jsoup.connect(url).get();
////
////            //Get the logo source of the website
////            Element img = document.select("img").first();
////            // Locate the src attribute
////            String imgSrc = img.absUrl("src");
////            // Download image from URL
////            InputStream input = new java.net.URL(imgSrc).openStream();
////            // Decode Bitmap
////            Bitmap bitmap = BitmapFactory.decodeStream(input);
////
////            //Get the title of the website
////            title = document.title();
////
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        return null;
////        return null;
////    }
////
////    @Override
////    protected void onPostExecute(Void aVoid) {
////        super.onPostExecute(aVoid);
////    }
//}