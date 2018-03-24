package com.hhu.acd.touching;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.LoadModel;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.model.RecentContact;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class Activity_04 extends AppCompatActivity {

    private ImageButton to_recentmsg;
    private ImageButton pop;
    private ImageButton to_mymeeting;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyHandler mHandler=new MyHandler(this);
    private meeting_list_04_adapter mAdapter;
   // private MaterialSearchView searchView;
    private RecyclerView mRecyclerView;
    EasyRefreshLayout easyRefreshLayout;
    LayoutInflater mInflater;
    private PopupWindow mDropdown = null;
    ArrayList<Meetings> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_04);
        to_recentmsg=findViewById(R.id.meetingtomessage);
        to_recentmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Activity_04.this,Activity_03.class);
                startActivity(i);
            }
        });
        to_mymeeting=findViewById(R.id.mymeeting);
        to_mymeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Activity_04.this,Activity_06.class);
                startActivity(i);
            }
        });

        pop = findViewById(R.id.create_meeting_pop);
        pop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                initiatePopupWindow();

            }
        });
        initView();
        easyRefreshLayout=findViewById(R.id.meeting_easylayout);
        easyRefreshLayout.setLoadMoreModel(LoadModel.NONE);
        easyRefreshLayout.addEasyEvent(new EasyRefreshLayout.EasyEvent() {


            @Override
            public void onLoadMore() {

            }

            @Override
            public void onRefreshing() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        easyRefreshLayout.refreshComplete();
                        Toast.makeText(getApplicationContext(), "refresh success", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);

            }
        });

    }


    private void initView() {

        //list.add(new message_data(1,"1","1","1"));
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new meeting_list_04_adapter(this,list);

        mAdapter.setHasStableIds(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);



    }
    private PopupWindow initiatePopupWindow() {

        try {

            mInflater = (LayoutInflater) getApplicationContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = mInflater.inflate(R.layout.create_meeting, null);

            //If you want to add any listeners to your textviews, these are two //textviews.
            final TextView itema = (TextView) layout.findViewById(R.id.create_meeting);
            itema.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(Activity_04.this,Activity_06.class);
                    startActivity(i);
                    finish();
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
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_item, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }*/
    private static class MyHandler extends Handler {

        //对Activity的弱引用
        private final WeakReference<Activity_04> mActivity;

        public MyHandler(Activity_04 activity) {
            mActivity = new WeakReference<Activity_04>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity_04 activity = mActivity.get();
            if (activity == null) {
                super.handleMessage(msg);
                return;
            }
            switch (msg.what) {
                case 1:
                    Toast.makeText(activity, (String) msg.obj, Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    activity.list=(ArrayList<Meetings>)msg.obj;
                    //activity.mAdapter = new MyAdapter_03(activity,(List<RecentContact>)msg.obj);
                    break;
                case 3:
                    activity.mAdapter.updateData((ArrayList<Meetings>)msg.obj);
                    break;
                /*case 4:
                    activity.list.add((message_data)msg.obj);
                    activity.mAdapter.updateData(activity.list);
                    break;*/
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
