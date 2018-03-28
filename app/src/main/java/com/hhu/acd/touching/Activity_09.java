package com.hhu.acd.touching;

import android.widget.Toast;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.lang.String;
import java.lang.StringBuffer;
import com.hhu.acd.touching.R;
import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.picker.DateTimePicker;


public class Activity_09 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_09);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

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
}


