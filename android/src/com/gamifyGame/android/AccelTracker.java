package com.gamifyGame.android;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


/**
 * Created by Stephen on 11/21/2014.
 */
public class AccelTracker extends IntentService {

    float Ax, A2x, A5x, Ay, A2y, A5y, Az, A2z, A5z;
    long timestamp;
    String writeData;
    int linecount;
    FileOutputStream accelData;
    SharedPreferences pref;

    private AndroidApplicationConfiguration config;

    public AccelTracker() {
        super("Tracker");
        linecount = 0;
        writeData = "";
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        for (int i = 0; i < 15; i++) {
            //sendNotification("WE'RE GETTING ACCELEROMETER DATA");
            Ax = Gdx.input.getAccelerometerX();
            Ay = Gdx.input.getAccelerometerY();
            Az = Gdx.input.getAccelerometerZ();
            timestamp = System.currentTimeMillis();
            writeData = writeData + String.valueOf(Ax) + ',' + String.valueOf(Ay) + ',' +
                    String.valueOf(Az) + ',' + timestamp + "\n";
            SystemClock.sleep(2000);
        }
        try {
            accelData = openFileOutput("accelData" + timestamp + ".txt", Context.MODE_PRIVATE);
            accelData.write(writeData.getBytes());
            File dir = getFilesDir();
            sendNotification("WE WROTE OUR DATA" + dir.getAbsolutePath());
            accelData.close();
            writeData = "";
            linecount = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendNotification(String msg) {
        NotificationManager mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, AndroidLauncher.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Hello World")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(msg))
                        .setContentText(msg);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(1, mBuilder.build());
    }
}
