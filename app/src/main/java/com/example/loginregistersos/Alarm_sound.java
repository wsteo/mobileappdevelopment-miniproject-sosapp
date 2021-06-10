package com.example.loginregistersos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import static com.example.loginregistersos.R.raw.*;

public class Alarm_sound extends AppCompatActivity {

    private Switch switch_main;
    private Button btn_siren1,  btn_siren2;
    private LinearLayout lay_main;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_sound);
        setTitle("Siren Sound");

        switch_main = findViewById(R.id.sw_main);
        lay_main = findViewById(R.id.lay);
        btn_siren1 = findViewById(R.id.btn_siren1);
        btn_siren2 = findViewById(R.id.btn_siren2);

        MediaPlayer mainSiren = MediaPlayer.create(this, main_sound);

        MediaPlayer siren1 = MediaPlayer.create(this, fast);
        MediaPlayer siren2 = MediaPlayer.create(this, slow_siren);


        switch_main.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (switch_main.isChecked()){
                    mainSiren.start();
                }else {
                    mainSiren.pause();
                    mainSiren.seekTo(0);
                }
            }
        });
/*
        btn_siren1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // start your timer

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // stop your timer.

                }
                return false;
            }
        }); */
/*
        btn_siren1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (switch_main.isChecked()){
                    siren1.start();
                }else {
                    siren1.pause();
                    siren1.seekTo(0);
                }
                return false;
            }
        }); */

        btn_siren1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        siren1.start();
                        break;
                    case MotionEvent.ACTION_UP:
                        siren1.pause();
                        siren1.seekTo(0);
                        break;
                }
                return false;
            }
        });


        btn_siren2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        siren2.start();
                        break;
                    case MotionEvent.ACTION_UP:
                        siren2.pause();
                        siren2.seekTo(0);
                        break;
                }
                return false;
            }
        });

        /*
        switch_main.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                main_sound.start();
            }
        });*/







    }




}