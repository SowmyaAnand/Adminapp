package com.dailyestoreapp.adminapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class test  extends RecyclerView.Adapter<test.MyViewHolder2> {
    ArrayList personNames,Imagesoffer;
    Context context;
    ArrayList subid;
    categorySubcategoryCommunicaion mComminication;
    public test(Context context, ArrayList personNames, ArrayList Imagesoffer, ArrayList subid,categorySubcategoryCommunicaion communication) {
        this.context = context;
        this.personNames = personNames;
        this.Imagesoffer =Imagesoffer;
        this.mComminication=communication;
        this.subid=subid;
    }
    @Override
    public test.MyViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.offers_trial, parent, false);
        // set the view's size, margins, paddings and layout parameters
        test.MyViewHolder2 vh = new test.MyViewHolder2(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(test.MyViewHolder2 holder, final int position) {
        Log.e("test","personanames="+personNames);
        // set the data in items
        String name = (String) personNames.get(position);
        int n= (int) Imagesoffer.get(position);
        holder.img.setImageResource(n);
        holder.name.setText(name);

        // holder.name.setText(name);
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String selectedname = (String) personNames.get(position);
                Integer selectedid = (Integer)subid.get(position);
mComminication.respond(selectedid);
            }
        });
    }


    @Override
    public int getItemCount() {
        return personNames.size();
    }
    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        TextView name;// init the item view's
        ImageView img;
        public MyViewHolder2(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.ct_image);
            name = (TextView)itemView.findViewById(R.id.ct_text);
            // get the reference of item view's

        }
    }
}
