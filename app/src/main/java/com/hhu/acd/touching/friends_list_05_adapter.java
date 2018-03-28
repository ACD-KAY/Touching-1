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
import com.netease.nimlib.sdk.uinfo.UserInfoProvider;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;
import com.stfalcon.multiimageview.MultiImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liziming on 18-3-24.
 */

public class friends_list_05_adapter extends RecyclerView.Adapter<friends_list_05_adapter.ViewHolder>{
//private OnItemClickListener onItemClickListener;

    private List<NimUserInfo> mData;
    private Context context;
    private Resources resources;
    public friends_list_05_adapter(Context context, List<NimUserInfo> data) {
        this.context=context;
        this.resources = context.getResources();
        this.mData = data;
        }

    public void updateData(List<NimUserInfo> data) {
        this.mData = data;
        notifyDataSetChanged();
        }
    /*public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }*/

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_item, parent, false);
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
        Glide.with(context)

                .load(okhttpurl.url_image)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.face))
                .into(holder.friends_item_portrait);
        holder.friends_item_portrait.setShape(MultiImageView.Shape.CIRCLE);
        holder.friend_name.setText(mData.get(position).getName());

        }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
        }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    //private final WeakReference<Activity_02_2> mActivity;
        MultiImageView friends_item_portrait;
        TextView friend_name;


        public ViewHolder(View itemView) {
            super(itemView);
            friends_item_portrait= itemView.findViewById(R.id.friends_item_portrait);
            friend_name=itemView.findViewById(R.id.friend_name);


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
