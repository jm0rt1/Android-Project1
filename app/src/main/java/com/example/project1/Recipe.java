package com.example.project1;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class Recipe implements Parcelable {
    String Name;
    String url;
    String Description;
    String Author;
    List<String> Ingredients;
    List<String> Method;
    Bitmap image;

    protected Recipe(Parcel in) {
        Name = in.readString();
        url = in.readString();
        Description = in.readString();
        Author = in.readString();
        Ingredients = in.createStringArrayList();
        Method = in.createStringArrayList();
        //image = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public void setImage(Bitmap image) {
        this.image = image;
    }

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
    public boolean saveImage(Context context) {

        if (image == null){
            return false;
        }
        ContextWrapper cw = new ContextWrapper(context);

        File directory = cw.getDir("images", Context.MODE_PRIVATE);

        File myPath=new File(directory,Name+".png");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myPath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            image.compress(Bitmap.CompressFormat.PNG, 100, fos);
            return true;
        } catch (Exception e) {
            Log.e("Image Save", e.toString());
        } finally {
            try {
                fos.close();

            } catch (Exception e) {
                Log.e("Image Save", e.toString());
            }
            return false;

        }
    }
    public boolean loadImage(Context context) {

        ContextWrapper cw = new ContextWrapper(context);

        File directory = cw.getDir("images", Context.MODE_PRIVATE);

        File myPath=new File(directory,Name+".png");

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(myPath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            Bitmap bitmap = BitmapFactory.decodeStream(fis);
            this.image = bitmap;
        } catch (Exception e) {
            Log.e("Image Save", e.toString());
            return false;
        } finally {
            try {
                fis.close();

            } catch (Exception e) {
                Log.e("Image Save", e.toString());
            }
        }
        return true;

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(url);
        dest.writeString(Description);
        dest.writeString(Author);
        dest.writeStringList(Ingredients);
        dest.writeStringList(Method);
        //dest.writeParcelable(image, flags);

    }
}


