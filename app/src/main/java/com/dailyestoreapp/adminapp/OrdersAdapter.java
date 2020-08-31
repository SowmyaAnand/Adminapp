package com.dailyestoreapp.adminapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {
    
    Context context;
    ArrayList<String> lts=new ArrayList<String>();
    ArrayList<String> orders_list_adapter_item = new ArrayList<>();
    ArrayList<String> orders_list_adapter_quantity = new ArrayList<>();
    ArrayList<String> orders_list_adapter_amount = new ArrayList<>();
    ArrayList<String> orders_list_adapter_date = new ArrayList<>();
   // ArrayList orders_list_adapter_item_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
    int quantity=1;
    public OrdersAdapter(Context context, ArrayList<String> orders_list_adapter_item, ArrayList<String> orders_list_adapter_quantity, ArrayList<String> orders_list_adapter_amount, ArrayList<String> orders_list_adapter_date) {
        this.context = context;
        this.orders_list_adapter_amount = orders_list_adapter_amount;
        this.orders_list_adapter_item = orders_list_adapter_item;
        this.orders_list_adapter_date = orders_list_adapter_date;
        this.orders_list_adapter_quantity = orders_list_adapter_quantity;
        this.lts.addAll(orders_list_adapter_item);

    }
    @Override
    public OrdersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_items, parent, false);
        // set the view's size, margins, paddings and layout parameters
        OrdersAdapter.MyViewHolder vh = new OrdersAdapter.MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(final OrdersAdapter.MyViewHolder holder, final int position) {

        // set the data in items
        if(orders_list_adapter_item.size()>0)
        {
            String name = orders_list_adapter_item.get(position);
            holder.name_orders.setText(name);
        }
            if(orders_list_adapter_quantity.size()>0)
            {
                String quantity = "QUANTITY:"+orders_list_adapter_quantity.get(position);
                holder.quantityy_orders.setText(quantity);
            }
                if(orders_list_adapter_date.size()>0)
                {
                    String created = orders_list_adapter_date.get(position);
                    holder.dt_orders.setText(created);
                }
                    if(orders_list_adapter_amount.size()>0) {

                        String price = "PRICE:"+orders_list_adapter_amount.get(position)+"/-";
                        holder.price_orders.setText(price);

                    }
    }


    @Override
    public int getItemCount() {
        return orders_list_adapter_item.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_orders,quantityy_orders,price_orders,dt_orders;// init the item view's
        Button addition,substraction,addbtn;
        public MyViewHolder(View itemView) {
            super(itemView);
            name_orders = (TextView) itemView.findViewById(R.id.Title_orders);
           quantityy_orders = (TextView) itemView.findViewById(R.id.publishNme_orders);
            price_orders = (TextView) itemView.findViewById(R.id.prce_orders);
            dt_orders = (TextView) itemView.findViewById(R.id.dte_orders);


        }
    }
    public void filter(String charText) {
        Log.e("texting if","persons="+charText);
        charText = charText.toLowerCase(Locale.getDefault());
        Log.e("texting if2","persons="+charText);
        orders_list_adapter_item.clear();
        Iterator itr=orders_list_adapter_item.iterator();
        if (charText.length() == 0) {
            Log.e("texting if3","persons="+charText);
            orders_list_adapter_item.addAll(lts);
        } else {
            for(int i =0;i<lts.size();i++) {
                Log.e("texting else","persons="+lts.get(i));
                String s = (String) lts.get(i);
                if (s.toLowerCase(Locale.getDefault()).contains(charText)) {
                    orders_list_adapter_item.add(s);
                }
            }
        }
        Log.e("text","persons="+orders_list_adapter_item);
        notifyDataSetChanged();
    }
}
