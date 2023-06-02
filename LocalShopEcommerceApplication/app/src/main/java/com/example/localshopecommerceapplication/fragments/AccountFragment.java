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

                String MindFlayer_Nonfoil_CLB = getAbsolutePathOfImage("MindFlayer_Non-foil_CLB.png");
                ItemModel item_MindFlayer_Nonfoil_CLB = new ItemModel(0, "Mind Flayer", "Singles", "0.20", "Non-foil",
                        "Commander Legends: Battle for Baldur's Gate", MindFlayer_Nonfoil_CLB, "Creature - Horror\n" +
                        "Dominate Monster — When Mind Flayer enters the battlefield, gain control of target creature for as long" +
                        "as you control Mind Flayer.\n“Shed your thoughts and let my will flow through you.”", 1);
                dbConnect.addItem(item_MindFlayer_Nonfoil_CLB);

                String GiadaFontOfHope_NonfoilAltArt_SNC = getAbsolutePathOfImage("GiadaFontOfHope_Non-foilAltArt_SNC.png");
                ItemModel item_GiadaFontOfHope_NonfoilAltArt_SNC = new ItemModel(1, "Giada, Font of Hope", "Singles", "2.20", "Non-foil Alt. art",
                        "Streets of New Capenna", GiadaFontOfHope_NonfoilAltArt_SNC, "Legendary Creature - Angel\n" +
                        "Flying, vigilance\n" + "Each other Angel you control enters the battlefield with an additional +1/+1 counter" +
                        " on it for each Angel you already control.\n" + "{T}: Add {W}. Spend this mana only to cast an Angel spell.", 1);
                dbConnect.addItem(item_GiadaFontOfHope_NonfoilAltArt_SNC);

                String GiadaFontOfHope_Nonfoil_SNC = getAbsolutePathOfImage("GiadaFontOfHope_Non-foil_SNC.png");
                ItemModel item_GiadaFontOfHope_Nonfoil_SNC = new ItemModel(2, "Giada, Font of Hope", "Singles", "2.70", "Non-foil",
                        "Streets of New Capenna", GiadaFontOfHope_Nonfoil_SNC, "Legendary Creature - Angel\n" +
                        "Flying, vigilance\n" + "Each other Angel you control enters the battlefield with an additional +1/+1 counter" +
                        " on it for each Angel you already control.\n" + "{T}: Add {W}. Spend this mana only to cast an Angel spell.", 1);
                dbConnect.addItem(item_GiadaFontOfHope_Nonfoil_SNC);

                String KrenkoMobBoss_Nonfoil_JMP = getAbsolutePathOfImage("KrenkoMobBoss_Non-foil_JMP.png");
                ItemModel item_KrenkoMobBoss_Nonfoil_JMP = new ItemModel(3, "Krenko, Mob Boss", "Singles", "4.99", "Non-foil",
                        "Jumpstart", KrenkoMobBoss_Nonfoil_JMP, "Legendary Creature - Goblin Warrior\n" +
                        "{T}: Create X 1/1 red Goblin creature tokens, where X is the number of Goblins you control.\n" +
                        "“He displays a perverse charisma fueled by avarice. Highly dangerous. Recommend civil sanctions.”\n" +
                        "—Agmand Sarv, Azorius hussar", 1);
                dbConnect.addItem(item_KrenkoMobBoss_Nonfoil_JMP);

                String LathrilBladeOfTheElves_Foil_KHC = getAbsolutePathOfImage("LathrilBladeOfTheElves_Foil_KHC.png");
                ItemModel item_LathrilBladeOfTheElves_Foil_KHC = new ItemModel(4, "Lathril, Blade of the Elves", "Singles", "4.50", "Foil",
                        "Kaldheim Commander", LathrilBladeOfTheElves_Foil_KHC, "Legendary Creature - Elf Noble\n" +
                        "Menace\n" + "Whenever Lathril, Blade of the Elves deals combat damage to a player, create that many 1/1 green Elf Warrior creature tokens.\n"
                        + "{T}, Tap ten untapped Elves you control: Each opponent loses 10 life and you gain 10 life.", 1);
                dbConnect.addItem(item_LathrilBladeOfTheElves_Foil_KHC);

                String BoosterBox_Set_MID = getAbsolutePathOfImage("BoosterBox_Set_MID.png");
                ItemModel item_BoosterBox_Set_MID = new ItemModel(5, "Innistrad: Midnight Hunt Booster Box", "Booster Boxes", "90.00", "Set",
                        "Innistrad: Midnight Hunt", BoosterBox_Set_MID, "Become what you fear in a gothic horror set overrun with werewolves, warlocks, and spooky mechanics.\n"
                        + "Each pack contains 12 Magic cards (360 Magic cards total).\n" + "1–4 Rares and/or Mythic Rares in every pack.\n" + "At least 1 traditional foil card in every pack.",
                        1);
                dbConnect.addItem(item_BoosterBox_Set_MID);

                String BoosterBox_Draft_MID = getAbsolutePathOfImage("BoosterBox_Draft_MID.png");
                ItemModel item_BoosterBox_Draft_MID = new ItemModel(6, "Innistrad: Midnight Hunt Booster Box", "Booster Boxes", "90.00", "Draft",
                        "Innistrad: Midnight Hunt", BoosterBox_Draft_MID, "Become what you fear in a gothic horror set overrun with werewolves, warlocks, and spooky mechanics.\n"
                        + "Each pack contains 15 Magic cards (540 Magic cards total).\n" + "Find 2 double-faced cards in every booster.\n" + "1 Eternal Night full-art basic land in every pack.",
                        1);
                dbConnect.addItem(item_BoosterBox_Draft_MID);


                String Booster_Collector_DMU = getAbsolutePathOfImage("Booster_Collector_DMU.png");
                ItemModel item_Booster_Collector_DMU = new ItemModel(7, "Dominaria United Booster", "Boosters", "21.00", "Collector",
                        "Dominaria United", Booster_Collector_DMU, "Return to Magic’s home plane in a set full of your favorite MTG legends and heroes\n"
                        + "15 Dominaria United cards and 1 foil token\n" + "5–6 cards of rarity Rare or higher\n" + "10–13 foil cards in every pack, including at least 2 foil Legendary Creatures\n"
                        + "Lost Legends—card from the 1994 set, Legends, in 3% of Collector Boosters\n" + "Full of rares, foils and special treatments", 1);
                dbConnect.addItem(item_Booster_Collector_DMU);

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
            if (filename.equals("images") || filename.equals("webkit")) {
                Log.d("tag", "Skipping directory: " + filename);
                continue;
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