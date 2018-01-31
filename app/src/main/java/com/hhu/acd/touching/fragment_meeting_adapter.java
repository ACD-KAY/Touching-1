package com.hhu.acd.touching;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stfalcon.multiimageview.MultiImageView;

import java.util.ArrayList;

/**
 * Created by liziming on 18-1-31.
 */

public class fragment_meeting_adapter extends RecyclerView.Adapter<fragment_meeting_adapter.ViewHolder> {
    private fragment_meeting_adapter.OnItemClickListener onItemClickListener;
    private ArrayList<meeting_data> mData;
    private Context context;
    private Resources resources;
    public fragment_meeting_adapter(Context context,ArrayList<meeting_data> data) {
        this.context=context;
        this.resources = context.getResources();
        this.mData = data;
    }

    public void updateData(ArrayList<meeting_data> data) {
        this.mData = data;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(fragment_meeting_adapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public fragment_meeting_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_meeting_brief_message_item, parent, false);
        // 实例化viewholder
        fragment_meeting_adapter.ViewHolder viewHolder = new fragment_meeting_adapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final fragment_meeting_adapter.ViewHolder holder, int position) {
        // 绑定数据
        Bitmap bitmap= BitmapFactory.decodeResource(resources,mData.get(position).getImgurl());
        holder.meeting_image.addImage(bitmap);
        holder.meeting_image.setShape(MultiImageView.Shape.CIRCLE);
        holder.meeting_name.setText(mData.get(position).getMeeting_name());
        holder.meeting_time.setText(mData.get(position).getMeeting_time());
        holder.meeting_place.setText(mData.get(position).getMeeting_place());

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

        MultiImageView meeting_image;
        TextView meeting_name;
        TextView meeting_time;
        TextView meeting_place;
        public ViewHolder(View itemView) {
            super(itemView);
            meeting_image= itemView.findViewById(R.id.meeting_image);
            meeting_name=itemView.findViewById(R.id.meeting_name);
            meeting_time=itemView.findViewById(R.id.meeting_time);
            meeting_place=itemView.findViewById(R.id.meeting_place);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        //void onItemLongClick(View view, int position);
    }

}
