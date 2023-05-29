package com.example.localshopecommerceapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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

        boolean loggedIn = LoginUtils.getLoginStatus(getContext());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUtils.setLoginStatus(getContext(), false);
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

                ItemModel itemTest1 = new ItemModel(0, "Mind Flayer", "Singles", 0.16, "Non-foil",
                        "Commander Legends: Battle for Baldur's Gate", MindFlayer_CLB, "Creature - Horror\n" +
                        "Dominate Monster — When Mind Flayer enters the battlefield, gain control of target creature for as long" +
                        "as you control Mind Flayer.\n“Shed your thoughts and let my will flow through you.”", 1);
                dbConnect.addItem(itemTest1);
                ItemModel itemTest2 = new ItemModel(1, "Booster Box", "Booster Boxes", 100.00, "Draft",
                        "Commander Legends: Battle for Baldur's Gate", MindFlayer_CLB, "Lorem ipsum", 1);
                dbConnect.addItem(itemTest2);
                ItemModel itemTest3 = new ItemModel(2, "Booster Pack", "Boosters", 3.99, "Set",
                        "Commander Legends: Battle for Baldur's Gate", MindFlayer_CLB, "Lorem ipsum", 1);
                dbConnect.addItem(itemTest3);

                Log.d("items added to database", "items added to database");
            }
        });
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