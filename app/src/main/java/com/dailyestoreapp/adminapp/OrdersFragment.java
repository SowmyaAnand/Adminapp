package com.dailyestoreapp.adminapp;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dailyestoreapp.adminapp.ui.gallery.GalleryViewModel;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrdersFragment extends Fragment {
    RecyclerView recyclerView_offers;

    OrdersAdapter customAdapter_offers_orders;
   // ArrayList personNames_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
    ArrayList<String> orders_list_array_item = new ArrayList<>();
    ArrayList<String> orders_list_array_quantity = new ArrayList<>();
    ArrayList<String> orders_list_array_amount = new ArrayList<>();
    ArrayList<String> orders_list_array_date = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment2, container, false);
        orders_list();
        recyclerView_offers = (RecyclerView) root.findViewById(R.id.itemrecycler_offers);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext());
        recyclerView_offers.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter

        customAdapter_offers_orders = new OrdersAdapter(root.getContext(), orders_list_array_item,orders_list_array_quantity,orders_list_array_amount,orders_list_array_date);
        recyclerView_offers.setAdapter(customAdapter_offers_orders);

        return root;
    }
    private void orders_list()
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
        Call<ListCategoryResponse> call = mainInterface.orderslist();
        call.enqueue(new Callback<ListCategoryResponse>() {
            @Override
            public void onResponse(Call<ListCategoryResponse> call, retrofit2.Response<ListCategoryResponse> response) {
                ListCategoryResponse listCategoryResponseobject = response.body();
                int success = Integer.parseInt(response.body().getResponsedata().getSuccess());
                try {

                    orders_list_array_item.clear();
                    if(success==1)
                    {
                        int data_length = response.body().getResponsedata().getData().size();



                        for(int i=0; i<data_length; i++)
                        {

                            String item_name = response.body().getResponsedata().getData().get(i).getItemName();
                            if(!orders_list_array_item.contains(item_name))
                            {

                                ListCategoryResponseData dt = response.body().getResponsedata().getData().get(i);
                                String itemname_orders = "ItemName";
                                String quantity = dt.getQuantity();
                                String amount = dt.getPrice();
                                String date = dt.getCreatedAt();
                                orders_list_array_quantity.add(quantity);
                                orders_list_array_item.add(itemname_orders);
                                orders_list_array_amount.add(amount);
                                orders_list_array_date.add(date);
                            }

                        }

                        customAdapter_offers_orders.notifyDataSetChanged();
                    }
                    else {
                        Toast.makeText(getContext(),"No Data found",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(),"something went wrong",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ListCategoryResponse> call, Throwable t) {

            }
        });


    }
}
