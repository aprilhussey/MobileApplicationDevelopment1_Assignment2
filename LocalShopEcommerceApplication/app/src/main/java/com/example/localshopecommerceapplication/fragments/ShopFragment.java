package com.example.localshopecommerceapplication.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.localshopecommerceapplication.db.DatabaseConnect;
import com.example.localshopecommerceapplication.adapters.ItemAdapter;
import com.example.localshopecommerceapplication.models.ItemModel;
import com.example.localshopecommerceapplication.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopFragment newInstance(String param1, String param2) {
        ShopFragment fragment = new ShopFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    // Declare variables
    RecyclerView recyclerView;
    ArrayList<ItemModel> itemModelArrayList;
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

        return view;
    }

    public void setupRecyclerView(String categoryName) {
        // Create new array list and add data to it
        ArrayList<ItemModel> itemModelArrayList = dbConnect.getItemsOfCategory(categoryName);
        itemAdapter = new ItemAdapter(getContext(), itemModelArrayList);    // Initialise adapter class and pass array list to it
        recyclerView.setAdapter(itemAdapter);   // Set adapter to recycler view
    }
}