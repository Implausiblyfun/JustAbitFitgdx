package com.gamifyGame.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by Folly on 1/20/2015.
 */
public final class Zxing extends Activity {

    private static final String TAG = Zxing.class.getSimpleName();
    private static final String PACKAGE_NAME = Zxing.class.getPackage().getName();


    Context context;
    Bundle thisBundle;

    public void onCreate(Bundle icicle) {
        this.thisBundle = icicle;
        this.context = context;
        IntentIntegrator integrator;
        integrator = new IntentIntegrator(Zxing.this);
        integrator.initiateScan();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        final CharSequence tmpMessage = result.toString();
        Toast.makeText(getApplicationContext(), tmpMessage, Toast.LENGTH_LONG).show();
        Intent intent2 = new Intent(getApplicationContext(), AndroidLauncher.class);
        intent.putExtra("Message", tmpMessage);
        startActivity(intent);

    }



}
