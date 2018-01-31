package com.hhu.acd.touching;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by liziming on 18-1-31.
 */

public class my_meeting_fragmentpageradapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"我参加的", "我发布的","我收藏的", "我的笔记"};
    public my_meeting_fragmentpageradapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new fragment_meeting();
        } else if (position == 2) {
            return new fragment_meeting_notes();
        }
        return new fragment_meeting();
    }

    @Override
    public int getCount() {
        return 0;
    }
}
