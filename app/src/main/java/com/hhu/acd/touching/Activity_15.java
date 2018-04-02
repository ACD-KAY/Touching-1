package com.hhu.acd.touching;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar;

import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.picker.DateTimePicker;


public class Activity_15 extends AppCompatActivity {
    private TextView btn1;
    private ImageButton btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_15);



        /*spinner();
        spinner2();*/

        /**发布投票*/
        btn1=(TextView)findViewById(R.id.B_release);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity_15.this,Activity_08.class);
                startActivity(intent);
            }
        });

        /**返回*/
        btn2=(ImageButton)findViewById(R.id.B_before);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity_15.this,Activity_08.class);
                startActivity(intent);
            }
        });
        

        /**标题栏设置*/
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
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
    /** 投票类型选择*/
    private void spinner() {
        Spinner spinner = (Spinner) findViewById(R.id.S_Vtype);
        String[] items = getResources().getStringArray(R.array.S_votationtype);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] select = getResources().getStringArray(R.array.S_votationtype);
                Toast.makeText(Activity_15.this, "你选择了"+select[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    /**提醒时间选择*/
   private void spinner2() {
        Spinner spinner = (Spinner) findViewById(R.id.S_Remind);
        String[] items = getResources().getStringArray(R.array.S_remind);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] select = getResources().getStringArray(R.array.S_remind);
                Toast.makeText(Activity_15.this, "你选择了"+select[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    public void Display(View v){

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

                TextView t=(TextView)findViewById(R.id.display);
                changeText(t,year,month,day,hour,minute);

            }

        });


        picker.show();
    }
}
