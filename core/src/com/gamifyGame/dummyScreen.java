package com.gamifyGame;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class dummyScreen implements Screen {

    SpriteBatch batch;
    Texture midbox;
    Texture quad1;
    Texture quad2;
    Texture quad3;
    Texture quad4;
    Texture background;
    static int scrWidth = 720;
    static int scrHeight = 1184;
    gamifyGame game; // Note it's "MyGame" not "Game"


    // constructor to keep a reference to the main Game class
    public dummyScreen(gamifyGame game){
        this.game = game;
        batch = new SpriteBatch();
        background = new Texture("Background180x296.png");
    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(1, 0, 0, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, scrWidth, scrHeight);
        batch.end();
        // update and draw stuff
        //if (Gdx.input.justTouched()) // use your own criterion here
            //game.setScreen(game.anotherScreen);
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