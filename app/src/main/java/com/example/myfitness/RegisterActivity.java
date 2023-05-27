package com.example.myfitness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText registerEmail, registerPassword, registerRePassword;
    Button registerBtn;
    TextView alreadyAccount;

    FirebaseAuth mAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        initialization
        registerEmail = findViewById(R.id.registerEmail);
        registerPassword = findViewById(R.id.registerPassword);
        registerRePassword = findViewById(R.id.registerRePassword);
        registerBtn = findViewById(R.id.registerBtn);
        alreadyAccount = findViewById(R.id.alreadyAccount);

        mAuth = FirebaseAuth.getInstance();

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("Register");
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);
        progressDialog = new ProgressDialog(this);

        alreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(registerIntent);
            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = registerEmail.getText().toString().trim();
                String password = registerPassword.getText().toString().trim();
                String rePassword = registerRePassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    registerEmail.setError("Email is required");
                }
                else if (TextUtils.isEmpty(password)) {
                    registerPassword.setError("Password is required");
                }
                else if (TextUtils.isEmpty(rePassword)) {
                    registerRePassword.setError("Re password is required");
                }

                else if (password.length()<6) {
                    Toast.makeText(RegisterActivity.this, "Password length must be greater than 6 digits", Toast.LENGTH_SHORT).show();
                }
                else {
                    registerUser(email, password, rePassword);
                }
            }
        });
    }

    private void registerUser(String email, String password, String rePassword) {
        progressDialog.setTitle("Please wait...");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            progressDialog.dismiss();
                            startActivity(new Intent(RegisterActivity.this,HomeActivity.class));
                            Toast.makeText(RegisterActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}