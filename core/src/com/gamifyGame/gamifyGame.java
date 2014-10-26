package com.gamifyGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class gamifyGame extends Game{
    mainScreen mainS;
    gameScreen gameS;
    dummyScreen dummyS;


    //READ THIS
    //READDDD

    //http://www.gamefromscratch.com/post/2013/11/27/LibGDX-Tutorial-9-Scene2D-Part-1.aspx

    //READDDD
    //READ

    @Override
    public void create() {
        mainS = new mainScreen(this);
        gameS = new gameScreen(this);
        dummyS = new dummyScreen(this);

        setScreen(mainS);
    }
}

