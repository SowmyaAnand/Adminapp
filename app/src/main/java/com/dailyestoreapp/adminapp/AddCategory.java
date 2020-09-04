package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.theartofdev.edmodo.cropper.CropImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class AddCategory extends AppCompatActivity {
    Button addattachcategory,addcategory,savesubcategory,sbaddattachcategory;
    ImageView imgaeitemcategory,sbimageitemcategory;
    TextView addsubcategoryhd;
    EditText newcategoryname,sbcategoryname;
    String NewCategoryname;
    String sbnewname;
    String sbimage;
    int flag=0;
    File subImage;
    File catImage;
    String selectedPathsub="";
    String selectedPathMain="";
    ArrayList<String> subcategoryarray = new ArrayList<String>();
    CardView card1,card2,card3,card4,card5,card6,card7,card8;
    //Bitmap to get image from gallery
    private Bitmap bitmap;

    //Uri to store the image uri
    private Uri filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ADD CATERGORY");

        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        newcategoryname=(EditText)findViewById(R.id.category_title);
        addsubcategoryhd=(TextView)findViewById(R.id.subcategoryheading);
        addattachcategory=(Button)findViewById(R.id.addcategoryimage);
        sbaddattachcategory=(Button)findViewById(R.id.subaddcategoryimage);
        addcategory=(Button)findViewById(R.id.addcategory);
        sbcategoryname=(EditText)findViewById(R.id.subcatvalue1);
        savesubcategory=(Button)findViewById(R.id.savesubcategory);
        imgaeitemcategory=(ImageView)findViewById(R.id.categoryImg);
        sbimageitemcategory=(ImageView)findViewById(R.id.subcategoryImg);
        card1=(CardView)findViewById(R.id.headersub1);
        card2=(CardView)findViewById(R.id.headersub2);
        card3=(CardView)findViewById(R.id.headersub3);
        card4=(CardView)findViewById(R.id.headersub4);
        card5=(CardView)findViewById(R.id.headersub5);
        card6=(CardView)findViewById(R.id.headersub6);
        card7=(CardView)findViewById(R.id.headersub7);
        card8=(CardView)findViewById(R.id.headersub8);

        savesubcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sbnewname=sbcategoryname.getText().toString();
            if((selectedPathsub==null)||(selectedPathsub.length()==0))
            {
              Toast.makeText(AddCategory.this,"Please select an image",Toast.LENGTH_SHORT).show();
            }
            else if ((sbnewname==null)||(sbnewname.length()==0))
            {
                Toast.makeText(AddCategory.this,"Please enter sub category name",Toast.LENGTH_SHORT).show();
            }
            else
            {

            }

            }
        });

        addcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=1;
                NewCategoryname=newcategoryname.getText().toString();
            if((selectedPathMain == null)||(selectedPathMain.length()==0))
                {
                    Toast.makeText(AddCategory.this,"Please select an image",Toast.LENGTH_LONG).show();
                }
            else if ((NewCategoryname == null)||(NewCategoryname.length()==0))
                {
                    Toast.makeText(AddCategory.this,"Please enter category name",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    card1.setVisibility(View.VISIBLE);
                    card2.setVisibility(View.GONE);
                    card3.setVisibility(View.GONE);
                    card4.setVisibility(View.GONE);
                    card5.setVisibility(View.GONE);
                    card6.setVisibility(View.GONE);
                    card7.setVisibility(View.GONE);
                    card8.setVisibility(View.GONE);
                    savesubcategory.setVisibility(View.VISIBLE);
                    addsubcategoryhd.setVisibility(View.VISIBLE);
                    newcategoryname.setEnabled(false);
                    addattachcategory.setVisibility(View.INVISIBLE);
                }




            }
        });
        addattachcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setMinCropResultSize(913,606)
                        .setMaxCropResultSize(913,606)
                        .start(AddCategory.this);
            }
        });
        sbaddattachcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setMinCropResultSize(913,606)
                        .setMaxCropResultSize(913,606)
                        .start(AddCategory.this);
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
                Intent cart = new Intent(AddCategory.this,Login.class);
                startActivity(cart);
                return true;
            case R.id.account:
                Intent account = new Intent(AddCategory.this,MyAccount.class);
                startActivity(account);
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
                filePath = data.getData();
                //selectedPath = FileUtils.getPath(getApplicationContext(), selectedFileUri);
                Log.e("selected img","selected img"+resultUri);

                if(flag==1)
                {
                    selectedPathsub = FileUtils.getPath(getApplicationContext(), resultUri);
                    subImage=new File(selectedPathsub);
                    sbimageitemcategory.setImageURI(resultUri);

                }
                else
                {
                    selectedPathMain = FileUtils.getPath(getApplicationContext(), resultUri);
                    catImage=new File(selectedPathMain);
                    imgaeitemcategory.setImageURI(resultUri);
                }


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }


    public void uploadSubCategory(final File file, final String categoryId, final String ADID, final String header, final String description, final String date, final String publisher){

        AndroidNetworking.enableLogging();
        AndroidNetworking.upload("http://dailyestoreapp.com/dailyestore/api/addSubCategory")
                .addMultipartFile("path",file)
                .addMultipartParameter("categoryid", categoryId)
                .addMultipartParameter("empcode", ADID)
                .addMultipartParameter("topic",header)
                .addMultipartParameter("body",description)
                .addMultipartParameter("date",date)
                .addMultipartParameter("publisher",publisher)
                .setTag("uploadTest")
                .setPriority(Priority.HIGH).doNotCacheResponse()
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {

                        // do anything with progress

                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {


                    }

                    @Override
                    public void onError(ANError anError) {
//
                        Log.e("RESPONSE",""+anError);
                        Log.d("", "onError errorCode : " + anError.getErrorCode());
                        Log.d("", "onError errorBody : " + anError.getErrorBody());
                        Log.d("", "onError errorDetail : " + anError.getErrorDetail());
//
                    }
                });



    }
    public void uploadCategory(final File file, final String new_category){

        AndroidNetworking.enableLogging();
        AndroidNetworking.upload("http://dailyestoreapp.com/dailyestore/api/addCategory")
                .addMultipartFile("path",file)
                .addMultipartParameter("itemName", new_category)
                .setTag("uploadTest")
                .setPriority(Priority.HIGH).doNotCacheResponse()
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {

                        // do anything with progress

                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {


                    }

                    @Override
                    public void onError(ANError anError) {
//
                        Log.e("RESPONSE",""+anError);
                        Log.d("", "onError errorCode : " + anError.getErrorCode());
                        Log.d("", "onError errorBody : " + anError.getErrorBody());
                        Log.d("", "onError errorDetail : " + anError.getErrorDetail());
//
                    }
                });



    }
}
