package com.example.loginregistersos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class edit_delete_list extends AppCompatActivity {

    private EditText selected;
    private Button save, delete;
    private ListHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete_list);

        save = findViewById(R.id.btn_save);
        delete = findViewById(R.id.btn_del);
        selected = findViewById(R.id.ed_selected);
        myDB = new ListHelper(this);

        selected.setText(getIntent().getStringExtra("inputed"));

        final String inputes = getIntent().getStringExtra("inputed").toString();



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selected.getText().length() !=0){
                    boolean isDeleted = myDB.deleteData((selected.getText().toString()));

                    selected.getText().clear();
                    if(isDeleted){
                        Toast.makeText(getApplicationContext(), "Data is Deleted", Toast.LENGTH_SHORT).show();
                        Intent edit2list = new Intent(edit_delete_list.this, Location_Receiver_List.class);
                        startActivity(edit2list);
                    }

                    else
                        Toast.makeText(getApplicationContext(), "Nothing deleted, somethings goes wrong", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "No university id as reference", Toast.LENGTH_SHORT).show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get Item ID
                Cursor data = myDB.getid(inputes);
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }

                if(selected.getText().length() !=0){

                    boolean isUpdated = myDB.editData(selected.getText().toString(), itemID);
                    // selected.getText().clear();
                    if(isUpdated){
                        Toast.makeText(getApplicationContext(), "Edit is done", Toast.LENGTH_SHORT).show();
                        Intent edit2Main = new Intent(edit_delete_list.this, Location_Receiver_List.class);
                        startActivity(edit2Main);
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Nothing updated, somethings goes wrong", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Nothing  filled to be updated", Toast.LENGTH_SHORT).show();

            }
        });

    }
}