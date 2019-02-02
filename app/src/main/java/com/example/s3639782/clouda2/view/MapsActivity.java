package com.example.s3639782.clouda2.view;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.example.s3639782.clouda2.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int zoom = 15;
    PlaceAutocompleteFragment placeAutoComplete;
    LatLng latLng;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    private String incName;
    private String incDesc;
    LatLng incPoint;
    private String address;
    private String Lat;
    private String Long;
    Geocoder geocode;
    private String incSeverity, incCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Firebase.setAndroidContext(this);

        placeAutoComplete = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete);
        placeAutoComplete.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Log.d("Maps", "Place selected: " + place.getName());

                latLng = place.getLatLng();
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );


            }

            @Override
            public void onError(Status status) {
                Log.d("Maps", "An error occurred: " + status);
            }
        });

        SupportMapFragment mapFragment2 = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Button pubIncBtn = (Button)findViewById(R.id.pubIncBtn);
        pubIncBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth = FirebaseAuth.getInstance();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("incidents").push();


                SimpleDateFormat s = new SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault());
                String date = s.format(new Date());

                SimpleDateFormat b = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                String time = b.format(new Date());


                incName = getIntent().getExtras().getString("incName");
                incDesc = getIntent().getExtras().getString("incDesc");
                incSeverity = getIntent().getExtras().getString("severity");
                incCategory = getIntent().getExtras().getString("category");



                mDatabase.child("Name").setValue(incName);
                mDatabase.child("Desc").setValue(incDesc);
               // mDatabase.child("LatLng").setValue(incPoint.toString());
                mDatabase.child("Address").setValue(address.toString());
                mDatabase.child("Lat").setValue(incPoint.latitude);
                mDatabase.child("Long").setValue(incPoint.longitude);
                mDatabase.child("Date").setValue(date);
                mDatabase.child("User").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                mDatabase.child("Time").setValue(time);
                mDatabase.child("Severity").setValue(incSeverity);
                mDatabase.child("Category").setValue(incCategory);

                Toast.makeText(getApplicationContext(),incName,Toast.LENGTH_LONG).show();

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url ="https://nodecca2.appspot.com/disaster?title="+incName+"&description="+ incDesc.replace(" ","+")+"&address="+address.replace(" ","+")+"&Lat="+incPoint.latitude+"&Lng="+incPoint.longitude;
                url.replace(",","");
                url.replace(" ", "+");

               //
// Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                               // mTextView.setText("Response is: "+ response.substring(0,500));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mTextView.setText("That didn't work!");
                    }
                });
// Add the request to the RequestQueue.
                queue.add(stringRequest);



                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                getApplicationContext().startActivity(i);
            }
        });

    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        geocode = new Geocoder(getApplicationContext(), Locale.getDefault());

        // Add a marker in Sydney and move the camera
        final LatLng sydney = new LatLng(-34, 151);
        // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney,zoom));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        //mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng point) {

                MarkerOptions marker = new MarkerOptions().position(
                        new LatLng(point.latitude, point.longitude)).title("New Marker");

                try {
                    List<Address> addresses = geocode.getFromLocation(point.latitude,point.longitude,1);
                    address = addresses.get(0).getAddressLine(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                marker.title(incName);
                marker.snippet(incDesc);
                incPoint = new LatLng(point.latitude,point.longitude);
                mMap.addMarker(marker);

               /* firebaseAuth = FirebaseAuth.getInstance();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("incidents").push();



                mDatabase.child("name").setValue(incName);
                mDatabase.child("desc").setValue(incDesc);
                mDatabase.child("LatLng").setValue(point.toString());
                mDatabase.child("User").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());*/



                System.out.println(point.latitude+"---"+ point.longitude);


            }

        });


        Marker melbourne = mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Melbourne")
                .snippet("Population: 4,137,400"));
        melbourne.showInfoWindow();


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                 marker.showInfoWindow();
                return true;
            }
        });


    }

}
