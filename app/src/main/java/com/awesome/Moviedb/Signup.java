package com.awesome.Moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    Button loginview,register;
    EditText email,password,unbox;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        loginview = findViewById(R.id.loginview);
        register = findViewById(R.id.register);
        email =  findViewById(R.id.emailbox);
        unbox = findViewById(R.id.unbox);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        password = findViewById(R.id.passwordsign);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getText().toString();
                String p = password.getText().toString();
                mAuth.createUserWithEmailAndPassword(e, p)
                        .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(Signup.this,Login.class));
                                } else {
                                    Toast.makeText(Signup.this, "Account Craetion Failed", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                String username = unbox.getText().toString();
                DatabaseReference myRef = database.getReference("message");
                myRef.setValue("Hello, World!");
                myRef.setValue(username);


            }
        });
    }
}
