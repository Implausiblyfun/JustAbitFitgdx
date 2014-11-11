package com.gamifyGame.android;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.gamifyGame.gamifyGame;


public class AndroidLauncher extends AndroidApplication {

    private gamifyGame gameProcess;
    ActionResolverAndroid actionResolverAndroid;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        actionResolverAndroid = new ActionResolverAndroid(this);
        gameProcess = new gamifyGame(actionResolverAndroid);

        // Preferences file stores data for later running of app
        gameProcess.setPref(this.getPreferences("Bitfitpref"));
        initialize(gameProcess, config);

	}
}
