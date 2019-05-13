package com.iteso.proyecto_pdm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences =
                        getSharedPreferences(Constants.MAIN_PACKAGE, MODE_PRIVATE);
                String token = sharedPreferences.getString(Constants.TOKEN_PREFERENCE, null);


                if (token== null){
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);

                }else{
                    Intent intent = new Intent(SplashActivity.this, Activity_Dashboard.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }

            }
        };
        //Log.e("token firebase message",  FirebaseInstanceId.getInstance().getToken());

        Timer timer = new Timer();
        timer.schedule(task, 2000);



    }
}
