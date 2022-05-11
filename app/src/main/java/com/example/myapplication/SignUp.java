package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.model.User;
import com.example.myapplication.sqlite.DatabaseHelper;

public class SignUp extends AppCompatActivity {

    private EditText txtUsernameSignup;
    private EditText txtPasswordSignup;
    private EditText txtFullname;
    private EditText txtAddress;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        txtUsernameSignup = findViewById(R.id.editText3);
        txtPasswordSignup = findViewById(R.id.editText4);
        txtFullname = findViewById(R.id.editText5);
        txtAddress = findViewById(R.id.editText6);
        btnSignUp = findViewById(R.id.button3);




        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
                String username = txtUsernameSignup.getText().toString();
                String password = txtPasswordSignup.getText().toString();
                String fullname = txtFullname.getText().toString();
                String address = txtAddress.getText().toString();
                User user = new User();
                user.setAddress(address);
                user.setFullname(fullname);
                user.setUsername(username);
                user.setPassword(password);
                databaseHelper.signUp(user);
                Intent intent = new Intent(getBaseContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
