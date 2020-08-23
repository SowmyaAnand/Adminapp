package com.dailyestoreapp.adminapp;

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
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HomeFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ArrayList<String> categories = new ArrayList<>();

        View root = inflater.inflate(R.layout.fragment_home, container, false);
Activate();
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getContext(),getChildFragmentManager());
        sectionsPagerAdapter.addFragment(new Fragment1(),"Home");
        sectionsPagerAdapter.addFragment(new Fragment4(),"Offers");
        sectionsPagerAdapter.addFragment(new Fragment4(),"Contact");
        sectionsPagerAdapter.addFragment(new Fragment4(),"My Account");
        sectionsPagerAdapter.addFragment(new Fragment4(),"Home");
        sectionsPagerAdapter.addFragment(new Fragment4(),"Offers");
        sectionsPagerAdapter.addFragment(new Fragment4(),"Contact");
        sectionsPagerAdapter.addFragment(new Fragment4(),"My Account");
        ViewPager viewPager =root.findViewById(R.id.view_pager2);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = root.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        return root;
    }
    private void Activate()
    {
        String url = "http://dailyestoreapp.com/dailyestore/api/";
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        ResponseInterface1 mainInterface = retrofit.create(ResponseInterface1.class);
        Call<ListCategoryResponse> call = mainInterface.CategoryList();
        call.enqueue(new Callback<ListCategoryResponse>() {
            @Override
            public void onResponse(Call<ListCategoryResponse> call, retrofit2.Response<ListCategoryResponse> response) {
                String res= new GsonBuilder().setPrettyPrinting().create().toJson(response.body().getResponsedata().getData());


                Log.e("response je","post result"+res);
            }

            @Override
            public void onFailure(Call<ListCategoryResponse> call, Throwable t) {

                Log.e("error","er"+t.getMessage());
            }
        });


    }
//    private void parseJson(JSONArray jsonArray) {
//        for(int i =0; i<jsonArray.length();i++)
//        {
//            try {
//                JSONObject object = jsonArray.getJSONObject(i);
//                Items dt = new Items();
//                dt.setImage(object.getString("download_url"));
//                dt.setName(object.getString("author"));
//                dataarraylist.add(dt);
//                customAdapter_offers = new Offers_ItemAdapter(getApplicationContext(),dataarraylist);
//                recyclerView_offers.setAdapter(customAdapter_offers);
//            } catch (JSONException e) {
//                e.printStackTrace();
//                Log.e("error","er"+e);
//            }
//        }
//    }

}
