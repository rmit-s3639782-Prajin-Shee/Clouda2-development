package com.example.s3639782.clouda2.view;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.s3639782.clouda2.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class AllIncidentsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Firebase mRef;
   /* private ArrayList<Marker> markers = new ArrayList<>();
    private ArrayList<Double> allLats = new ArrayList<>();
    private ArrayList<Double> allLongs = new ArrayList<>();
    private ArrayList<LatLng> Ltlng = new ArrayList<>();*/
    private String title;
    private double lat, lngs;
    private String desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_incidents);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Firebase.setAndroidContext(this);


    Button backBtn = (Button)findViewById(R.id.backMainMenu);
    backBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(AllIncidentsActivity.this, MenuActivity.class);
            startActivity(i);
        }
    });

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

        mRef = new Firebase("https://clouda2-712ea.firebaseio.com/incidents");
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if(dataSnapshot.child("Lat").getValue()!=null){
                lat = Double.valueOf(dataSnapshot.child("Lat").getValue().toString());}
                if(dataSnapshot.child("Long").getValue()!=null){
                lngs =Double.valueOf(dataSnapshot.child("Long").getValue().toString());}
                if(dataSnapshot.child("Name").getValue()!=null){
                title = dataSnapshot.child("Name").getValue().toString();}
                if(dataSnapshot.child("Desc").getValue()!=null){
                desc = dataSnapshot.child("Desc").getValue().toString();}

               /* LatLng point = new LatLng(1,1);
                MarkerOptions marker = new MarkerOptions().position(
                        new LatLng(point.latitude, point.longitude)).title("New Marker");
                */
                //latLongs.add(latLngs);


                //Log.e("LAts", lat);
               // allLats.add(lat);
                //allLongs.add(lngs);
               // Ltlng.add(new LatLng(lat,lngs));

                MarkerOptions marker = new MarkerOptions().position(
                        new LatLng(lat, lngs)).title("New Marker");
                marker.title(title);
                marker.snippet(desc);


                mMap.addMarker(marker);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, lngs)));



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        // Add a marker in Sydney and move the camera

        /*for(int i = 0; i < Ltlng.size(); i++){

            mMap.addMarker(new MarkerOptions().position(Ltlng.get(i)).title("Marker in Sydney"));
            Log.e("Hello", Ltlng.get(i)+"");
        }*/

        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }
}
