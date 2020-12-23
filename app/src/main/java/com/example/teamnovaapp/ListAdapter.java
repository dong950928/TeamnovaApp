package com.example.teamnovaapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> implements ItemTouchHelperListener, OnDialogListener{

    ArrayList<Person> items = new ArrayList<>();
    Context context;
    public ListAdapter(Context context) {


        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater를 이용해서 원하는 레이아웃을 띄워줌
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        //ItemViewHolder가 생성되고 넣어야할 코드들을 넣어준다.
        holder.onBind(items.get(position));
    }

    @Override public int getItemCount() {
        return items.size();
    }

    public void addItem(Person person){
        items.add(person);
    }


    @Override
    public boolean onItemMove(int from_position, int to_position) {

        //이동할 객체 저장
        Person person = items.get(from_position);

        //이동할 객체 삭제
        items.remove(from_position);

        //이동하고 싶은 position에 추가
        items.add(to_position,person);

        //Adapter에 데이터 이동알림
        notifyItemMoved(from_position,to_position);

        return true;
    }

    @Override
    public void onItemSwipe(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    //왼쪽 버튼 누르면 수정할 다이얼로그 띄우기
    @Override
    public void onLeftClick(int position, RecyclerView.ViewHolder viewHolder) {

        //수정 버튼 클릭시 다이얼로그 생성
        CustomDialog dialog = new CustomDialog(context, position, items.get(position));

        //화면 사이즈 구하기
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //다이얼로그 사이즈 세팅
        WindowManager.LayoutParams wm = dialog.getWindow().getAttributes();
        wm.copyFrom(dialog.getWindow().getAttributes());
        wm.width = (int) (width * 1);
        wm.height = (int) (height * 0.8);

        //다이얼로그 Listener 세팅
        dialog.setDialogListener(this);

        //다이얼로그 띄우기
        dialog.show();
        }



        //오른쪽 버튼 누르면 아이템 삭제
    @Override
    public void onRightClick(int position, RecyclerView.ViewHolder viewHolder) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onFinish(int position, Person person) {
        items.set(position,person);
        notifyItemChanged(position);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView list_name,list_age; ImageView list_image;
        public ItemViewHolder(View itemView) {
            super(itemView);
            list_name = itemView.findViewById(R.id.list_name);
            list_age = itemView.findViewById(R.id.list_age);
            list_image = itemView.findViewById(R.id.list_image);

            //아이템 클릭시
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){

                    //자신의 위치를 확인하는방법
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Intent intent = new Intent(v.getContext(), Review.class);

                        //닉네임과 리뷰 넘겨주기
                        intent.putExtra("name",list_name.getText());
                        intent.putExtra("memo",list_age.getText());

//                        BitmapDrawable d = (BitmapDrawable)list_image.getDrawable();
//                        Bitmap b = d.getBitmap();
//                        list_image.setImageBitmap(b);
//                        intent.putExtra("bm",(Bitmap)b);

                        v.getContext().startActivity(intent);
                    }

                }
            });
        }

        public void onBind(Person person) {
            list_name.setText(person.getName());
            list_age.setText(String.valueOf(person.getAge()));
            list_image.setImageBitmap(person.getBitmap());
        }
    }
}
