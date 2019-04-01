package com.iteso.proyecto_pdm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class Activity_Categorias extends AppCompatActivity {

    ImageButton next;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        next = findViewById(R.id.activity_solicitud_categorias_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Categorias.this, Activity.class);
                startActivity(intent);
                finish();
            }
        });

    }


}
