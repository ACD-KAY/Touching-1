package com.hhu.acd.touching;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liziming on 18-1-29.
 */

public class linkman_group {
    //分组名
    public String groupName;
    //有很多User
    public List<linkman_users> list;

    public linkman_group(String groupName){
        this.groupName = groupName;
        list = new ArrayList<linkman_users>();
    }
    //添加User
    public void addUser(linkman_users user){
        list.add(user);
    }

    //获取某个分组中User的数量
    public int getChildCount() {
        return list.size();
    }
    //获取某个分组中User在线的数量
    public int getOnlineCount(){
        int sum = 0;
        for (linkman_users user : list) {
            if(user.isOnline()){
                sum++;
            }
        }
        return sum;
    }
    //获取分组中某个孩子
    public linkman_users getChild(int childPosition) {
        return list.get(childPosition);
    }
}
