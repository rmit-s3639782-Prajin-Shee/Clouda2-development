package com.example.s3639782.clouda2.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.s3639782.clouda2.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button viewAllInc = (Button)findViewById(R.id.viewAllInc);
        Button viewLiveFeed = (Button)findViewById(R.id.viewLiveFeed);
        Button reportInc = (Button)findViewById(R.id.reportIncident);
        Button dataAnalysisBtn = (Button)findViewById(R.id.dataAnalysisBtn);


        viewAllInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, AllIncidentsActivity.class);
                startActivity(i);
            }
        });

        dataAnalysisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, WebViewActivity.class );
                startActivity(i);
            }
        });

        viewLiveFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, IncidentListActivity.class);
                startActivity(i);
            }
        });

        reportInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, IncidentDescActivity.class);
                startActivity(i);
            }
        });

    }
}
