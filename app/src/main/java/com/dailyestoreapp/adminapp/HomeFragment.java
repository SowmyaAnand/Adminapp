package com.dailyestoreapp.adminapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dailyestoreapp.adminapp.ui.home.HomeViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Set;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {
    String category_selected;
    public static final String MY_PREFS_NAME = "AdminApp";
    ArrayList<String> categoriesHome = new ArrayList<>();
Fragment4 frag4;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);

        SharedPreferences shared = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        Set<String> set = shared.getStringSet("categories", null);
        categoriesHome.addAll(set);
        Log.e("cat","cat home"+categoriesHome);
category_selected=categoriesHome.get(0);
Log.e("cat","cat_home selected"+category_selected);

        final SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getContext(),getChildFragmentManager(),categoriesHome);
       // frag4 = new Fragment4();
        for(int i = 0;i<categoriesHome.size();i++)
        {
category_selected=categoriesHome.get(i);
            if(i==0)
            {
                Bundle bundle = new Bundle();
                bundle.putString("category", category_selected);
                Fragment1 mapFragment1 = new Fragment1(category_selected);
                mapFragment1.setArguments(bundle);

                Log.e("tag","tag cat"+categoriesHome.get(0));
                sectionsPagerAdapter.addFragment(mapFragment1,categoriesHome.get(i));


            }
            else if(i==1)
            {
                Bundle bundle = new Bundle();
                bundle.putString("category", category_selected);
                Fragment4 mapFragment2 = new Fragment4(category_selected);
                mapFragment2.setArguments(bundle);


                Log.e("tag","tag cat"+categoriesHome.get(i));
                sectionsPagerAdapter.addFragment(mapFragment2,categoriesHome.get(i));

           }
            else if(i==2)
            {
                Bundle bundle = new Bundle();
                bundle.putString("category", category_selected);
                fragment3 mapFragment3 = new fragment3(category_selected);
                mapFragment3.setArguments(bundle);


                Log.e("tag","tag cat"+categoriesHome.get(i));
                sectionsPagerAdapter.addFragment(mapFragment3,categoriesHome.get(i));
            }
        }

        ViewPager viewPager =root.findViewById(R.id.view_pager2);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = root.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                category_selected = String.valueOf(sectionsPagerAdapter.getPageTitle(position));
//                Log.e("onpageselected",""+sectionsPagerAdapter.getPageTitle(position));
//                Bundle bundle = new Bundle();
//                bundle.putString("category", category_selected);
//                Fragment4 p = new Fragment4(String.valueOf(sectionsPagerAdapter.getPageTitle(position)));
//                p.setArguments(bundle);
//                p.pageselect(category_selected);
//
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//            Log.e("home","tab selected"+tab.getPosition());
//           // category_selected=categoriesHome.get(tab.getPosition());
//                category_selected=categoriesHome.get(tab.getPosition());
//                Log.e("cat","cat_home selected"+category_selected);
//                Bundle bundle = new Bundle();
//                bundle.putString("category", category_selected);
//                Fragment4 mapFragment = new Fragment4();
//                mapFragment.setArguments(bundle);
//
//                SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getContext(),getChildFragmentManager(),categoriesHome);
//
//                sectionsPagerAdapter.addFragment(mapFragment,category_selected);
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
        return root;
    }



}
