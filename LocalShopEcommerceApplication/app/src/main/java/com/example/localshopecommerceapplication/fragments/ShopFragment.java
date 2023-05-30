package com.example.localshopecommerceapplication.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.localshopecommerceapplication.LoginUtils;
import com.example.localshopecommerceapplication.db.DatabaseConnect;
import com.example.localshopecommerceapplication.adapters.ItemAdapter;
import com.example.localshopecommerceapplication.models.ItemModel;
import com.example.localshopecommerceapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopFragment extends Fragment {
    public ShopFragment() {
        // Required empty public constructor
    }
    // Declare variables
    DatabaseConnect dbConnect;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dbConnect = new DatabaseConnect(getContext());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Declare variables
    RecyclerView recyclerView;
    ItemAdapter itemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        // Get a reference to the recycler view
        recyclerView = view.findViewById(R.id.recyclerView);

        Bundle bundle = getArguments();
        String categoryName = bundle.getString("categorySelected");

        // Setup the recycler view
        setupRecyclerView(categoryName);

        // Make button visible if admin
        if (LoginUtils.getCurrentEmail(getContext()).equals("admin@email.com")) {
            View button = view.findViewById(R.id.addItemButton);
            button.setVisibility(View.VISIBLE);
        }

        return view;
    }

    public void setupRecyclerView(String categoryName) {
        // Create new array list and add data to it
        ArrayList<ItemModel> itemModelArrayList = dbConnect.getItemsOfCategory(categoryName);
        itemAdapter = new ItemAdapter(getContext(), itemModelArrayList);    // Initialise adapter class and pass array list to it
        recyclerView.setAdapter(itemAdapter);   // Set adapter to recycler view
    }
}