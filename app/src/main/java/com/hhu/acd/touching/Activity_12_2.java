package com.hhu.acd.touching;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;


public class Activity_12_2 extends AppCompatActivity {
    private Button btn1,btn2,btn3,btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_12);


        /**取消关注*/
        btn1=(Button)findViewById(R.id.B_qg);
        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent it = new Intent(PersonInfor2Activity.this, MainActivity.class);
                //startActivity(it);
            }
        });

        /**添加好友*/
        btn2=(Button)findViewById(R.id.B_add);
        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent it = new Intent(Activity_12_2.this, MainActivity.class);
                //startActivity(it);
            }
        });

        /**发送消息*/
        btn3=(Button)findViewById(R.id.B_send);
        btn3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Activity_12_2.this,Activity_13.class);
                //startActivity(it);
            }
        });

        /**关注*/
        btn4=(Button)findViewById(R.id.B_g);
        btn4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent it = new Intent(PersonInfor2Activity.this, MainActivity.class);
                //startActivity(it);
            }
        });
    }
}
