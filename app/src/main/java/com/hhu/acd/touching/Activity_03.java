package com.hhu.acd.touching;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.LoadModel;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.model.RecentContact;

public class Activity_03 extends AppCompatActivity {



    private ImageButton btn,btn1,btn2,btn3;

    private RecyclerView mRecyclerView;
    private MyHandler mHandler=new MyHandler(this);
    private MyAdapter_03 mAdapter;
    EasyRefreshLayout easyRefreshLayout;
    private RecyclerView.LayoutManager mLayoutManager;
    private PopupWindow mDropdown = null;
    ArrayList<RecentContact> list;


    LayoutInflater mInflater;
    ImageButton pop;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_03);

        //initData();
        initView();

        easyRefreshLayout=findViewById(R.id.easylayout);
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
                        NIMClient.getService(MsgService.class).queryRecentContacts()
                                .setCallback(new RequestCallbackWrapper<List<RecentContact>>() {
                                    @Override
                                    public void onResult(int code, List<RecentContact> recents, Throwable e) {
                                        // recents参数即为最近联系人列表（最近会话列表）
                                        Message msg=Message.obtain();
                                        msg.what=3;
                                        msg.obj=recents;

                                        mHandler.sendMessage(msg);
                                    }
                                });
                        /*Message msg=Message.obtain();
                        msg.what=4;
                        msg.obj=new message_data(1,"1","1","1");

                        mHandler.sendMessage(msg);*/
                        //list.add(new message_data(1,"1","1","1"));
                       // mAdapter.notifyDataSetChanged();
                        easyRefreshLayout.refreshComplete();
                        Toast.makeText(getApplicationContext(), "refresh success", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);

            }
        });
        mRecyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        //((MultiImageView)findViewById(R.id.chat_item_test)).addImage(BitmapFactory.decodeResource(getResources(),R.mipmap.bussiness_man));
        //initData();

        pop = findViewById(R.id.addfriends);
        pop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                initiatePopupWindow();

            }
        });

        getSupportActionBar().setDisplayShowTitleEnabled(false);



        /** 跳转到消息界面*/
        btn1=(ImageButton)findViewById(R.id.message);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Activity_03.this, Activity_13.class);
                startActivity(it);
            }
        });

        /** 跳转到会议界面*/
        btn2=(ImageButton)findViewById(R.id.meeting);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Activity_03.this, Activity_04.class);
                startActivity(it);
            }
        });

        /** 跳转到好友列表界面*/
        btn3=(ImageButton)findViewById(R.id.myfriends);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Activity_03.this, Activity_05.class);
                startActivity(it);
            }
        });
    }


    private void initData() {
        NIMClient.getService(MsgService.class).queryRecentContacts()
                .setCallback(new RequestCallbackWrapper<List<RecentContact>>() {
                    @Override
                    public void onResult(int code, List<RecentContact> recents, Throwable e) {
                        // recents参数即为最近联系人列表（最近会话列表）
                        Message msg=Message.obtain();
                        msg.what=2;
                        msg.obj=recents;

                        mHandler.sendMessage(msg);
                    }
                });
    }

    private void initView() {
        NIMClient.getService(MsgService.class).queryRecentContacts()
                .setCallback(new RequestCallbackWrapper<List<RecentContact>>() {
                    @Override
                    public void onResult(int code, List<RecentContact> recents, Throwable e) {
                        // recents参数即为最近联系人列表（最近会话列表）
                        Message msg=Message.obtain();
                        msg.what=2;
                        msg.obj=recents;

                        mHandler.sendMessage(msg);
                    }
                });
        //list.add(new message_data(1,"1","1","1"));
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new MyAdapter_03(this,list);

        mAdapter.setHasStableIds(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);
        /*mAdapter.setOnItemClickListener(new MyAdapter_03.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(Activity_03.this,"click " + position + " item", Toast.LENGTH_SHORT).show();
            }

            //@Override
            //public void onItemLongClick(View view, int position) {
            //Toast.makeText(MDRvActivity.this,"long click " + position + " item", Toast.LENGTH_SHORT).show();
            //}
        });*/


    }

    /*private void getData() {
        NIMClient.getService(MsgService.class).queryRecentContacts()
                .setCallback(new RequestCallbackWrapper<List<RecentContact>>() {
                    @Override
                    public void onResult(int code, List<RecentContact> recents, Throwable e) {
                        // recents参数即为最近联系人列表（最近会话列表）
                        Message msg=Message.obtain();
                        msg.what=2;
                        msg.obj=recents;

                        mHandler.sendMessage(msg);
                    }
                });
    }*/




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
                    Intent i=new Intent(Activity_03.this,Activity_06.class);
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
    public void link_man(View view){
        Intent i=new Intent(Activity_03.this,Activity_05.class);
        startActivity(i);
        finish();
    }
    private static class MyHandler extends Handler {

        //对Activity的弱引用
        private final WeakReference<Activity_03> mActivity;

        public MyHandler(Activity_03 activity) {
            mActivity = new WeakReference<Activity_03>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity_03 activity = mActivity.get();
            if (activity == null) {
                super.handleMessage(msg);
                return;
            }
            switch (msg.what) {
                case 1:
                    Toast.makeText(activity, (String) msg.obj, Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    activity.list=(ArrayList<RecentContact>)msg.obj;
                    //activity.mAdapter = new MyAdapter_03(activity,(List<RecentContact>)msg.obj);
                    break;
                case 3:
                    activity.mAdapter.updateData((ArrayList<RecentContact>)msg.obj);
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

