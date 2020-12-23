package com.example.teamnovaapp;

import android.graphics.Bitmap;

public class Person {
    int image;
    String name;
    String age;
    Bitmap bitmap;
    public Person(Bitmap bitmap, String name, String age){
        //this.image = image;
        this.name = name;
        this.age = age;
        this.bitmap = bitmap;
    }
//    public int getImage() {
//
//        return image;
//    }
//    public void setImage(int image) {
//
//        this.image = image;
//    }
    public Bitmap getBitmap() {
    return bitmap;
}
    public void setAge(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public String getName() {

        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }


}
