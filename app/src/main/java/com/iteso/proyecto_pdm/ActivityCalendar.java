package com.iteso.proyecto_pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActivityCalendar extends AppCompatActivity {

    int day, localMonth, year;

    CalendarView calendarView;
    Button aceptar, cancelar;

    private void convertDateToInt(String [] date){
        int [] dateInt = new int[3];

        for(int i = 0; i < date.length; i ++)
            dateInt[i] = Integer.parseInt(date[i]);

        day = dateInt[0];
        localMonth = dateInt[1];
        year = dateInt[2];
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendarView);
        aceptar = findViewById(R.id.activity_calendar_aceptar);
        cancelar = findViewById(R.id.activity_calendar_cancelar);

        calendarView.setMinDate(System.currentTimeMillis());

        calendarView.setDate(Calendar.getInstance().getTimeInMillis(), false, true);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //TODO: save the date in a map and store it into firebase

                Intent intent = getIntent();

                intent.putExtra("DAY", dayOfMonth);
                intent.putExtra("MONTH", month + 1);
                intent.putExtra("YEAR", year);

                setResult(RESULT_OK, intent);

                finish();

            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String date = sdf.format(new Date());

                String [] dateFormat = date.split("-");

                convertDateToInt(dateFormat);

                intent.putExtra("DAY", day);
                intent.putExtra("MONTH", localMonth);
                intent.putExtra("YEAR", year);

                setResult(RESULT_OK, intent);
                finish();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }
}