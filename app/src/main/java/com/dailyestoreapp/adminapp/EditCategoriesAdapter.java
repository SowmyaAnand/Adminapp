package com.dailyestoreapp.adminapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.theartofdev.edmodo.cropper.CropImage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

public class EditCategoriesAdapter extends RecyclerView.Adapter<EditCategoriesAdapter.MyViewHolder> {
    ArrayList<String> categories_editcategory = new ArrayList<String>();
    ArrayList<Integer> categories_no_editcategory= new ArrayList<Integer>();
    Context context;

    ArrayList<String> Images = new ArrayList<String>();
    ArrayList<String> lts = new ArrayList<String>();
  //  ArrayList personNames_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
    int quantity = 1;

    public EditCategoriesAdapter(Context context, ArrayList personNames, ArrayList Images,ArrayList catno_edit) {
        this.context = context;
        this.categories_editcategory = personNames;
        this.Images=Images;
        categories_no_editcategory = catno_edit;
        this.lts.addAll(personNames);

    }

    @Override
    public EditCategoriesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.editcategorieslayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        EditCategoriesAdapter.MyViewHolder vh = new EditCategoriesAdapter.MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(final EditCategoriesAdapter.MyViewHolder holder, final int position) {

        // set the data in items
        holder.ed_pic_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        String name_editcategory = (String) categories_editcategory.get(position);
//        String img_editcategory = (String) Images.get(position);
//        if(Images.size()>0)
//        {
//            Glide.with(context).load(img_editcategory)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(holder.image_image_edit);
//        }
        holder.name_edit.setText(name_editcategory);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next= new Intent(context,editsubcategory.class);
                Bundle bundle = new Bundle();

//Add your data to bundle
                bundle.putInt("edit_cat_no", categories_no_editcategory.get(position));

//Add the bundle to the intent
                next.putExtras(bundle);
                context.startActivity(next);

            }
        });
       holder.ed_edit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String text = holder.ed_edit.getText().toString();
               if(text.equals("SAVE"))
               {
                   holder.ed_edit.setText("EDIT");
               }
               else
               {
                   holder.name_edit.setEnabled(true);
                   holder.ed_edit.setText("SAVE");
                   holder.ed_pic_edit.setVisibility(View.VISIBLE);
               }

           }
       });


    }


    @Override
    public int getItemCount() {
        return categories_editcategory.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        EditText name_edit, quantityy;// init the item view's
        Button ed_edit,ed_pic_edit;
        ImageView image_image_edit;
        public MyViewHolder(View itemView) {
            super(itemView);
            name_edit = (EditText) itemView.findViewById(R.id.Title_editcategory);
           ed_edit = (Button) itemView.findViewById(R.id.edit_editcategory);
           ed_pic_edit =(Button)itemView.findViewById(R.id.editpic_editcategory);
            image_image_edit=(ImageView) itemView.findViewById(R.id.im_editcategory);
        }
    }



}