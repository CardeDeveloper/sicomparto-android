package com.iteso.proyecto_pdm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;


public class Activity_Solicitud_Alimento extends AppCompatActivity {

    ImageButton next, categories;
    CheckBox carnesRojasBox, lacteosBox;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_alimento);

        next = findViewById(R.id.activity_solicitud_next);
        categories = findViewById(R.id.activity_solicitud_choose_category);

        carnesRojasBox = (CheckBox)findViewById(R.id.carnes_rojas);
        lacteosBox = (CheckBox)findViewById(R.id.lacteos);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Solicitud_Alimento.this, Activity_Solicitud_Horario.class);
                startActivity(intent);
                finish();
            }
        });

        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Solicitud_Alimento.this, Activity_Categorias.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences =
                getSharedPreferences("com.iteso.proyecto_pdm.CATEGORIAS", MODE_PRIVATE);

        Boolean carnesRojasValue = sharedPreferences.getBoolean(getString(R.string.carnes_rojas), true);
        Boolean lacteosValue = sharedPreferences.getBoolean(getString(R.string.lacteos), true);

        carnesRojasBox.setChecked(carnesRojasValue);
        lacteosBox.setChecked(lacteosValue);

    }
}
