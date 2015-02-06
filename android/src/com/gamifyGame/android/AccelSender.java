package com.gamifyGame.android;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

/**
 * Created by Stephen on 2/5/2015.
 */
public class AccelSender {

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
        }catch(JSONException e) {
            e.printStackTrace();
        }
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

    protected void onHandleIntent(Intent intent) {
        String writeData = intent.getStringExtra("writeData");
        String[] preCoords = writeData.split(System.getProperty("line.separator"));
        String[][] Coords = new String[preCoords.length][4];
        for(int i=0; i < preCoords.length; i++){
            Coords[i] = preCoords[i].split(",");
        }
        String[] actThing = intent.getStringArrayExtra("activity");
        connectTry(Coords, actThing);
    }
}
