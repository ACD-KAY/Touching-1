package com.hhu.acd.touching;


import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class Activity_02 extends AppCompatActivity  {


    // UI references.

    private EditText phonenumber;
    private EditText identity_code;
    //private TimeButton send_msg;
    private Button send_msg;
    private Button check;
    //private Gson gson;
    private MyOkHttp mMyOkhttp;
    public String identity;
   // private final Activity_02.MyHandler mHandler = new Activity_02.MyHandler(this);
    private MyHandler mHandler=new MyHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_02);
        mMyOkhttp = NimApplication.getInstance().getMyOkHttp();
        phonenumber=findViewById(R.id.phonenumber);
        identity_code=findViewById(R.id.identity_code);
        send_msg =  findViewById(R.id.send_msg);
        //send_msg.setTextAfter("秒后重新获取").setTextBefore("获取验证码").setLenght(15 * 1000);


        /*发送验证码*/
        send_msg=findViewById(R.id.send_msg);
        send_msg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataAsync(phonenumber.getText().toString());

            }
            /*@Override
            public void onClick(View v) {
                if(isPhoneValid(phonenumber.getText().toString())) {
                    Map<String, String> params = new HashMap<>();
                    params.put("id", phonenumber.getText().toString());

                    mMyOkhttp.post()
                            .url(okhttpurl.url_sendmsg)
                            .params(params)
                            .tag(1)
                            .enqueue(new GsonResponseHandler() {


                                @Override
                                public void onFailure(int statusCode, String error_msg) {
                                    mHandler.obtainMessage(1, "可能网络出了点问题").sendToTarget();
                                }

                                @Override
                                public void onSuccess(int statusCode, Object response) {
                                    //identity=identity_code.getText().toString();
                                    /*if(identity==(String)response) {
                                        mHandler.obtainMessage(1, "验证通过！！").sendToTarget();
                                        Intent it = new Intent(Activity_02.this, Activity_03.class);
                                        startActivity(it);
                                    }
                                    else
                                    {
                                        mHandler.obtainMessage(1, "验证通过！！").sendToTarget();
                                    }
                                    if (statusCode==200)
                                    {   mHandler.obtainMessage(1, "请注意查收短信！！").sendToTarget();
                                        identity=(String)response;
                                    }
                                    else
                                        mHandler.obtainMessage(1, "服务器出了点问题喽").sendToTarget();
                                }
                            });
                }
            }*/
        });


        /*验证*/
        check=findViewById(R.id.checkcode);
        check.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(identity_code.getText().toString().equals(identity))
                {   mHandler.obtainMessage(1, "验证通过！！").sendToTarget();
                    Intent it = new Intent(Activity_02.this, Activity_02_2.class);
                    it.putExtra("id",phonenumber.getText().toString());
                    startActivity(it);
                    finish();
                }
                else
                {mHandler.obtainMessage(1, "您所输入的验证码错误").sendToTarget();
                 mHandler.obtainMessage(1, identity_code.getText().toString()).sendToTarget();
                }

            }
        });

    }




    @Override
    protected void onDestroy() {
        mMyOkhttp.cancel(1);
        super.onDestroy();
    }


    private boolean isPhoneValid(String phonenumber) {
        //TODO: Replace this with your own logic
        return phonenumber.length() ==11;
    }

    private boolean isIdentutyValid(String identuty_code) {
        //TODO: Replace this with your own logic
        return identuty_code.length() == 6;
    }

    private static class MyHandler extends Handler {

        //对Activity的弱引用
        private final WeakReference<Activity_02> mActivity;

        public MyHandler(Activity_02 activity){
            mActivity = new WeakReference<Activity_02>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity_02 activity = mActivity.get();
            if(activity==null){
                super.handleMessage(msg);
                return;
            }
            switch (msg.what) {
                case 1:
                    Toast.makeText(activity, (String)msg.obj, Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    activity.identity=(String)msg.obj;
                    Toast.makeText(activity, activity.identity, Toast.LENGTH_LONG).show();
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
    private void getDataAsync(String PHONE) {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("id",PHONE);//传递键值对参数
        Request request = new Request.Builder()
                .url(okhttpurl.url_sendmsg_test)
                .post(formBody.build())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.obtainMessage(1, "可能网络出了点问题").sendToTarget();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //回调的方法执行在子线程。
                    /*Log.d("kwwl","获取数据成功了");
                    Log.d("kwwl","response.code()=="+response.code());
                    Log.d("kwwl","response.body().string()=="+response.body().string());*/
                    if (response.isSuccessful()) {
                        mHandler.obtainMessage(1, "请注意查收短信！！").sendToTarget();
                        //send_msg.setBackgroundColor(Color.GRAY);
                        mHandler.obtainMessage(2, response.body().string().trim()).sendToTarget();
                        //identity = response.body().string();

                    }else
                        mHandler.obtainMessage(1, "服务器出了点问题喽").sendToTarget();



            }
        });
    }

}
