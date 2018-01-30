package com.hhu.acd.touching;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.stfalcon.multiimageview.MultiImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liziming on 18-1-25.
 */

public class mainactivity_5_linkman extends AppCompatActivity {
    private ExpandableListView listview;
    private ArrayList<linkman_group> list = new ArrayList<linkman_group>();
    int[] img = new int[6];
    MultiImageView my_portrait;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity_5_linkman);
        initData();
        listview = (ExpandableListView) findViewById(R.id.linkman_expand_list);
        linkman_adapter adapter = new linkman_adapter(this,list);
        listview.setAdapter(adapter);
        listview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(getApplicationContext(),list.get(i).getChild(i1).getNickName(), Toast.LENGTH_SHORT).show();

                return true;
            }
        });
        my_portrait=findViewById(R.id.my_head_portrait);
        my_portrait.addImage(BitmapFactory.decodeResource(getResources(),R.drawable.bussiness_man));
        my_portrait.setShape(MultiImageView.Shape.CIRCLE);
    }
    private void initData() {
        for (int i = 0; i < img.length; i++) {
            try {
                //img[i] = R.drawable.class.getField("img0"+(i+1)).getInt(null);
                img[i]=R.drawable.bussiness_man;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        linkman_group group1 = new linkman_group("我的关注");
        group1.addUser(new linkman_users(img[0], "张翰", true));
        group1.addUser(new linkman_users(img[1], "郑爽", false));
        group1.addUser(new linkman_users(img[2], "胡彦斌", true));
        group1.addUser(new linkman_users(img[5], "撒贝宁", true));
        group1.addUser(new linkman_users(img[3], "杨幂", false));

        linkman_group group2 = new linkman_group("我的好友");
        group2.addUser(new linkman_users(img[4], "林志炫", true));

        list.add(group1);
        list.add(group2);

    }

}
