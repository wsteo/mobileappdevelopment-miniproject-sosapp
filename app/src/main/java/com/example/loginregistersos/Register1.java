package com.example.loginregistersos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register1 extends AppCompatActivity {
    private EditText nameET, idET, phoneET, addressET,emailET, passwordET;
    private Button registerBtn;
    private FirebaseAuth mAuth;
    private String name, id, phone, address, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Create New User Account");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        nameET = findViewById(R.id.editTextName);
        idET = findViewById(R.id.editTextID);
        phoneET = findViewById(R.id.editTextPhone);
        addressET = findViewById(R.id.editTextAddress);
        emailET = findViewById(R.id.editTextEmail);
        passwordET = findViewById(R.id.editTextPassword);
        registerBtn = findViewById(R.id.buttonRegister);

        mAuth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputData();
            }
        });
    }

    private void inputData() {
        name = nameET.getText().toString().trim();
        id = idET.getText().toString().trim();
        phone = phoneET.getText().toString().trim();
        address = addressET.getText().toString().trim();
        email = emailET.getText().toString().trim();
        password = passwordET.getText().toString().trim();
        //check data if empty
        if(nameET.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(),"Name is empty!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(idET.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(),"ID is empty!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(phoneET.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(),"Phone number is empty!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(addressET.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(),"Address is empty!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(getApplicationContext(),"Invalid email pattern!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length()<6){
            Toast.makeText(getApplicationContext(),"Password must be at least 6 characters!!!",Toast.LENGTH_SHORT).show();
            return;
        }
        createAccount();
    }

    private void createAccount() {

        //create account and save into firebase
        mAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                //account created
                saveAccountData();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //failed to create account
                Toast.makeText(getApplicationContext(),""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveAccountData() {

            //set data to save
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("uid",""+mAuth.getUid());
            hashMap.put("accountType","User");
            hashMap.put("name",""+name);
            hashMap.put("id",""+id);
            hashMap.put("phone",""+phone);
            hashMap.put("address",""+address);
            hashMap.put("email",""+email);
            hashMap.put("password",""+password);

            //save to database
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
            databaseReference.child(mAuth.getUid()).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Intent intentToMain = new Intent(Register1.this, LandingPage.class);
                    startActivity(intentToMain);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Intent intentToMain = new Intent(Register1.this, LandingPage.class);
                    startActivity(intentToMain);
                    finish();
                }
            });
        }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}