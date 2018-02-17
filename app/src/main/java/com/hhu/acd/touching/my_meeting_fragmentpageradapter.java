package com.hhu.acd.touching;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liziming on 18-1-31.
 */

public class my_meeting_fragmentpageradapter extends FragmentPagerAdapter {
    private Context context;
    private List<Fragment> mFragmentList;
    protected my_meeting_fragmentpageradapter( FragmentManager fm) {

        super(fm);
        //this.context=context;
    }



    public void setFragments(ArrayList<Fragment> fragments) {
        mFragmentList = fragments;
    }



    @Override
    public Fragment getItem(int position) {

        Fragment fragment = mFragmentList.get(position);

        return fragment;
    }

    @Override
    public int getCount() {

        return mFragmentList.size();
    }
}
