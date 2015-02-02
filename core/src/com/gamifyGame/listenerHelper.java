package com.gamifyGame;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Patrick Stephen on 2/1/2015.
 * This guy should hold all the listeners. Anyone who wants a listener should go here to get it.
 */
public class listenerHelper {
    final gamifyGame game;
    Preferences pref;
    ClickListener returnS, goS1, goS2, goS3, goS4, goS5;

    public listenerHelper(gamifyGame gamify, Preferences pref){
        this.pref = pref;
        this.game = gamify;
        returnS = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {game.setScreen(game.mainS); return true;}};
        goS1 = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {game.setScreen(game.quad1S); return true;}};
        goS2 = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {game.setScreen(game.quad2S); return true;}};
        goS3 = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {game.setScreen(game.quad3S); return true;}};
        goS4 = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {game.setScreen(game.quad4S); return true;}};
        goS5 = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {game.setScreen(game.buyS); return true;}};
    }

    public ClickListener goScreen(int val){
        switch (val){
            case 0: return returnS;
            case 1: return goS1;
            case 2: return goS2;
            case 3: return goS3;
            case 4: return goS4;
            case 5: return goS5;
            default: return null;
        }
    }

    public ClickListener setInt(final String key,final int val){
        return new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {pref.putInteger(key,val); return true;}};
    }

}
