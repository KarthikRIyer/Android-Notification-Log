package com.example.karthik.notificationlog;

import io.realm.RealmObject;

/**
 * Created by karthik on 1/6/18.
 */

public class LogObject extends RealmObject {

    private String title,tickerText,packageText,text;

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
