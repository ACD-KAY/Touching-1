package com.hhu.acd.touching;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Activity_04 extends AppCompatActivity {



    private Button btn;

    private TextView textview_title;
    private TextView hello1;
    private ImageButton img_shrink;
    int myflag = 0;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_04);
        img_shrink= (ImageButton) findViewById(R.id.ib);
        textview_title=(TextView) findViewById(R.id.tv);
        hello1.setTextColor(Color.BLUE);
    }*/

    /**跳转到消息列表界面*/
    /*btn =(Button)findViewById(R.id.message);
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent it = new Intent(Activity_04.this, Activity_03.class);
            startActivity(it);
        }
    });*/
}
