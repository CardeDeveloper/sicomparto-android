package com.iteso.proyecto_pdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_queSoy extends AppCompatActivity {

    Button comenzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que_soy);

        comenzar = findViewById(R.id.activity_queSoy_comenzar);

        comenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_queSoy.this, RegistroDonador.class);
                startActivity(intent);
            }
        });

    }
}
