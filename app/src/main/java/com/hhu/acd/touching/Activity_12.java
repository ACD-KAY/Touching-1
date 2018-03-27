package com.hhu.acd.touching;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;

public class Activity_12 extends AppCompatActivity {
    
     private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_12);
        
         /**标题栏设置*/
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");


        /** 返回到前一个界面"*/
        btn=(Button)findViewById(R.id.B_before);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
        
    }
}
