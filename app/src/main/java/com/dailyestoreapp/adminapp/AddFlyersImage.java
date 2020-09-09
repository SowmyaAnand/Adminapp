package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.theartofdev.edmodo.cropper.CropImage;

public class AddFlyersImage extends AppCompatActivity {
Button addimage_flyer;
ImageView Flyerimg;
String flyer_cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flyers_image);
        addimage_flyer=(Button)findViewById(R.id.edit1_flyer);
        Flyerimg =(ImageView)findViewById(R.id.first_popup_img_flyer);
        Bundle val = getIntent().getExtras();
        flyer_cat=val.getString("flyertype");
        addimage_flyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flyer_cat=="1")
                {
                    CropImage.activity()
                            .setMinCropResultSize(913,606)
                            .setMaxCropResultSize(913,606)
                            .start(AddFlyersImage.this);
                }
                else
                { CropImage.activity()
                        .setMinCropResultSize(913,606)
                        .setMaxCropResultSize(913,606)
                        .start(AddFlyersImage.this);

                }

            }
        });
    }
}
