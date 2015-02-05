package com.gamifyGame.android;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.gamifyGame.gamifyGame;
import com.gamifyGame.testGame;

import java.io.File;


public class NotificationLauncher extends AndroidApplication {

    final String GAMIFY_VERSION = "0.0.02a";
    private gamifyGame gameProcess;
    ActionResolverAndroid actionResolverAndroid;
    Preferences pref;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        actionResolverAndroid = new ActionResolverAndroid(this);
        gameProcess = new testGame(actionResolverAndroid);

        File directory = getFilesDir();

        pref = this.getPreferences("Bitfitpref");
        setContentView(R.layout.loginscreenres);

        // Preferences file stores data for later running of app
        gameProcess.setPref(pref);
        initialize(gameProcess, config);

    }

    public Preferences getPref(){
        return pref;
    }
}
