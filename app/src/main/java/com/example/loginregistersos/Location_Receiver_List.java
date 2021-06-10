package com.example.loginregistersos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Location_Receiver_List extends AppCompatActivity {

    private Button btn_add,btn_bck;
    ListView listView;
    ListHelper myDB;
    ArrayList arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location__receiver__list);

        btn_add = findViewById(R.id.btn_add);
        btn_bck = findViewById(R.id.btn_bck);

        listView = findViewById(R.id.lv_viewReceiver);
        myDB = new ListHelper(this);
        arrayList = new ArrayList();


        Cursor myData = myDB.viewData();

        if (myData.getCount() == 0 ){
            Toast.makeText(getApplicationContext(), "You Have No List", Toast.LENGTH_SHORT).show();
        }else{
            while (myData.moveToNext()){
                arrayList.add(myData.getString(1));
                ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, arrayList);
                listView.setAdapter(arrayAdapter);
            }

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String input_list = (String) listView.getItemAtPosition(position);

                    // Toast.makeText(getApplicationContext(),"The selected input is "+input_list,Toast.LENGTH_SHORT).show();

                    Intent viewData2Edit = new Intent(Location_Receiver_List.this, edit_delete_list.class);
                    viewData2Edit.putExtra("inputed", input_list);
                    startActivity(viewData2Edit);

                }
            });

        }

        btn_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent2add_new = new Intent(Location_Receiver_List.this,Add_New_Receiver.class);
                startActivity(intent2add_new);


            }

        });

        btn_bck.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent2GPS = new Intent(Location_Receiver_List.this,gps.class);
                startActivity(intent2GPS);


            }

        });




    }
}