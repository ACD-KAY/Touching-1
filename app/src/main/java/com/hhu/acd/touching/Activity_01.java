package com.hhu.acd.touching;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;

import java.lang.ref.WeakReference;

public class Activity_01 extends AppCompatActivity {
    private Button btn;
    private TextView btn2;
    private EditText account;
    private EditText password;
    private final MyHandler mHandler = new MyHandler(this);
    private String myid;

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
                doLogin(id,pw);
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
    /*private void showToast(String msg){
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
    }*/
    private static class MyHandler extends Handler{

        //对Activity的弱引用
        private final WeakReference<Activity_01> mActivity;

        public MyHandler(Activity_01 activity){
            mActivity = new WeakReference<Activity_01>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity_01 activity = mActivity.get();
            if(activity==null){
                super.handleMessage(msg);
                return;
            }
            switch (msg.what) {
                case 1:
                    Toast.makeText(activity, (String)msg.obj, Toast.LENGTH_SHORT).show();
                    break;
                /*case 2:
                    Toast.makeText(activity, "下载成功", Toast.LENGTH_SHORT).show();
                    Bitmap bitmap = (Bitmap) msg.obj;
                    activity.imageView.setVisibility(View.VISIBLE);
                    activity.imageView.setImageBitmap(bitmap);
                    break;*/
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }

    public void doLogin(String account,String token) {
            LoginInfo info = new LoginInfo(account, token); // config...
            RequestCallback<LoginInfo> callback =
                    new RequestCallback<LoginInfo>() {
                        @Override
                        public void onSuccess(LoginInfo param) {

                            mHandler.obtainMessage(1, "登录成功").sendToTarget();

                            Intent it = new Intent(Activity_01.this, Activity_03.class);
                            NimApplication.getInstance().setId(param.getAccount());
                            startActivity(it);
                        }

                        @Override
                        public void onFailed(int code) {

                            mHandler.obtainMessage(1, "登录失败,请检查帐号或密码"+code).sendToTarget();

                        }

                        @Override
                        public void onException(Throwable exception) {

                            mHandler.obtainMessage(1, "可能哪里粗了未知问题额").sendToTarget();

                        }
                        // 可以在此保存LoginInfo到本地，下次启动APP做自动登录用
                    };
            NIMClient.getService(AuthService.class).login(new LoginInfo(account, token))
                    .setCallback(callback);
    }
}
