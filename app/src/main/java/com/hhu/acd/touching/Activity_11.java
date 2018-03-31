package com.hhu.acd.touching;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_11 extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private ImageButton button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_11);

        /**标题栏设置*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        editText=(EditText)findViewById(R.id.edittext);
        button=(Button)findViewById(R.id.search_button);

        //添加点击事件
        button.setOnClickListener(new MyonclickListener());

        button2=(ImageButton)findViewById(R.id.B_before);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Activity_11.this, Activity_03.class);
                startActivity(it);
                finish();
            }
        });

    }

    private class MyonclickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            //获取editText控件的数据
            String my_string = editText.getText().toString();

            Intent intent = new Intent(Activity_11.this, Activity_12_2.class);
            if(TextUtils.isEmpty(my_string))
            {
                Toast.makeText(Activity_11.this, "没有数据输入", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(Activity_11.this, "数据为:"+my_string, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }

        }

    }

}
