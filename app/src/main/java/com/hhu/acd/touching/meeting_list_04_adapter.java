package com.hhu.acd.touching;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.stfalcon.multiimageview.MultiImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import cn.bingoogolapple.badgeview.BGABadgeTextView;

/**
 * Created by liziming on 18-3-23.
 */

public class meeting_list_04_adapter extends RecyclerView.Adapter<meeting_list_04_adapter.ViewHolder>{
//private OnItemClickListener onItemClickListener;
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM");
private ArrayList<Meetings> mData;
private Context context;
private Resources resources;
public meeting_list_04_adapter(Context context, ArrayList<Meetings> data) {
        this.context=context;
        this.resources = context.getResources();
        this.mData = data;
        }

public void updateData(ArrayList<Meetings> data) {
        this.mData = data;
        notifyDataSetChanged();
        }
    /*public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }*/

@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_meeting_brief_item, parent, false);
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
    /*Glide.with(context)
            .load(okhttpurl.url_image)
            .apply(new RequestOptions()
            .placeholder(R.drawable.face))
            .into(holder.meeting_brief_item_portrait);*/
    holder.meeting_brief_item_portrait.setShape(MultiImageView.Shape.CIRCLE);
    holder.meeting_brief_item_name.setText(mData.get(position).getTname());
    holder.meeting_brief_item_place.setText(mData.get(position).getPlace());
    holder.meeting_brief_item_time.setText(sdf.format(mData.get(position).getStart())+"-"+sdf.format(mData.get(position).getEnd()));

        }

@Override
public int getItemCount() {
        return mData == null ? 0 : mData.size();
        }

public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    //private final WeakReference<Activity_02_2> mActivity;
    MultiImageView meeting_brief_item_portrait;
    TextView meeting_brief_item_name;
    TextView meeting_brief_item_place;
    TextView meeting_brief_item_time;

    public ViewHolder(View itemView) {
        super(itemView);
        meeting_brief_item_portrait= itemView.findViewById(R.id.meeting_brief_item_portrait);
        meeting_brief_item_name=itemView.findViewById(R.id.meeting_brief_item_name);
        meeting_brief_item_place=itemView.findViewById(R.id.meeting_brief_item_place);
        meeting_brief_item_time=itemView.findViewById(R.id.meeting_brief_item_time);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}


    /*public interface OnItemClickListener {
        void onItemClick(View view, int position);
        //void onItemLongClick(View view, int position);
    }*/


}

