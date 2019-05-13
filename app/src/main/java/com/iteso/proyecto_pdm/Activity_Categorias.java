package com.iteso.proyecto_pdm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;

public class Activity_Categorias extends AppCompatActivity {


    ImageButton next;
    CheckBox verdura, lacteos, cereales, carnesRojas, carnesBlancas, fastFood, cremas, condimentos,
            cafe, pasteles, enlatados, agua, dulces, jugos, leguminosas, procesados;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        verdura = (CheckBox)findViewById(R.id.verdura);
        lacteos = (CheckBox) findViewById(R.id.lacteos);
        cereales = (CheckBox) findViewById(R.id.cereales);
        carnesRojas = (CheckBox) findViewById(R.id.carnes_rojas);
        carnesBlancas = (CheckBox) findViewById(R.id.carnes_blancas);
        fastFood = (CheckBox) findViewById(R.id.comida_rapida);
        cremas = (CheckBox) findViewById(R.id.mermelada_crema);
        condimentos = (CheckBox) findViewById(R.id.condimentos);
        cafe = (CheckBox) findViewById(R.id.cafe);
        pasteles = (CheckBox) findViewById(R.id.pasteles);
        enlatados = (CheckBox) findViewById(R.id.enlatados);
        agua = (CheckBox) findViewById(R.id.agua);
        dulces = (CheckBox) findViewById(R.id.dulces);
        jugos = (CheckBox) findViewById(R.id.jugos);
        leguminosas = (CheckBox) findViewById(R.id.leguminosas);
        procesados = (CheckBox) findViewById(R.id.alimentos_procesados);


        next = findViewById(R.id.activity_solicitud_categorias_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //save checkboxes state into shared preferences
                SharedPreferences sharedPreferences =
                        getSharedPreferences(Constants.SP_CATEGORIAS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putBoolean(getString(R.string.verdura), verdura.isChecked());
                editor.putBoolean(getString(R.string.lacteos), lacteos.isChecked());
                editor.putBoolean(getString(R.string.cereales), cereales.isChecked());
                editor.putBoolean(getString(R.string.carnes_rojas), carnesRojas.isChecked());
                editor.putBoolean(getString(R.string.carnes_blancas), carnesBlancas.isChecked());
                editor.putBoolean(getString(R.string.comida_rapida), fastFood.isChecked());
                editor.putBoolean(getString(R.string.mermelada_cremas), cremas.isChecked());
                editor.putBoolean(getString(R.string.condimentos), condimentos.isChecked());
                editor.putBoolean(getString(R.string.cafe), cafe.isChecked());
                editor.putBoolean(getString(R.string.pasteles), pasteles.isChecked());
                editor.putBoolean(getString(R.string.enlatados), enlatados.isChecked());
                editor.putBoolean(getString(R.string.agua), agua.isChecked());
                editor.putBoolean(getString(R.string.dulces), dulces.isChecked());
                editor.putBoolean(getString(R.string.jugos), jugos.isChecked());
                editor.putBoolean(getString(R.string.leguminosas), leguminosas.isChecked());
                editor.putBoolean(getString(R.string.procesados), procesados.isChecked());

                editor.apply();

                Intent intent = new Intent(Activity_Categorias.this, Activity_Solicitud_Alimento.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void onCheckBoxClicked(View v){
        boolean checked = ((CheckBox) v).isChecked();

        //TODO: save in firebase
        switch (v.getId()){
            case R.id.verdura:
                if(checked){

                }
                else{

                }
                break;
            case R.id.lacteos:
                if(checked){

                }
                else{

                }
                break;
            case R.id.cereales:
                if(checked){

                }
                else{

                }
                break;
            case R.id.carnes_rojas:
                if(checked){

                }
                else{

                }
                break;
            case R.id.carnes_blancas:
                if(checked){

                }
                else{

                }
                break;
            case R.id.comida_rapida:
                if(checked){

                }
                else{

                }
                break;
            case R.id.mermelada_crema:
                if(checked){

                }
                else{

                }
                break;
            case R.id.condimentos:
                if(checked){

                }
                else{

                }
                break;
            case R.id.cafe:
                if(checked){

                }
                else{

                }
                break;
            case R.id.pasteles:
                if(checked){

                }
                else{

                }
                break;
            case R.id.enlatados:
                if(checked){

                }
                else{

                }
                break;
            case R.id.agua:
                if(checked){

                }
                else{

                }
                break;
            case R.id.dulces:
                if(checked){

                }
                else{

                }
                break;
            case R.id.jugos:
                if(checked){

                }
                else{

                }
                break;
            case R.id.leguminosas:
                if(checked){

                }
                else{

                }
                break;
            case R.id.alimentos_procesados:
                if(checked){

                }
                else{

                }
                break;

        }
    }


}
