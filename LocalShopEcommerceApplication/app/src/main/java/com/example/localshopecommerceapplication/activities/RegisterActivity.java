package com.example.localshopecommerceapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.localshopecommerceapplication.db.DatabaseConnect;
import com.example.localshopecommerceapplication.LoginUtils;
import com.example.localshopecommerceapplication.R;
import com.example.localshopecommerceapplication.User;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    // Declare variables
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

        DatabaseConnect dbConnect = new DatabaseConnect(RegisterActivity.this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strFirstName = edtFirstName.getText().toString();
                String strLastName = edtLastName.getText().toString();
                String strEmail = edtEmail.getText().toString();
                String strPassword = edtPassword.getText().toString();

                if (strFirstName.isEmpty() || strLastName.isEmpty() || strEmail.isEmpty() || strPassword.isEmpty()) {
                    //txtDisplayInfo.setText("All fields are required")
                }
                else {
                    boolean emailExists = dbConnect.checkIfEmailExists(strEmail);

                    if (emailExists)
                    {
                        //txtDisplayInfo.setText("Account already exists");
                    }
                    else {
                        User newUser = new User(strFirstName, strLastName, strEmail, strPassword);
                        dbConnect.addUser(newUser);

                        LoginUtils.setLoginStatus(RegisterActivity.this, true, strEmail);

                        Intent intentHome = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intentHome);
                    }
                }
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