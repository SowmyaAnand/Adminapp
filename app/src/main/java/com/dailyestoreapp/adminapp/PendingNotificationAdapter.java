package com.dailyestoreapp.adminapp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.dailyestoreapp.adminapp.R.color.green;
import static com.dailyestoreapp.adminapp.R.color.white;

public class PendingNotificationAdapter extends RecyclerView.Adapter<PendingNotificationAdapter.MyViewHolder> {
    //ArrayList<String> pending_orders_list_array_item = new ArrayList<String>();
    Context context;
  Integer orderid_adapter;
  ACProgressFlower  dialog;
    ArrayList<String> lts=new ArrayList<String>();
    ArrayList<String> pending_orders_list_array_item = new ArrayList<>();
    ArrayList<String> pending_orders_list_array_address = new ArrayList<>();
    ArrayList<String> pending_orders_list_array_quantity = new ArrayList<>();
    ArrayList<String> pending_orders_list_array_amount = new ArrayList<>();
    ArrayList<String> pending_orders_list_array_satus = new ArrayList<>();
    ArrayList<Integer> pending_orders_list_array_orderid = new ArrayList<>();
    //ArrayList pending_orders_list_array_item_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
    int quantity=1;
    public PendingNotificationAdapter(Context context, ArrayList<String> pending_orders_list_array_address,ArrayList<String> pending_orders_list_array_item, ArrayList<String> pending_orders_list_array_satus, ArrayList<Integer> pending_orders_list_array_orderid,ArrayList<String> pending_orders_list_array_qnty,ArrayList<String> pending_orders_list_array_amnt) {
        this.context = context;
        this.pending_orders_list_array_address=pending_orders_list_array_address;
                this.pending_orders_list_array_item=pending_orders_list_array_item;
                        this.pending_orders_list_array_satus=pending_orders_list_array_satus;
                        this.pending_orders_list_array_orderid = pending_orders_list_array_orderid;
                        this.pending_orders_list_array_quantity=pending_orders_list_array_qnty;
                        this.pending_orders_list_array_amount=pending_orders_list_array_amnt;
        this.lts.addAll(pending_orders_list_array_item);

    }
    @Override
    public PendingNotificationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pendingofferitems, parent, false);
        // set the view's size, margins, paddings and layout parameters
        PendingNotificationAdapter.MyViewHolder vh = new PendingNotificationAdapter.MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(final PendingNotificationAdapter.MyViewHolder holder, final int position) {

        // set the data in items
        String name = pending_orders_list_array_item.get(position);
        holder.name_pending.setText(name);
        String address = pending_orders_list_array_address.get(position);
        holder.address_pending.setText(address);
        orderid_adapter = pending_orders_list_array_orderid.get(position);
        String qty = "QUANTITY:"+pending_orders_list_array_quantity.get(position);
        String pr = "PRICE:"+pending_orders_list_array_amount.get(position);
        holder.q_pending.setText(qty);
        holder.p_pending.setText(pr);

        holder.pd_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.pd_pending.setText("Approved");
                holder.pd_pending.setTextColor(ContextCompat.getColor(context, white));
                holder.pd_pending.setBackgroundColor(ContextCompat.getColor(context, green));
               // Toast.makeText(context,"Aproved",Toast.LENGTH_LONG).show();
                dialog = new ACProgressFlower.Builder(context)
                        .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                        .borderPadding(1)
                        .fadeColor(Color.WHITE).build();
                dialog.show();
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
                Call<ListCategoryResponse> call = mainInterface.changeOrderStatus(orderid_adapter,1);
                call.enqueue(new Callback<ListCategoryResponse>() {
                    @Override
                    public void onResponse(Call<ListCategoryResponse> call, retrofit2.Response<ListCategoryResponse> response) {
                        ListCategoryResponse listCategoryResponseobject = response.body();
                        int success = Integer.parseInt(response.body().getResponsedata().getSuccess());
                        try {


                            if(success==1)
                            {
                                holder.pd_pending.setText("Approved");
                                holder.pd_pending.setTextColor(ContextCompat.getColor(context, white));
                                holder.pd_pending.setBackgroundColor(ContextCompat.getColor(context, green));
                                Toast.makeText(context,"Aproved",Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(context,"No Data found",Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(context,"something went wrong",Toast.LENGTH_SHORT).show();
                        }



                        dialog.dismiss();

                    }

                    @Override
                    public void onFailure(Call<ListCategoryResponse> call, Throwable t) {
dialog.dismiss();
                    }
                });



            }
        });

    }


    @Override
    public int getItemCount() {
        return pending_orders_list_array_item.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_pending,address_pending,q_pending,p_pending;// init the item view's
        Button pd_pending;
        public MyViewHolder(View itemView) {
            super(itemView);
            name_pending = (TextView) itemView.findViewById(R.id.Title_pending);
            pd_pending=(Button)itemView.findViewById(R.id.pending_pending);
            address_pending=(TextView)itemView.findViewById(R.id.address_pending);
            q_pending=(TextView)itemView.findViewById(R.id.Quantity_pending);
            p_pending=(TextView)itemView.findViewById(R.id.Price_pending);
            // get the reference of item view's

        }
    }
    public void filter(String charText) {
        Log.e("texting if","persons="+charText);
        charText = charText.toLowerCase(Locale.getDefault());
        Log.e("texting if2","persons="+charText);
        pending_orders_list_array_item.clear();
        Iterator itr=pending_orders_list_array_item.iterator();
        if (charText.length() == 0) {
            Log.e("texting if3","persons="+charText);
            pending_orders_list_array_item.addAll(lts);
        } else {
            for(int i =0;i<lts.size();i++) {
                Log.e("texting else","persons="+lts.get(i));
                String s = (String) lts.get(i);
                if (s.toLowerCase(Locale.getDefault()).contains(charText)) {
                    pending_orders_list_array_item.add(s);
                }
            }
        }
        Log.e("text","persons="+pending_orders_list_array_item);
        notifyDataSetChanged();
    }
}
