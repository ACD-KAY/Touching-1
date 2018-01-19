package com.hhu.acd.touching;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

public class mainactivity_3_message extends AppCompatActivity {

    private RecyclerView  recyclerView;
    private List<Model> datas;
    private MyAdapter adapter;
    ImageButton red, blue;

    private PopupWindow mDropdown = null;
    LayoutInflater mInflater;
    ImageButton pop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity_3_messagelist);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        pop = findViewById(R.id.addfriends);
        pop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                initiatePopupWindow();

            }
        });

    getSupportActionBar().setDisplayShowTitleEnabled(false);}
        /*//初始化RecyclerView
        recyclerView =  findViewById(R.id.recycler_view);

        //模拟的数据（实际开发中一般是从网络获取的）
        datas = new ArrayList<>();
        Model model;
        for (int i = 0; i < 15; i++) {
            model = new Model();
            model.setTitle("我是第" + i + "条标题");
            model.setContent("第" + i + "条内容");
            datas.add(model);
        }

        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //创建适配器
        adapter = new MyAdapter(R.layout.item_rv, datas);

        //给RecyclerView设置适配器
        recyclerView.setAdapter(adapter);*/
//        setOverflowShowingAlways();

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.messagelistmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.toolbar_add) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

*/

    private PopupWindow initiatePopupWindow() {

        try {

            mInflater = (LayoutInflater) getApplicationContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = mInflater.inflate(R.layout.add_friends, null);

            //If you want to add any listeners to your textviews, these are two //textviews.
            final TextView itema = (TextView) layout.findViewById(R.id.add_friends);
            itema.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(mainactivity_3_message.this,);
                    startActivity(i);
                }
            });





            layout.measure(View.MeasureSpec.UNSPECIFIED,
                    View.MeasureSpec.UNSPECIFIED);
            mDropdown = new PopupWindow(layout, FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT,true);

            mDropdown.showAsDropDown(pop, 5, 40);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDropdown;

    }

    }
