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
import java.util.StringTokenizer;

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

    ArrayList<Integer> categoriesHomeNo2 = new ArrayList<>();
Fragment4 frag4;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);

        SharedPreferences shared = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        Set<String> set = shared.getStringSet("categories", null);
        categoriesHome.addAll(set);

        SharedPreferences shared2 = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String savedString = shared2.getString("categories_no", "");
//        StringTokenizer st = new StringTokenizer(savedString, ",");
//        int[] savedList = new int[10];
//        for (int i = 0; i < 10; i++) {
//            savedList[i] = Integer.parseInt(st.nextToken());
//        }
        String[] numbers = savedString.split(",");//if spaces are uneven, use \\s+ instead of " "
        for (String number : numbers) {
            categoriesHomeNo2.add(Integer.valueOf(number));
        }
        Log.e("cat_home","cat home num="+categoriesHomeNo2);
category_selected=categoriesHome.get(0);



        final SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getContext(),getChildFragmentManager(),categoriesHome);
       // frag4 = new Fragment4();
        for(int i = 0;i<categoriesHome.size();i++)
        {
category_selected=categoriesHome.get(i);


            if(category_selected.equals("Food"))
            {
                Bundle bundle = new Bundle();
                bundle.putString("category", category_selected);
                Fragment1 mapFragment1 = new Fragment1(category_selected);
                mapFragment1.setArguments(bundle);

                Log.e("tag","tag cat"+categoriesHome.get(0));
                sectionsPagerAdapter.addFragment(mapFragment1,categoriesHome.get(i));


            }
           else if (i==0)
            {
                Bundle bundle = new Bundle();
                bundle.putString("category", category_selected);
                Fragment4 mapFragment2 = new Fragment4(category_selected,categoriesHomeNo2.get(i));
                mapFragment2.setArguments(bundle);


                Log.e("tag","tag cat fragment4"+categoriesHome.get(i));

                sectionsPagerAdapter.addFragment(mapFragment2,categoriesHome.get(i));

            }
            else
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

        return root;
    }



}
