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

public class RegistroDonador3 extends AppCompatActivity {

    Button next;
    EditText nombre, curp, razon, rfc, email;
    String initialDocRef = Constants.USUARIOS;
    private DocumentReference docRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_donador3);

        next = findViewById(R.id.activity_regFac_enviar);
        nombre = findViewById(R.id.activity_regFac_nombre);
        curp = findViewById(R.id.activity_regFac_curp);
        razon = findViewById(R.id.activity_regFac_razon_social);
        rfc = findViewById(R.id.activity_regFac_RFC);
        email = findViewById(R.id.activity_regFac_email);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> factura = new HashMap<>();
                final Toast toast;

                if(nombre.getText().length() > 0 && curp.getText().length() > 0 && razon.getText().length() > 0 && rfc.getText().length() > 0 &&
                        email.getText().length() > 0){

                    SharedPreferences sharedPreferences = getSharedPreferences(Constants.MAIN_PACKAGE, MODE_PRIVATE);
                    String document = sharedPreferences.getString(Constants.TOKEN_PREFERENCE, null);

                    initialDocRef += document;
                    docRef = FirebaseFirestore.getInstance().document(initialDocRef);

                    //llenando firestore
                    factura.put("Factura", "True");
                    factura.put("NombreFactura", nombre.getText().toString());
                    factura.put("RFCFactura", rfc.getText().toString());
                    factura.put("CurpFactura", curp.getText().toString());
                    factura.put("RazonFactura", razon.getText().toString());
                    factura.put("EmailFactura", email.getText().toString());

                    docRef.update(factura).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent intent = new Intent(RegistroDonador3.this, NewDonationActivity.class);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG);
                            toast1.show();
                        }
                    });

                }else{
                    toast = Toast.makeText(getApplicationContext(), "Por favor llene todos los datos", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
}
