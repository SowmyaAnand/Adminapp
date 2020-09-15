package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CouponsList extends AppCompatActivity {
Button save ;
ACProgressFlower dialog;
EditText coupon_name,desc,offer_percent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons_list);
        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               CouponsList.CustomDialogClass1 cdd2=new CouponsList.CustomDialogClass1(CouponsList.this);
                cdd2.show();
            }
        });

    }
    public class CustomDialogClass1 extends Dialog implements
            android.view.View.OnClickListener {

        public Context c;
        public Dialog d;
        public Button yes, no;
        String txt;
        public TextView textdisplayed;

        public CustomDialogClass1(Context a) {
            super(a);
            // TODO Auto-generated constructor stub
            this.c = a;

        }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.approve_dialog);
            yes = (Button) findViewById(R.id.btn_yes);

            textdisplayed=(TextView)findViewById(R.id.txt_dia);
            textdisplayed.setText("Coupon added successfully");
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
    private void addCoupon()
    {

        dialog = new ACProgressFlower.Builder(CouponsList.this)
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
        Call<ListCategoryResponse> call = mainInterface.CategoryList();
        call.enqueue(new Callback<ListCategoryResponse>() {
            @Override
            public void onResponse(Call<ListCategoryResponse> call, retrofit2.Response<ListCategoryResponse> response) {
                ListCategoryResponse catObj = response.body();
                int cat_length = catObj.getResponsedata().getData().size();

//

            }

            @Override
            public void onFailure(Call<ListCategoryResponse> call, Throwable t) {

        }
        });


    }
}
