package com.gamifyGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Preferences;

public class gamifyGame extends Game {
    private Preferences pref;
    private ActionResolver actionResolver;
    public CoreScreen mainS;
    public testScreen testS;
    public StepScreen quad1S;
    public StreakScreen quad2S;
    public ChallengeScreen quad3S;
    public StatScreen quad4S;
    public BaseScreen buyS;
    private listenerHelper helper;


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
            gamifyGame.setActionResolver(actionResolver);
        return gamifyGame;
    }

    public void setActionResolver(ActionResolver actionResolver) {
        this.actionResolver = actionResolver;
    }

    private gamifyGame(ActionResolver actionResolver) {
        this.actionResolver = actionResolver;
    }

    public void create() {

        //Define session variables
        pref.putBoolean("showChallengeHours", false);
        pref.flush();
        renderHelper.forceRemake();

        helper = new listenerHelper(this);
        mainS = new CoreScreen(this);
        testS = new testScreen(this, actionResolver, helper, pref);
        quad1S = new StepScreen(this);
        quad2S = new StreakScreen(this);
        quad3S = new ChallengeScreen(this);
        quad4S = new StatScreen(this);
        buyS = new BaseScreen(this);


        setScreen(mainS);
    }

    public Preferences getPrefs() {
        return pref;
    }

    public listenerHelper getListener() {
        return helper;
    }

    public void sendInt(String key, int val) {
        serverHelper.sendTestConfirm(val); //TODO: different application of this function,
        // might not need this function later.
    }

    // Setter(s)
    public void setPref(Preferences preferences) {
        pref = preferences;
    }
}

