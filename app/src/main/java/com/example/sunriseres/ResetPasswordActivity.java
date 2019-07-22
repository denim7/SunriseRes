package com.example.sunriseres;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editEmail;
    Button btnlogin;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        editEmail = findViewById(R.id.editEmail);
        btnlogin = findViewById(R.id.btnlogin);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        editEmail.setOnClickListener(this);
        btnlogin.setOnClickListener(this);
    }
        public void resetPassword() {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            String emailAddress;
            emailAddress = editEmail.getText().toString().trim();
            if (emailAddress.isEmpty()) {
                editEmail.setError("Enter email ");
                editEmail.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
                editEmail.setError("Enter valid Email");
                editEmail.requestFocus();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            auth.sendPasswordResetEmail(emailAddress).
                    addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                Toast.makeText(ResetPasswordActivity.this,
                                        "Password sent to your email", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(ResetPasswordActivity.this,
                                        task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }

                        }
                    });
        }



    @Override
    public void onClick(View view) {
        if (view == btnlogin){
                resetPassword();

        }
    }
}
