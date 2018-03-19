package com.hhu.acd.touching;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class Activity_02 extends AppCompatActivity  {


    // UI references.
    private EditText phonenumber;
    private EditText identity_code;
    private TimeButton send_msg;
    private Gson gson;
    private MyOkHttp mMyOkhttp;
    private final Activity_02.MyHandler mHandler = new Activity_02.MyHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_02);
        mMyOkhttp = NimApplication.getInstance().getMyOkHttp();
        phonenumber=findViewById(R.id.phonenumber);
        identity_code=findViewById(R.id.identity_code);
        send_msg=findViewById(R.id.send_msg);
        send_msg.setOnClickListener(new OnClickListener() {
            @Override
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

                                }

                                @Override
                                public void onSuccess(int statusCode, Object response) {
                                    String identity=identity_code.getText().toString();
                                    if(identity==(String)response) {
                                        mHandler.obtainMessage(1, "验证通过！！").sendToTarget();
                                        Intent it = new Intent(Activity_02.this, Activity_03.class);
                                        startActivity(it);
                                    }

                                }
                            });
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

}
