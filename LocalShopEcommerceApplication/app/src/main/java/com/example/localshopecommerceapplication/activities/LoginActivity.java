package com.example.localshopecommerceapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.localshopecommerceapplication.db.DatabaseConnect;
import com.example.localshopecommerceapplication.LoginUtils;
import com.example.localshopecommerceapplication.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    // Declare variables
    TextInputEditText edtEmail;
    TextInputEditText edtPassword;
    TextView txtDisplayInfo;
    Button btnLogin;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialise variables
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        txtDisplayInfo = findViewById(R.id.txtDisplayInfo);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        DatabaseConnect dbConnect = new DatabaseConnect(LoginActivity.this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEmail = edtEmail.getText().toString();
                String strPassword = edtPassword.getText().toString();

                boolean emailExists = dbConnect.checkIfEmailExists(strEmail);
                Log.d("emailExists", "Email Exists: " + emailExists);

                if (emailExists)
                {
                    String passwordReceived = dbConnect.passwordCheck(strEmail);
                    if (strPassword.equals(passwordReceived)) {
                        LoginUtils.setLoginStatus(getApplicationContext(), true, strEmail);

                        Intent intentHome = new Intent(getApplicationContext(), MainActivity.class);
                        intentHome.putExtra("email", strEmail);
                        startActivity(intentHome);
                    }
                    else {
                        txtDisplayInfo.setText("Incorrect credentials");
                    }
                }
                else {
                    txtDisplayInfo.setText("Account doesn't exist");
                }

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
            }
        });
    }
}