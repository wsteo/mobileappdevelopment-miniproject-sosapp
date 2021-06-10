package com.example.loginregistersos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class Login1 extends AppCompatActivity {

    private TextView register, textView;
    private EditText emailET,passwordET;
    private Button loginBtn,authe;
    private FirebaseAuth mAuth;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    private Executor executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        emailET = findViewById(R.id.editTextLEmail);
        passwordET = findViewById(R.id.editTextLPassword);
        register = findViewById(R.id.textViewRegister);
        loginBtn = findViewById(R.id.buttonLogIn);
        mAuth = FirebaseAuth.getInstance();
        textView = findViewById(R.id.textView9);
        executor = ContextCompat.getMainExecutor(this);
        authe = findViewById(R.id.authenticate);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                if(email.isEmpty()){
                    emailET.setError("Email is required!");
                    emailET.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailET.setError("Please provide a valid email!");
                    emailET.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    passwordET.setError("Password is required!");
                    passwordET.requestFocus();
                    return;
                }
                if(password.length()<6){
                    passwordET.setError("Password cannot less than 6 character!");
                    passwordET.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();

                            if (user.isEmailVerified()){
                                Intent mainactivity = new Intent(Login1.this, MainMenu.class);
                                startActivity(mainactivity);
                                finish();
                            }
                            else{
                                user.sendEmailVerification();
                                Toast.makeText(getApplicationContext(),"Please check your email to verify your SOS emergency application!",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Login failed.",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),""+ e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToRegister = new Intent(Login1.this,Register1.class);
                startActivity(intentToRegister);
            }
        });

        biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull @org.jetbrains.annotations.NotNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(Login1.this, errString, Toast.LENGTH_SHORT).show();
                textView.setText("Fail to Verify, Error!");
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull @org.jetbrains.annotations.NotNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

                Intent intent2mainmenu = new Intent(Login1.this, MainMenu.class);
                startActivity(intent2mainmenu);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();

                textView.setText("Failure");
            }
        });


        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("SOS APPLICATION AUTHENTICATION")
                .setNegativeButtonText("Cancel/ Use Password")
                .setConfirmationRequired(false)
                .build();
    }

    public void buttonAuthenticate(View view){
        BiometricManager biometricManager = BiometricManager.from(this);
        if (biometricManager.canAuthenticate() != BiometricManager.BIOMETRIC_SUCCESS){

            textView.setText("Biometric Not Supported");
            return;
        }
        biometricPrompt.authenticate(promptInfo);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}