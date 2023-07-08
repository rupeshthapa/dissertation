package com.example.driversdoor;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;

    private dbConnection db;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        db = new dbConnection(this);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(login.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
        } else {
            if (db.checkCredentials(email, password)) {
                // Login successful
                Toast.makeText(login.this, "Login successful!", Toast.LENGTH_SHORT).show();

                // Start the home activity
                Intent intent = new Intent(login.this, home.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(login.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        }


        registerButton = findViewById(R.id.buttonRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the RegisterActivity
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }
        });
    }
}
