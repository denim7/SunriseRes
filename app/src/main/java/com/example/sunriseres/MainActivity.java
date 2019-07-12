package com.example.sunriseres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnRegister;
    EditText editEmail, editPassword;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnRegister = findViewById(R.id.btnRegister);

        Button btnSignup = findViewById(R.id.btnLogin);
        btnSignup.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        editPassword.setOnClickListener(this);
        editEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegister:
                startActivity(new Intent(getApplicationContext(),SignupActivity.class));
                break;
        }

    }
}
