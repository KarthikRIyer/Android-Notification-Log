package com.example.karthik.notificationlog;

import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

/**
 * Created by karthik on 30/5/18.
 */

public class NLService extends NotificationListenerService {

    private String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn){
        Log.i(TAG,"onNotificationPosted");
        Intent i = new Intent("com.example.karthik.notificationlog.NOTIFICATION_LOG");
        i.putExtra("packageText",sbn.getPackageName());
        i.putExtra("ticker",sbn.getNotification().tickerText);
        Bundle extras = sbn.getNotification().extras;
        i.putExtra("title",extras.getString("android.title"));
        i.putExtra("text",extras.getCharSequence("android.text").toString());
        sendBroadcast(i);
    }
    @Override
    public  void onNotificationRemoved(StatusBarNotification sbn){
        Log.i(TAG,"onNotificationRemoved");
    }

}
