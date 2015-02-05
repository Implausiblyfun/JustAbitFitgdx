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

public class testGame extends gamifyGame{
    Preferences pref;
    ActionResolver actionResolver;
    public testScreen testS;

    //READ THIS
    //READDDD

    //http://www.gamefromscratch.com/post/2013/11/27/LibGDX-Tutorial-9-Scene2D-Part-1.aspx

    public testGame(ActionResolver actionResolver) {
        this.actionResolver = actionResolver;
    }

    public void create() {

        renderHelper renderer = new renderHelper();
        listenerHelper listenerHelper = new listenerHelper(this, pref);
        testS = new testScreen(this, actionResolver, renderer, listenerHelper, pref);
        setScreen(testS);
    }

    // Setter(s)
    public void setPref(Preferences preferences){
        pref = preferences;
    }
}

