package com.hhu.acd.touching;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.lang.String;
import java.lang.StringBuffer;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.gson.Gson;
import com.hhu.acd.touching.R;
import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.picker.DateTimePicker;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Activity_09 extends AppCompatActivity {
        private ImageButton btn1;
        private Button btn2;
        private EditText edt1;
        private EditText edt2;
        private EditText edt3;

        private TextView text3;
        private TextView text4;
        private Gson gson;
        private Myhandler mHandler=new Myhandler(this);
        SimpleDateFormat formatter=new SimpleDateFormat ("yyyy-MM-dd HH:mm", Locale.CHINESE);

        long now=new Date(System.currentTimeMillis()).getTime();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_09);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        btn1=(ImageButton)findViewById(R.id.back);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(Activity_09.this, Activity_04.class);
                startActivity(it);
            }
        });
        btn2=(Button)findViewById(R.id.finish) ;
        btn2.setOnClickListener(Finish);

        edt1=(EditText)findViewById(R.id.meetingtheme);
        edt2=(EditText)findViewById(R.id.meetingbrief);
        edt3=(EditText)findViewById(R.id.meetingarea);

        text3=(TextView)findViewById(R.id.starttime);
        text4=(TextView)findViewById(R.id.endtime);
        gson=new Gson();
    }
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    public void changeText(TextView t,String year,String month,String day,String hour,String minute){
        StringBuffer str=new StringBuffer();
        str.append(year);
        str.append("-");
        str.append(month);
        str.append("-");
        str.append(day);
        str.append(" ");
        str.append(hour);
        str.append(":");
        str.append(minute);
        t.setText(str.toString());
    }

    public void logintime(View v){
        final View tempt=v;
        DateTimePicker picker = new DateTimePicker(this, DateTimePicker.HOUR_24);
        picker.setActionButtonTop(false);
        picker.setDateRangeStart(2017, 1, 1);
        picker.setDateRangeEnd(2025, 11, 11);
        picker.setSelectedItem(2018,3,15,16,43);
        picker.setTimeRangeStart(9, 0);
        picker.setTimeRangeEnd(20, 30);
        picker.setCanLinkage(false);
        picker.setTitleText("请选择");
//        picker.setStepMinute(5);
        picker.setWeightEnable(true);
        picker.setWheelModeEnable(true);
        LineConfig config = new LineConfig();
        config.setColor(Color.BLUE);//线颜色
        config.setAlpha(120);//线透明度
        config.setVisible(true);//线不显示 默认显示
        picker.setLineConfig(config);
        picker.setLabel(null,null,null,null,null);
        picker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {

            @Override
            public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                showToast(year + "-" + month + "-" + day + " " + hour + ":" + minute);
                TextView t=(TextView)tempt;
                changeText(t,year,month,day,hour,minute);

            }

        });


        picker.show();
    }
   View.OnClickListener Finish=new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Date st=null,et=null;
           try{
               st=formatter.parse(text3.getText().toString());
               et=formatter.parse(text4.getText().toString());

           }catch (ParseException pe) {

           }
           if(IsMeetingThemeBriefTimeRight()){
               OkHttpClient client = new OkHttpClient();
               MediaType JSON = MediaType.parse("application/json; charset=utf-8");
               RequestBody body=RequestBody.create(JSON,gson.toJson(new Meetings(edt1.getText().toString().trim(),NimApplication.getInstance().getId(),st,et,edt3.getText().toString().trim(),edt2.getText().toString().trim())));
               Request req=new Request.Builder()
                       .url(okhttpurl.url_createmeeting)
                       .post(body)
                       .build();
               client.newCall(req).enqueue(new Callback() {
                   @Override
                   public void onFailure(Call call, IOException e) {
                       mHandler.obtainMessage(1, "网络有点问题！！").sendToTarget();
                   }

                   @Override
                   public void onResponse(Call call, Response response) throws IOException {
                       mHandler.obtainMessage(1,"创建成功！！").sendToTarget();
                       finish();
                   }
               });
           }
           else
               mHandler.obtainMessage(1, "信息填写不正确！！");

       }
   };
    public boolean IsMeetingThemeBriefTimeRight(){
        Date st=null,et=null;
        try{
             st=formatter.parse(text3.getText().toString());
             et=formatter.parse(text4.getText().toString());

        }catch (ParseException pe) {

        }
        if(edt1.getText().toString().trim().equals(""))
            return false;
        else if(edt2.getText().toString().trim().equals(""))
            return false;
        else if(edt3.getText().toString().trim().equals(""))
            return false;
        else if(st.getTime()<now||et.getTime()<now)
            return false;
        return true;
    }



    private static class Myhandler extends Handler {
        private final WeakReference<Activity_09> mActivity;

        public Myhandler(Activity_09 activity){
            mActivity = new WeakReference<Activity_09>(activity);
        }

        public void handleMessage(Message msg){
            Activity_09 activity = mActivity.get();
            if (activity == null) {
                super.handleMessage(msg);
                return;
            }

            switch(msg.what){
                case 1:
                    Toast.makeText(activity, (String) msg.obj, Toast.LENGTH_LONG).show();
                    break;

                    default:
                        super.handleMessage(msg);
                        break;
            }

        }


    }
}


