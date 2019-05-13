package com.iteso.proyecto_pdm.services;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.iteso.proyecto_pdm.Constants;
import com.iteso.proyecto_pdm.DialogActivity;
import com.iteso.proyecto_pdm.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private DocumentReference mDocRef;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.e("datos", remoteMessage.getData().toString());
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("notificacion new token", s);

        /*SharedPreferences sharedPreferences = getSharedPreferences(Constants.MAIN_PACKAGE, MODE_PRIVATE);
        String document = sharedPreferences.getString(Constants.TOKEN_PREFERENCE, null);

        String path = Constants.MAIN_PACKAGE + document;
        mDocRef = FirebaseFirestore.getInstance().document(path);

        //TODO: get the array and update it*/
    }




    protected void createNotification(String title, String content){
        Intent snoozeIntent = new Intent(this, DialogActivity.class);
        //snoozeIntent.setAction(ACTION_SNOOZE);
        //snoozeIntent.putExtra(0, title);
        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(this, 0, snoozeIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.enlatados)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(snoozePendingIntent)
                .addAction(R.drawable.com_facebook_button_like_icon_selected, "aceptar",
                        snoozePendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(0, builder.build());
    }




}
