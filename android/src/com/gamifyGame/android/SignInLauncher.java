package com.gamifyGame.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.client.android.camera.CameraConfigurationUtils;
import com.google.zxing.integration.android.IntentIntegrator;

/**
 * Created by Folly on 1/26/2015.
 */
public class SignInLauncher extends Activity{

    Context context;
    Bundle thisBundle;

    public void onCreate(Bundle savedStuff) {
        super.onCreate(savedStuff);
        this.thisBundle = savedStuff;
        this.context = getApplicationContext();
    



        setContentView(R.layout.loginscreenres);
        findViewById(R.id.SignIn).setOnClickListener(signIn);


    }

    private final View.OnClickListener signIn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent change = new Intent(context, AndroidLauncher.class);
            if(thisBundle != null){change.putExtras(thisBundle);}
            startActivity(change);
            String tmpMessage = "hiiiii";
            Toast.makeText(getApplicationContext(), tmpMessage, Toast.LENGTH_LONG).show();
        }
    };
}
