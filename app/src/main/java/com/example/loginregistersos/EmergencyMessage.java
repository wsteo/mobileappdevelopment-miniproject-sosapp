package com.example.loginregistersos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmergencyMessage extends AppCompatActivity {

    private EditText viewOnlyEmergencyMessage;
    private Button buttonEditEmergencyMessage, buttonViewEmergencyContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_message);

        viewOnlyEmergencyMessage = findViewById(R.id.editText_ViewOnlyEmergencyTextMessage);
        buttonEditEmergencyMessage = findViewById(R.id.button_EditEmergencyMessage);
        buttonViewEmergencyContacts = findViewById(R.id.button_ViewEmergencyContact);



        buttonEditEmergencyMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Go the EditEmergencyMessage Activity
                Intent intent2EditEmergencyMessage = new Intent(EmergencyMessage.this,EditEmergencyMessage.class);
                startActivity(intent2EditEmergencyMessage);
            }
        });

        buttonViewEmergencyContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2ViewEmergencyContacts = new Intent(EmergencyMessage.this, ViewEmergencyContact.class);
                startActivity(intent2ViewEmergencyContacts);
            }
        });
    }
}