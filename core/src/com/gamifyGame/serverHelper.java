package com.gamifyGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpParametersUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stephen on 2/3/2015.
 */
public class serverHelper {

    static String status;

    protected static void sendTestConfirm(int confirm){
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("userID", "1234"); //TODO: USERID INPUT
        parameters.put("confirm", String.valueOf(confirm));
        parameters.put("timestamp", String.valueOf(System.currentTimeMillis()));

        Net.HttpRequest httpPost = new Net.HttpRequest(Net.HttpMethods.POST);
        httpPost.setUrl("http://104.131.171.125:3000/api/storeTestConfirm");
        httpPost.setContent(HttpParametersUtils.convertHttpParameters(parameters));

        Gdx.net.sendHttpRequest (httpPost, new Net.HttpResponseListener() {
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                status = httpResponse.getResultAsString();
                //do stuff here based on response
            }

            public void failed(Throwable t) {
                status = "failed";
                //do stuff here based on the failed attempt
            }

            public void cancelled(){

            }
        });
    }
}
