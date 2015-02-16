package com.gamifyGame.android;

import android.app.IntentService;
import android.content.Intent;

import com.gamifyGame.gamifyGame;

/**
 * Created by Stephen on 2/12/2015.
 */
public class GameUpdater extends IntentService {

    public GameUpdater(){
        super("Tracker");
        setIntentRedelivery(true);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        ActionResolverAndroid actionResolverAndroid = new ActionResolverAndroid(this);
        gamifyGame gameProcess = gamifyGame.getGamifyGame(actionResolverAndroid);
        String activity = intent.getStringExtra("curActivity");
        gameProcess.getPrefs().putString("curActivity",activity);
        gameProcess.getPrefs().putInteger("updaterRunning",gameProcess.getPrefs().getInteger("updaterRunning",0)+1);
        gameProcess.getPrefs().flush();
    }
}