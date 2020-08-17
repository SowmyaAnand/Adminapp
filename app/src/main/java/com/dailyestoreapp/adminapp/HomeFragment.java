package com.dailyestoreapp.adminapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.dailyestoreapp.adminapp.ui.home.HomeViewModel;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getContext(),getChildFragmentManager());
        sectionsPagerAdapter.addFragment(new Fragment1(),"Home");
        sectionsPagerAdapter.addFragment(new Fragment4(),"Offers");
        sectionsPagerAdapter.addFragment(new fragment3(),"Contact");
        sectionsPagerAdapter.addFragment(new Fragment4(),"My Account");
        sectionsPagerAdapter.addFragment(new fragment3(),"Home");
        sectionsPagerAdapter.addFragment(new fragment3(),"Offers");
        sectionsPagerAdapter.addFragment(new Fragment4(),"Contact");
        sectionsPagerAdapter.addFragment(new fragment3(),"My Account");
        ViewPager viewPager =root.findViewById(R.id.view_pager2);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = root.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        return root;
    }
}
