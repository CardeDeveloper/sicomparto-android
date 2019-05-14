package com.iteso.proyecto_pdm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
    DocumentReference documentReference;

    Map<String, Object> categoriesMap = new HashMap<>();


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_alimento);

        next = findViewById(R.id.activity_solicitud_next);
        categories = findViewById(R.id.activity_solicitud_choose_category);


        carnesRojasBox = (CheckBox)findViewById(R.id.carnes_rojas);
        lacteosBox = (CheckBox)findViewById(R.id.lacteos);

        String path = Constants.REGISTROS + Constants.count;
        documentReference = FirebaseFirestore.getInstance().document(path);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Solicitud_Alimento.this, Activity_Dashboard.class);
                Constants.count ++;
                startActivity(intent);
                finish();
            }
        });

        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Solicitud_Alimento.this, Activity_Categorias.class);
                Constants.SOLICITUD_ALIMENTO = true;
                startActivity(intent);
                //startActivityForResult(intent, Constants.DONACION_ALIMENTO);
                //finish();
            }
        });

    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Constants.SOLICITUD_ALIMENTO){

            SharedPreferences sharedPreferences =
                    getSharedPreferences("com.iteso.proyecto_pdm.CATEGORIAS", MODE_PRIVATE);

            Boolean carnesRojasValue = sharedPreferences.getBoolean(getString(R.string.carnes_rojas), true);
            Boolean lacteosValue = sharedPreferences.getBoolean(getString(R.string.lacteos), true);

            carnesRojasBox.setChecked(carnesRojasValue);
            lacteosBox.setChecked(lacteosValue);

            if(carnesRojasValue)
                categoriesMap.put("carnesRojas", true);
            else
                categoriesMap.put("carnesRojas", false);

            if(lacteosValue)
                categoriesMap.put("lacteos", true);
            else
                categoriesMap.put("lacteos", false);


            SharedPreferences sPreferences = getSharedPreferences(Constants.MAIN_PACKAGE, MODE_PRIVATE);
            String document = sPreferences.getString(Constants.TOKEN_PREFERENCE, null);

            String path = Constants.USUARIOS + document + "/" + Constants.REGISTROS + Constants.count;

            DocumentReference documentReference = FirebaseFirestore.getInstance().document(path);

            documentReference.update(categoriesMap);

        }
    }*/

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences =
                getSharedPreferences("com.iteso.proyecto_pdm.CATEGORIAS", MODE_PRIVATE);

        Boolean carnesRojasValue = sharedPreferences.getBoolean(getString(R.string.carnes_rojas), true);
        Boolean lacteosValue = sharedPreferences.getBoolean(getString(R.string.lacteos), true);

        carnesRojasBox.setChecked(carnesRojasValue);
        lacteosBox.setChecked(lacteosValue);

        if(carnesRojasValue)
            categoriesMap.put("carnesRojas", true);
        else
            categoriesMap.put("carnesRojas", false);

        if(lacteosValue)
            categoriesMap.put("lacteos", true);
        else
            categoriesMap.put("lacteos", false);


        SharedPreferences sPreferences = getSharedPreferences(Constants.MAIN_PACKAGE, MODE_PRIVATE);
        String document = sPreferences.getString(Constants.TOKEN_PREFERENCE, null);

        String path = Constants.USUARIOS + document + "/" + Constants.REGISTROS + Constants.count;

        DocumentReference documentReference = FirebaseFirestore.getInstance().document(path);

        documentReference.update(categoriesMap);

    }
}
