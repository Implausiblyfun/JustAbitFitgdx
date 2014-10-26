package com.gamifyGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.Screen;


public class gameScreen implements Screen{
    SpriteBatch batch;
    Texture midbox;
    Texture quad1;
    Texture quad2;
    Texture quad3;
    Texture quad4;
    Texture background;
    Texture itemBar;
    static int scrWidth = 720;
    static int scrHeight = 1184;

    gamifyGame game;

    public gameScreen(gamifyGame game){
        this.game = game;
        batch = new SpriteBatch();
        background = new Texture("Background180x296.png");
        itemBar = new Texture("ItemBar.png");

    }


    public void drawCenter(Texture img, int hOffset, int vOffset){
        // Default WMult and HMult are 4 because we are rendering everything
        // at x4 their default dimensions.
        drawCenter(img,hOffset,vOffset,4,4);
    }

    public void drawCenter(Texture img, int hOffset, int vOffset, int wMult, int hMult){
        int iWidth = img.getWidth();
        int iHeight = img.getHeight();
        batch.draw(img, (scrWidth / 2) - (iWidth * wMult / 2) + hOffset,
                (scrHeight / 2) - (iHeight * hMult / 2) + vOffset, iWidth * wMult, iHeight * hMult);
    }


    public void render (float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background,0,0, scrWidth, scrHeight);
        batch.draw(itemBar,0,scrHeight-(itemBar.getHeight()*4), scrWidth,itemBar.getHeight()*4);
        batch.end();
        if (Gdx.input.justTouched()) // http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/Input.html
            game.setScreen(game.mainS);
    }

    @Override
    public void resize(int width, int height) {
    }


    @Override
    public void show() {
        // called when this screen is set as the screen with game.setScreen();
    }


    @Override
    public void hide() {
        // called when current screen changes from this to a different screen
    }


    @Override
    public void pause() {
    }


    @Override
    public void resume() {
    }


    @Override
    public void dispose() {
        // never called automatically
    }


}
