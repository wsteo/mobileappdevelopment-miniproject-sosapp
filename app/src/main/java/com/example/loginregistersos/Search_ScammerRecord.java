package com.example.loginregistersos;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Search_ScammerRecord extends AppCompatActivity {
    private AutoCompleteTextView msearch_record;
    private ImageButton msearchButton;
    private ListView mresult_list;
    DatabaseReference mref;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_scammer);

        msearch_record = (AutoCompleteTextView) findViewById(R.id.search_record);
        msearchButton = (ImageButton)findViewById(R.id.searchButton);
        mresult_list = (ListView) findViewById(R.id.result_list);

        mref = FirebaseDatabase.getInstance().getReference("Scammer");

        ValueEventListener event = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                populateSearch(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mref.addListenerForSingleValueEvent(event);

    }

    private void populateSearch(DataSnapshot snapshot) {
        ArrayList<String> names = new ArrayList<>();
        if (snapshot.exists())
    {
        for(DataSnapshot ds:snapshot.getChildren())
        {
            String phone = ds.child("phone").getValue(String.class);
        names.add(phone);
        }
        ArrayAdapter adapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1, names);
        msearch_record.setAdapter(adapter);
        msearch_record.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String phone= msearch_record.getText().toString();
                searchUser(phone);
            }
        });
    }else
        {
         Log.d("Scammer","No data found");
        }
    }

    private void searchUser(String phone) {
    Query query = mref.orderByChild("phone").equalTo(phone);
    query.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if(snapshot.exists())
            {
                ArrayList<String> listscammers= new ArrayList<>();
              for(DataSnapshot ds:snapshot.getChildren())
              {
                  Scammer scammer = new Scammer(ds.child("phone").getValue(String.class),ds.child("name").getValue(String.class));
                  listscammers.add(scammer.getPhone()+"\n"+scammer.getName());
              }
              ArrayAdapter adapter =new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,listscammers);
              mresult_list.setAdapter(adapter);
            }else
                {
                Log.d("Scammer","No data found");
                }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
    }
        class  Scammer {
            String phone, name;


            public Scammer(String phone, String name) {
                this.phone = phone;
                this.name = name;

            }

            public Scammer() {

            }

            public String getName() {
                return name;
            }

            public String getPhone() {
                return phone;
            }

            public void setName(String reportname) {
                this.name = name;
            }

            public void setPhone(String reportphone) {
                this.phone = phone;
            }
        }

}
