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

    @Override
    public void create() {
        mainS = new mainScreen(this);
        gameS = new gameScreen(this);
        //dummyS = new dummyScreen(this);

        setScreen(mainS);
    }
}

