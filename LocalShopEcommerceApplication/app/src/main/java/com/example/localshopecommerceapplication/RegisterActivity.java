package com.example.localshopecommerceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText edtFirstName;
    TextInputEditText edtLastName;
    TextInputEditText edtEmail;
    TextInputEditText edtPassword;
    Button btnRegister;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialise variables
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if email exists
                    // if yes; account already exists
                    // else; registerSuccessful

                // TESTING PURPOSES JUST WORK FOR NOW
                LoginUtils.setLoginStatus(RegisterActivity.this, true);

                Intent intentHome = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intentHome);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intentLogin);
            }
        });
    }
}