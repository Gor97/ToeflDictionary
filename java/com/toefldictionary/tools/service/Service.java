package com.toefldictionary.tools.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;

import com.toefldictionary.R;

/**
 * Created by Gor on 05-Jun-16.
 */
public class Service  extends IntentService {

    public Service() {
        super(Service.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        createSMSReceiveNotification();
    }

    public void createSMSReceiveNotification() {
        Notification notif = new Notification.Builder(this)
                .setContentTitle("Reminder")
                .setContentText("Time to learn new words!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notif.flags |= Notification.FLAG_SHOW_LIGHTS | Notification.FLAG_AUTO_CANCEL;
        notif.ledARGB = Color.GREEN;
        notif.ledOnMS = 1000;
        notif.ledOffMS = 300;
        notif.defaults |= Notification.DEFAULT_SOUND;
        notif.defaults |= Notification.DEFAULT_VIBRATE;
        notificationManager.notify(0, notif);
    }
}
