package com.dailyestoreapp.adminapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

import static com.dailyestoreapp.adminapp.R.color.green;
import static com.dailyestoreapp.adminapp.R.color.white;

public class PendingNotificationAdapter extends RecyclerView.Adapter<PendingNotificationAdapter.MyViewHolder> {
    ArrayList<String> personNames = new ArrayList<String>();
    Context context;
    ArrayList<String> lts=new ArrayList<String>();
    ArrayList personNames_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
    int quantity=1;
    public PendingNotificationAdapter(Context context, ArrayList personNames) {
        this.context = context;
        this.personNames = personNames;
        this.lts.addAll(personNames);

    }
    @Override
    public PendingNotificationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pendingofferitems, parent, false);
        // set the view's size, margins, paddings and layout parameters
        PendingNotificationAdapter.MyViewHolder vh = new PendingNotificationAdapter.MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(final PendingNotificationAdapter.MyViewHolder holder, final int position) {

        // set the data in items
        String name = (String) personNames.get(position);
        holder.name.setText(name);
        holder.pd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://dailyestoreapp.com/dailyestore/api/orderStatus";
                JSONObject obj = new JSONObject();
                try {
                    obj.put("orderId", 1);
                    obj.put("status", "delivered");
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
                holder.pd.setText("Approved");
                holder.pd.setTextColor(ContextCompat.getColor(context, white));
                holder.pd.setBackgroundColor(ContextCompat.getColor(context, green));
            }
        });

    }


    @Override
    public int getItemCount() {
        return personNames.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,quantityy;// init the item view's
        Button pd,addition,substraction,addbtn;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.Title);
            pd=(Button)itemView.findViewById(R.id.pending);
            // get the reference of item view's

        }
    }
    public void filter(String charText) {
        Log.e("texting if","persons="+charText);
        charText = charText.toLowerCase(Locale.getDefault());
        Log.e("texting if2","persons="+charText);
        personNames.clear();
        Iterator itr=personNames.iterator();
        if (charText.length() == 0) {
            Log.e("texting if3","persons="+charText);
            personNames.addAll(lts);
        } else {
            for(int i =0;i<lts.size();i++) {
                Log.e("texting else","persons="+lts.get(i));
                String s = (String) lts.get(i);
                if (s.toLowerCase(Locale.getDefault()).contains(charText)) {
                    personNames.add(s);
                }
            }
        }
        Log.e("text","persons="+personNames);
        notifyDataSetChanged();
    }
}
