package com.hhu.acd.touching;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.netease.nimlib.sdk.team.model.Team;
import com.stfalcon.multiimageview.MultiImageView;

import java.util.ArrayList;

/**
 * Created by liziming on 18-1-31.
 */

public class fragment_meeting_adapter extends RecyclerView.Adapter<fragment_meeting_adapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private ArrayList<Team> mData;
    private Context context;
    private Resources resources;
    public fragment_meeting_adapter(Context context, ArrayList<Team> data) {
        this.context=context;
        this.resources = context.getResources();
        this.mData = data;
    }

    public void updateData(ArrayList<Team> data) {
        this.mData = data;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_meeting_brief_message_item, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // 绑定数据

        /*Glide.with(context)
                .load(okhttpurl.url_image)
                .apply(new RequestOptions()
                .circleCrop()
                .placeholder(R.drawable.vector_drawable__meeting_image_black))
                .into(holder.meeting_image);*/
        holder.meeting_name.setText(mData.get(position).getName());
        holder.meeting_idnumber.setText(mData.get(position).getId());
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

        ImageView meeting_image;
        TextView meeting_name;
        TextView meeting_idnumber;

        public ViewHolder(View itemView) {
            super(itemView);
            meeting_image= itemView.findViewById(R.id.meeting_image);
            meeting_name=itemView.findViewById(R.id.meeting_name);
            meeting_idnumber=itemView.findViewById(R.id.meeting_idnumber);

        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        //void onItemLongClick(View view, int position);
    }

}
