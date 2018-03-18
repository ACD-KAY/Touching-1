package com.hhu.acd.touching;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity_01 extends AppCompatActivity {
    private Button btn;
    private TextView btn2;
    private EditText account;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);
        account=findViewById(R.id.account);
        password=findViewById(R.id.password);
        /** when the user clicks "登录"*/
        btn=(Button)findViewById(R.id.B_login);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=account.getText().toString();
                String pw=password.getText().toString();
                Intent it = new Intent(Activity_01.this, Activity_03.class);
                startActivity(it);
            }
        });

        /** when the user clicks ""新用户注册*/
        btn2=(TextView)findViewById(R.id.textView);
        btn2.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Activity_01.this, Activity_02.class);
                startActivity(it);
            }
        });
    }
}
