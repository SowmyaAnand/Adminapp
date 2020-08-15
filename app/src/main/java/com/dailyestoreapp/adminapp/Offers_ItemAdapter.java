package com.dailyestoreapp.adminapp;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
public class Offers_ItemAdapter extends RecyclerView.Adapter<Offers_ItemAdapter.MyViewHolder> {
    ArrayList<String> personNames = new ArrayList<String>();
    Context context;
    ArrayList Images;
    ArrayList<String> lts = new ArrayList<String>();
    ArrayList personNames_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
    int quantity = 1;

    public Offers_ItemAdapter(Context context, ArrayList personNames, ArrayList Images) {
        this.context = context;
        this.personNames = personNames;
        this.Images=Images;
        this.lts.addAll(personNames);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_items, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        // set the data in items
        String name = (String) personNames.get(position);
        int n= (int) Images.get(position);
        holder.image_image.setImageResource(n);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
holder.outofstock.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String text = holder.outofstock.getText().toString();
        String url = "http://dailyestoreapp.com/dailyestore/api/activateItem";
        JSONObject obj = new JSONObject();
        try {
            obj.put("itemId", 1);
            obj.put("status", "activate");
            //  obj.put(Constant.SESSION_USERID_KEY,adid );
            // obj.put(Constant.SESSION_USERID_KEY,adid );

        } catch (JSONException e) {
            e.printStackTrace();
        }




        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST,url,obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);

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



        if(text.equals("Activate"))
        {
            text="OUT OF STOCK";
            holder.outofstock.setText(text);
             CustomDialogClass1 cdd1=new CustomDialogClass1(context,text);
             cdd1.show();
        }
        else
        {
            text="Activate";
            holder.outofstock.setText(text);
            CustomDialogClass1 cdd2=new CustomDialogClass1(context,text);
            cdd2.show();
        }
       // CustomDialogClass1 cdd1=new CustomDialogClass1(context,position);
       // cdd1.show();
    }
});


    }


    @Override
    public int getItemCount() {
        return Images.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, quantityy;// init the item view's
        Button outofstock;
        ImageView image_image;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.Title);
            outofstock = (Button) itemView.findViewById(R.id.outofstock);
            image_image=(ImageView)itemView.findViewById(R.id.im);

        }
    }

    public void filter(String charText) {
        Log.e("texting if", "persons=" + charText);
        charText = charText.toLowerCase(Locale.getDefault());
        Log.e("texting if2", "persons=" + charText);
        personNames.clear();
        Iterator itr = personNames.iterator();
        if (charText.length() == 0) {
            Log.e("texting if3", "persons=" + charText);
            personNames.addAll(lts);
        } else {
            for (int i = 0; i < lts.size(); i++) {
                Log.e("texting else", "persons=" + lts.get(i));
                String s = (String) lts.get(i);
                if (s.toLowerCase(Locale.getDefault()).contains(charText)) {
                    personNames.add(s);
                }
            }
        }
        Log.e("text", "persons=" + personNames);
        notifyDataSetChanged();
    }

    public class CustomDialogClass1 extends Dialog implements
            android.view.View.OnClickListener {

        public Context c;
        public Dialog d;
        public Button yes, no;
        String txt;
        public TextView textdisplayed;

        public CustomDialogClass1(Context a,String t) {
            super(a);
            // TODO Auto-generated constructor stub
            this.c = a;
            this.txt = t;
        }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.approve_dialog);
            yes = (Button) findViewById(R.id.btn_yes);

            textdisplayed=(TextView)findViewById(R.id.txt_dia);
            textdisplayed.setText("Your Item is marked as "+this.txt);
            yes.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_yes:
                    dismiss();
                    break;

                default:
                    break;
            }

        }
    }
}