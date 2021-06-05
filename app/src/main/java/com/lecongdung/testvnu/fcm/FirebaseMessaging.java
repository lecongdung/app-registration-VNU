package com.lecongdung.testvnu.fcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.view.HomeActivity;

import static com.google.firebase.messaging.RemoteMessage.PRIORITY_HIGH;

public class FirebaseMessaging extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sendOAndAboveNotification(remoteMessage);
        } else {
            sendNormalNotification(remoteMessage);
        }
    }

    private void sendNormalNotification(RemoteMessage remoteMessage) {
        String title = remoteMessage.getNotification().getTitle();
        String body = remoteMessage.getNotification().getBody();

        RemoteMessage.Notification notification = remoteMessage.getNotification();

        int i = 10;
        Intent intent = new Intent(this, HomeActivity.class);

//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(this, i, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = null;

            builder = new NotificationCompat.Builder(this)
                    .setContentText(body)
                    .setContentTitle(title)
                    .setAutoCancel(true)
                    .setPriority(PRIORITY_HIGH)
                    .setSound(soundUri)
                    .setContentIntent(pIntent)
                    .setSmallIcon(R.drawable.logo)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(i, builder.build());
    }

    private void sendOAndAboveNotification(RemoteMessage remoteMessage) {
        String title = remoteMessage.getNotification().getTitle();
        String body = remoteMessage.getNotification().getBody();

        RemoteMessage.Notification notification = remoteMessage.getNotification();
        int i = 10;
        Intent intent = new Intent(this, HomeActivity.class);

//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(this, i, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        OreoAndAboveNotification notification1 = new OreoAndAboveNotification(this);
        Notification.Builder builder = notification1.getONotifications(title, body, pIntent, soundUri);

        notification1.getManager().notify(i, builder.build());
    }
}
