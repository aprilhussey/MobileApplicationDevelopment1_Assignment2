package com.example.localshopecommerceapplication.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.localshopecommerceapplication.adapters.BasketAdapter;
import com.example.localshopecommerceapplication.adapters.OrderAdapter;
import com.example.localshopecommerceapplication.db.DatabaseConnect;
import com.example.localshopecommerceapplication.models.ItemModel;
import com.example.localshopecommerceapplication.activities.LoginActivity;
import com.example.localshopecommerceapplication.LoginUtils;
import com.example.localshopecommerceapplication.R;
import com.example.localshopecommerceapplication.models.OrderModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Locale;

public class AccountFragment extends Fragment {
    RecyclerView recyclerView;
    OrderAdapter orderAdapter;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    // Declare variables
    DatabaseConnect dbConnect;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dbConnect = new DatabaseConnect(getContext());
    }

    // Declare variables
    Button btnLogout;
    Button btnAddItemsToDb;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialise variables
        btnLogout = view.findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUtils.setLoginStatus(getContext(), false, "");
                Intent intentLogin = new Intent(getContext(), LoginActivity.class);
                startActivity(intentLogin);
            }
        });

        btnAddItemsToDb = view.findViewById(R.id.btnAddItemsToDb);

        btnAddItemsToDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyAssets();
                String MindFlayer_CLB = getAbsolutePathOfImage("MindFlayer_Non-foil_CLB.png");

                ItemModel itemTest1 = new ItemModel(0, "Mind Flayer", "Singles", "0.16", "Non-foil",
                        "Commander Legends: Battle for Baldur's Gate", MindFlayer_CLB, "Creature - Horror\n" +
                        "Dominate Monster — When Mind Flayer enters the battlefield, gain control of target creature for as long" +
                        "as you control Mind Flayer.\n“Shed your thoughts and let my will flow through you.”", 1);
                dbConnect.addItem(itemTest1);
                ItemModel itemTest2 = new ItemModel(1, "Booster Box", "Booster Boxes", "100.00", "Draft",
                        "Commander Legends: Battle for Baldur's Gate", MindFlayer_CLB, "Lorem ipsum", 1);
                dbConnect.addItem(itemTest2);
                ItemModel itemTest3 = new ItemModel(2, "Booster Pack", "Boosters", "3.99", "Set",
                        "Commander Legends: Battle for Baldur's Gate", MindFlayer_CLB, "Lorem ipsum", 1);
                dbConnect.addItem(itemTest3);

                Log.d("items added to database", "items added to database");
            }
        });

        Button btnCheckItemsTable;
        btnCheckItemsTable = view.findViewById(R.id.btnCheckItemsTable);

        btnCheckItemsTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbConnect.checkItemsTable();
            }
        });

        // Populate orders based on user, if email is admin@email.com (Admin account) populate with ALL orders, else just that user's orders
        String email = LoginUtils.getCurrentEmail(getContext());
        DatabaseConnect dbConnect = new DatabaseConnect(getContext());
        ArrayList<OrderModel> orderModels;
        if (email.equals("admin@email.com")) {
            orderModels = dbConnect.getAdminOrders(); //Security flaw! using wildcards as an email address on registration has the same result!
        } else {
            orderModels = dbConnect.getOrders(email);
        }
        orderAdapter = new OrderAdapter(getContext(), orderModels);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(orderAdapter);
    }

    private void copyAssets() {
        AssetManager assetManager = getContext().getAssets();
        String[] files = null;

        try {
            files = assetManager.list("");
            Log.d("tag", files[0]);
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }

        if (files != null) for (String filename : files) {
            // Skip directories
            try {
                if (assetManager.list(filename) != null) continue;
            } catch (IOException e) {
                Log.e("tag", "Failed to check if asset is a directory: " + filename, e);
            }

            InputStream in = null;
            OutputStream out = null;

            try {
                in = assetManager.open(filename);
                File outFile = new File(getContext().getExternalFilesDir(null), filename);
                out = new FileOutputStream((outFile));
                copyFile(in, out);
            } catch (IOException e) {
                Log.e("tag", "Failed to copy asset file: " + filename, e);
            }

            finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        // NOOP
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        // NOOP
                    }
                }
            }
        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    private String getAbsolutePathOfImage(String imageName) {
        File outFile = new File(getContext().getExternalFilesDir(null), imageName);
        String filePath = outFile.getAbsolutePath();

        return filePath;
    }
}