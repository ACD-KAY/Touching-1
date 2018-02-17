package com.hhu.acd.touching;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.stfalcon.multiimageview.MultiImageView;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by liziming on 18-1-21.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private MyAdapter.OnItemClickListener onItemClickListener;
    private ArrayList<message_data> mData;
    private Context context;
    private Resources resources;
    public MyAdapter(Context context,ArrayList<message_data> data) {
        this.context=context;
        this.resources = context.getResources();
        this.mData = data;
    }

    public void updateData(ArrayList<message_data> data) {
        this.mData = data;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(MyAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rv_item, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // 绑定数据
        Bitmap bitmap=BitmapFactory.decodeResource(resources,mData.get(position).getImgurl());
        holder.chat_item_portrait.addImage(bitmap);
        holder.chat_item_portrait.setShape(MultiImageView.Shape.CIRCLE);
        holder.name.setText(mData.get(position).getName());
        holder.message.setText(mData.get(position).getMassage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        MultiImageView chat_item_portrait;
        TextView name;
        TextView message;
        public ViewHolder(View itemView) {
            super(itemView);
            chat_item_portrait= itemView.findViewById(R.id.chat_item_portrait);
            name=itemView.findViewById(R.id.friend_name);
            message=itemView.findViewById(R.id.message_brief);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        //void onItemLongClick(View view, int position);
    }


}

