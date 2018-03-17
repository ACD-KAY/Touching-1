package com.hhu.acd.touching;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by liziming on 18-1-31.
 */

public class fragment_meeting_notes extends Fragment {
    private RecyclerView mRecyclerView;
    private Context mContext;
    View view;
    private fragment_meeting_notes_adapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mContext=getActivity();
        view=inflater.inflate(R.layout.fragment_meeting_brief_message, null);
        initData();
        initView();
        return view;
    }
    private void initData() {
        mLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new fragment_meeting_notes_adapter(this.getContext(),getData());
    }

    private void initView() {
        mRecyclerView =  view.findViewById(R.id.fragment_my_meeting);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new fragment_meeting_notes_adapter.OnItemClickListener() {
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
    private ArrayList<notes_data> getData() {
        ArrayList<notes_data> data = new ArrayList<>();
        //int url= R.drawable.bussiness_man;
        for(int i = 0; i < 2; i++) {
            data.add(new notes_data("2018202","2018nkslnflskdfnlknflwnfbjlwbfjwlhfwowjbfwjk" +
                    "webfwkbfkwjbfwkjbkwjbfkjwbfwkjfbwjkbwjkbwjkfbwkjfbkjwbfw" +
                    "jhewbwbf"));
        }

        return data;
    }
}

