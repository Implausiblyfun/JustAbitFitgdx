package com.gamifyGame.android;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;

import com.badlogic.gdx.Preferences;

import java.util.Calendar;

/**
 * Created by Stephen on 11/21/2014.
 */
public class AccelAlarm extends WakefulBroadcastReceiver {

    String GAMIFY_VERSION;

    Preferences pref;
    @Override
    public void onReceive(Context context, Intent intent) {
        String version = intent.getStringExtra("VERSION");
        String userID = intent.getStringExtra("userID");
        Intent service = new Intent(context, AccelTracker.class);
        service.putExtra("VERSION", version);
        service.putExtra("userID", userID);
        startWakefulService(context, service);
}

    public void setAlarm(Context context, String version, String userID){
        this.GAMIFY_VERSION = version;

        AlarmManager alrmMngr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AccelAlarm.class);
        intent.putExtra("VERSION", version);
        intent.putExtra("userID", userID);
        //Toast.makeText(context,userID,Toast.LENGTH_SHORT).show();
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        alrmMngr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()-1,
                              1000 * 32, alarmIntent);

    }

    public void setPref(Preferences pref){this.pref = pref;}
    public void setVersion(String version){this.GAMIFY_VERSION = version;}
}
