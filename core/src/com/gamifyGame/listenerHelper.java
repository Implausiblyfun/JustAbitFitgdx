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
    final Preferences pref;
    ClickListener returnS, goS1, goS2, goS3, goS4, goS5, testYes, testNo;

    public listenerHelper(gamifyGame gamify, renderHelper renderer, Preferences preferences){
        this.pref = preferences;
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
        testYes = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {pref.putInteger("confirmed",1); pref.flush(); game.sendInt("userConfirm",1); return true;}};
        testNo = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {pref.putInteger("confirmed",-1); pref.flush(); game.sendInt("userConfirm",0); return true;}};
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
            {pref.putInteger(key,val); pref.flush(); return true;}};
    }

    public ClickListener setBoolean(final String key, final boolean val){
        return new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {pref.putBoolean(key,val); pref.flush(); return true;}};
    }

    public ClickListener setBoolean(final String key, final char inp){
        boolean val = true;
        switch (inp){
            case 't': val = true; break;
            case 'f': val = false; break;
            case 'a':
                return new ClickListener(){
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
                    {
                        if (!(pref.getBoolean(key,false))){pref.putBoolean(key,true);}
                        else pref.putBoolean(key,false);
                        pref.flush();
                        return true;}};
        }
        return setBoolean(key,val);
    }
}
