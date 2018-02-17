package com.hhu.acd.touching;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liziming on 18-1-25.
 */

public class mainactivity_6_my_meeting extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String[] mTitles = new String[]{"我参加的", "我发布的","我收藏的", "我的笔记"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity_6_my_meeting);

        initView(); // 初始化控件
        initViewPager(); // 初始化ViewPager
    }
    /**
     * 初始化控件
     */
    private void initView() {
        mTabLayout = (TabLayout)findViewById(R.id.my_meeting_tl);
        mViewPager = (ViewPager)findViewById(R.id.my_meeting_vp);
    }

    private void initViewPager() {
        // 创建一个集合,装填Fragment
        ArrayList<Fragment> fragments = new ArrayList<>();
        // 装填
        fragments.add(new fragment_meeting());
        fragments.add(new fragment_meeting());
        fragments.add(new fragment_meeting());
        fragments.add(new fragment_meeting_notes());
        // 创建ViewPager适配器
        my_meeting_fragmentpageradapter myPagerAdapter = new my_meeting_fragmentpageradapter(getSupportFragmentManager());
        myPagerAdapter.setFragments(fragments);
        // 给ViewPager设置适配器
        mViewPager.setAdapter(myPagerAdapter);
        // TabLayout 指示器 (记得自己手动创建4个Fragment,注意是 app包下的Fragment 还是 V4包下的 Fragment)
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        // 使用 TabLayout 和 ViewPager 相关联
        mTabLayout.setupWithViewPager(mViewPager);
        // TabLayout指示器添加文本
        mTabLayout.getTabAt(0).setText(mTitles[0]);
        mTabLayout.getTabAt(1).setText(mTitles[1]);
        mTabLayout.getTabAt(2).setText(mTitles[2]);
        mTabLayout.getTabAt(3).setText(mTitles[3]);
    }
}
