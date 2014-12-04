package com.gamifyGame.android;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Environment;
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
public class AccelTracker extends IntentService implements SensorEventListener {

    float Ax, A2x, A5x, Ay, A2y, A5y, Az, A2z, A5z;
    long timestamp;
    String writeData;
    int linecount;
    FileOutputStream accelData;
    SharedPreferences pref;
    private SensorManager mSensorManager;
    private Sensor mSensor;

    private AndroidApplicationConfiguration config;

    public AccelTracker() {
        super("Tracker");
        linecount = 0;
        writeData = "";
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor , SensorManager.SENSOR_DELAY_NORMAL);
        SystemClock.sleep(32000);
        try {
            if (isExternalStorageWritable()) {
                File f = getAlbumStorageDir("Gamify/accelData");
                String dir = f.getAbsolutePath();
                String newfile = dir + File.pathSeparator + String.valueOf(timestamp) + ".txt";
                accelData = new FileOutputStream(newfile);
                accelData.write(writeData.getBytes());
                sendNotification("WE WROTE OUR DATA");
                accelData.close();
                writeData = "";
                linecount = 0;
            }
            else {
                sendNotification("Storage not available!");
            }
        } catch (Exception e) {
            sendNotification(e.getMessage());
            e.printStackTrace();
        }
        System.exit(0);
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

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            sendNotification("No directory!");
        }
        return file;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float axisX = event.values[0];
        float axisY = event.values[1];
        float axisZ = event.values[2];
        timestamp = System.currentTimeMillis();
        writeData = writeData + String.valueOf(axisX) + ',' + String.valueOf(axisY) + ',' +
                String.valueOf(axisZ) + ',' + String.valueOf(timestamp) + "\n";
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
