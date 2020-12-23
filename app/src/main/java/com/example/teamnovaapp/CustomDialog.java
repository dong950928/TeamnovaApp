package com.example.teamnovaapp;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CustomDialog extends Dialog{

    private com.example.teamnovaapp.OnDialogListener listener;
    private Context context;
    private Button mod_bt;
    private EditText mod_name, mod_age;
    private String name;
    private String age;
    private ImageView imageview;
    Bitmap image;
    public CustomDialog(Context context,final int position, Person person){
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.customdialog);

        name = person.getName();
        age = person.getAge();
        image = person.getBitmap();

        imageview = findViewById(R.id.imageView);
        imageview.setImageBitmap(image);

        //이름, 나이 EditText에 값 채우기
        mod_name = findViewById(R.id.mod_name);
        mod_name.setText(name);

        mod_age = findViewById(R.id.mod_age);
        mod_age.setText(String.valueOf(age));

        mod_bt = findViewById(R.id.mod_bt);




        mod_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){

                    //EditText의 수정된 값 가져오기
                    String name = mod_name.getText().toString();
                    String age = mod_age.getText().toString();

                    //int age = Integer.parseInt(mod_age.getText().toString());

                    Person person = new Person(image,name, age);

                    //Listener를 통해서 person객체 전달
                    listener.onFinish(position, person);
                    //다이얼로그 종료
                    dismiss();
                }
            }
        });
    } public void setDialogListener(com.example.teamnovaapp.OnDialogListener listener){
        this.listener = listener;
    }
}


