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
import com.google.gson.Gson;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.model.RecentContact;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Activity_04 extends AppCompatActivity {

    private ImageButton to_recentmsg;
    private ImageButton pop;
    private ImageButton to_mymeeting;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyHandler mHandler=new MyHandler(this);
    private meeting_list_04_adapter mAdapter;
    private Gson gson=new Gson();
    private RecyclerView mRecyclerView;
    EasyRefreshLayout easyRefreshLayout;
    LayoutInflater mInflater;
    private PopupWindow mDropdown = null;
    ArrayList<Meetings> list;
    //private CustomLoadingView customLoadingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_04);
        //customLoadingView=findViewById(R.id.loadingView);
        to_recentmsg=findViewById(R.id.meetingtomessage);
        to_recentmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Activity_04.this,Activity_03.class);
                startActivity(i);
                finish();
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

        //list=new ArrayList<Meetings>();

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
                        OkHttpClient client = new OkHttpClient();
                        //RequestBody body = RequestBody.create();
                        Request request = new Request.Builder()
                                .url(okhttpurl.url_searchmeetingbytime)
                                .post(okhttp3.internal.Util.EMPTY_REQUEST)
                                .build();
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                               // mHandler.obtainMessage(4).sendToTarget();
                                mHandler.obtainMessage(1, "网络有点问题！！").sendToTarget();

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                mHandler.obtainMessage(3, new ArrayList<Meetings>(Arrays.asList(gson.fromJson(response.body().string().trim(),Meetings[].class)))).sendToTarget();
                               // mHandler.obtainMessage(4).sendToTarget();


                            }
                        });

                        easyRefreshLayout.refreshComplete();
                        Toast.makeText(getApplicationContext(), "refresh success", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);

            }
        });

    }
    @Override
    protected void onResume(){
        //customLoadingView.start();
        super.onResume();
    }

    private void initView() {

        //list.add(new message_data(1,"1","1","1"));

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(okhttpurl.url_searchmeetingbytime)
                .post(okhttp3.internal.Util.EMPTY_REQUEST)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
               // mHandler.obtainMessage(4).sendToTarget();
                mHandler.obtainMessage(1, "网络有点问题！！").sendToTarget();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                mHandler.obtainMessage(2, new ArrayList<Meetings>(Arrays.asList(gson.fromJson(response.body().string().trim(),Meetings[].class)))).sendToTarget();
               // mHandler.obtainMessage(4).sendToTarget();


            }
        });
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new meeting_list_04_adapter(this,list);

        mAdapter.setHasStableIds(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view_meetings);
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
                    //finish();
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
                    activity.customLoadingView.stop();
                    //activity.customLoadingView.setVisibility(View.GONE);
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
