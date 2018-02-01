package com.hhu.acd.touching;


import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class fragment_meeting extends Fragment {
    private RecyclerView mRecyclerView;

    private fragment_meeting_adapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meeting_brief_message, container, false);
    }
    private void initData() {
        mLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new fragment_meeting_adapter(this.getContext(),getData());
    }

    private void initView() {
        mRecyclerView = (RecyclerView) this.getView().findViewById(R.id.my_recycler_view);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(),"click " + position + " item", Toast.LENGTH_SHORT).show();
            }

            //@Override
            //public void onItemLongClick(View view, int position) {
            //Toast.makeText(MDRvActivity.this,"long click " + position + " item", Toast.LENGTH_SHORT).show();
            //}
        });


    }
    private ArrayList<meeting_data> getData() {
        ArrayList<meeting_data> data = new ArrayList<>();
        int url= R.drawable.bussiness_man;
        for(int i = 0; i < 15; i++) {
            data.add(new meeting_data(url," 李子铭","2018","hhu",1606010316));
        }

        return data;
    }
}
