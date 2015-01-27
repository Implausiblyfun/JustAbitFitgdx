package com.gamifyGame.android;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Entity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Environment;
import android.os.SystemClock;
import android.preference.PreferenceActivity;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;


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

    private String GAMIFY_VERSION;



    public AccelTracker() {
        super("Tracker");
        linecount = 0;
        writeData = "";
    }

    protected void connectTry(String[][] coord, String[] actId){

        for(int i=0; i< coord.length; i++) {
            JSONObject toSend = new JSONObject();
            try {
                String tmpStr = coord[i][0]+","+coord[i][1]+","+coord[i][2]+","+coord[i][3];

                toSend.put("userID", 1234);
                toSend.put("xyz", tmpStr);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            doJSONReq(toSend);
        }
        try {


            JSONObject toSend = new JSONObject();
            toSend.put("userID", 1234);
            toSend.put("activity", actId[0]+","+actId[1]+","+actId[2]);
            doJSONACT(toSend);
        }catch(JSONException e){e.printStackTrace();}
    }

    protected void doJSONReq(JSONObject jsonObject1){
        HttpURLConnection connection = null;
        String output = "";

        //Make web request to fetch new data
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost("http://104.131.171.125:3000/api/storeXYZ");
            request.setHeader("Content-Type", "application/json");

            request.setEntity(new StringEntity(jsonObject1.toString()));

            HttpResponse response = client.execute(request);



        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null){
                connection.disconnect();
            }
        }
    }

    protected void doJSONACT(JSONObject jsonObject1){
        HttpURLConnection connection = null;
        String output = "";

        //Make web request to fetch new data
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost("http://104.131.171.125:3000/api/storeACT");
            request.setHeader("Content-Type", "application/json");

            request.setEntity(new StringEntity(jsonObject1.toString()));

            HttpResponse response = client.execute(request);



        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null){
                connection.disconnect();
            }
        }
    }

    protected  void getBackendResponse(JSONObject jsonObject1){
        HttpURLConnection connection = null;
        int output = -999999;

        //Make web request to fetch new data
        try{
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost("http://104.131.171.125:3000/api/getData");
            request.setHeader("Content-Type", "application/json");

            request.setEntity(new StringEntity(jsonObject1.toString()));

            HttpResponse response = client.execute(request);


            String res = EntityUtils.toString(response.getEntity());

            JSONObject result = new JSONObject(res);


            output = result.getInt("id");

            String strOut = Integer.toString(output);
            sendNotification("x="+String.valueOf(output));



        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(JSONException e){
            e.printStackTrace();
        }
        finally {
            if (connection != null){
                connection.disconnect();
            }
        }



    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GAMIFY_VERSION = intent.getStringExtra("GAMIFY_VERSION");
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor , SensorManager.SENSOR_DELAY_NORMAL);


        JSONObject result = new JSONObject();
        try {
            result.put("userID", 1234);
            result.put("startTime", "1421812247091");
            result.put("endTime", "1421812252091");
            getBackendResponse(result);
        }catch(Exception e){e.printStackTrace();}

        SystemClock.sleep(32000);
        String completeData = writeData.substring(0);
        int activity = Classify(completeData);

        String[] preCoords = writeData.split(System.getProperty("line.separator"));
        String[][] Coords = new String[preCoords.length][4];
        for(int i=0; i < preCoords.length; i++){
            Coords[i] = preCoords[i].split(",");
        }
        String[] actThing = new String [3];
        actThing[0] = Integer.toString(activity);
        actThing[1] = Coords[0][3];
        actThing[2] = GAMIFY_VERSION;
        //connectTry(Coords, actThing);

        JSONObject toSend = new JSONObject();
        try {
            toSend.put("userID", 1234);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getBackendResponse(toSend);

        System.exit(0);
        try {
            if (isExternalStorageWritable()) {
                File f = getAlbumStorageDir("Gamify2/accelData");
                String dir = f.getAbsolutePath();
                String newfile = dir + File.pathSeparator + String.valueOf(timestamp) + ".txt";
                accelData = new FileOutputStream(newfile);
                accelData.write(writeData.getBytes());
                accelData.close();
                f = getAlbumStorageDir("GamifyActivity/accelData");
                dir = f.getAbsolutePath();
                newfile = dir + File.pathSeparator + String.valueOf(timestamp) + ".txt";
                accelData = new FileOutputStream(newfile);
                accelData.write(String.valueOf(activity).getBytes());
                //sendNotification("WE WROTE OUR DATA");
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

    protected int Classify(String completeData){
        float xthreshold = (float)0.0;
        float ythreshold = (float)0.0;
        float zthreshold = (float)0.0;
        int xthresholdTotal = 0;
        int ythresholdTotal = 0;
        int zthresholdTotal = 0;
        int xbypass = 1;
        int ybypass = 1;
        int zbypass = 1;
        int timer = 3000;
        long timestamp = 0;
        int inactiveTime = 0;
        int length;
        String[] coords;
        String line;
        String[] lines = completeData.split(System.getProperty("line.separator"));
        for(int i = 0; i < lines.length; i++) {
            line = lines[i];
            coords = line.split(",");
            if (timestamp != 0){
                timer -= Long.valueOf(coords[3]) - timestamp;
            }
            timestamp = Long.valueOf(coords[3]);
            if (timer < 0){
                inactiveTime -= timer;
                timer = 0;
            }
            xthreshold = thresholdCheck(xthreshold,Float.valueOf(coords[0]));
            if (xthreshold < -4000) {
                xthreshold += 5000;
                xbypass = 1;
            }
            else xbypass = 0;
            xthresholdTotal += xthreshold;
            ythreshold = thresholdCheck(ythreshold,Float.valueOf(coords[1]));
            if (ythreshold < -4000) {
                ythreshold += 5000;
                ybypass = 1;
            }
            else ybypass = 0;
            ythresholdTotal += ythreshold;
            zthreshold = thresholdCheck(zthreshold,Float.valueOf(coords[2]));
            if (zthreshold < -4000) {
                zthreshold += 5000;
                zbypass = 1;
            }
            else zbypass = 0;
            zthresholdTotal += zthreshold;
            if (0 == xbypass || 0 == ybypass || 0 == zbypass){
                timer = 3000;
            }
        }
        float xaverage = xthresholdTotal / lines.length;
        float yaverage = ythresholdTotal / lines.length;
        float zaverage = zthresholdTotal / lines.length;
        return activityAnalysis(xaverage,yaverage,zaverage,inactiveTime);
    }

    protected int activityAnalysis(float x, float y, float z, int t){
        if ((x < 0) && (y < -10) && (z < -5) && t < 5000){
            // "active", or walking
            sendNotification("x="+String.valueOf((int)x)+" y="+String.valueOf((int)y)+" z="+String.valueOf((int)z)+" t="+String.valueOf(t)+" 1");
            return 1;
        }
        sendNotification("x="+String.valueOf((int)x)+" y="+String.valueOf((int)y)+" z="+String.valueOf((int)z)+" t="+String.valueOf(t)+" 0");
        // 0 currently means in-active
        return 0;
    }

    protected float thresholdCheck(float threshold,float curValue){
        float delta = (float)2.0;
        if (Math.abs(curValue - threshold) < delta){
            return curValue;
        }
        else if (threshold > curValue){
            return curValue - 5000;
        }
        return threshold - 5000;
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
            //sendNotification("No directory!");
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
