package com.example.karthik.notificationlog;

/**
 * Created by karthik on 30/5/18.
 */

public class NotificationDataList {

    private String title,tickerText,packageText,text;

    public NotificationDataList(){}

    public NotificationDataList(String title,String packageText,String tickerText,String text){
        this.text = text;
        this.packageText = packageText;
        this.tickerText = tickerText;
        this.title = title;
    }

    public void setPackageText(String packageText){
            this.packageText = packageText;
    }
    public void setTitleText(String title){
        this.title = title;
    }
    public void setTickerText(String tickerText){
        this.tickerText = tickerText;
    }
    public void setTextText(String text){
        this.text = text;
    }

    public String getTitleText(){return title;}
    public String getTickerText(){return tickerText;}
    public String getPackageText(){return packageText;}
    public String getTextText(){return text;}

}
