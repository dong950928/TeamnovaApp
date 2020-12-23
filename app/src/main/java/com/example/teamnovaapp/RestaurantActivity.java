package com.example.teamnovaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class RestaurantActivity  extends AppCompatActivity {
    private static final String TAG= "RestaurantActivity";

    SharedPreferences sharedpreferences;
    EditText save_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant);

        Button recommend_btn = (Button)findViewById(R.id.recommend);
        Button review_btn = (Button)findViewById(R.id.review);
        Button top_btn = (Button)findViewById(R.id.top);
        Button setting_btn = (Button)findViewById(R.id.setting);
        Button review_main_btn = (Button)findViewById(R.id.review_main);

        save_edit = (EditText)findViewById(R.id.save_edit);

        sharedpreferences = getSharedPreferences("sp", MODE_PRIVATE);
        String save = sharedpreferences.getString("save","");
        save_edit.setText(save);

        review_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){  //사용자가 클릭한 버튼이 view에 들어간다.
                //구현할 내용
                Intent intent = new Intent(getApplicationContext(),ReviewActivity.class );
                startActivity(intent);
            }
        });

        recommend_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){  //사용자가 클릭한 버튼이 view에 들어간다.
                //구현할 내용
                Intent intent = new Intent(getApplicationContext(),RecommendActivity.class );
                startActivity(intent);
            }
        });
        top_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), TopActivity.class);
                startActivity(intent);
            }
        });
        review_main_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), ReviewMainActivity.class);
                startActivity(intent);
            }
        });

        setting_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(intent);
            }
        });

        ImageButton food =(ImageButton)findViewById(R.id.food);
        food.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //실행되는 View를 통해 uri 홈페이지로 이동한다
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://map.naver.com/v5/search/%EC%A3%BC%EB%B3%80%EB%A7%9B%EC%A7%91?c=14134395.6128191,4507229.5226708,14,0,0,0,dh"));
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        save(save_edit.getText().toString());
    }

    public void save(String s){
        sharedpreferences = getSharedPreferences("sp",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("save",s);  //save변수 안에 문자열 저장
        editor.commit();             //커밋을 해야 확정적으로 저장을한다고함

    }



}
