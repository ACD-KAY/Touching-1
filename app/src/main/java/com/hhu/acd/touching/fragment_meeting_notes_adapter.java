package com.hhu.acd.touching;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by liziming on 18-2-2.
 */

public class fragment_meeting_notes_adapter extends RecyclerView.Adapter<fragment_meeting_notes_adapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private ArrayList<notes_data> mData;
    private Context context;
    private Resources resources;
    public fragment_meeting_notes_adapter(Context context, ArrayList<notes_data> data) {
        this.context=context;
        this.resources = context.getResources();
        this.mData = data;
    }

    public void updateData(ArrayList<notes_data> data) {
        this.mData = data;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_meeting_notes_item, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // 绑定数据
        /*Bitmap bitmap= BitmapFactory.decodeResource(resources,mData.get(position).getImgurl());
        holder.meeting_image.addImage(bitmap);
        holder.meeting_image.setShape(MultiImageView.Shape.CIRCLE);
        holder.meeting_name.setText(mData.get(position).getMeeting_name());*/
        holder.note_time.setText(mData.get(position).getNotestime());
        holder.note_content.setText(mData.get(position).getNotescontent());

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

        //MultiImageView meeting_image;
        TextView note_time;
        TextView note_content;
        //TextView meeting_place;
        public ViewHolder(View itemView) {
            super(itemView);
            //meeting_image= itemView.findViewById(R.id.meeting_image);
            //meeting_name=itemView.findViewById(R.id.meeting_name);
            note_time=itemView.findViewById(R.id.meeting_notes_time);
            note_content=itemView.findViewById(R.id.meeting_notes_content);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        //void onItemLongClick(View view, int position);
    }

}
