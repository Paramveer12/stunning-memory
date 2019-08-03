package com.awesome.Moviedb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText mEmailField,mPasswordField;
    TextView createbtn;
    Button loginbtn;
    private TextView mStatusTextView;
    private FirebaseAuth mAuth;
    public ProgressDialog mProgressDialog;
    private TextView mDetailTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createbtn = findViewById(R.id.createbtn);
        loginbtn = findViewById(R.id.loginbtn);
        mEmailField = findViewById(R.id.usernamebox);
        mPasswordField = findViewById(R.id.passwordbox);
        mStatusTextView = findViewById(R.id.status);
        mDetailTextView = findViewById(R.id.details);
        mAuth = FirebaseAuth.getInstance();
        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Signup.class));
            }
        });

        if(mAuth.getCurrentUser() != null){
            login();
        }

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signInWithEmailAndPassword(mEmailField.getText().toString(), mPasswordField.getText().toString())
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    login();
                                } else {
                                    Toast.makeText(Login.this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                                    mDetailTextView.setText("Sign up if you don't already have account");
                                }

                            }
                        });

            }
        });
        
    }

    private void login() {
        startActivity(new Intent(Login.this,MainActivity.class));
    }


}
