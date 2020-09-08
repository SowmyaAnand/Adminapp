package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
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

public class Splash extends AppCompatActivity {
  //  private static int SPLASH_TIME_OUT = 1000;
  StringBuilder strbul  = new StringBuilder();
    StringBuilder ct  = new StringBuilder();
    ArrayList<String> categories = new ArrayList<>();
    ArrayList<String> categories_image = new ArrayList<>();
    List<String> cat_no = new ArrayList<String>();
    ArrayList<Integer> nums = new ArrayList<>();
    ACProgressFlower dialog;

    public static final String MY_PREFS_NAME = "AdminApp";
    private static String tag = "splash";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Activate();
//        new Handler().postDelayed(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                Intent homeintent = new Intent(Splash.this,Login.class);
//                startActivity(homeintent);
//                finish();
//
//            }
//        },SPLASH_TIME_OUT);
    }

    private void Activate()
    {
//        dialog = new ACProgressFlower.Builder(Login.this)
//                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
//                .themeColor(Color.WHITE)
//                .borderPadding(1)
//
//                .fadeColor(Color.DKGRAY).build();
//        dialog.show();

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

//                String res= new GsonBuilder().setPrettyPrinting().create().toJson(response.body().getResponsedata());
//                JsonObject obj = new JsonParser().parse(res).getAsJsonObject();
                try {
//                    JSONObject jo2 = new JSONObject(obj.toString());
//                    JSONArray categoriesarray = jo2.getJSONArray("data");
//                    Log.e(tag,"categoriesarray"+categoriesarray);
//                    Set<Integer> set3 = new HashSet<Integer>();

                    for(int i=0; i<cat_length; i++)
                    {
//                        JSONObject j1= categoriesarray.getJSONObject(i);
//                        String item = j1.getString("itemName");
//                        String item_image = j1.getString("itemImage");
//                        int item_no = Integer.parseInt(j1.getString("typeId"));
                        ListCategoryResponseData catObj1 = catObj.getResponsedata().getData().get(i);
                        String item = catObj1.getCategoryName();
                        String item_image = catObj1.getCategoryImage();
                        int item_no = Integer.parseInt(catObj1.getTypeId());
                        nums.add(item_no);
                        categories.add(item);
                        categories_image.add(item_image);

                        Log.e(tag,"value added "+item_no);
                    }
                    Log.e(tag,"value added "+nums);
                    Iterator<Integer> iter = nums.iterator();
                    while(iter.hasNext())
                    {
                        strbul.append(iter.next());
                        if(iter.hasNext()){
                            strbul.append(",");
                        }
                    }
                    strbul.toString();

                    // to store categories
                    Log.e("res","res="+strbul);
                    Iterator<String> itr_string = categories.iterator();
                    while (itr_string.hasNext())
                    {

                        ct.append(itr_string.next());
                        if(itr_string.hasNext()){
                            ct.append(",");
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(tag,"catch exception"+e.getMessage());
                }

                Log.e(tag,"categories = "+categories);
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
               Set<String> set = new LinkedHashSet<String>();
                set.addAll(categories);

                editor.putString("categories", ct.toString());
                editor.apply();
                if(categories_image.size()>0){
                    SharedPreferences.Editor editor3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    Set<String> set3 = new HashSet<String>();
                    set3.addAll(categories_image);
                    editor3.putStringSet("categories_image", set3);
                    editor.apply();
                }


                Log.e(tag,"array of numbers "+strbul.toString());
                SharedPreferences.Editor editor2 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("categories_no", strbul.toString());
                editor.apply();


                Intent next = new Intent(Splash.this,Login.class);
                startActivity(next);

            }

            @Override
            public void onFailure(Call<ListCategoryResponse> call, Throwable t) {

            }
        });


    }
}
