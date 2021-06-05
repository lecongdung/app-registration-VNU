package com.lecongdung.testvnu.utils;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.os.Build;

import com.lecongdung.testvnu.R;

public class OreoAndAboveNotification extends ContextWrapper {
    private static final String ID = "some_id";
    private static final String NAME = "FirebaseApp";

    private NotificationManager notificationManager;

    public OreoAndAboveNotification(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(ID, NAME, NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        getManager().createNotificationChannel(notificationChannel);
    }

    public NotificationManager getManager() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }

    @TargetApi(Build.VERSION_CODES.O)
    public Notification.Builder getONotifications(String title, String body, PendingIntent pIntent, Uri soundUri) {
            return new Notification.Builder(getApplicationContext(), ID)
                    .setContentIntent(pIntent)
                    .setSmallIcon(R.drawable.logo)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setVisibility(Notification.VISIBILITY_PUBLIC)
                    .setSound(soundUri)
                    .setAutoCancel(true);
    }
}
