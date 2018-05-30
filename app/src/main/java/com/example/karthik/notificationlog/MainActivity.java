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



public class MainActivity extends AppCompatActivity {

    NotificationReceiver nReceiver;
    RecyclerView recyclerView;
    DataListAdapter dataListAdapter;
    List<NotificationDataList> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(nReceiver);
    }

    public class NotificationReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent){
            String packageText = intent.getStringExtra("packageText");
            String titleText = intent.getStringExtra("title");
            String tickerText = intent.getStringExtra("ticker");
            String textText = intent.getStringExtra("text");
            NotificationDataList notificationDataList = new NotificationDataList(titleText,packageText,tickerText,textText);
            dataList.add(notificationDataList);
            dataListAdapter.notifyDataSetChanged();
        }

    }
}

