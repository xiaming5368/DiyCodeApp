package com.android.diy.app.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.diy.app.ui.fragment.NewsFragment;
import com.android.diy.app.ui.fragment.SiteFragment;
import com.android.diy.app.ui.fragment.TopicFragment;

/**
 * Created by cheng on 2017/1/19.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TopicFragment();
            case 1:
                return new NewsFragment();
            case 2:
                return new SiteFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "TOPICS";
            case 1:
                return "NEWS";
            case 2:
                return "SITES";
            default:
                return null;
        }
    }
}
