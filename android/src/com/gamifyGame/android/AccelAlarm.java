package com.gamifyGame.android;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

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
        Intent service = new Intent(context, AccelTracker.class);
        service.putExtra("VERSION", GAMIFY_VERSION);
        startWakefulService(context, service);
}

    public void setAlarm(Context context){
        AlarmManager alrmMngr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        // Bundle into this intent linecount for file writing
        Intent intent = new Intent(context, AccelAlarm.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        alrmMngr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()-1,
                              1000 * 32, alarmIntent);

    }

    public void setPref(Preferences pref){this.pref = pref;}
    public void setVersion(String version){this.GAMIFY_VERSION = version;}
}
