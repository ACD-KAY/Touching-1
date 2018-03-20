package com.hhu.acd.touching;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.netease.nim.uikit.common.util.sys.TimeUtil;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.stfalcon.multiimageview.MultiImageView;

import java.util.List;

import cn.bingoogolapple.badgeview.BGABadgeTextView;

/**
 * Created by liziming on 18-1-21.
 */

public class MyAdapter_03 extends RecyclerView.Adapter<MyAdapter_03.ViewHolder>{
    private OnItemClickListener onItemClickListener;
    private List<RecentContact> mData;
    private Context context;
    private Resources resources;
    public MyAdapter_03(Context context, List<RecentContact> data) {
        this.context=context;
        this.resources = context.getResources();
        this.mData = data;
    }

    public void updateData(List<RecentContact> data) {
        this.mData = data;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
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
    public long getItemId(int position) {
        return position;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // 绑定数据
        //Bitmap bitmap= BitmapFactory.decodeResource(resources,mData.get(position));
        //holder.chat_item_portrait.addImage(bitmap);
        Glide.with(context)
                .load("http://106.14.195.234/springmvc/images/000.jpeg")
                .into(holder.chat_item_portrait);
        holder.chat_item_portrait.setShape(MultiImageView.Shape.CIRCLE);
        holder.name.setText(mData.get(position).getContactId());
        holder.message.setText(mData.get(position).getContent());
        holder.time.setText(TimeUtil.getTimeString(mData.get(position).getTime()));
        int unread=mData.get(position).getUnreadCount();
        if (unread != 0) {
            holder.messageNum.showCirclePointBadge();
            holder.messageNum.showTextBadge(unread + "");
        }
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
        TextView time;
        BGABadgeTextView messageNum;
        public ViewHolder(View itemView) {
            super(itemView);
            chat_item_portrait= itemView.findViewById(R.id.chat_item_portrait);
            name=itemView.findViewById(R.id.chat_item_name);
            message=itemView.findViewById(R.id.chat_item_content);
            time=itemView.findViewById(R.id.chat_item_time);
            messageNum=itemView.findViewById(R.id.chat_item_message_num);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        //void onItemLongClick(View view, int position);
    }


}

