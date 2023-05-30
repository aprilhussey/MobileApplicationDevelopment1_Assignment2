package com.example.localshopecommerceapplication.activities;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.localshopecommerceapplication.db.DatabaseConnect;
import com.example.localshopecommerceapplication.models.ItemModel;
import com.example.localshopecommerceapplication.LoginUtils;
import com.example.localshopecommerceapplication.R;
import com.example.localshopecommerceapplication.User;
import com.example.localshopecommerceapplication.fragments.AccountFragment;
import com.example.localshopecommerceapplication.fragments.BasketFragment;
import com.example.localshopecommerceapplication.fragments.CategoriesFragment;
import com.example.localshopecommerceapplication.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Declare variables
    BottomNavigationView bottomNavigationView;
    MenuItem navHome;
    MenuItem navShop;
    MenuItem navBasket;
    MenuItem navAccount;

    public ArrayList<ItemModel> basket = new ArrayList<>();

    DatabaseConnect dbConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Comment out following line, this is test code
//        LoginUtils.setLoginStatus(getApplicationContext(), false, "");

        dbConnect = new DatabaseConnect(MainActivity.this);

        User adminUser = new User("Admin", "Account", "admin@email.com", "account");
        if (!dbConnect.checkIfEmailExists(adminUser.getEmail())) {
            dbConnect.addUser(adminUser);
        }

        User userUser = new User("User", "Account", "user@email.com", "account");
        if (!dbConnect.checkIfEmailExists(userUser.getEmail())) {
            dbConnect.addUser(userUser);
        }

        boolean loggedIn = LoginUtils.getLoginStatus(getApplicationContext());

        if (loggedIn) {
            bottomNavigationView = findViewById(R.id.bottomNavigationView);

            bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    int itemId = item.getItemId();

                    if (itemId == R.id.navHome) {
                        selectedFragment = new HomeFragment();
                    } else if (itemId == R.id.navShop) {
                        selectedFragment = new CategoriesFragment();
                    } else if (itemId == R.id.navBasket) {
                        selectedFragment = new BasketFragment();
                    } else if (itemId == R.id.navAccount) {
                        selectedFragment = new AccountFragment();
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, selectedFragment).commit();
                    return true;
                }
            });
        }
        else {
            Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intentLogin);
        }
    }

    public void addItemToBasket(ItemModel itemToAdd) {
        basket.add(itemToAdd);
    }
}