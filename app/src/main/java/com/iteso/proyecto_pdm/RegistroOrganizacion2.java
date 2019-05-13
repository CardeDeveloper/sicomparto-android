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

public class RegistroOrganizacion2 extends AppCompatActivity {


    String initialDocRef = Constants.USUARIOS;
    private DocumentReference docRef;

    Button next;
    EditText nombre, razon, rfc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_organizacion2);

        nombre = findViewById(R.id.activity_registro_organizacion3_nombre);
        razon = findViewById(R.id.activity_registro_organizacion_razon);
        rfc = findViewById(R.id.activity_registro_organizacion_rfc);

        next = findViewById(R.id.activity_registro_organizacion3_next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> org = new HashMap<>();
                final Toast toast;

                //todos los campos llenos
                if(nombre.getText().length() > 0 && razon.getText().length() > 0 && rfc.getText().length() > 0){

                    initialDocRef += nombre.getText().toString();
                    docRef = FirebaseFirestore.getInstance().document(initialDocRef);

                    //llenando firestore
                    org.put(getString(R.string.nombre), nombre.getText().toString());
                    org.put(getString(R.string.razon), razon.getText().toString());
                    org.put(getString(R.string.rfc), rfc.getText().toString());

                    docRef.update(org).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent intent = new Intent(RegistroOrganizacion2.this, RegistroOrganizacion3.class);
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
