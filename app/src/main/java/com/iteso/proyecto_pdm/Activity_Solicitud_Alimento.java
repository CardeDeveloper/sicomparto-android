package com.iteso.proyecto_pdm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Activity_Solicitud_Alimento extends AppCompatActivity {

    ImageButton categories;
    Button next;
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
                Intent intent = new Intent(Activity_Solicitud_Alimento.this, Activity_Dashboard.class);
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

        Map<String, Object> categories = new HashMap<>();

        if(carnesRojasValue)
            categories.put("carnesRojas", true);
        else
            categories.put("carnesRojas", false);
        if(lacteosValue)
            categories.put("lacteos", true);
        else
            categories.put("lacteos", false);


        SharedPreferences sPreferences = getSharedPreferences(Constants.MAIN_PACKAGE, MODE_PRIVATE);
        String document = sPreferences.getString(Constants.TOKEN_PREFERENCE, null);

        String path = Constants.USUARIOS + document + "/" + Constants.REGISTROS + Constants.count;

        DocumentReference documentReference = FirebaseFirestore.getInstance().document(path);

        documentReference.update(categories).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Solicitud Exitosa", Toast.LENGTH_LONG).show();
            }
        });

    }
}
