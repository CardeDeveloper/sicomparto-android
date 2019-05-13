package com.iteso.proyecto_pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistroOrganizacion3 extends AppCompatActivity {


    String initialDocRef = Constants.USUARIOS;
    private DocumentReference docRef;

    Button next;
    EditText causa, personas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_organizacion3);

        causa = findViewById(R.id.activity_registro_organizacion_causa);
        personas = findViewById(R.id.activity_registro_organizacion_personas);

        next = findViewById(R.id.activity_registro_organizacion_next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> org = new HashMap<>();
                final Toast toast;

                //todos los campos llenos
                if(causa.getText().length() > 0 && personas.getText().length() > 0){

                    initialDocRef += causa.getText().toString();
                    docRef = FirebaseFirestore.getInstance().document(initialDocRef);

                    //llenando firestore
                    org.put(getString(R.string.nombre), causa.getText().toString());
                    org.put(getString(R.string.razon_social), personas.getText().toString());

                    docRef.update(org).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent intent = new Intent(RegistroOrganizacion3.this, Activity_Solicitud_Alimento.class);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG);
                            toast1.show();
                        }
                    });

                }
                else {
                    toast = Toast.makeText(getApplicationContext(), "Por favor llene todos los datos", Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });
    }
}
