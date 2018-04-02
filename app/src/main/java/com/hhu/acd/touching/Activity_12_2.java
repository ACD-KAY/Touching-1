package com.hhu.acd.touching;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

public class Activity_12_2 extends AppCompatActivity {
    private ImageButton btn1;
    private Button btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_12_2);


        /**标题栏设置*/
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        
         /** 返回前一个界面*/
        btn1=(ImageButton)findViewById(R.id.B_before);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


        /**添加好友*/
        btn2=(Button)findViewById(R.id.B_addfriends);


        /**删除好友*/
        btn3=(Button)findViewById(R.id.B_deletefriends);

    }
}
