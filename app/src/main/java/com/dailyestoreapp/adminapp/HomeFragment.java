package com.dailyestoreapp.adminapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dailyestoreapp.adminapp.ui.home.HomeViewModel;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);
Activate();
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getContext(),getChildFragmentManager());
        sectionsPagerAdapter.addFragment(new Fragment1(),"Home");
        sectionsPagerAdapter.addFragment(new Fragment4(),"Offers");
        sectionsPagerAdapter.addFragment(new Fragment4(),"Contact");
        sectionsPagerAdapter.addFragment(new Fragment4(),"My Account");
        sectionsPagerAdapter.addFragment(new Fragment4(),"Home");
        sectionsPagerAdapter.addFragment(new Fragment4(),"Offers");
        sectionsPagerAdapter.addFragment(new Fragment4(),"Contact");
        sectionsPagerAdapter.addFragment(new Fragment4(),"My Account");
        ViewPager viewPager =root.findViewById(R.id.view_pager2);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = root.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        return root;
    }
    private void Activate()
    {
        String url = "http://dailyestoreapp.com/dailyestore/api/listcategory";
        JSONObject obj = new JSONObject();
        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
// response start
                        JSONObject student1 = new JSONObject();
                        try {
                            student1.put("typeId", "3");
                            student1.put("itemName", "NAME OF STUDENT");
                            student1.put("itemImage", "3rd");
                            student1.put("createdBy", "Arts");
                            student1.put("createdAt", "5/5/1993");

                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        JSONObject student2 = new JSONObject();
                        try {
                            student2.put("typeId", "3");
                            student2.put("itemName", "NAME OF STUDENT");
                            student2.put("itemImage", "3rd");
                            student2.put("createdBy", "Arts");
                            student2.put("createdAt", "5/5/1993");

                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        JSONObject student3 = new JSONObject();
                        try {
                            student3.put("typeId", "3");
                            student3.put("itemName", "NAME OF STUDENT");
                            student3.put("itemImage", "3rd");
                            student3.put("createdBy", "Arts");
                            student3.put("createdAt", "5/5/1993");

                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        JSONObject student4 = new JSONObject();
                        try {
                            student4.put("typeId", "3");
                            student4.put("itemName", "NAME OF STUDENT");
                            student4.put("itemImage", "3rd");
                            student4.put("createdBy", "Arts");
                            student4.put("createdAt", "5/5/1993");

                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        JSONArray jsonArray = new JSONArray();

                        jsonArray.put(student1);
                        jsonArray.put(student2);
                        jsonArray.put(student3);
                        jsonArray.put(student4);
                        JSONObject sub = new JSONObject();
                        try {

                            sub.put("success","0");
                            sub.put("data",jsonArray);
                            response.put("responsedata",sub);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//response end
                        Log.e("RESPONSE",""+response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            if(jsonObject.has("responsedata")) {
                                Log.e("jsonObject","jsonObject is "+jsonObject);
                                JSONObject json2 = new JSONObject(String.valueOf(jsonObject));
                                JSONObject res = (JSONObject) json2.get("responsedata");
                                JSONObject actualvalue = new JSONObject(res.toString());
                                Log.e("json2","json2is "+json2);
                                Log.e("res","res is"+res);
                                Log.e("actualvalue","actualvalue is"+actualvalue);
                                String result = actualvalue.getString("success");
                                Log.e("actualvalue","success  is"+result);
                                if(result.equals("0"))
                                {

                                }
                                else
                                {

                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

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

}
