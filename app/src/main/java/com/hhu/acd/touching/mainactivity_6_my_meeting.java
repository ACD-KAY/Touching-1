package com.hhu.acd.touching;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liziming on 18-1-25.
 */

public class mainactivity_6_my_meeting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity_6_my_meeting);

        ViewPager vp = findViewById(R.id.my_meeting_vp);

        TabLayout tabLayout =  findViewById(R.id.my_meeting_tl);

        List<String> list = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
//            list.add(String.format(Locale.CHINA,"第02d%页",i));
            list.add("第"+i+"页");
        }
        //vp.setAdapter(new MyAdapter(getSupportFragmentManager(),list));

        tabLayout.setupWithViewPager(vp);
    }
}
