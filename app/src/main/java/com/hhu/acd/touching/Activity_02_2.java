package com.hhu.acd.touching;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Activity_02_2 extends AppCompatActivity {
    private EditText mAccount;                        //用户名编辑
    private EditText mPwd;                            //密码编辑
    private EditText mPwdCheck;                       //密码编辑
    private Button mSureButton;                       //确定按钮
    private Button mCancelButton;                     //取消按钮
    private MyOkHttp mMyOkhttp;
    private Gson gson;
    String id;
    private MyHandler mHandler=new MyHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_02_2);
        mMyOkhttp = NimApplication.getInstance().getMyOkHttp();
        id=getIntent().getExtras().getString("id");
        mAccount = (EditText) findViewById(R.id.resetpwd_edit_name);
        mPwd = (EditText) findViewById(R.id.resetpwd_edit_pwd_old);
        mPwdCheck = (EditText) findViewById(R.id.resetpwd_edit_pwd_new);

        mSureButton = (Button) findViewById(R.id.register_btn_sure);
        mCancelButton = (Button) findViewById(R.id.register_btn_cancel);

        mSureButton.setOnClickListener(m_register_Listener);      //注册界面两个按钮的监听事件
        mCancelButton.setOnClickListener(m_register_Listener);


    }
    View.OnClickListener m_register_Listener = new View.OnClickListener() {    //不同按钮按下的监听事件选择
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.register_btn_sure:                       //确认按钮的监听事件

                    if (!isUserNameAndPwdValid())
                    {   //gson.toJson(new Userinfos(id,mAccount.getText().toString().trim(),mPwd.getText().toString().trim()));
                        OkHttpClient client = new OkHttpClient();
                        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                        RequestBody body = RequestBody.create(JSON, gson.toJson(new Userinfos(id,mAccount.getText().toString().trim(),mPwd.getText().toString().trim())));
                        Request request = new Request.Builder()
                                .url(okhttpurl.url_sendmsg)
                                .post(body)
                                .build();
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                mHandler.obtainMessage(1, "网络有点问题！！").sendToTarget();
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                if (response.code() == 200) {
                                    if(gson .fromJson(response.body().string(),boolean.class)==true)
                                    {mHandler.obtainMessage(1, "注册成功！").sendToTarget();
                                        //send_msg.setBackgroundColor(Color.GRAY);
                                        //mHandler.obtainMessage(2,response.body().string().trim()).sendToTarget();
                                        finish();
                                    }
                                     else
                                        mHandler.obtainMessage(1, "帐号已存在").sendToTarget();
                                }
                                else
                                    mHandler.obtainMessage(1, "服务器出了点问题喽").sendToTarget();




                            }
                        });
                    }
                    break;
                case R.id.register_btn_cancel:                     //取消按钮的监听事件,由注册界面返回登录界面

                    finish();
                    break;
            }
        }
    };

    public boolean isUserNameAndPwdValid() {
        if (mAccount.getText().toString().trim().equals("")) {
            Toast.makeText(this,"输入账号为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwd.getText().toString().trim().equals("")) {
            Toast.makeText(this, "密码输入为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        }else if(mPwdCheck.getText().toString().trim().equals("")) {
            Toast.makeText(this, "密码输入为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private static class MyHandler extends Handler {

        //对Activity的弱引用
        private final WeakReference<Activity_02_2> mActivity;

        public MyHandler(Activity_02_2 activity) {
            mActivity = new WeakReference<Activity_02_2>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity_02_2 activity = mActivity.get();
            if (activity == null) {
                super.handleMessage(msg);
                return;
            }
            switch (msg.what) {
                case 1:
                    Toast.makeText(activity, (String) msg.obj, Toast.LENGTH_LONG).show();
                    break;
                case 2:

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
}