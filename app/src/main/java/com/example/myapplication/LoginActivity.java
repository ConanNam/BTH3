package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.model.User;
import com.example.myapplication.sqlite.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsername, txtpassword;
    private Button btnLogin, btnGoSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsername = findViewById(R.id.editText);
        txtpassword = findViewById(R.id.editText2);
        btnLogin = findViewById(R.id.button);
        btnGoSignup = findViewById(R.id.button2);


        btnGoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),SignUp.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                String password = txtpassword.getText().toString();
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);

                DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
                User u = databaseHelper.checkLogin(user);
                if (u != null){
                    Intent intent = new Intent(getBaseContext(),MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
