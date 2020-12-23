package com.example.teamnovaapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecommendActivity extends AppCompatActivity {
    private static final String TAG= "RecommendActivity";


    int[] images = new int[] {R.drawable.chicken, R.drawable.jajangmyeon, R.drawable.pizza,
            R.drawable.hamburger, R.drawable.pork_cutlet,R.drawable.fish,R.drawable.sushi};

    String[] texts = new String[] {"치킨", "짜장면", "피자", "햄버거", "돈까스", "회", "스시"};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend);

        ImageView menu = (ImageView)findViewById(R.id.menu);
        TextView menuT = (TextView)findViewById(R.id.menuT);
        Button random = (Button)findViewById(R.id.random);

        random.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int imageId = (int)(Math.random() * images.length); //랜덤한 숫자를 imagesId 에 저장후
                menu.setBackgroundResource(images[imageId]);        //images[] 배열에 넣어 이미지를 imageView인 menu에 넣어준다.
                menuT.setText(texts[imageId]);
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
    }
}
