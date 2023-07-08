package com.example.driversdoor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class register extends AppCompatActivity {
    private EditText editTextName, editTextEmail, editTextPassword;
    private RadioGroup radioGroupOptions;
    private RadioButton radioButtonPassenger, radioButtonDriver;
    private Button buttonRegister, buttonLogin;

    private dbConnection db;



    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        db = new dbConnection(this);


        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        radioGroupOptions = findViewById(R.id.radioGroupOptions);
        radioButtonPassenger = findViewById(R.id.radioButtonPassenger);
        radioButtonDriver = findViewById(R.id.radioButtonDriver);
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonLogin = findViewById(R.id.buttonLogin);


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void registerUser() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String userType = radioButtonPassenger.isChecked() ? "Passenger" : "Driver";

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(register.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else {
            if (db.checkUsername(email)) {
                Toast.makeText(register.this, "Email already exists. Please use a different email.", Toast.LENGTH_SHORT).show();
            } else {
                AddressBook addressBook = new AddressBook(name, email, password, userType);
                db.addUser(addressBook);

                Toast.makeText(register.this, "Registration successful!", Toast.LENGTH_SHORT).show();

                editTextName.setText("");
                editTextEmail.setText("");
                editTextPassword.setText("");

                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
                finish();
            }
        }
    }
}

