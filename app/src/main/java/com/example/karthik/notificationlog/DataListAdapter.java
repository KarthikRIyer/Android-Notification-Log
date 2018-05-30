package com.example.karthik.notificationlog;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by karthik on 30/5/18.
 */

public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.MyViewHolder>{

    private List<NotificationDataList> dataList;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView packText,titleText,tickText,textText;

        public MyViewHolder(View v){
            super(v);
            packText = (TextView)v.findViewById(R.id.packageText);
            tickText = (TextView)v.findViewById(R.id.tickerText);
            titleText = (TextView)v.findViewById(R.id.title);
            textText = (TextView)v.findViewById(R.id.text);
        }

    }

    public DataListAdapter(List<NotificationDataList> dataList){
        this.dataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_list_layout,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,int position){
        NotificationDataList notifiationList = dataList.get(position);
        holder.textText.setText(notifiationList.getTextText());
        holder.titleText.setText(notifiationList.getTitleText());
        holder.tickText.setText(notifiationList.getTickerText());
        holder.packText.setText(notifiationList.getPackageText());
    }

    @Override
    public int getItemCount(){
        return dataList.size();
    }

}
