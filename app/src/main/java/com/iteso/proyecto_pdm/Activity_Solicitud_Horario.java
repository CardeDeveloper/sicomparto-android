package com.iteso.proyecto_pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Solicitud_Horario extends AppCompatActivity {

    EditText calendar, time;
    Button next;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_horario);

        calendar = findViewById(R.id.activity_solicitud_horario_calendar);
        time = findViewById(R.id.activity_solicitud_horario_time);
        next = findViewById(R.id.activity_solicitud_horario_enviar);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Activity_Solicitud_Horario.this, Activity_Dashboard.class);
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
            if (requestCode == Constants.SOLICITUD_CALENDAR) {

                int day = data.getExtras().getInt("DAY");
                int month = data.getExtras().getInt("MONTH");
                int year = data.getExtras().getInt("YEAR");

                String date = String.format("%02d/%02d/%d", day, month, year);

                calendar.setText(date);

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
            case R.id.activity_solicitud_horario_calendar:
                intent = new Intent(this, ActivityCalendar.class);
                startActivityForResult(intent, Constants.SOLICITUD_CALENDAR);
                break;
            case R.id.activity_solicitud_horario_time:
                //TODO: change activityClendar for Activity time
                intent = new Intent(this, ActivityCalendar.class);
                startActivityForResult(intent, Constants.SOLICITUD_TIME);
                break;
            //case R.id.activity_solicitud_horario_enviar:
              //  break;

        }
    }



}
