package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
Button lg;
    ArrayList<String> categories = new ArrayList<>();
    public static final String MY_PREFS_NAME = "AdminApp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lg = (Button)findViewById(R.id.login);
        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activate();

            }
        });
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
                String res= new GsonBuilder().setPrettyPrinting().create().toJson(response.body().getResponsedata());
                JsonObject obj = new JsonParser().parse(res).getAsJsonObject();
                try {
                    JSONObject jo2 = new JSONObject(obj.toString());
                    JSONArray categoriesarray = jo2.getJSONArray("data");
                    for(int i=0; i<categoriesarray.length(); i++)
                    {
                        JSONObject j1= categoriesarray.getJSONObject(i);
                        String item = j1.getString("itemName");
                        //categories.add(item);

                    }
                    categories.add("Food");
                    categories.add("Home Appliences");
                    categories.add("Grocery");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("cat","cat"+categories);

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                Set<String> set = new HashSet<String>();
                set.addAll(categories);
                editor.putStringSet("categories", set);
                editor.apply();
                Intent next = new Intent(Login.this,Main2Activity.class);
                startActivity(next);

            }

            @Override
            public void onFailure(Call<ListCategoryResponse> call, Throwable t) {

            }
        });


    }
}
