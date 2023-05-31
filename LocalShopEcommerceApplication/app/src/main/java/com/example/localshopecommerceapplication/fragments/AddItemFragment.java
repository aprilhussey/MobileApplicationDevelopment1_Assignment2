package com.example.localshopecommerceapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import com.example.localshopecommerceapplication.R;
import com.example.localshopecommerceapplication.db.DatabaseConnect;
import com.example.localshopecommerceapplication.models.ItemModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddItemFragment extends Fragment {

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

    EditText edtName;
    EditText edtCategory;
    EditText edtPrice;
    EditText edtVersion;
    EditText edtSet;
    EditText edtImageFilePath;
    EditText edtDescription;

    Button btnItemInStock;
    Button btnItemOutOfStock;

    Button btnAddItemToDb;

    int inStock;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);

        edtName = view.findViewById(R.id.edtName);
        edtCategory = view.findViewById(R.id.edtCategory);
        edtPrice = view.findViewById(R.id.edtPrice);
        edtVersion = view.findViewById(R.id.edtVersion);
        edtSet = view.findViewById(R.id.edtSet);
        edtImageFilePath = view.findViewById(R.id.edtImageFilePath);
        edtDescription = view.findViewById(R.id.edtDescription);

        btnItemInStock = view.findViewById(R.id.btnItemInStock);
        btnItemOutOfStock = view.findViewById(R.id.btnItemOutOfStock);
        btnAddItemToDb = view.findViewById(R.id.btnAddItemToDb);

        inStock = 1;

        btnItemInStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inStock = 1;
            }
        });

        btnItemOutOfStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inStock = 0;
            }
        });

        btnAddItemToDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add item to the DB based on contents of EditTexts

                String strName = edtName.getText().toString();
                String strCategory = edtCategory.getText().toString();
                String strPrice = edtPrice.getText().toString();
                String strVersion = edtVersion.getText().toString();
                String strSet = edtSet.getText().toString();
                String strImageFilePath = edtImageFilePath.getText().toString();
                String strDescription = edtDescription.getText().toString();

                if (strName != "" || strCategory != "" || strPrice != "" || strVersion != ""
                        || strSet != "" || strImageFilePath != "" || strDescription != "") {
                    ItemModel newItem = new ItemModel(strName, strCategory, strPrice, strVersion,
                            strSet, strImageFilePath, strDescription, inStock);
                    dbConnect.addItem(newItem);
                }
            }
        });
        return view;
    }
}