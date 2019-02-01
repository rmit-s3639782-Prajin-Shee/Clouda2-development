package com.example.s3639782.clouda2.view;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.s3639782.clouda2.R;

public class IncidentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident);

        Intent intent = getIntent();
        String heading = intent.getStringExtra("heading");
        String description = intent.getStringExtra("desc");
        String address = intent.getStringExtra("address");
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");
        String severity = intent.getStringExtra("severity");
        Log.e("asd" ,time);


        TextView IncName = (TextView)findViewById(R.id.incidentName);
        TextView IncDate = (TextView)findViewById(R.id.incidentDate);
        TextView IncDesc = (TextView)findViewById(R.id.incidentDesc);
        TextView IncAddress = (TextView)findViewById(R.id.incidentLocation);
        TextView IncTime = (TextView)findViewById(R.id.incidentTime);


        IncName.setText(heading);
        IncDesc.setText(description);
        IncAddress.setText(address);
        IncDate.setText(date);
        IncTime.setText(time);

      /*  if(severity.equals("1")){
            IncName.setTextColor(Color.parseColor("#FFFF00"));
        }
        else if(severity.equals("2")){
            IncName.setTextColor(Color.parseColor("#ffa500"));
        }*/


//        Button button = (Button)findViewById(R.id.testBtn);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), AllIncidentsActivity.class);
//                startActivity(i);
//            }
//        });


    }
}
