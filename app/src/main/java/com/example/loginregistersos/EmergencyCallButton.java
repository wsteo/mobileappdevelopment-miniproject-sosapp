package com.example.loginregistersos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class EmergencyCallButton extends AppCompatActivity {

    ImageView sos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_call_button);

        sos = findViewById(R.id.sos_btn);

        sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "999";
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(phoneIntent);

            }

    });







}
}