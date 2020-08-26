package com.dailyestoreapp.adminapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment4 extends Fragment   {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList Images_offers = new ArrayList<>(Arrays.asList(R.drawable.h1,R.drawable.h2, R.drawable.h1, R.drawable.h2, R.drawable.h1));
    ArrayList Images_images = new ArrayList<>(Arrays.asList(R.drawable.home,R.drawable.home, R.drawable.home, R.drawable.home, R.drawable.home,R.drawable.home));
    ArrayList personNames = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6"));

    ArrayList personNames_offers = new ArrayList<>(Arrays.asList("farg4ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6"));
    RecyclerView recyclerView_offers,itemlistingcategory_offers;
    LinearLayoutManager linearLayoutManager_offers,linearLayoutManager2_offers;
    Offers_ItemAdapter customAdapter_offers;
    test customadapter2_offers;
 private static String cat_selected ;
    private static Integer cat_number ;
    public Fragment4(String categoryy,Integer cat_no) {
cat_selected=categoryy;
cat_number=cat_no;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment4 newInstance(String param1,String param2) {
        Fragment4 fragment = new Fragment4(param1,1);
        Bundle args = new Bundle();
        args.putString("category", param1);
        fragment.setArguments(args);
        return fragment;
    }

public void pageselect(String param)
{cat_selected=param;

}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            String st = getArguments().getString("category");


            Log.e("fragment2","oncreate"+cat_selected);
            Log.e("fragment","oncreate"+cat_number);
        }

    }
//    public void pass(String ct) {
//        Log.e("paramsss","paramsss"+ct);
//        cat_selected=ct;
//        Log.e("paramsss","paramsss"+ cat_selected);
//    }
//    public void callOnlyService(){
////this.cat_selected=this.getArguments().getString("category");
//       // Log.e("frg","cat vlaue in callonly service"+this.cat_selected);
////        isCategory1AlreadyCalled = true;
////        new NotificationUtility(getActivity());
////        new MessageAPI().execute();
////        //   messageAPI();
////        new GetDataListAPI().execute(categoryId, fromDate, toDate);
//
//
//
//    }
public void change()
{  Log.e("fragment2","change"+this.cat_selected);
   // personNames_offers = new ArrayList<>(Arrays.asList("farg4ITEM1", "frag4ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6"));

}
    private void Activate()
    {
        Log.e("fragment2","Inside Activate"+cat_number);
        int type = cat_number;
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
        Call<ListCategoryResponse> call = mainInterface.SubCategory(1);
        call.enqueue(new Callback<ListCategoryResponse>() {
            @Override
            public void onResponse(Call<ListCategoryResponse> call, retrofit2.Response<ListCategoryResponse> response) {
                String res= new GsonBuilder().setPrettyPrinting().create().toJson(response.body().getResponsedata());
                JsonObject obj = new JsonParser().parse(res).getAsJsonObject();
                try {
                    JSONObject jo2 = new JSONObject(obj.toString());
                    JSONArray categoriesarray = jo2.getJSONArray("data");
                    Log.e("Fragment4","subcategories="+jo2);


                    //personNames_offers = new ArrayList<>(Arrays.asList("farg4ITEM1", "frag4ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ListCategoryResponse> call, Throwable t) {

            }
        });


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        change();
        Activate();
        if (getArguments() != null) {
            mParam1 = getArguments().getString("category");


            Log.e("fragment2","**"+this.cat_selected);
            Log.e("fragment2","**"+this.cat_number);
        }
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        itemlistingcategory_offers = (RecyclerView) rootView.findViewById(R.id.recyclerView_categories_offer);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager2_offers = new LinearLayoutManager(rootView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        itemlistingcategory_offers.setLayoutManager(linearLayoutManager2_offers);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        customadapter2_offers = new test(rootView.getContext(), personNames,Images_offers,communication);
        itemlistingcategory_offers.setAdapter(customadapter2_offers);
        //second recyclerview
        recyclerView_offers = (RecyclerView) rootView.findViewById(R.id.itemrecycler_offers);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView_offers.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter

        customAdapter_offers = new Offers_ItemAdapter(rootView.getContext(), personNames_offers,Images_images);
        recyclerView_offers.setAdapter(customAdapter_offers);
        // GridView gridview = (GridView) rootView.findViewById(R.id.gridview);
        // gridview.setAdapter(new ImageAdapter(rootView.getContext()));
        return rootView;
    }
    categorySubcategoryCommunicaion communication=new categorySubcategoryCommunicaion() {
        @Override
        public void respond(String name) {
            Log.e("name","name is"+name);
            personNames_offers.clear();
            personNames_offers.add("Item7");
            personNames_offers.add("Item8");
            personNames_offers.add("Item9");

            customAdapter_offers.notifyDataSetChanged();

           //Toast.makeText(getContext(),name,Toast.LENGTH_LONG).show();
        }

    };
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            cat_selected = getArguments().getString("category");
        }
        Log.e("frag","onactivitycreated  category from pass"+ cat_selected);
//
//
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString("category");
//
//            Log.e("fragment","oncreateview called"+mParam1);
//        }

    }




}
