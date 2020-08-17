package com.dailyestoreapp.adminapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dailyestoreapp.adminapp.ui.Messages.MessagesViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class MessagesFragment  extends Fragment {
    RecyclerView recyclerView_messagelist;

    MessagesAdapter customAdapter_offers;
    ArrayList personNames_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.messageslist, container, false);
        recyclerView_messagelist = (RecyclerView) root.findViewById(R.id.messages_list);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext());
        recyclerView_messagelist.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter

        customAdapter_offers = new MessagesAdapter(root.getContext(), personNames_offers);
        recyclerView_messagelist.setAdapter(customAdapter_offers);

        return root;
    }
}
