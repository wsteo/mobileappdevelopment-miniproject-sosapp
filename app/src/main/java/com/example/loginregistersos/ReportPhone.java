package com.example.loginregistersos;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;

public class ReportPhone extends AppCompatActivity {
        EditText reportname, reportphone;
        Button submmitreport;

        FirebaseDatabase database;
        DatabaseReference ref;
        Scammer scammer;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.submit_scammer_phone);

            reportphone = findViewById(R.id.ed_report_phone);
            reportname = findViewById(R.id.ed_report_name);
            submmitreport = findViewById(R.id.submit_report_btn);


        submmitreport.setOnClickListener(new View.OnClickListener (){
        @Override
        public void onClick(View view){
            database = FirebaseDatabase.getInstance();
            ref = database.getReference("Scammer");

            String phone= reportphone.getText().toString();
            String name= reportname.getText().toString();

            if (phone.equals("") && !name.equals("") ){
                Toast.makeText(ReportPhone.this,"Please enter a phone number",Toast.LENGTH_LONG).show();
                return;
            }

            if (name.equals("") && !phone.equals("")){
                Toast.makeText(ReportPhone.this,"Please enter a name",Toast.LENGTH_LONG).show();
                return;
            }

            if (phone.equals("") || name.equals("")){
                Toast.makeText(ReportPhone.this,"Please enter a name and phone number",Toast.LENGTH_LONG).show();
                return;
            }

            Scammer scammer = new Scammer(phone,name);
            ref.child(phone).setValue(scammer);
            Toast.makeText(ReportPhone.this,"Data inserted",Toast.LENGTH_LONG).show();
        }
        } );
        }
    }

