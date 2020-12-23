package com.example.teamnovaapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Review extends AppCompatActivity {


    private Context context;
    private EditText textname,textmemo;
    private ImageView imageview;
    private String name;
    private String memo;
    private int image;

//    public Review(@NonNull Context context,final int positon, Person person) {
//        super(context);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        setContentView(R.layout.customdialog);
//
//        name = person.getName();
//        memo = person.getAge();
//        image = person.getBitmap();
//    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_activty);

        Intent intent = getIntent();

        textname = findViewById(R.id.text_name);
        textname.setText(intent.getStringExtra("name"));

        textmemo = findViewById(R.id.text_memo);
        textmemo.setText(intent.getStringExtra("memo"));

//        Bitmap bm = (Bitmap) intent.getExtras().get("bm");
//        imageview.setImageBitmap(bm);


    }
}
