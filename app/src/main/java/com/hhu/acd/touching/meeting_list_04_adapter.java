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

import java.util.ArrayList;

import cn.bingoogolapple.badgeview.BGABadgeTextView;

/**
 * Created by liziming on 18-3-23.
 */

public class meeting_list_04_adapter extends RecyclerView.Adapter<meeting_list_04_adapter.ViewHolder>{
//private OnItemClickListener onItemClickListener;

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
        //String URL="http://106.14.195.234/springmvc/images/000.jpeg";
        // 绑定数据
        //Bitmap bitmap= BitmapFactory.decodeResource(resources,mData.get(position));
        //holder.chat_item_portrait.addImage(bitmap);
        /*Glide.with(context)

        .load(okhttpurl.url_image)
        .apply(new RequestOptions()
        .placeholder(R.drawable.face))
        .into(holder.chat_item_portrait);

        holder.chat_item_portrait.setShape(MultiImageView.Shape.CIRCLE);
        holder.name.setText(mData.get(position).getContactId());
        holder.message.setText(mData.get(position).getContent());
        holder.time.setText(TimeUtil.getFormatTime(mData.get(position).getTime()));
        int unread=mData.get(position).getUnreadCount();
        if (unread != 0) {
        holder.messageNum.showCirclePointBadge();
        holder.messageNum.showTextBadge(unread + "");
        }*/
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            }
        });*/
        //holder.chat_item_portrait.setImageBitmap(BitmapFactory.decodeResource(resources,R.drawable.bussiness_man));
        //holder.name.setText(mData.get(position).getName());
        //holder.message.setText(mData.get(position).getMassage());
        // holder.time.setText(TimeUtil.getFormatTime(600000000));
        /*int unread=6;
        if (unread != 0) {
            holder.messageNum.showCirclePointBadge();
            holder.messageNum.showTextBadge(unread + "");
        }*/
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            }
        });*/

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
            case R.id.main:
                //Toast.makeText(v.getContext(), "点击了main，位置为：" + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.delete:
                int pos1 = getAdapterPosition();
                mData.remove(pos1);
                notifyItemRemoved(pos1);
                break;
            case R.id.stick:
                int pos2 = getAdapterPosition();
                mData.add(0,mData.get(pos2));
                mData.remove(pos2+1);
                notifyDataSetChanged();
        }
    }
}


    /*public interface OnItemClickListener {
        void onItemClick(View view, int position);
        //void onItemLongClick(View view, int position);
    }*/


}

