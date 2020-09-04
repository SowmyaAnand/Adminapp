package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity

{
Button lg;

    private String tag = "Login";
   EditText username;
   EditText pswd;
   String uname;
   String password;
   String login_type="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lg = (Button)findViewById(R.id.login);
        username = (EditText) findViewById(R.id.edit_text_user);
        pswd = (EditText)findViewById(R.id.edit_text2_pswd);

        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname =username.getText().toString();
                password = pswd.getText().toString();
                if( (uname==null)||(uname.length()==0)||(password==null)|(password.length()==0))
                {
                    Toast.makeText(Login.this,"Please enter valid username and Password",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent next = new Intent(Login.this,Main2Activity.class);
                    startActivity(next);
                   // login_call(uname,password);
                }

            }
        });
    }

    void login_call(String usname,String pass)
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
        Call<ListCategoryResponse> call = mainInterface.Loginapi(uname,password,login_type);
        call.enqueue(new Callback<ListCategoryResponse>() {
            @Override
            public void onResponse(Call<ListCategoryResponse> call, retrofit2.Response<ListCategoryResponse> response) {
                ListCategoryResponse obj =response.body();
                int success = Integer.parseInt(obj.getResponsedata().getSuccess());
                Log.e(tag,"success="+obj.getResponsedata().getSuccess());
                if(success==1)
                {
                    Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_LONG).show();
Intent next = new Intent(Login.this,Main2Activity.class);
startActivity(next);
                }
              else
                {
                    Toast.makeText(Login.this,"Invalid Username and Password",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ListCategoryResponse> call, Throwable t) {
                Toast.makeText(Login.this,"Invalid Username and Password",Toast.LENGTH_LONG).show();

            }
        });


    }



}
