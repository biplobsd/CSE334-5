package com.example.quiztime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText username, password, rePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        rePassword = findViewById(R.id.repassword);

    }

    public void registerNowFun(View view) {
        String userNameTxt = username.getText().toString().trim();
        String passwordTxt = password.getText().toString().trim();
        String rePasswordTxt = rePassword.getText().toString().trim();
        if(userNameTxt.length() == 0 || passwordTxt.length() == 0 || rePasswordTxt.length() == 0){
            Toast.makeText(this, "Input correctly...", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!passwordTxt.equals(rePasswordTxt)){
            Toast.makeText(this, "Password not same...", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(userNameTxt, passwordTxt)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Account created successful",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this, Login.class));
                        } else {
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
}