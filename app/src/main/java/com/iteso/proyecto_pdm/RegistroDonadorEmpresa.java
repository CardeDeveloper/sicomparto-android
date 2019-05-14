package com.iteso.proyecto_pdm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class RegistroDonadorEmpresa extends AppCompatActivity {

    Button next;
    EditText name, rfc, descripcion, kg;
    String initialDocRef = Constants.USUARIOS;
    private DocumentReference docRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_donador_empresa);

        next = findViewById(R.id.activity_regDon_Empresa_arrowNext);
        name = findViewById(R.id.activity_regDon_empresa_nombre);
        rfc = findViewById(R.id.activity_regDon_empresa_rfc);
        descripcion = findViewById(R.id.activity_regDon_empresa_descripcion);
        kg = findViewById(R.id.activity_regDon_empresa_promedioDesperdicios);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> empresa = new HashMap<>();
                final Toast toast;

                if(name.getText().length() > 0 && rfc.getText().length() > 0 && descripcion.getText().length() > 0 && rfc.getText().length() > 0 &&
                        descripcion.getText().length() > 0 && kg.getText().length() > 0){

                    SharedPreferences sharedPreferences = getSharedPreferences(Constants.MAIN_PACKAGE, MODE_PRIVATE);
                    String document = sharedPreferences.getString(Constants.TOKEN_PREFERENCE, null);

                    initialDocRef += document;
                    docRef = FirebaseFirestore.getInstance().document(initialDocRef);

                    //llenando firestore
                    empresa.put("TipoEmpresa", "True");
                    empresa.put("NombreEmpresa", name.getText().toString());
                    empresa.put("RFCEmpresa", rfc.getText().toString());
                    empresa.put("DescripciónEmpresa", descripcion.getText().toString());
                    empresa.put("Desperdicio", kg.getText().toString());

                    docRef.update(empresa).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent intent = new Intent(RegistroDonadorEmpresa.this, RegistroDonador3.class);
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
                else{
                    toast = Toast.makeText(getApplicationContext(), "Por favor llene todos los datos", Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });
    }
}
