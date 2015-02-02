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
    quad1Screen quad1S;
    quad2Screen quad2S;
    quad3Screen quad3S;
    quad4Screen quad4S;
    buyScreen buyS;

    //READ THIS
    //READDDD

    //http://www.gamefromscratch.com/post/2013/11/27/LibGDX-Tutorial-9-Scene2D-Part-1.aspx


    public gamifyGame(ActionResolver actionResolver) {
        this.actionResolver = actionResolver;
    }



    public void create() {

        renderHelper renderer = new renderHelper();
        listenerHelper listenerHelper = new listenerHelper(this, pref);
        mainS = new mainScreen(this, actionResolver, renderer, listenerHelper, pref);
        quad1S = new quad1Screen(this, actionResolver, renderer, listenerHelper, pref);
        quad2S = new quad2Screen(this, actionResolver, renderer, listenerHelper, pref);
        quad3S = new quad3Screen(this, actionResolver, renderer, listenerHelper, pref);
        quad4S = new quad4Screen(this, actionResolver, renderer, listenerHelper, pref);
        buyS = new buyScreen(this, actionResolver, renderer, listenerHelper, pref);
        setScreen(mainS);
    }

    // Setter(s)
    public void setPref(Preferences preferences){
        pref = preferences;
    }
}

