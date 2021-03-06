package com.iteso.proyecto_pdm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Activity_Solicitud_Horario extends AppCompatActivity {

    EditText calendar;
    Button next;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_horario);

        calendar = findViewById(R.id.activity_solicitud_horario_calendar);

        next = findViewById(R.id.activity_solicitud_horario_enviar);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Activity_Solicitud_Horario.this, Activity_Solicitud_Alimento.class);
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

                Map<String, Object> map = new HashMap<>();

                map.put("day", day);
                map.put("month", month);
                map.put("year", year);

                String date = String.format("%02d/%02d/%d", day, month, year);

                calendar.setText(date);

                SharedPreferences sharedPreferences = getSharedPreferences(Constants.MAIN_PACKAGE, MODE_PRIVATE);
                String document = sharedPreferences.getString(Constants.TOKEN_PREFERENCE, null);

                String path = Constants.USUARIOS + document + "/" + Constants.REGISTROS + Constants.count;

                DocumentReference documentReference = FirebaseFirestore.getInstance().document(path);

                documentReference.set(map);
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
            //case R.id.activity_solicitud_horario_enviar:
              //  break;

        }
    }



}
