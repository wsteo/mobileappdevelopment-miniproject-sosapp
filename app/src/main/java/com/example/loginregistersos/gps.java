package com.example.loginregistersos;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class gps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private LocationManager locationManager;
    private ListHelper myDB;
    private LocationListener locationListener;
    private Button btn_help, btn_receive;
    private Button btn_back;

    public String url,phoneNumber;

    private final long MIN_TIME = 1000;
    private final long MIN_DIST = 5;

    private LatLng latLng;
    public boolean sendNow = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        btn_help = findViewById(R.id.btn_Help);
        btn_receive= findViewById(R.id.btn_receiver);
        btn_back = findViewById(R.id.btn_back);

        myDB = new ListHelper(this);






    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Malaysia and move the camera
        LatLng malaysia = new LatLng(4.1093195, 109.45547499999998);
        mMap.addMarker(new MarkerOptions().position(malaysia).title("Marker in Malaysia"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(malaysia));


        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                try{
                    latLng = new LatLng(location.getLatitude(),location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title("My Position"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.animateCamera( CameraUpdateFactory.zoomTo( 10.0f ) );

                    String url = "https://www.google.com/maps/search/?api=1&query="+ location.getLatitude() +","+location.getLongitude();
                    String myLatidude = String.valueOf(location.getLatitude());
                    String myLongtidue = String.valueOf(location.getLongitude());


                    btn_help.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            int hey = 0;
                            if(hey == 0){
                                String message = "HELP ME!! MY LOCATION: \n"+url;
                                SmsManager smsManager = SmsManager.getDefault();

                                Toast.makeText(getApplicationContext(),"LOCATION SENT TO ALL RECEIVER!", Toast.LENGTH_SHORT).show();

                                Cursor cursor = myDB.viewData();

                                if(cursor.getCount()==0){
                                    Toast.makeText(getApplicationContext(),"You Not Register a Receiver Yet", Toast.LENGTH_SHORT).show();
                                }else {
                                    while(cursor.moveToNext()){
                                        smsManager.sendTextMessage(cursor.getString(1), null, message,null, null);
                                    }
                                }
                            }

                        }

                    });




                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        };





        btn_receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gps2receiver = new Intent(gps.this,Location_Receiver_List.class);
                startActivity(gps2receiver);

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gps2main = new Intent(gps.this,MainMenu.class);
                startActivity(gps2main);

            }
        });






        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        try{

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,MIN_TIME,MIN_DIST, locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_TIME,MIN_DIST, locationListener);
        }catch (SecurityException e){
            e.printStackTrace();
        }




    }
}