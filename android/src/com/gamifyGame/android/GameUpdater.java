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
        gameProcess.getPrefs().putString("curActivity",intent.getStringExtra("curActivity"));
        gameProcess.getPrefs().flush();
        System.gc();
    }
}
