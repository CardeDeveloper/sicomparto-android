package com.iteso.proyecto_pdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class Activity_queSoy extends AppCompatActivity {

    Button comenzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que_soy);

        comenzar = findViewById(R.id.activity_queSoy_comenzar);

    }

    public void onRadioButtonClicked(View v) {
        //require to import the RadioButton class
        RadioButton rb1 = (RadioButton) findViewById(R.id.radio_soyEmpresa);
        RadioButton rb2 = (RadioButton) findViewById(R.id.radio_soyPersona);

        //is the current radio button now checked?
        boolean  checked = ((RadioButton) v).isChecked();

        //now check which radio button is selected
        //android switch statement
        switch(v.getId()) {

            case R.id.radio_soyEmpresa:
                if (checked)
                    comenzar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Activity_queSoy.this, RegistroDonadorEmpresa.class);
                            startActivity(intent);
                        }
                    });
                break;

            case R.id.radio_soyPersona:
                if (checked)
                    comenzar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Activity_queSoy.this, RegistroDonador.class);
                            startActivity(intent);
                        }
                    });
                break;
        }


    }
}
