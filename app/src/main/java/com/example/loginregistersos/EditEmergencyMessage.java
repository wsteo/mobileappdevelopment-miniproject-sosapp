package com.example.loginregistersos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class EditEmergencyMessage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String emergencyMessageStr;
    private EditText emergencyMessage;
    private Button buttonSaveEditedEmergencyMessage, buttonBackToEmergencyMessageActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_emergency_message);

        emergencyMessage = findViewById(R.id.editText_EmergencyTextMessage);
        buttonSaveEditedEmergencyMessage = findViewById(R.id.button_SaveEditedEmergencyMessage);
        buttonBackToEmergencyMessageActivity = findViewById(R.id.button_Back2EmergencyMessageActivity);

        mAuth = FirebaseAuth.getInstance();

        buttonSaveEditedEmergencyMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emergencyMessageStr = emergencyMessage.getText().toString();
                final String timeStamp = "" + System.currentTimeMillis();
                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("MessageID","" + timeStamp);
                hashMap.put("emergencyMessage", "" + emergencyMessageStr);
                hashMap.put("userID", "" + mAuth.getUid());

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                databaseReference.child(mAuth.getUid()).child("EmergencyMessage").child(timeStamp).setValue(hashMap)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            //TODO Add Success Toast for updating the emergency message.
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            //TODO Add Failure Toast for updating the emergency message.
                        }
                    });
            }
        });

        buttonBackToEmergencyMessageActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2EmergencyMessage = new Intent(EditEmergencyMessage.this,EmergencyMessage.class);
                startActivity(intent2EmergencyMessage);
            }
        });
    }
}