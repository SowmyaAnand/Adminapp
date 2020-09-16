package com.dailyestoreapp.adminapp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

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

public class Coupon_Adapter extends RecyclerView.Adapter<Coupon_Adapter.MyViewHolder> {
   
    Context context;

    ArrayList<String>coupon_names=new ArrayList<>();
    ArrayList<String>coupon_desc=new ArrayList<>();
    ArrayList<Integer> coupon_percent = new ArrayList<>();
    ArrayList<Integer> coupon_status = new ArrayList<>();
    ArrayList<Integer> coupon_id = new ArrayList<>();
    ArrayList personNames_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
    int quantity = 1;
    String text_item_status;
    private String tag ="OfferItemadapter";
    ACProgressFlower dialog;
    
    public Coupon_Adapter(Context context, ArrayList<String> couponNames, ArrayList<String> couponDesc , ArrayList<Integer> coupon_percent,ArrayList<Integer> coupon_status,ArrayList<Integer>coupon_id) {
        this.context = context;
        this.coupon_names = couponNames;
        this.coupon_desc=couponDesc;
        this.coupon_percent=coupon_percent;
        this.coupon_status=coupon_status;
        this.coupon_id=coupon_id;
    }

    @Override
    public Coupon_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupons, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Coupon_Adapter.MyViewHolder vh = new Coupon_Adapter.MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(final Coupon_Adapter.MyViewHolder holder, final int position)
    {
        String coup_name = coupon_names.get(position);
        String coup_desc = coupon_desc.get(position);
        Integer coup_pr = coupon_percent.get(position);

        Log.e("adapater",coup_name+coup_desc+coup_pr);
        holder.coupon_name.setText(coup_name);
        holder.coupon_desc.setText(coup_desc);
      holder.coupon_percent_adapter.setText(coup_pr+"% OFF");
      if(coupon_status.get(position)==1)
      {
          holder.act.setText("ACTIVE");
      }
      else
      {
          holder.act.setText("INACTIVE");
      }
        holder.act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return coupon_names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
      TextView coupon_name,coupon_desc,coupon_percent_adapter;
      Button act;
        public MyViewHolder(View itemView) {
            super(itemView);
            coupon_name=itemView.findViewById(R.id.coupon_nm_card);
            coupon_desc=itemView.findViewById(R.id.copuon_desc_card);
            coupon_percent_adapter=itemView.findViewById(R.id.coupon_prcnt_card);
            act = itemView.findViewById(R.id.save_coupon);
        }
    }



    public class CustomDialogClass1 extends Dialog implements
            android.view.View.OnClickListener {

        public Context c;
        public Dialog d;
        public Button yes, no;
        String txt;
        public TextView textdisplayed;

        public CustomDialogClass1(Context a,String t) {
            super(a);
            // TODO Auto-generated constructor stub
            this.c = a;
            this.txt = t;
        }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.approve_dialog);
            yes = (Button) findViewById(R.id.btn_yes);

            textdisplayed=(TextView)findViewById(R.id.txt_dia);
            textdisplayed.setText("Your Coupon is marked as "+this.txt);
            yes.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_yes:
                    dismiss();
                    break;

                default:
                    break;
            }

        }
    }


}