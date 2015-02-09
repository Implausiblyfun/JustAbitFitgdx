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
    Preferences pref;
    ActionResolver actionResolver;
    mainScreen mainS;
    testScreen testS;
    quad1Screen quad1S;
    quad2Screen quad2S;
    quad3Screen quad3S;
    quad4Screen quad4S;
    buyScreen buyS;


    ChangingImage[] rooms;
    int[] bridges;


    //READ THIS
    //READDDD

    //http://www.gamefromscratch.com/post/2013/11/27/LibGDX-Tutorial-9-Scene2D-Part-1.aspx


    public gamifyGame(){
        System.exit(0);
    }

    public gamifyGame(ActionResolver actionResolver) {
        this.actionResolver = actionResolver;
    }

    public void create() {

        //Define session variables
        pref.putBoolean("showChallengeHours",false);
        pref.flush();

        listenerHelper listenerHelper = new listenerHelper(this, pref);
        mainS = new mainScreen(this, actionResolver, listenerHelper, pref);
        testS = new testScreen(this, actionResolver, listenerHelper, pref);
        quad1S = new quad1Screen(this, actionResolver, listenerHelper, pref);
        quad2S = new quad2Screen(this, actionResolver, listenerHelper, pref);
        quad3S = new quad3Screen(this, actionResolver, listenerHelper, pref);
        quad4S = new quad4Screen(this, actionResolver, listenerHelper, pref);
        buyS = new buyScreen(this, actionResolver, listenerHelper, pref);


        setScreen(mainS);
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

