package com.dailyestoreapp.adminapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class EditSubCategoriesAdapter extends RecyclerView.Adapter<EditSubCategoriesAdapter.MyViewHolder> {
    ArrayList<String> personNames = new ArrayList<String>();
    Context context;
    ArrayList Images;
    ArrayList<String> lts = new ArrayList<String>();
    ArrayList personNames_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
    int quantity = 1;

    public EditSubCategoriesAdapter(Context context, ArrayList personNames, ArrayList Images) {
        this.context = context;
        this.personNames = personNames;
        this.Images=Images;
        this.lts.addAll(personNames);

    }

    @Override
    public EditSubCategoriesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.editsubcategorieslayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        EditSubCategoriesAdapter.MyViewHolder vh = new EditSubCategoriesAdapter.MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(final EditSubCategoriesAdapter.MyViewHolder holder, final int position) {

        // set the data in items
        String name = (String) personNames.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        holder.ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = holder.ed.getText().toString();
                if(text.equals("SAVE"))
                {
                    holder.ed.setText("EDIT");
                }
                else
                {
                    holder.name.setEnabled(true);
                    holder.ed.setText("SAVE");
                    holder.ed_pic.setVisibility(View.VISIBLE);
                }

            }
        });


    }


    @Override
    public int getItemCount() {
        return personNames_offers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        EditText name, quantityy;// init the item view's
        Button ed,ed_pic;
        ImageView image_image;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (EditText) itemView.findViewById(R.id.Title);
            ed = (Button) itemView.findViewById(R.id.edit);
            ed_pic =(Button)itemView.findViewById(R.id.editpic);
            image_image=(ImageView) itemView.findViewById(R.id.im);
        }
    }



}
