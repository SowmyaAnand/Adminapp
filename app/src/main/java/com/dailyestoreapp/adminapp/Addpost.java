package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

public class Addpost extends AppCompatActivity  {
    TextView addattach;
    ImageView imgaeitem;
    RadioGroup radiogroup;
    Spinner Category_spinner;
    Spinner Sub_Category_spinner;
    public static final String MY_PREFS_NAME = "AdminApp";
    private static final String[] paths = {"item 1", "item 2", "item 3"};
    ArrayList<Integer> categoriescatno_edit = new ArrayList<>();
    ArrayList<Integer> subcategoriescatno_edit = new ArrayList<>();
    ArrayAdapter<String>subadapter1;
    ACProgressFlower dialog;
    Integer selected_cat_no;
    List<String> subcategorylist1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ADD ITEM");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ArrayList<String> categoriesEditCategies = new ArrayList<>();
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });

        // Cat_name
        SharedPreferences shared = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        Set<String> set = shared.getStringSet("categories", null);
        List<String> categorylist = new ArrayList<String>();
        categorylist.add("Select Category");
        categorylist.addAll(set);
//Cat_number
        SharedPreferences shared2 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String savedString = shared2.getString("categories_no", "subId");
        String[] numbers = savedString.split(",");//if spaces are uneven, use \\s+ instead of " "
        for (String number : numbers) {
            categoriescatno_edit.add(Integer.valueOf(number));
        }

//// sub_Cat_number
//        SharedPreferences shared3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//        String savedString3 = shared3.getString("sub_categories_no", "");
//
//
//        String[] numbers_sub = savedString3.split(",");//if spaces are uneven, use \\s+ instead of " "
//
//        for (String number : numbers_sub) {
//            if()
//            subcategoriescatno_edit.add(Integer.valueOf(number));
//        }
////// sub_Cat_ name
       subcategorylist1 = new ArrayList<String>();
//        SharedPreferences shared4 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//        Set<String> set4 = shared4.getStringSet("categories", null);
//
       subcategorylist1.add("Select Sub Category");
//        subcategorylist1.addAll(set4);


        Category_spinner = findViewById(R.id.categoryspinner);
        Sub_Category_spinner = findViewById(R.id.subcategoryspinner);
        addattach=(TextView)findViewById(R.id.txtAttachment);
        imgaeitem=(ImageView)findViewById(R.id.imageitem);
        subadapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,subcategorylist1);

        subadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Sub_Category_spinner.setAdapter(subadapter1);
        Sub_Category_spinner.setOnItemSelectedListener(new SubCategoriesSpinnerClass());
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,categorylist);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Category_spinner.setAdapter(adapter);
        Category_spinner.setOnItemSelectedListener(new CategoriesSpinnerClass());
        addattach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setMinCropResultSize(300,300)
                        .setMaxCropResultSize(300,300)
                        .start(Addpost.this);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Log.e("MAin","Item selected ="+item.getItemId());
        switch (item.getItemId()) {
            case R.id.logout:
                Intent cart = new Intent(Addpost.this,Login.class);
                startActivity(cart);
                return true;
            case R.id.account:
                Intent account1 = new Intent(Addpost.this,MyAccount.class);
                startActivity(account1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                imgaeitem.setImageURI(resultUri);


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
class CategoriesSpinnerClass implements AdapterView.OnItemSelectedListener
{

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(Addpost.this,"categoryselected"+position,Toast.LENGTH_LONG).show();


        Toast.makeText(Addpost.this,"subcategoryselected"+selected_cat_no,Toast.LENGTH_LONG).show();
        if(position!=1)
        { selected_cat_no=categoriescatno_edit.get(position);
            subcategoryactivatepost();
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}

    class SubCategoriesSpinnerClass implements AdapterView.OnItemSelectedListener
    {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
    private void subcategoryactivatepost()
    {

        dialog = new ACProgressFlower.Builder(Addpost.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .borderPadding(1)

                .fadeColor(Color.DKGRAY).build();
        dialog.show();
        int type = selected_cat_no;
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
        Call<ListCategoryResponse> call = mainInterface.SubCategory(type);
        call.enqueue(new Callback<ListCategoryResponse>() {
            @Override
            public void onResponse(Call<ListCategoryResponse> call, retrofit2.Response<ListCategoryResponse> response) {
                String res= new GsonBuilder().setPrettyPrinting().create().toJson(response.body().getResponsedata());
                JsonObject obj = new JsonParser().parse(res).getAsJsonObject();
                int success = Integer.parseInt(response.body().getResponsedata().getSuccess());
                dialog.dismiss();
                if(success==1)
                {
                    try {
                        JSONObject jo2 = new JSONObject(obj.toString());
                        JSONArray categoriesarray = jo2.getJSONArray("data");

                        
                        for(int i=0; i<categoriesarray.length(); i++)
                        {
                            JSONObject j1= categoriesarray.getJSONObject(i);
                            String sub_name = j1.getString("subName");
                            int item_no = Integer.parseInt(j1.getString("subId"));

                            if(!subcategorylist1.contains(sub_name))
                            {
                                subcategorylist1.add(sub_name);
                               subcategoriescatno_edit.add(item_no);



                            }

                        }

                        subadapter1.notifyDataSetChanged();







                        //personNames_offers = new ArrayList<>(Arrays.asList("farg4ITEM1", "frag4ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(Addpost.this,"No Data found",Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<ListCategoryResponse> call, Throwable t) {

            }
        });


    }
}