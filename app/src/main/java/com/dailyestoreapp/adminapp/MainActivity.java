package com.dailyestoreapp.adminapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private List<String> categoryNameList;
    private List<Integer>  categoryID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        categoryNameList = new ArrayList<>();
        categoryID = new ArrayList<>();
        categoryNameList.add("categories");
        categoryID.add(1);
        categoryNameList.add("Items");
        categoryID.add(2);
        categoryNameList.add("Deal of the day");
        categoryID.add(3);
        categoryNameList.add("flyers");
        categoryID.add(4);
        categoryNameList.add("2nd flyers");
        categoryID.add(5);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
//        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
//        sectionsPagerAdapter.addFragment(new Fragment1(),"Home");
//        sectionsPagerAdapter.addFragment(new Fragment2(),"Offers");
//        sectionsPagerAdapter.addFragment(new Fragment1(),"Contact");
//        sectionsPagerAdapter.addFragment(new Fragment1(),"My Account");
//        sectionsPagerAdapter.addFragment(new Fragment2(),"Home");
//        sectionsPagerAdapter.addFragment(new Fragment1(),"Offers");
//        sectionsPagerAdapter.addFragment(new Fragment1(),"Contact");
//        sectionsPagerAdapter.addFragment(new Fragment2(),"My Account");
//        ViewPager viewPager = findViewById(R.id.view_pager2);
//        viewPager.setAdapter(sectionsPagerAdapter);
//        TabLayout tabs = findViewById(R.id.tabs);
//        tabs.setupWithViewPager(viewPager);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    private void alertDialog() {
        final CharSequence[] name = categoryNameList.toArray(new String[categoryNameList.size()]);
        final Integer[] categoryId = categoryID.toArray(new Integer[categoryID.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Your Post");
        builder.setItems(name, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // String selectedCartegoryNme = name[which].toString();
                String selectedCategoryId = categoryId[which].toString();
                if(categoryId[which].equals(1))
                {
                    Intent i = new Intent(MainActivity.this, AddCategory.class);
                    i.putExtra("CATEGORYID", selectedCategoryId);
                    startActivity(i);
                }

                else if(categoryId[which].equals(2))
                {
                    Intent i = new Intent(MainActivity.this, Addpost.class);
                    i.putExtra("CATEGORYID", selectedCategoryId);
                    startActivity(i);
                }
                else if(categoryId[which].equals(3))
                {
                    Intent i = new Intent(MainActivity.this, FlyersandDeals.class);
                    i.putExtra("CATEGORYID", selectedCategoryId);
                    startActivity(i);
                }
                else if(categoryId[which].equals(4))
                {
                    Intent i = new Intent(MainActivity.this, FlyersandDeals.class);
                    i.putExtra("CATEGORYID", selectedCategoryId);
                    startActivity(i);
                }
                else if(categoryId[which].equals(5))
                {
                    Intent i = new Intent(MainActivity.this, FlyersandDeals.class);
                    i.putExtra("CATEGORYID", selectedCategoryId);
                    startActivity(i);
                }


            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}
