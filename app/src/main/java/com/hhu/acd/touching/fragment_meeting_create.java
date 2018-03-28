package com.hhu.acd.touching;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.netease.nimlib.sdk.team.model.Team;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liziming on 18-1-31.
 */

public class fragment_meeting_create extends Fragment {
    private RecyclerView mRecyclerView;
    private Context mContext;
    private MyHandler mHandler = new MyHandler(this);
    private fragment_meeting_create_adapter mAdapter;
    View view;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Meetings> list;
    Gson gson=new Gson();
   /* @Override
    public void onCreate(){

    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mContext=getActivity();

        view=inflater.inflate(R.layout.fragment_meeting_brief_message_create, null);
        initData();
        initView();
        return view;
    }
    private void initData() {
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody
                .Builder()
                .add("owner",NimApplication.getInstance().getId())//设置参数名称和参数值
                .build();
        Request request = new Request.Builder()
                .url(okhttpurl.url_searchmeetingbyowner)
                .post(formBody)
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
        mLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);

    }

    private void initView() {
        mRecyclerView =  view.findViewById(R.id.fragment_my_meeting_create);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter

        mAdapter.setOnItemClickListener(new fragment_meeting_create_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext, "click " + position + " item", Toast.LENGTH_SHORT).show();

            }

            //@Override
            //public void onItemLongClick(View view, int position) {
            //Toast.makeText(MDRvActivity.this,"long click " + position + " item", Toast.LENGTH_SHORT).show();
            //}
        });


    }


    private static class MyHandler extends Handler {

        //对Activity的弱引用
        private final WeakReference<fragment_meeting_create> mActivity;

        public MyHandler(fragment_meeting_create activity) {
            mActivity = new WeakReference<fragment_meeting_create>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            fragment_meeting_create activity = mActivity.get();
            if (activity == null) {
                super.handleMessage(msg);
                return;
            }
            switch (msg.what) {
                case 1:
                    Toast.makeText(activity.mContext, (String) msg.obj, Toast.LENGTH_LONG).show();
                    break;
                case 2:

                    activity.list = (ArrayList<Meetings>) msg.obj;
                    activity.mAdapter = new fragment_meeting_create_adapter(activity.mContext,activity.list);
                    activity.mAdapter.setHasStableIds(true);
                    activity.mRecyclerView.setAdapter(activity.mAdapter);

                    break;
                case 3:


                    activity.mAdapter.updateData((ArrayList<Meetings>) msg.obj);
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
