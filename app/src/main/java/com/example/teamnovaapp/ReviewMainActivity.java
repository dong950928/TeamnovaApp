package com.example.teamnovaapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ReviewMainActivity extends AppCompatActivity {

    private final int GET_GALLERY_IMAGE= 200;
    private ImageView imageview;
    RecyclerView rv;
    com.example.teamnovaapp.ListAdapter adapter;
    ItemTouchHelper helper;


    Button btnAdd;
    EditText textname;
    EditText textmemo;

    String name;
    String memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_main);

        imageview = (ImageView)findViewById(R.id.imageView);
        imageview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);

            }
        });





        rv = findViewById(R.id.rv);
        btnAdd = (Button)findViewById(R.id.btnAdd);

        textname = (EditText)findViewById(R.id.text_name);
        textmemo = (EditText)findViewById(R.id.text_memo);

        //RecyclerView의 레이아웃 방식을 지정

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);

        //RecyclerView의 Adapter 세팅
        adapter = new com.example.teamnovaapp.ListAdapter(this);
        rv.setAdapter(adapter);

        //ItemTouchHelper 생성
        helper = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));

        //RecyclerView에 ItemTouchHelper 붙이기
        helper.attachToRecyclerView(rv);

        //Adapter에 데이터 추가
//        Person person1 = new Person(R.drawable.image1,"파이","ㅁㅁ");
//
//        adapter.addItem(person1);

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                name = textname.getText().toString();
                memo = textmemo.getText().toString();

                Bitmap bitmap = ((BitmapDrawable)imageview.getDrawable()).getBitmap();
                textname.setText("");
                textmemo.setText("");


                Person person = new Person(bitmap,name,memo);
                adapter.addItem(person);

                Intent intent = new Intent(getApplicationContext(), Review.class);

               intent.putExtra("name",name);
               intent.putExtra("memo",memo);

               intent.putExtra("image",bitmap);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            //갤러리 사진 저장
            Uri uri = data.getData();
            //사진 비트맵으로 변환
            try{
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imageview.setImageBitmap(bm);

            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
    private void setUpRecyclerView(){
        rv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                helper.onDraw(c,parent, state);
            }
        });
    }
}