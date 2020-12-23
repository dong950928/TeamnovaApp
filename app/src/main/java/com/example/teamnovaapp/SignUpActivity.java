package com.example.teamnovaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        Button skip_btn = (Button)findViewById(R.id.skip);

        skip_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){  //사용자가 클릭한 버튼이 view에 들어간다.
                //구현할 내용
                Intent intent = new Intent(getApplicationContext(),RestaurantActivity.class );
                startActivity(intent);
                finish();
            }
        });


    }

}
