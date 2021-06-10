package com.example.loginregistersos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class Application extends AppCompatActivity {

    GridView gridView;
    String[] names = {"GPS Tracker", "Phone", "Play", "SMS", "Siren"};
    int[] images = {R.drawable.gps, R.drawable.ic_phone, R.drawable.ic_play, R.drawable.ic_sms, R.drawable.ic_siren};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        gridView = findViewById(R.id.grid);

        CustomAdapter customAdapter = new CustomAdapter(names, images, this);

        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                String selectedNames = names[i];
                int selectedImages = images[i];

                startActivity(new Intent(Application.this, ClickedItem.class).putExtra("name", selectedNames).putExtra("image", selectedImages));
            }
        });
    }

    public class CustomAdapter extends BaseAdapter {

        private String[] appName;
        private int[] appIcon;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(String[] appName, int[] appIcon, Context context) {
            this.appName = appName;
            this.appIcon = appIcon;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return appIcon.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if (view == null){
                view = layoutInflater.inflate(R.layout.row_items, viewGroup, false);

            }
            TextView tv_name = view.findViewById(R.id.tv_name);
            ImageView imageView = view.findViewById(R.id.image_view);

            tv_name.setText(appName[i]);
            imageView.setImageResource(appIcon[i]);

            return view;
        }
    }
}