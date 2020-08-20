package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class editsubcategory extends AppCompatActivity {
    RecyclerView subcategories;
    LinearLayoutManager linearLayoutManager_offers,linearLayoutManager2_offers;
    EditSubCategoriesAdapter sub_customAdapter_offers;
    ArrayList Images_images = new ArrayList<>(Arrays.asList(R.drawable.wp2375838_1,R.drawable.wp2375838_1, R.drawable.wp2375838_1, R.drawable.wp2375838_1, R.drawable.wp2375838_1));
    ArrayList personNames_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editsubcategory);
        subcategories = (RecyclerView)findViewById(R.id.subcategorieslist);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
       subcategories.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter

        sub_customAdapter_offers = new EditSubCategoriesAdapter(getApplicationContext(), personNames_offers,Images_images);
        subcategories.setAdapter(sub_customAdapter_offers);
    }
}
