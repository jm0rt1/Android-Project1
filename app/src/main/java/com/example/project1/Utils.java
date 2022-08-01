package com.example.project1;

import android.content.Context;
import android.util.Log;

import java.io.InputStream;

public class Utils {
    public static final String TAG = "Utils";
    static String getJSONFromAssets(Context context, String fileName){
        String jsonString;
        try{
            InputStream inputStream = context.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, "UTF-8");

        } catch (Exception ex){
            Log.e(TAG, ex.toString());
            return null;
        }
        return jsonString;
    }

}
