package com.example.loginregistersos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_New_Receiver extends AppCompatActivity {

    private EditText phoneNum;
    private Button save;
    private ListHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__new__receiver);
        setTitle("Add Receiver");


        phoneNum = findViewById(R.id.ed_phone);
        save = findViewById(R.id.btn_save);
        myDB = new ListHelper(this);



        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(phoneNum.getText().length() !=0) {

                    boolean isInserted = myDB.insertData(phoneNum.getText().toString());
                    phoneNum.getText().clear();

                    if(isInserted){
                        Toast.makeText(getApplicationContext(), "Data successfully inserted", Toast.LENGTH_SHORT).show();
                        Intent intent2back = new Intent(Add_New_Receiver.this,Location_Receiver_List.class);
                        startActivity(intent2back);
                    }

                    else
                        Toast.makeText(getApplicationContext(), "Nothing save, somethings goes wrong", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Please enter you data", Toast.LENGTH_SHORT).show();


            }

        });



    }
}