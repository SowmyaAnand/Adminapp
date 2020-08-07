package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class Food_second_layer extends AppCompatActivity {
    ArrayList Images_offers = new ArrayList<>(Arrays.asList(R.drawable.newvegetable,R.drawable.fried_chicken_clip_art_8_1, R.drawable.newvegetable, R.drawable.fried_chicken_clip_art_8_1, R.drawable.newvegetable));
    ArrayList Images_images = new ArrayList<>(Arrays.asList(R.drawable.wp2375838_1,R.drawable.wp2375838_1, R.drawable.wp2375838_1, R.drawable.wp2375838_1, R.drawable.wp2375838_1));
    ArrayList personNames_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
    RecyclerView recyclerView_offers,itemlistingcategory_offers;
    LinearLayoutManager linearLayoutManager_offers,linearLayoutManager2_offers;
   ItemAdapterFood customAdapter_offers;
    OffersListingsubcategoryadapter customadapter2_offers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_second_layer);
        itemlistingcategory_offers = (RecyclerView)findViewById(R.id.recyclerView_categories_offer_categories);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager2_offers = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        itemlistingcategory_offers.setLayoutManager(linearLayoutManager2_offers);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        customadapter2_offers = new OffersListingsubcategoryadapter(getApplicationContext(), personNames_offers,Images_offers);
        itemlistingcategory_offers.setAdapter(customadapter2_offers);
        //second recyclerview
        recyclerView_offers = (RecyclerView)findViewById(R.id.itemrecycler_offers_categories);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView_offers.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter

        customAdapter_offers = new ItemAdapterFood(getApplicationContext(), personNames_offers,Images_images);
        recyclerView_offers.setAdapter(customAdapter_offers);
    }
}
