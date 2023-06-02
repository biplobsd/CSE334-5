package com.example.quiztime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText email, password, rePassword, fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        rePassword = findViewById(R.id.repassword);
        fullName = findViewById(R.id.fullname);

    }

    public void registerNowFun(View view) {
        String userNameTxt = email.getText().toString().trim();
        String passwordTxt = password.getText().toString().trim();
        String rePasswordTxt = rePassword.getText().toString().trim();
        String fullNameTxt = fullName.getText().toString().trim();
        if (userNameTxt.length() == 0 || passwordTxt.length() == 0 || rePasswordTxt.length() == 0 || fullNameTxt.length() == 0) {
            Toast.makeText(this, "Input correctly...", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!passwordTxt.equals(rePasswordTxt)) {
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
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(fullNameTxt)
                                    .build();

                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                startActivity(new Intent(Register.this, Login.class));
                                            }else{
                                                Toast.makeText(Register.this, "Display name update failed.",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    public void forgotFun(View view) {
        Toast.makeText(this, "Not implemented yet!", Toast.LENGTH_SHORT).show();
    }

    public void loginFun(View view) {
        startActivity(new Intent(this, Login.class));
        finish();
    }
}