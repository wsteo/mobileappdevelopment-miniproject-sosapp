package com.example.loginregistersos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    private Button emergency_msg, gps_location, sos_btn, siren_alarm, scammer, first_aid, exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        emergency_msg = findViewById(R.id.btn_emergency);
        gps_location = findViewById(R.id.btn_gps);
        sos_btn = findViewById(R.id.btn_sos);
        siren_alarm = findViewById(R.id.btn_siren);
        scammer = findViewById(R.id.btn_scammer);
        first_aid = findViewById(R.id.btn_firstAid);
        exit = findViewById(R.id.btn_exit);


        emergency_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main2emergency = new Intent(MainMenu.this, Location_Receiver_List.class);
                startActivity(main2emergency);

            }
        });

        gps_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main2gps = new Intent(MainMenu.this, gps.class);
                startActivity(main2gps);

            }
        });


        sos_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main2sos = new Intent(MainMenu.this, gps.class);
                startActivity(main2sos);

            }
        });

        siren_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main2siren = new Intent(MainMenu.this, Alarm_sound.class);
                startActivity(main2siren);

            }
        });

        scammer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main2scam = new Intent(MainMenu.this, Location_Receiver_List.class);
                startActivity(main2scam);

            }
        });

        first_aid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main2first = new Intent(MainMenu.this, EmergencyTutorialVideo.class);
                startActivity(main2first);

            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);

            }
        });







    }
}