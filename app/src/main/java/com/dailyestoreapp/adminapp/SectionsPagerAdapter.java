package com.dailyestoreapp.adminapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.category1, R.string.category4, R.string.category7, R.string.category2,R.string.category5, R.string.category6, R.string.category3, R.string.category8,R.string.category9};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }
    @Override
    public int getCount() {
        return 8;
    }
    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);

    }
}
