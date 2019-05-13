package com.iteso.proyecto_pdm.services;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.e("datos", remoteMessage.getData().toString());
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("notificacion new token", s);
    }
}
