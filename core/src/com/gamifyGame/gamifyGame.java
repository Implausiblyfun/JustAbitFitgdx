package com.gamifyGame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Map;

public class gamifyGame extends Game{
    private Preferences pref;
    private ActionResolver actionResolver;
    public mainScreen mainS;
    public testScreen testS;
    public quad1Screen quad1S;
    public quad2Screen quad2S;
    public quad3Screen quad3S;
    public quad4Screen quad4S;
    public buyScreen buyS;
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
        mainS = new mainScreen(this);
        testS = new testScreen(this, actionResolver, helper, pref);
        quad1S = new quad1Screen(this);
        quad2S = new quad2Screen(this);
        quad3S = new quad3Screen(this);
        quad4S = new quad4Screen(this);
        buyS = new buyScreen(this);


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

