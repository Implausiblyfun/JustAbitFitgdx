package com.gamifyGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Preferences;
import com.gamifyGame.screens.MainScreen;
import com.gamifyGame.screens.BuyScreen;
import com.gamifyGame.screens.Quad1Screen;
import com.gamifyGame.screens.Quad2Screen;
import com.gamifyGame.screens.Quad3Screen;
import com.gamifyGame.screens.Quad4Screen;

public class gamifyGame extends Game{
    private Preferences pref;
    private ActionResolver actionResolver;
    public MainScreen mainS;
    public testScreen testS;
    public Quad1Screen quad1S;
    public Quad2Screen quad2S;
    public Quad3Screen quad3S;
    public Quad4Screen quad4S;
    public BuyScreen buyS;
    private listenerHelper helper;


    private ChangingImage[] rooms;
    private int[] bridges;

    private static gamifyGame gamifyGame;
    //READ THIS
    //READDDD

    //http://www.gamefromscratch.com/post/2013/11/27/LibGDX-Tutorial-9-Scene2D-Part-1.aspx


    public static gamifyGame getGamifyGame(ActionResolver actionResolver)
    {
        if(gamifyGame==null)
            gamifyGame=new gamifyGame(actionResolver);
        else
            gamifyGame.setActionResolver(actionResolver);
        return gamifyGame;
    }

    public void setActionResolver(ActionResolver actionResolver)
    {
       this.actionResolver=actionResolver;
    }

    private gamifyGame(ActionResolver actionResolver) {
        this.actionResolver = actionResolver;
    }

    public void create() {

        //Define session variables
        pref.putBoolean("showChallengeHours",false);
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

    public Preferences getPrefs()
    {
        return pref;
    }
    public listenerHelper getListener()
    {
        return helper;
    }

    public void sendInt(String key, int val){
        serverHelper.sendTestConfirm(val); //TODO: different application of this function,
                                           // might not need this function later.
    }

    // Setter(s)
    public void setPref(Preferences preferences){
        pref = preferences;
    }
}

