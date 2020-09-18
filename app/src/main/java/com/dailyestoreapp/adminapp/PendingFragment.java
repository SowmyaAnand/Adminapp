package com.dailyestoreapp.adminapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dailyestoreapp.adminapp.ui.Messages.MessagesViewModel;

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

public class PendingFragment extends Fragment {
    RecyclerView recyclerView_offers;
ACProgressFlower dialog;
    PendingNotificationAdapter customAdapter_offers_pending;
   // ArrayList personNames_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
   ArrayList<String> item_image_pending = new ArrayList<>();
    ArrayList<String> pending_orders_list_array_item = new ArrayList<>();
    ArrayList<String> pending_orders_list_array_address = new ArrayList<>();
    ArrayList<String> pending_orders_list_array_satus = new ArrayList<>();
    ArrayList<String> orders_list_array_quantity_pending = new ArrayList<>();
    ArrayList<String> orders_list_array_amount_pending = new ArrayList<>();
    ArrayList<Integer> pending_orders_list_array_orderid = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.pendingrequests, container, false);
        pending_orders_list();
        recyclerView_offers = (RecyclerView) root.findViewById(R.id.itemrecycler_offers_pending);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext());
        recyclerView_offers.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter

        customAdapter_offers_pending = new PendingNotificationAdapter(root.getContext(),pending_orders_list_array_address,pending_orders_list_array_item,pending_orders_list_array_satus,pending_orders_list_array_orderid,orders_list_array_quantity_pending,orders_list_array_amount_pending,item_image_pending);
        recyclerView_offers.setAdapter(customAdapter_offers_pending);
        return root;

    }
    private void pending_orders_list()
    {
        dialog = new ACProgressFlower.Builder(getContext())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .borderPadding(1)
                .fadeColor(Color.WHITE).build();
        dialog.show();

        String url = "http://dailyestoreapp.com/dailyestore/api/";
        final String url1 = "http://dailyestoreapp.com/dailyestore/";
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

                    pending_orders_list_array_item.clear();
                    pending_orders_list_array_address.clear();
                    pending_orders_list_array_satus.clear();
                    if(success==1)
                    {
                        int data_length = response.body().getResponsedata().getData().size();



                        for(int i=0; i<data_length; i++)
                        {
                            ListCategoryResponseData dt = response.body().getResponsedata().getData().get(i);
                            String item_name = dt.getItemName();
                            String status = dt.getStatus();
                            int st = Integer.parseInt(status);
                            if(st == 0)
                            {


                                String itemname_orders = dt.getItemName();
                                String address = dt.getAddress();
                                String orderid = dt.getOrderId();
                                int order_no = Integer.parseInt(orderid);
                                String quantity_pending = dt.getQuantity();
                                String amount_pending = dt.getPrice();
                                String imageurl = dt.getImage();
                                String imageurl_total=url1+imageurl;
                                pending_orders_list_array_satus.add(status);
                                item_image_pending.add(imageurl_total);
                                pending_orders_list_array_address.add(address);
                                pending_orders_list_array_item.add(itemname_orders);
                                pending_orders_list_array_orderid.add(order_no);
                                orders_list_array_quantity_pending.add(quantity_pending);
                                orders_list_array_amount_pending.add(amount_pending);
                            }

                        }

                        customAdapter_offers_pending.notifyDataSetChanged();
                    }
                    else {
                        Toast.makeText(getContext(),"No Data found",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(),"something went wrong",Toast.LENGTH_SHORT).show();
                }
dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ListCategoryResponse> call, Throwable t) {
dialog.dismiss();
            }
        });


    }
}
