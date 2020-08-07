package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class FlyersandDeals extends AppCompatActivity {
String categoryselected;
Integer id;
TextView txt;
Button ed1,ed2,ed3,ed4;
int flagpic1,flagpic2,flagpic3,flagpic4;
ImageView img1,img2,img3,img4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flyersand_deals);
        Bundle val = getIntent().getExtras();
        categoryselected=val.getString("CATEGORYID");
        Log.e("iddd","the is selecetd is "+categoryselected);
        id= Integer.valueOf(categoryselected);
        txt=(TextView)findViewById(R.id.title);
        ed1=(Button)findViewById(R.id.edit1);
        ed2=(Button)findViewById(R.id.edit2);
        ed3=(Button)findViewById(R.id.edit3);
        ed4=(Button)findViewById(R.id.edit4);
        img1=(ImageView)findViewById(R.id.image1);
        img2=(ImageView)findViewById(R.id.image2);
        img3=(ImageView)findViewById(R.id.image3);
        img4=(ImageView)findViewById(R.id.image4);
        ed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setMinCropResultSize(2955,2173)
                        .setMaxCropResultSize(2955,2173)
                        .start(FlyersandDeals.this);
                flagpic1=1;
                flagpic2=0;
                flagpic3=0;
                flagpic4=0;
            }
        });
        ed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setMinCropResultSize(300,300)
                        .setMaxCropResultSize(300,300)
                        .start(FlyersandDeals.this);
                flagpic1=0;
                flagpic2=1;
                flagpic3=0;
                flagpic4=0;
            }
        });
        ed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setMinCropResultSize(300,300)
                        .setMaxCropResultSize(300,300)
                        .start(FlyersandDeals.this);
                flagpic1=0;
                flagpic2=0;
                flagpic3=1;
                flagpic4=0;
            }
        });
        ed4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setMinCropResultSize(300,300)
                        .setMaxCropResultSize(300,300)
                        .start(FlyersandDeals.this);
                flagpic1=0;
                flagpic2=0;
                flagpic3=0;
                flagpic4=1;
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        if(id==3)
        {
            txt.setText("Select Deals of the day");
        }
        if(id==4)
        {
            txt.setText("Select 1st Flyers");
        }
        if(id==5)
        {
            txt.setText("Select 2nd Flyers");
        }

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
                Intent cart = new Intent(FlyersandDeals.this,Login.class);
                startActivity(cart);
                return true;
            case R.id.account:
                Intent account3 = new Intent(FlyersandDeals.this,MyAccount.class);
                startActivity(account3);
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
                if(flagpic1==1)
                {
                    img1.setImageURI(resultUri);
                }
                else if(flagpic2==1)
                {
                    img2.setImageURI(resultUri);
                }
                else if(flagpic3==1)
                {
                    img3.setImageURI(resultUri);
                }
                else if(flagpic4==1)
                {
                    img4.setImageURI(resultUri);
                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}
