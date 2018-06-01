package com.example.karthik.notificationlog;

import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.Toast;

import io.realm.Realm;

/**
 * Created by karthik on 30/5/18.
 */

public class NLService extends NotificationListenerService {

    private String TAG = this.getClass().getSimpleName();

    Realm realm;

    @Override
    public void onCreate(){
        super.onCreate();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        realm.close();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn){

        realm = Realm.getDefaultInstance();

        String packageName = sbn.getPackageName();
        String tickerText = null;
        if(sbn.getNotification().tickerText != null){tickerText = sbn.getNotification().tickerText.toString();}

        Log.i(TAG,"onNotificationPosted");

        Bundle extras = sbn.getNotification().extras;

        String titleText = extras.getString("android.title");
        String textText = null;
        if(extras.getCharSequence("android.text") != null){textText = extras.getCharSequence("android.text").toString();}
        updateDataBase(packageName,tickerText,titleText,textText);

    }
    @Override
    public  void onNotificationRemoved(StatusBarNotification sbn){
        Log.i(TAG,"onNotificationRemoved");
    }

    public void updateDataBase(final String packageName, final String tickerText, final String titleText, final String textText){
        realm.executeTransactionAsync(new Realm.Transaction() {
                                          @Override
                                          public void execute(Realm realm) {
                                              LogObject logObject = realm.createObject(LogObject.class);
                                              logObject.setPackageText(packageName);
                                              logObject.setTextText(textText);
                                              logObject.setTickerText(tickerText);
                                              logObject.setTitleText(titleText);
                                          }
                                      },
                new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        Intent i = new Intent("com.example.karthik.notificationlog.NOTIFICATION_LOG");
                        sendBroadcast(i);
                        Log.i(TAG, "Data Inserted");
                        Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
                    }
                },
                new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        Log.i(TAG, "Data Insertion Failed");
                        Toast.makeText(getApplicationContext(), "Data Insertion Failed", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

}
