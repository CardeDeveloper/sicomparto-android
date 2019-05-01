package com.iteso.proyecto_pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Activity_Solicitud_Horario extends AppCompatActivity {
    Button next;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_horario);
        next = findViewById(R.id.activity_solicitud_horario_enviar);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Solicitud_Horario.this, Activity_Dashboard.class);
                startActivity(intent);
            }
        });
    }
}
