package com.hhu.acd.touching;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Size;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.graphics.Color;

public class Activity_14 extends AppCompatActivity {

    private TextView subject;
    private ImageButton ib1;
    private ImageButton ib2;
    int myflag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_14);
        ib1= (ImageButton) findViewById(R.id.ib);
        ib2= (ImageButton) findViewById(R.id.ib2);
        subject=(TextView) findViewById(R.id.tv);
        subject.setTextColor(Color.BLUE);
    }
}
