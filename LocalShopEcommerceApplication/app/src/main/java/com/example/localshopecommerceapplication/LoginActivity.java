package com.example.localshopecommerceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText edtEmail;
    TextInputEditText edtPassword;
    Button btnLogin;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialise variables
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEmail = edtEmail.getText().toString();
                String strPassword = edtPassword.getText().toString();

                // check if email exists
                // if yes; check if password is correct
                    //if yes; loginSuccessful
                    //else; !loginSuccessful
                // else; account doesn't exist

                // TESTING PURPOSES JUST WORK FOR NOW
                LoginUtils.setLoginStatus(LoginActivity.this, true);

                Intent intentHome = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intentHome);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intentRegister = new Intent(LoginActivity.this, RegisterActivty.class);
                //startActivity(intentRegister);
            }
        });
    }
}