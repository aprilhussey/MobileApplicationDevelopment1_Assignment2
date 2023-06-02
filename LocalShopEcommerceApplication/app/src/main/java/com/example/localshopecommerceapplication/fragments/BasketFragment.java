package com.example.localshopecommerceapplication.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.localshopecommerceapplication.LoginUtils;
import com.example.localshopecommerceapplication.activities.LoginActivity;
import com.example.localshopecommerceapplication.adapters.BasketAdapter;
import com.example.localshopecommerceapplication.db.DatabaseConnect;
import com.example.localshopecommerceapplication.models.ItemModel;
import com.example.localshopecommerceapplication.activities.MainActivity;
import com.example.localshopecommerceapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BasketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BasketFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BasketFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BasketFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BasketFragment newInstance(String param1, String param2) {
        BasketFragment fragment = new BasketFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
    BasketAdapter basketAdapter;
    Button btnCheckout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_basket, container, false);

        // Get a reference to the recycler view
        recyclerView = fragmentView.findViewById(R.id.recyclerView);

        // Setup the recycler view
        setupRecyclerView();

        btnCheckout = fragmentView.findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText addressInput = fragmentView.findViewById(R.id.addressInput);
                String address = addressInput.getText().toString();
                // Basic address check, address has to be 10+ characters
                if (address.length() > 10 ) {
                    // Get current user email:
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
                    String userEmail = sharedPreferences.getString("email", "user@email.com");

                    // Enter to the database and clear the basket, clear address, play toast saying order placed, then navigate to home.
                    MainActivity main = (MainActivity) getActivity();
                    DatabaseConnect dbConnect = new DatabaseConnect(getContext());
                    dbConnect.addOrder(address, main.basket, userEmail);
                    main.basket = new ArrayList<>();

                    Toast.makeText(getActivity(), "Order placed!", Toast.LENGTH_LONG).show();
                    BottomNavigationView navView = getActivity().findViewById(R.id.bottomNavigationView);
                    navView.setSelectedItemId(R.id.navShop);
                } else {
                    // Pop up toast saying that address needs to be more than 10 characters
                    Toast.makeText(getActivity(), "Address has to be more than 10 characters", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return fragmentView;
    }

    public void setupRecyclerView() {
        // Create new array list and add data to it
        MainActivity main = (MainActivity) getActivity();
        if (main != null) {
            ArrayList<ItemModel> basketItems = main.basket;
            basketAdapter = new BasketAdapter(getContext(), basketItems);    // Initialise adapter class and pass array list to it
            recyclerView.setAdapter(basketAdapter);   // Set adapter to recycler view
        }
    }
}