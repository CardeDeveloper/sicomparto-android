package com.iteso.proyecto_pdm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class Activity_Dashboard extends AppCompatActivity {
    Button donar, recibir, quienes, salir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__dashboard);
        donar = findViewById(R.id.activity_dashboard_donar);
        recibir = findViewById(R.id.activity_dashboard_recibir);
        quienes = findViewById(R.id.activity_dashboard_aQuienesAyudamos);
        salir = findViewById(R.id.activity_dashboard_salir);

        donar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Dashboard.this, Activity_queSoy.class);
                startActivity(intent);
            }
        });

        recibir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Dashboard.this, Activity_Solicitud_Alimento.class);
                startActivity(intent);
            }
        });

        quienes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Dashboard.this, Activity_Donar.class);
                startActivity(intent);
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                SharedPreferences settings = getSharedPreferences(Constants.MAIN_PACKAGE, MODE_PRIVATE);
                settings.edit().clear().commit();

                Intent intent = new Intent(Activity_Dashboard.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
