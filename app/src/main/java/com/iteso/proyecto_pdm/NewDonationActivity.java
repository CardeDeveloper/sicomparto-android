package com.iteso.proyecto_pdm;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NewDonationActivity extends AppCompatActivity {

    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_donation);
        next = findViewById(R.id.btnNext);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewDonationActivity.this, Activity_Dashboard.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_CANCELED)
            Toast.makeText(this, "Fecha no seleccionada", Toast.LENGTH_LONG).show();
        else {
            if (requestCode == Constants.DONACION_CALENDAR) {

                int day = data.getExtras().getInt("DAY");
                int month = data.getExtras().getInt("MONTH");
                int year = data.getExtras().getInt("YEAR");

                //TODO: save in firebase
            }
            else{
                //TODO: activityTime
            }
        }

    }

    public void onClick(View v){
        Intent intent;

        switch(v.getId()){
            case R.id.activity_donacion_calendar:
                intent = new Intent(this, ActivityCalendar.class);
                startActivityForResult(intent, Constants.DONACION_CALENDAR);
                break;
            case R.id.activity_donacion_time:
                //TODO:ActivityTime
                intent = new Intent(this, ActivityCalendar.class);
                startActivityForResult(intent, Constants.DONACION_TIME);
                break;
        }
    }
}
