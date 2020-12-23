package com.example.teamnovaapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ReviewActivity extends AppCompatActivity {

    private Button btnPopupLoad;      //팝업창 띄우는 버튼
    private Button btnPopupClose;     //팝업창 닫기 버튼
    private PopupWindow popupWindow;  //팝업 윈도우
    private EditText txtData;         //팝업 레이아웃에 위치한 editText
    private Button btnData;           //팝업 레이아웃에 위치한 리스트 생성버튼
    private ListView listPopup;       //리스트뷰
    private TextView settxt;

    List list = new ArrayList<>();    //데이터를 저장할 리스트
    ArrayAdapter adapter;             //어뎁터

    //팝업창 위치설정을 위한 변수
    private int mWidthPixel = 0;
    private int mHeightPixel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);

        //윈도우 매니저, displayMetrics : 가로와 세로길이를 지정하기 위해
        WindowManager windowManager = getWindowManager();  //윈도우 매니저 객체
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        mWidthPixel = displayMetrics.widthPixels;
        mHeightPixel = displayMetrics.heightPixels;

        //리스트뷰 객체
        listPopup = (ListView) findViewById(R.id.listPopup);

        //리스트뷰와 리스트(데이터)를 연결하기 위해 사용되는 어댑터
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        //리스트뷰의 어댑터 지정
        listPopup.setAdapter(adapter);

        Button btnPopupLoad = (Button)findViewById(R.id.btnPopupLoad);
        btnPopupLoad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //팝업윈도우를 띄울 메소드 호출
                initiatePopupWindow();
            }
        });
    }
    private void initiatePopupWindow(){
        try{
            //팝업윈도우는 부분화면을 인플레이터 함
            LayoutInflater inflater = (LayoutInflater)ReviewActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //뷰그룹으로 형변환
            View layout = inflater.inflate(R.layout.layout_popup,(ViewGroup) findViewById(R.id.popupLayout));
            //focusable은 팝업윈도우를 실행시켰을 때 윈도우상의 아이템에 초점을 둘지 말지 정함
            popupWindow = new PopupWindow(layout, mWidthPixel-100, mHeightPixel-500, true);
            //팝업윈도우 위치, 숫자값을 임의로 준다면 center에서 숫자값만큼 이동
            popupWindow.showAtLocation(layout, Gravity.CENTER,0,0);

            //팝업윈도우에 위치한 버튼을 사용할땐 뷰.findViewById로 해줌
            btnPopupClose = (Button) layout.findViewById(R.id.btnPopupClose);  //팝업 닫기 버튼
            btnPopupClose.setOnClickListener(disListener);

            // 팝업윈도우에 위치한 editText, 리스트 생성 button
            txtData = (EditText) layout.findViewById(R.id.txtdata);
            btnData = (Button) layout.findViewById(R.id.btnData);

            btnData.setOnClickListener(addListener); //팝업 추가 버튼
        }
        catch (Exception e){
            e.printStackTrace();
        }
        settxt = (TextView)findViewById(R.id.tv_info);


        //목록 아이템 클릭시 화면넘어감   todo:데이터도 넘겨야함 여기서
        listPopup.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position,long id){
                Intent intent = new Intent(getApplicationContext(), ReviewClicked.class);

                startActivity(intent);
            }
        });

    }
    // popupWindow dismiss 리스너  팝업닫기
    private View.OnClickListener disListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            popupWindow.dismiss();
        }
    };


    // btnData, 리스트 생성버튼을 눌렀을 시 적용될 리스너   팝업추가
    private View.OnClickListener addListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // editText data를 arrayList에 add함
            list.add(txtData.getText().toString());
            //리스트뷰 동기화
            adapter.notifyDataSetChanged();

        }
    };

}