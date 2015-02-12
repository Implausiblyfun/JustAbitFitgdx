package com.gamifyGame.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.view.View;
import android.widget.Toast;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.gamifyGame.gamifyGame;

import java.io.File;


public class AndroidLauncher extends AndroidApplication {

    private final String GAMIFY_VERSION = "0.1.0a";
    //Sprivate gamifyGame gameProcess;
    //ActionResolverAndroid actionResolverAndroid;
    private Preferences pref;


	@Override
	protected void onCreate (Bundle savedInstanceState)
    {
        //Toast.makeText(this,"Comps",Toast.LENGTH_SHORT).show();
		super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        ActionResolverAndroid actionResolverAndroid = new ActionResolverAndroid(this);
        gamifyGame gameProcess = gamifyGame.getGamifyGame(actionResolverAndroid);

        Bundle extras = this.getIntent().getExtras();
        try{String userID = (String) extras.get("ID");}
        catch(Exception e){String userID = "4321";}

        // Make a fake ID, Replace when userID is implemented
        pref = this.getPreferences("Bitfitpref");
        double ID = Math.random()*(Math.pow(10d,15d))%Math.pow(10d,15d)+Math.pow(10d,16d);
        String fakeID = pref.getString("userID",String.valueOf(ID));

        // Replace fakeID with userID when userID is implemented
        pref.putString("userID",fakeID);
        pref.flush();

        if (extras == null){
            //Toast.makeText(this, "NULL!!", Toast.LENGTH_SHORT).show();
        }
        if (extras != null && extras.getString("curActivity") != null) {
            //Toast.makeText(this, extras.getString("curActivity"), Toast.LENGTH_SHORT).show();
            pref.putString("curActivity", extras.getString("curActivity"));
            pref.flush();
            //Toast.makeText(this, pref.getString("curActivity"), Toast.LENGTH_LONG).show();
        }
        AccelAlarm alarm = new AccelAlarm();
        alarm.setPref(pref);
        alarm.setVersion(GAMIFY_VERSION);
        alarm.setAlarm(this, GAMIFY_VERSION, fakeID);

        setContentView(R.layout.loginscreenres);

        // Preferences file stores data for later running of app
        gameProcess.setPref(pref);
        initialize(gameProcess, config);

	}

    public Preferences getPref(){
        return pref;
    }
}
