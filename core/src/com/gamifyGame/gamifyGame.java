package com.gamifyGame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class gamifyGame extends Game {
    private Preferences pref, graphPref;
    private ActionResolver actionResolver;
    public MainScreen mainS;
    public testScreen testS;
    public Quad1Screen quad1S;
    public Quad2Screen quad2S;
    public Quad3Screen quad3S;
    public Quad4Screen quad4S;
    public BuyScreen buyS;
    private listenerHelper helper;
    boolean paused;

    private ChangingImage[] rooms;
    private int[] bridges;

    private static gamifyGame gamifyGame;
    //READ THIS
    //READDDD

    //http://www.gamefromscratch.com/post/2013/11/27/LibGDX-Tutorial-9-Scene2D-Part-1.aspx


    public static gamifyGame getGamifyGame(ActionResolver actionResolver) {
        if (gamifyGame == null)
            gamifyGame = new gamifyGame(actionResolver);
        else
            gamifyGame.actionResolver = actionResolver;
        return gamifyGame;
    }

    private gamifyGame(ActionResolver actionResolver) {
        this.actionResolver = actionResolver;
    }

    public void create() {

        Gdx.input.setCatchBackKey(true);
        paused = false;

        //Define session variables
        pref.putBoolean("showChallengeHours", false);
        pref.flush();
        renderHelper.forceRemake();

        helper = new listenerHelper(this);
        mainS = new MainScreen(this);
        testS = new testScreen(this, actionResolver, helper, pref);
        quad1S = new Quad1Screen(this);
        quad2S = new Quad2Screen(this);
        quad3S = new Quad3Screen(this);
        quad4S = new Quad4Screen(this);
        buyS = new BuyScreen(this);


        setScreen(mainS);
    }

    public void pause(){
        paused = true;
    }

    public void resume(){
        paused = false;
    }

    public void storeUpdatePrefs(Preferences updatePref){
        Map<String,?> kvPairs = updatePref.get();
        Set<String> keySet = kvPairs.keySet();
        for(Iterator i = keySet.iterator(); i.hasNext();){
            String key = String.valueOf(i.next());
            String val = String.valueOf(kvPairs.get(key));
            graphUpdate(key,val);
        }
    }

    public void graphUpdate(String key, String val){
        graphPref.putString("activity"+key,val);
        if (val == "running"){
            pref.putInteger("minutesRan",pref.getInteger("minutesRan",0)+1);
        } else if (val == "walking"){
            pref.putInteger("minutesWalked",pref.getInteger("minutesWalked",0)+1);
        } else if (val == "dancing"){
            pref.putInteger("minutesDanced",pref.getInteger("minutesDanced",0)+1);
        } else if (val == "cycling"){
            pref.putInteger("minutesBiked",pref.getInteger("minutesBiked",0)+1);
        }
    }

    public boolean isActive(){return !paused;}
    public Preferences getPrefs() {
        return pref;
    }
    public ActionResolver getActionResolver() { return actionResolver;}
    public listenerHelper getListener() {
        return helper;
    }

    public void sendInt(String key, int val) {
        serverHelper.sendTestConfirm(val, pref.getString("userID","12345")); //TODO: different application of this function,
        // might not need this function later.
    }

    // Setter(s)
    public void setPref(Preferences preferences) {
        pref = preferences;
    }
    public void setGraphPref(Preferences preferences) { graphPref = preferences;}
}

