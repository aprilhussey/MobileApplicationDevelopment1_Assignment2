package com.example.localshopecommerceapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    // Declare variables
    BottomNavigationView bottomNavigationView;
    MenuItem navHome;
    MenuItem navShop;
    MenuItem navBasket;
    MenuItem navWishlist;
    MenuItem navAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean loggedIn = LoginUtils.getLoginStatus(MainActivity.this);

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
                        selectedFragment = new ShopFragment();
                    } else if (itemId == R.id.navBasket) {
                        selectedFragment = new BasketFragment();
                    } else if (itemId == R.id.navWishlist) {
                        selectedFragment = new WishlistFragment();
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
}