package com.iteso.proyecto_pdm;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class RegistroOrganizacion extends AppCompatActivity {


    String initialDocRef = Constants.USUARIOS;
    private DocumentReference docRef;

    Button next;
    EditText nombre, email, telefono, direccion, codigoPostal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_orgnizacion);

        nombre = findViewById(R.id.activity_registro_organizacion_nombre);
        email = findViewById(R.id.activity_registro_organizacion_email);
        telefono = findViewById(R.id.activity_registro_organizacion_telefono);
        direccion = findViewById(R.id.activity_registro_organizacion_direccion);
        codigoPostal = findViewById(R.id.activity_registro_organizacion_codigoPostal);

        next = findViewById(R.id.activity_registro_organizacion2_next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> org = new HashMap<>();
                final Toast toast;

                //todos los campos llenos
                if(nombre.getText().length() > 0 && email.getText().length() > 0 && telefono.getText().length() > 0
                        && direccion.getText().length() > 0 && codigoPostal.getText().length() > 0){

                    SharedPreferences sharedPreferences = getSharedPreferences(Constants.MAIN_PACKAGE, MODE_PRIVATE);
                    String document = sharedPreferences.getString(Constants.TOKEN_PREFERENCE, null);

                    initialDocRef += document;
                    docRef = FirebaseFirestore.getInstance().document(initialDocRef);

                    //llenando firestore
                    org.put("TipoOrganizacion", "True");
                    org.put("NombreUs", nombre.getText().toString());
                    org.put("EmailOrg", email.getText().toString());
                    org.put("TelefonoOrg", telefono.getText().toString());
                    org.put("DireccionOrg", direccion.getText().toString());
                    org.put("CodigoPostalOrg", codigoPostal.getText().toString());

                    docRef.update(org).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent intent = new Intent(RegistroOrganizacion.this, RegistroOrganizacion2.class);
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
