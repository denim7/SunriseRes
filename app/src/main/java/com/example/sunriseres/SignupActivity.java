package com.example.sunriseres;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sunriseres.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    //Firebase
    FirebaseDatabase database;
    DatabaseReference users;

     EditText editUsername, editEmail, editPassword;
     Button btnSignup;
     FirebaseAuth mAuth;
     //ProgressBar progressBar;
     TextView textViewLog;

    private static final String TAG = "FirebaseErrors";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Firebase
        database = FirebaseDatabase.getInstance();
        //users = database.getReference("Users");
        FirebaseApp.initializeApp(this);

        //Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(SignupActivity.this);

        //EditText
        editUsername = findViewById(R.id.editUsername);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnSignup = findViewById(R.id.btnSignup);
        textViewLog = findViewById(R.id.textViewLog);


        //progressBar = findViewById(R.id.progressBarReg);

        btnSignup.setOnClickListener(this);
        editUsername.setOnClickListener(this);
        editEmail.setOnClickListener(this);
        editPassword.setOnClickListener(this);
        textViewLog.setOnClickListener(this);
    }
 /*
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    hideProgressDialog();
        if (users != null) {
            mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));
            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            findViewById(R.id.emailPasswordButtons).setVisibility(View.GONE);
            findViewById(R.id.emailPasswordFields).setVisibility(View.GONE);
            findViewById(R.id.signedInButtons).setVisibility(View.VISIBLE);

            findViewById(R.id.verifyEmailButton).setEnabled(!user.isEmailVerified());
        } else {
            mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);

            findViewById(R.id.emailPasswordButtons).setVisibility(View.VISIBLE);
            findViewById(R.id.emailPasswordFields).setVisibility(View.VISIBLE);
            findViewById(R.id.signedInButtons).setVisibility(View.GONE);
        }
    }*/


    //create a new user
    private void registerUser() {
        String username = editUsername.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (username.isEmpty()) {
            editUsername.setError("Enter Username");
            editUsername.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editEmail.setError("Enter Email ");
            editEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Enter valid Email!");
            editEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editPassword.setError("Enter Password");
            editPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editPassword.setError("Minimum of six characters");
            editPassword.requestFocus();
            return;
        }

        //progressBar.setVisibility(View.INVISIBLE);

        //create new user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            //FirebaseUser user = mAuth.getCurrentUser();
                           // updateUI(user);
                            Toast.makeText(getApplicationContext(),"User registered successfully.", Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnSignup:
                registerUser();
                break;

            case R.id.textViewLog:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
        }


    }
}