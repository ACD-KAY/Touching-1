package com.hhu.acd.touching;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.support.v7.widget.Toolbar;

public class Activity_12_2 extends AppCompatActivity {
    private Button btn1,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_12_2);


        /**标题栏设置*/
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        
         /** 返回前一个界面*/
        btn1=(Button)findViewById(R.id.B_before);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        /**添加好友*/
        btn2=(Button)findViewById(R.id.B_addfriends);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Activity_12_2.this, Activity_14.class);
                startActivity(it);
            }
        });

        /**删除好友*/
        btn3=(Button)findViewById(R.id.B_deletefriends);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Activity_12_2.this,Activity_12.class);
                startActivity(it);
            }
        });
        
        
    }
}
