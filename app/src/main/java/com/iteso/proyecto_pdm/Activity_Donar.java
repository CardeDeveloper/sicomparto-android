package com.iteso.proyecto_pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.iteso.proyecto_pdm.beans.ItemAsociacion;

import java.util.ArrayList;
import java.util.List;

public class Activity_Donar extends AppCompatActivity {

    private TextView mTextMessage;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dona:
                    Intent intent = new Intent(Activity_Donar.this, Activity_queSoy.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__donar);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Inicializar Animes
        List items = new ArrayList();


        items.add(new ItemAsociacion(R.mipmap.casa1, "Florecitas del Carmen","36172699","Niños desde los 5 años hasta los 16", 1));
        items.add(new ItemAsociacion(R.mipmap.casa2, "Hogares de la Caridad","123312098","Niños desde los 5 años hasta los 16", 2));
        items.add(new ItemAsociacion(R.mipmap.casa1, "Casas las Rosas","12664390","Niños desde los 5 años hasta los 16", 3));

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.activity_donar_recycler_view);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new AsociacionAdapter(items);
        recycler.setAdapter(adapter);
    }

}
