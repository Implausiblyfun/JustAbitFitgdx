package com.gamifyGame.android;

import android.os.Bundle;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.gamifyGame.gamifyGame;


public class AndroidLauncher extends AndroidApplication {

    private gamifyGame gameProcess;
    AccelAlarm alarm = new AccelAlarm();
    ActionResolverAndroid actionResolverAndroid;
    Preferences pref;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        actionResolverAndroid = new ActionResolverAndroid(this);
        gameProcess = new gamifyGame(actionResolverAndroid);

        // Start Accel tracking in background
        pref = this.getPreferences("Bitfitpref");
        alarm.setPref(pref);
        alarm.setAlarm(this);

        // Preferences file stores data for later running of app
        gameProcess.setPref(pref);
        initialize(gameProcess, config);

	}

    public Preferences getPref(){
        return pref;
    }
}
