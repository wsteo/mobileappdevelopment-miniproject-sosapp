package com.example.loginregistersos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditEmergencyMessage extends AppCompatActivity {

    private EditText emergencyMessage;
    private Button buttonSaveEditedEmergencyMessage, buttonBackToEmergencyMessageActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_emergency_message);

        emergencyMessage = findViewById(R.id.editText_EmergencyTextMessage);
        buttonSaveEditedEmergencyMessage = findViewById(R.id.button_SaveEditedEmergencyMessage);
        buttonBackToEmergencyMessageActivity = findViewById(R.id.button_Back2EmergencyMessageActivity);

        buttonBackToEmergencyMessageActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2EmergencyMessage = new Intent(EditEmergencyMessage.this,EmergencyMessage.class);
                startActivity(intent2EmergencyMessage);
            }
        });
    }
}