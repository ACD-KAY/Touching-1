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

import com.ajguan.library.EasyRefreshLayout;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.team.TeamService;
import com.netease.nimlib.sdk.team.constant.TeamTypeEnum;
import com.netease.nimlib.sdk.team.model.Team;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liziming on 18-1-31.
 */

public class fragment_meeting_collect extends Fragment {
    private RecyclerView mRecyclerView;
    private Context mContext;
    EasyRefreshLayout easyRefreshLayout;
    private fragment_meeting_adapter mAdapter;
    private MyHandler mHandler = new MyHandler(this);
    View view;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Team> list;

    /* @Override
     public void onCreate(){

     }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mContext = getActivity();
        list = new ArrayList<>();
        view = inflater.inflate(R.layout.fragment_meeting_brief_message_collect, null);
        easyRefreshLayout = view.findViewById(R.id.meeting_easylayout_collect);
        easyRefreshLayout.addEasyEvent(new EasyRefreshLayout.EasyEvent() {


            @Override
            public void onLoadMore() {

            }

            @Override
            public void onRefreshing() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        NIMClient.getService(TeamService.class).queryTeamListByType(TeamTypeEnum.Advanced).setCallback(new RequestCallback<List<Team>>() {
                            @Override
                            public void onSuccess(List<Team> teams) {
                                // 成功
                                mHandler.obtainMessage(3, teams).sendToTarget();
                            }

                            @Override
                            public void onFailed(int i) {
                                // 失败
                                mHandler.obtainMessage(1, " 获取信息失败").sendToTarget();
                            }

                            @Override
                            public void onException(Throwable throwable) {
                                // 错误
                                mHandler.obtainMessage(1, " 可能哪里出现了问题").sendToTarget();
                            }
                        });
                        easyRefreshLayout.refreshComplete();
                        Toast.makeText(mContext, "refresh success", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);

            }
        });
        initData();
        initView();
        return view;
    }

    private void initData() {

        NIMClient.getService(TeamService.class).queryTeamListByType(TeamTypeEnum.Advanced).setCallback(new RequestCallback<List<Team>>() {
            @Override
            public void onSuccess(List<Team> teams) {
                // 成功
                mHandler.obtainMessage(2, teams).sendToTarget();
            }

            @Override
            public void onFailed(int i) {
                // 失败
                mHandler.obtainMessage(1, " 获取信息失败").sendToTarget();
            }

            @Override
            public void onException(Throwable throwable) {
                // 错误
                mHandler.obtainMessage(1, " 可能哪里出现了问题").sendToTarget();
            }
        });
        mLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new fragment_meeting_adapter(this.getContext(), list);
    }

    private void initView() {
        mRecyclerView = view.findViewById(R.id.fragment_my_meeting_collect);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new fragment_meeting_adapter.OnItemClickListener() {
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
        private final WeakReference<fragment_meeting_collect> mActivity;

        public MyHandler(fragment_meeting_collect activity) {
            mActivity = new WeakReference<fragment_meeting_collect>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            fragment_meeting_collect activity = mActivity.get();
            if (activity == null) {
                super.handleMessage(msg);
                return;
            }
            switch (msg.what) {
                case 1:
                    Toast.makeText(activity.mContext, (String) msg.obj, Toast.LENGTH_LONG).show();
                    break;
                case 2:

                    activity.list = (ArrayList<Team>) msg.obj;

                    break;
                case 3:


                    activity.mAdapter.updateData((ArrayList<Team>) msg.obj);
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
