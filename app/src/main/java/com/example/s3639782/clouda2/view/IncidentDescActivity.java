package com.example.s3639782.clouda2.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.s3639782.clouda2.R;

public class IncidentDescActivity extends AppCompatActivity {

    private EditText incName, incDesc;
    private Button incReportbtn;
    private String severity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident_desc);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar1);
        incName = (EditText)findViewById(R.id.incName);
        incDesc = (EditText)findViewById(R.id.incDesc);
        incReportbtn = (Button)findViewById(R.id.incReportBtn);

        incReportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(IncidentDescActivity.this, MapsActivity.class );
                i.putExtra("incName", incName.getText().toString());
                i.putExtra("incDesc", incDesc.getText().toString());
                i.putExtra("severity",severity);
                Log.e("incDesc", incDesc.getText().toString());

                startActivity(i);

            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 1;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                if(progresValue<1){
                    Toast.makeText(getApplicationContext(), "Value must be greater than " + progresValue, Toast.LENGTH_SHORT).show();

                }
                else{
                severity = String.valueOf(progresValue);}
                Toast.makeText(getApplicationContext(), "Changing seekbar's progress" + progresValue, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
               // Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //textView.setText("Covered: " + progress + "/" + seekBar.getMax());
                Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
