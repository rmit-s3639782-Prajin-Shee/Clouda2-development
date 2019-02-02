package com.example.s3639782.clouda2.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.s3639782.clouda2.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class IncidentListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    private Firebase mRef;
    DatabaseReference databaseReference;
    private String name;
    private String desc;
    private String address;
    private String user;
    private String latLng;
    private String date;
    private String time;
    private String Lat;
    private String Long;
    private String severity;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident_list);

        Firebase.setAndroidContext(getApplicationContext());
        mRef = new Firebase("https://clouda2-712ea.firebaseio.com/incidents");
        listItems = new ArrayList<>();
        //ListItem listItem = new ListItem("s", "");
       // listItems.add(listItem);
        Firebase.setAndroidContext(this);

        Button mainMenuBtn = (Button)findViewById(R.id.backMainMenu2);
        mainMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(IncidentListActivity.this, MenuActivity.class);
                startActivity(i);
            }
        });


        mRef.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                   name = dataSnapshot.child("Name").getValue(String.class);
                   desc = dataSnapshot.child("Desc").getValue(String.class);
                   address = dataSnapshot.child("Address").getValue(String.class);
                   user = dataSnapshot.child("User").getValue(String.class);
//                   latLng = dataSnapshot.child("LatLng").getValue().toString();
                   date = dataSnapshot.child("Date").getValue(String.class);
                   time = dataSnapshot.child("Time").getValue(String.class);
                   Lat = dataSnapshot.child("Lat").getValue(String.class);
                   Long = dataSnapshot.child("Long").getValue(String.class);
                   severity = dataSnapshot.child("Severity").getValue(String.class);
                   category = dataSnapshot.child("Category").getValue(String.class);


               ListItem listItem = new ListItem(name, desc,address,user,Lat,Long,date,time,severity, category);

               listItems.add(listItem);
               adapter.notifyDataSetChanged();
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

        recyclerView = (RecyclerView)findViewById(R.id.incListRecycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);



        adapter = new MyAdapter(listItems, this);
        recyclerView.setAdapter(adapter);
    }
}
