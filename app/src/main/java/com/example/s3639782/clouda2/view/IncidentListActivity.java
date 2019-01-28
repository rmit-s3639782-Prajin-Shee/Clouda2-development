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

import java.util.ArrayList;
import java.util.List;


public class IncidentListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    private Firebase mRef;
    DatabaseReference databaseReference;
    String a;
    String b;

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


              // for(DataSnapshot dataSnapshot2: dataSnapshot.getChildren()){
                  // String a = dataSnapshot2.child("name").getValue().toString();


                   a = dataSnapshot.getKey();
                   b = dataSnapshot.child("Address").getValue().toString();

                  // String c = dataSnapshot2.child("name")
                  Log.e("key", a);
                  Log.e("Value", b);
                  Log.e("Value2", dataSnapshot.getKey());




            //   }


               //String incKey = mRef.child("incidents").getParent().toString();
              // String incVal = mRef.child("incidents").toString();
               ListItem listItem = new ListItem(a, b);
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        adapter = new MyAdapter(listItems, this);
        recyclerView.setAdapter(adapter);
    }
}
