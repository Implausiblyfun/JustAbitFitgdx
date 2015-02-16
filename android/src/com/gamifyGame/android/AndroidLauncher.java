package com.gamifyGame.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.support.annotation.IntegerRes;
import android.view.View;
import android.widget.Toast;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.gamifyGame.gamifyGame;

import java.io.File;


public class AndroidLauncher extends AndroidApplication {

    private final String GAMIFY_VERSION = "0.0.02a";
    //Sprivate gamifyGame gameProcess;
    //ActionResolverAndroid actionResolverAndroid;
    private Preferences pref;


	@Override
	protected void onCreate (Bundle savedInstanceState)
    {
        Toast.makeText(this,"Comps",Toast.LENGTH_SHORT).show();
		super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        ActionResolverAndroid actionResolverAndroid = new ActionResolverAndroid(this);
        gamifyGame gameProcess = gamifyGame.getGamifyGame(actionResolverAndroid);

        Bundle extras = this.getIntent().getExtras();
        try{String userID = (String) extras.get("ID");}
        catch(Exception e){String userID = "4321";}

        File directory = getFilesDir();
        // Start Accel tracking in background
        pref = this.getPreferences("Bitfitpref");
        prefHelper prefH = new prefHelper();
        prefH.setPref(pref);
        if (extras == null){
            Toast.makeText(this, "NULL!!", Toast.LENGTH_SHORT).show();
        }
        if (extras != null && extras.getString("curActivity") != null) {
            Toast.makeText(this, extras.getString("curActivity"), Toast.LENGTH_SHORT).show();
            pref.putString("curActivity", extras.getString("curActivity"));
            pref.flush();
            Toast.makeText(this, pref.getString("curActivity"), Toast.LENGTH_LONG).show();
        }

        AccelAlarm alarm = new AccelAlarm();
        alarm.setPref(prefH);
        alarm.setVersion(GAMIFY_VERSION);
        alarm.setAlarm(this, GAMIFY_VERSION);

        setContentView(R.layout.loginscreenres);

        // Preferences file stores data for later running of app
        gameProcess.setPref(pref);
        initialize(gameProcess, config);

	}

    protected void onResume(){
        Bundle extras = this.getIntent().getExtras();

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("bitPref", 0);

        if(sharedPref.getString("currentFood", null) != null){
            Toast.makeText(this, "Hello Here We Are", Toast.LENGTH_SHORT).show();
            pref.putString("latestFood", sharedPref.getString("currentFood", null));
            pref.flush();
        }

        Toast.makeText(this, "PRINNTTTT" + sharedPref.getString("currentFood", null), Toast.LENGTH_SHORT).show();
        super.onResume();

    }

    public Preferences getPref(){
        return pref;
    }
}
