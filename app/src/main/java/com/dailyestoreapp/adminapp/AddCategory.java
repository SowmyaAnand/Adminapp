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

               Log.e("category","category="+NewCategoryname);
                String url = "http://dailyestoreapp.com/dailyestore/api/addSubCategory";
                JSONObject obj = new JSONObject();
                try {
                    obj.put("data", NewCategoryname);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                RequestQueue queue = Volley.newRequestQueue(AddCategory.this);

                JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST,url,obj,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                System.out.println(response);




                                sbcategoryname.setText("");
                                sbimageitemcategory.setImageResource(R.drawable.ic_image_black_24dp);
                                Log.e("RESPONSE",""+response.toString());

//                        try{
//                            JSONObject jsonObject=new JSONObject(response.toString());
//                            if(jsonObject.has("output")){
//                                String result=jsonObject.getString("output");
//                                // String token = jsonObject.getString("token");
//                                if (result.contains("Success")&& jsonObject.has("token")) {
//                                    String token = jsonObject.getString("token");
//                                    String text[] = result.split(",");
//                                    //String status=text[0];
//                                    //String resp=str.trim();
//                                    String groupId = text[1];
//                                    String empNme = text[2];
////                                    sharedPreferences = getSharedPreferences(Constant.LOGIN_CREDENTIAL, Context.MODE_PRIVATE);
////                                    SharedPreferences.Editor editor = sharedPreferences.edit();
////                                    editor.putString(Constant.LOGIN_ADID, adid);
////                                    editor.putString(Constant.LOGIN_SECTION, groupId);
////                                    editor.putString(Constant.LOGIN_NAME, empNme);
////
////                                    editor.putString(Constant.SHARED_PREF_REFRESH_TOKEN,token);
////
////
////                                    editor.apply();
//
////                                    ApplicationLoader.getPreferences().userID(adid);
////
////                                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
////                                    startActivity(i);
////                                    finish();
//                                } else {
//                                  //  showToast(result);
//                                }
//                            }
//                        }
//                        catch(Exception e){
//                            e.printStackTrace();
//                        }


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // progressDialog.dismiss();
                                // showToast("Unable to connect Server,please try after sometime!");
                                Log.e("ERROR",""+error);
                            }
                        });

                jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                        20000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(jsObjRequest);
                Log.d("request>>>>>>", queue.toString());
            }
        });

        addcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewCategoryname=newcategoryname.getText().toString();

                String url = "http://dailyestoreapp.com/dailyestore/api/addCategory";
                JSONObject obj = new JSONObject();
                try {
                    obj.put("itemName", NewCategoryname);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
                flag=1;

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
                    sbimageitemcategory.setImageURI(resultUri);
                }
                else
                {
                    imgaeitemcategory.setImageURI(resultUri);
                }


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }


    public void uploadSubCategory(final File file, final String categoryId, final String ADID, final String header, final String description, final String date, final String publisher){
        //String refreshToken = HelperClass.getRefreshToken(PostActivity.this);
        //String userADID = HelperClass.getUserADID(PostActivity.this);

//        final ProgressDialog progressDialog=new ProgressDialog(PostActivity.this);
//        progressDialog.setCancelable(false);
//        progressDialog.setTitle("Please Wait");
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
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
//                        progressDialog.dismiss();
//                        FileUtils.deleteCache(PostActivity.this.getApplicationContext());
//                        Log.e("CATEGORYList---->", "" + jsonObject);
//
//
//                        String result = null;
//                        JSONObject fullResponseObject = null;
//                        try {
//                            fullResponseObject = jsonObject;
//
//                            result = fullResponseObject.getString(Constant.API_RESPONSE);
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }


//                        try {
//
//                            JSONArray jsonTokenBased = fullResponseObject.getJSONArray(Constant.API_TOKEN_RESPONSE);
//                            for (int i = 0; i < jsonTokenBased.length(); i++) {
//                                JSONObject jsonTokenObject = jsonTokenBased.getJSONObject(i);
//                                String authenticateTokenMsg = jsonTokenObject.getString(Constant.API_AUTHENTICATE_TOKEN_KEY);
//                                String refreshToken = jsonTokenObject.getString(Constant.API_TOKEN_KEY);
//                                if (refreshToken != null && !refreshToken.equalsIgnoreCase("null")) {
//                                    HelperClass.insertingRefreshToken(PostActivity.this, refreshToken);
//                                }
//                                else {
//
//                                    if (authenticateTokenMsg.contains(Constant.API_TOKEN_AUTHORIZATION_FAILURE)) {
//                                        uploadParameter("", categoryId, ADID, header, description, date,publisher);
//                                    } else {
//                                        showToast(authenticateTokenMsg);
//                                        HelperClass.LogOut(PostActivity.this);
//                                    }
//
//                                }
//                            }
//
//
//                            if (result != null && !result.equalsIgnoreCase("null")) {
//                                try{
//                                    JSONArray jsonArray = new JSONArray(result);
//                                    String postValue=jsonArray.getString(0);
//                                    String postResponse=postValue.trim();
//                                    if(postResponse.equals("Post updated")){
//                                        showToast("Successfully Posted");
//                                        finish();
//                                    }else{
//                                        showToast(postResponse);
//                                    }
//
//                                }
//                                catch(Exception e){
//                                    e.printStackTrace();
//                                }
//                            }
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            Log.e("DecryptCategory ->", "" + e);
//                        }

                    }

                    @Override
                    public void onError(ANError anError) {
//                        progressDialog.dismiss();
                      //  FileUtils.deleteCache(PostActivity.this.getApplicationContext());

                        Log.e("RESPONSE",""+anError);
                        Log.d("", "onError errorCode : " + anError.getErrorCode());
                        Log.d("", "onError errorBody : " + anError.getErrorBody());
                        Log.d("", "onError errorDetail : " + anError.getErrorDetail());
//                        if(anError.toString().contains("javax.net.ssl.SSLPeerUnverifiedException") && count==1){
//                           // uploadFile(file,categoryId,ADID, header, description,  date,  publisher);
//                            Log.e("Request->",""+file+categoryId+ADID+header+description+date+publisher);
//                          //  ++count;
//
//
//                        }
//                        else{
//                         //   showToast("Unable to connect with server,please try after sometime!!");
//
//                        }
                    }
                });


              /*  .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        Log.e("RESPONSE",""+s);
                        if(s.equals("Post updated")){
                            showToast("Successfully Posted");
                            finish();
                        }else{
                            showToast(s);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Log.e("RESPONSE",""+anError);
                        Log.d("", "onError errorCode : " + anError.getErrorCode());
                        Log.d("", "onError errorBody : " + anError.getErrorBody());
                        Log.d("", "onError errorDetail : " + anError.getErrorDetail());
                        if(anError.toString().contains("javax.net.ssl.SSLPeerUnverifiedException") && count==1){
                            uploadFile(file,categoryId,ADID, header, description,  date,  publisher);
                            Log.e("Request->",""+file+categoryId+ADID+header+description+date+publisher);
                            ++count;
                        }
                        else{
                            showToast("Unable to connect with server,please try after sometime!!");

                        }
                    }
                });*/
    }
}
