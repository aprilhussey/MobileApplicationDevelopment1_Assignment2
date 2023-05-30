package com.example.localshopecommerceapplication.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.localshopecommerceapplication.adapters.BasketAdapter;
import com.example.localshopecommerceapplication.db.DatabaseConnect;
import com.example.localshopecommerceapplication.models.ItemModel;
import com.example.localshopecommerceapplication.activities.MainActivity;
import com.example.localshopecommerceapplication.R;

import java.io.File;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemPageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ItemPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemPageFragment newInstance(String param1, String param2) {
        ItemPageFragment fragment = new ItemPageFragment();
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
    ImageView imgItem;
    ImageButton btnAddItemToBasket;
    TextView txtItemName;
    TextView txtItemPrice;
    Spinner spnItemVer;
    Spinner spnItemSet;
    TextView txtItemStock;
    TextView txtItemDescriptionTxt;

    BasketAdapter basketAdapter;
    ArrayList<ItemModel> itemsInBasket = new ArrayList<ItemModel>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_page, container, false);

        imgItem = view.findViewById(R.id.imgItem);
        btnAddItemToBasket = view.findViewById(R.id.btnAddItemToBasket);
        txtItemName = view.findViewById(R.id.txtItemName);
        txtItemPrice = view.findViewById(R.id.txtItemPrice);
        spnItemVer = view.findViewById(R.id.spnItemVer);
        spnItemSet = view.findViewById(R.id.spnItemSet);
        txtItemStock = view.findViewById(R.id.txtItemStock);
        txtItemDescriptionTxt = view.findViewById(R.id.txtItemDescriptionTxt);

        basketAdapter = new BasketAdapter(getContext(), itemsInBasket);

        Bundle bundle = getArguments();
        String selectedItemName = bundle.getString("itemSelected");
        int spnVerPosition = bundle.getInt("verSelected");
        int spnSetPosition = bundle.getInt("setSelected");

        //btnAddItemToBasket  // Set
        txtItemName.setText(selectedItemName);

        ArrayAdapter<String> spnVerAdapter = new ArrayAdapter<>(spnItemVer.getContext(), android.R.layout.simple_spinner_item, dbConnect.getVersionsOfItem(selectedItemName));
        spnVerAdapter.setDropDownViewResource((android.R.layout.simple_spinner_dropdown_item));
        spnItemVer.setAdapter(spnVerAdapter);
        spnItemVer.setSelection(spnVerPosition);

        ArrayAdapter<String> spnSetAdapter = new ArrayAdapter<>(spnItemSet.getContext(), android.R.layout.simple_spinner_item, dbConnect.getSetsOfItem(selectedItemName));
        spnSetAdapter.setDropDownViewResource((android.R.layout.simple_spinner_dropdown_item));
        spnItemSet.setAdapter(spnSetAdapter);
        spnItemSet.setSelection(spnSetPosition);

        String selectedVer = spnVerAdapter.getItem(spnVerPosition);
        String selectedSet = spnSetAdapter.getItem(spnSetPosition);

        ItemModel item = dbConnect.getItem(selectedItemName, selectedVer, selectedSet);

        imgItem.setImageURI(Uri.fromFile(new File(dbConnect.getItemImageFilePath(item))));
        txtItemPrice.setText(dbConnect.getItemPrice(item));    // Set look for itemName that has selectedVer and selectedSet

        txtItemStock.setText(dbConnect.getInStock(item));

        btnAddItemToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedVer = spnVerAdapter.getItem(spnVerPosition);
                String selectedSet = spnSetAdapter.getItem(spnSetPosition);

                ItemModel item = dbConnect.getItem(selectedItemName, selectedVer, selectedSet);

                MainActivity main = (MainActivity) getActivity();
                if (main != null) {
                    main.addItemToBasket(item);
                }
            }
        });

        return view;
    }
}