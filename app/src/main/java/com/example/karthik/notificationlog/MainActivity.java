package com.example.karthik.notificationlog;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {

    NotificationReceiver nReceiver;
    RecyclerView recyclerView;
    DataListAdapter dataListAdapter;
    List<LogObject> dataList;
    Realm realm;
    ///////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();

        dataList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        dataListAdapter = new DataListAdapter(dataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(dataListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        nReceiver = new NotificationReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.karthik.notificationlog.NOTIFICATION_LOG");
        registerReceiver(nReceiver,filter);
        startActivity(new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS));

        updateList();


    }
    /////////////////////////////////////////////////////
    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(nReceiver);
        realm.close();
    }
    ///////////////BROADCAST RECEIVER/////////////////////
    public class NotificationReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent){
            updateList();
        }

    }
    /////////////////////UPDATE LIST///////////////////////
    public void updateList(){

        RealmResults<LogObject> LogResults = realm.where(LogObject.class).findAll();
        realm.beginTransaction();
        dataList.clear();
        for(LogObject logObject: LogResults){
            dataList.add(logObject);
        }
        realm.commitTransaction();

        dataListAdapter.notifyDataSetChanged();
    }
    ///////////////////////////////////////////////////////
}

