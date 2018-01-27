package com.hhu.acd.touching;


import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.stfalcon.multiimageview.MultiImageView;

public class mainactivity_3_message extends AppCompatActivity {


    private RecyclerView mRecyclerView;

    private MyAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;
    private PopupWindow mDropdown = null;
    LayoutInflater mInflater;
    ImageButton pop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity_3_messagelist);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        //((MultiImageView)findViewById(R.id.chat_item_test)).addImage(BitmapFactory.decodeResource(getResources(),R.mipmap.bussiness_man));
        initData();
        initView();
        pop = findViewById(R.id.addfriends);
        pop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                initiatePopupWindow();

            }
        });

    getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initData() {
      mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
      mAdapter = new MyAdapter(this,getData());
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mainactivity_3_message.this,"click " + position + " item", Toast.LENGTH_SHORT).show();
            }

            //@Override
            //public void onItemLongClick(View view, int position) {
                //Toast.makeText(MDRvActivity.this,"long click " + position + " item", Toast.LENGTH_SHORT).show();
            //}
        });


    }

    private ArrayList<message_data> getData() {
        ArrayList<message_data> data = new ArrayList<>();
        int url= R.drawable.bussiness_man;
        for(int i = 0; i < 15; i++) {
            data.add(new message_data(url," 李子铭","这只是测试",1998));
        }

        return data;
    }


    private PopupWindow initiatePopupWindow() {

        try {

            mInflater = (LayoutInflater) getApplicationContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = mInflater.inflate(R.layout.add_friends, null);

            //If you want to add any listeners to your textviews, these are two //textviews.
            final TextView itema = (TextView) layout.findViewById(R.id.add_friends);
            itema.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Intent i=new Intent(mainactivity_3_message.this,);
                    //startActivity(i);
                }
            });





            layout.measure(View.MeasureSpec.UNSPECIFIED,
                    View.MeasureSpec.UNSPECIFIED);
            mDropdown = new PopupWindow(layout, FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT,true);

            mDropdown.showAsDropDown(pop, 5, 40);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDropdown;

    }

    }
