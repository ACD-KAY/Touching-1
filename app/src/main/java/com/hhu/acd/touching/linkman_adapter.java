package com.hhu.acd.touching;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.stfalcon.multiimageview.MultiImageView;

import java.util.List;

/**
 * Created by liziming on 18-1-29.
 */

public class linkman_adapter extends BaseExpandableListAdapter {
    Context context;
    List<linkman_group> list;
    public linkman_adapter(Context context,List<linkman_group> linkman_groups){
        this.context=context;
        this.list=linkman_groups;
    }



    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getChildCount();
    }

    @Override
    public linkman_group getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public linkman_users getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getChild(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        GroupHolder holder;
        if(convertView == null){
            convertView = View.inflate(context, R.layout.linkman_group, null);
            holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (GroupHolder) convertView.getTag();
        }
        //设置数据
        linkman_group group = getGroup(groupPosition);
        holder.groupName.setText(group.groupName);
        holder.groupOnline.setText(group.getOnlineCount()+"/"+getChildrenCount(groupPosition));

        return convertView;
    }
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        if(convertView == null){
            convertView = View.inflate(context, R.layout.linkman_child, null);
            holder = new ChildHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ChildHolder) convertView.getTag();
        }
        //设置数据
        linkman_users user = getGroup(groupPosition).getChild(childPosition);
        holder.img.addImage(BitmapFactory.decodeResource(context.getResources(),user.getImgId()));
        holder.nickName.setText(user.getNickName());
        holder.img.setShape(MultiImageView.Shape.CIRCLE);
        //holder.online.setText(user.isOnline()?"[在线]":"[离线]");
       // holder.sign.setText(user.getSign());

        return convertView;

    }
  /*  @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }*/




    static class GroupHolder{
        TextView groupName;
        TextView groupOnline;

        public GroupHolder(View convertView){
            groupName = (TextView) convertView.findViewById(R.id.groupName);
            groupOnline = (TextView) convertView.findViewById(R.id.groupOnline);

        }
    }
    static class ChildHolder {
        com.stfalcon.multiimageview.MultiImageView img;
        TextView nickName;


        public ChildHolder(View convertView) {
            img = convertView.findViewById(R.id.linkman_portrait);
            nickName = convertView.findViewById(R.id.linkman_name);

        }
    }
}
