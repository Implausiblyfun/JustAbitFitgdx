package com.gamifyGame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Map;

public class gamifyGame extends Game{
    gameScreen gameS;
    Preferences pref;
    ActionResolver actionResolver;

    //READ THIS
    //READDDD

    //http://www.gamefromscratch.com/post/2013/11/27/LibGDX-Tutorial-9-Scene2D-Part-1.aspx


    public gamifyGame(ActionResolver actionResolver) {
        this.actionResolver = actionResolver;
    }



    public void create() {

        gameS = new gameScreen(this, actionResolver);
        gameS.setPref(pref);
        setScreen(gameS);
    }

    // Setter(s)
    public void setPref(Preferences preferences){
        pref = preferences;
    }
}

