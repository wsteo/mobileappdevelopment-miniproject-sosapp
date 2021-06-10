package com.example.loginregistersos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmergencyTutorialVideo extends AppCompatActivity {

    private ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_tutorial_video);
    listView = findViewById(R.id.lv);
        ArrayList<String> awareness = new ArrayList<>();
        ArrayList<String> link = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("awareness")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                awareness.add(document.getString("name"));
                                link.add(document.getString("link"));
                            }
                            ArrayAdapter adapter = new ArrayAdapter(EmergencyTutorialVideo.this, android.R.layout.simple_list_item_1, awareness);
                            listView.setAdapter(adapter);
                        } else {
                            Toast.makeText(EmergencyTutorialVideo.this, "fail fetch", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentovideo = new Intent(Intent.ACTION_VIEW, Uri.parse(link.get(position)));
                startActivity(intentovideo);
            }
        });
    }
}