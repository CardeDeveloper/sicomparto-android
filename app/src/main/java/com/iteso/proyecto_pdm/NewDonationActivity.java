package com.iteso.proyecto_pdm;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NewDonationActivity extends AppCompatActivity {

    Button next;
    EditText calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_donation);
        calendar = findViewById(R.id.activity_donacion_calendar);

        next = findViewById(R.id.btnNext);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewDonationActivity.this, ActivityDonacionAlimento.class);
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

                Map<String, Object> map = new HashMap<>();

                map.put("day", day);
                map.put("month", month);
                map.put("year", year);


                String date = String.format("%02d/%02d/%d", day, month, year);

                calendar.setText(date);

                String path = Constants.REGISTROS + Constants.count;

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
