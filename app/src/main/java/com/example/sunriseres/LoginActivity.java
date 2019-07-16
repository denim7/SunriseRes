package com.example.sunriseres;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

     EditText editEmail, editPassword;
     Button btnlogin;
     TextView textforgot;
     Button btnSignup;

     private FirebaseAuth firebaseAuth;
     ProgressBar progressBar;

    private static final String TAG = "FirebaseErrors";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //get firebase auth instance
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        FirebaseApp.initializeApp(LoginActivity.this);

        //button,textview,edittext
        editEmail.findViewById(R.id.editEmail);
        editPassword.findViewById(R.id.editPassword);
        btnlogin.findViewById(R.id.btnlogin);
        textforgot.findViewById(R.id.textforgot);
        btnSignup.findViewById(R.id.btnSignup);


        btnSignup.setOnClickListener(this);
        editEmail.setOnClickListener(this);
        editPassword.setOnClickListener(this);

        //progressBar = findViewById(R.id.progressBarLog);
        progressBar.setVisibility(View.INVISIBLE);



    }


    private void userLogin() {
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        //checking if email and password are empty
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }
        //if the email and password are not empty display progressbar
        progressBar.setVisibility(View.VISIBLE);
    }


     @Override
     public void onClick(View view) {
         if(view == btnlogin){
             userLogin();
         }

         if(view == btnSignup){
             finish();
             startActivity(new Intent(this, SignupActivity.class));
         }
     }

}