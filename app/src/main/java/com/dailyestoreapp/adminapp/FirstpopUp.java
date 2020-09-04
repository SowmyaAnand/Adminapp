package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;

public class FirstpopUp extends AppCompatActivity {
ImageView firstpopUp_img;
Button firstpopUp_change_img ;
Button firstpopUp_save_img;
    File firstpopUpImage;
    String selectedPathfirstpopUp="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpop_up);
        firstpopUp_img =(ImageView)findViewById(R.id.first_popup_img);
        firstpopUp_change_img = (Button)findViewById(R.id.addImageFirstPopup);
        firstpopUp_save_img = (Button)findViewById(R.id.saveFirstPopup);
        firstpopUp_change_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setMinCropResultSize(913,606)
                        .setMaxCropResultSize(913,606)
                        .start(FirstpopUp.this);
            }
        });
        firstpopUp_save_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((selectedPathfirstpopUp==null)||(selectedPathfirstpopUp.length()==0))
                {
                    Toast.makeText(FirstpopUp.this,"Please select an image",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri_firstpopup = result.getUri();
                selectedPathfirstpopUp = FileUtils.getPath(getApplicationContext(), resultUri_firstpopup);
                firstpopUpImage=new File(selectedPathfirstpopUp);
                firstpopUp_img.setImageURI(resultUri_firstpopup);


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}
