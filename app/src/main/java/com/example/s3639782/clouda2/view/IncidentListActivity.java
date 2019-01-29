package com.example.s3639782.clouda2.view;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
    String name;
    String desc;
    String address;
    String user;
    String latLng;
    String date;
    String time;
    String Lat;
    String Long;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident_list);

        Firebase.setAndroidContext(getApplicationContext());
        mRef = new Firebase("https://clouda2-712ea.firebaseio.com/incidents");
        listItems = new ArrayList<>();
        //ListItem listItem = new ListItem("s", "");
       // listItems.add(listItem);

       mRef.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                   name = dataSnapshot.child("Name").getValue().toString();
                   desc = dataSnapshot.child("Desc").getValue().toString();
                   address = dataSnapshot.child("Address").getValue().toString();
                   user = dataSnapshot.child("User").getValue().toString();
//                   latLng = dataSnapshot.child("LatLng").getValue().toString();
                   date = dataSnapshot.child("Date").getValue().toString();
                   time = dataSnapshot.child("Time").getValue().toString();
                   Lat = dataSnapshot.child("Lat").getValue().toString();
                   time = dataSnapshot.child("Long").getValue().toString();


               ListItem listItem = new ListItem(name, desc,address,user,Lat,Long,date, time);

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
