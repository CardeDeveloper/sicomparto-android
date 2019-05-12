package com.iteso.proyecto_pdm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
/*import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;*/
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistroDonador extends AppCompatActivity {

    //Firestore db;

    String initialDocRef = "donors/";
    private DocumentReference docRef;

    Button next;
    EditText nombre, email, telefono, direccion, codigoPostal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_donador);

        nombre = findViewById(R.id.activity_regDon_nombre);
        email = findViewById(R.id.activity_regDon_email);
        telefono = findViewById(R.id.activity_regDon_telefono);
        direccion = findViewById(R.id.activity_regDon_direccion);
        codigoPostal = findViewById(R.id.activity_regDon_codigoPostal);

        next = findViewById(R.id.activity_regDon_arrowNext);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> donor = new HashMap<>();
                final Toast toast;

                //todos los campos llenos
                if(nombre.getText().length() > 0 && email.getText().length() > 0 && telefono.getText().length() > 0
                        && direccion.getText().length() > 0 && codigoPostal.getText().length() > 0){

                    initialDocRef += nombre.getText().toString();
                    docRef = FirebaseFirestore.getInstance().document(initialDocRef);

                    //llenando firestore
                    donor.put(getString(R.string.nombre), nombre.getText().toString());
                    donor.put(getString(R.string.email), email.getText().toString());
                    donor.put(getString(R.string.telefono), telefono.getText().toString());
                    donor.put(getString(R.string.direccion), direccion.getText().toString());
                    donor.put(getString(R.string.codigo_postal), codigoPostal.getText().toString());

                    docRef.set(donor).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast toast2 = Toast.makeText(getApplicationContext(), "Info stored on Firebase!", Toast.LENGTH_LONG);
                            toast2.show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG);
                            toast1.show();
                        }
                    });


                    //ApiFuture<String> result = docRef.set(donor);


                    //Intent intent = new Intent(RegistroDonador.this, RegistroDonador2.class);
                    //startActivity(intent);

                }
                else {
                    toast = Toast.makeText(getApplicationContext(), "Por favor llene todos los datos", Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });
    }
}
