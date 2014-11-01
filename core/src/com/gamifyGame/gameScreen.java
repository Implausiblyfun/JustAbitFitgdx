package com.gamifyGame;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.lang.Math;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;


public class gameScreen implements Screen{
    SpriteBatch batch;
    Image background;
    Image itemBar;
    static int scrWidth;
    static int scrHeight;
    BitmapFont font;

    gamifyGame game;

    Stage layer1;

    float Ax,A2x,A5x,Ay,A2y,A5y,Az,A2z,A5z;

    int framecount;

    public gameScreen(gamifyGame game){
        this.game = game;
        scrWidth = Gdx.graphics.getWidth();
        scrHeight = Gdx.graphics.getHeight();

        ScalingViewport view = new ScalingViewport(Scaling.stretch, 180, 296);

        layer1 = new Stage(view);

        batch = new SpriteBatch();
        background = imageSetup("Background180x296.png", layer1, 0, 0);
        itemBar = imageSetup("ItemBar.png",layer1,0,254);
        font = new BitmapFont();
        framecount = 0;

        Ax = Gdx.input.getAccelerometerX();
        Ay = Gdx.input.getAccelerometerY();
        Az = Gdx.input.getAccelerometerZ();
    }

    /*
     **********Image Setup functions***********
                                              */

    public Image imageSetupCenter(String file, Stage stage, int hOffset, int vOffset){
        return imageSetup(file,stage,(scrWidth/2)+hOffset,(scrHeight)/2+vOffset);
    }

    public Image imageSetup(String file, Stage stage, int hOrigin, int vOrigin){
        Texture texture = new Texture(file);
        Image image = new Image(texture);
        image.setPosition(hOrigin,vOrigin);
        image.setSize(texture.getWidth(),texture.getHeight());
        stage.addActor(image);
        return image;
    }

    public void render (float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        layer1.draw();
        font.setColor(1.0f, 1.0f, 1.0f, 1.0f);

        batch.begin();
        // ***** DEBUG PRINTING ***** //
        if (framecount % 5 == 0) {
            A5x = A2x;
            A5y = A2y;
            A5z = A2z;
            A2x = Ax;
            A2y = Ay;
            A2z = Az;
            Ax = Gdx.input.getAccelerometerX();
            Ay = Gdx.input.getAccelerometerY();
            Az = Gdx.input.getAccelerometerZ();
        }
        font.draw(batch, String.valueOf(Math.abs(A5x - Ax)), 50, 180);
        font.draw(batch, String.valueOf(Math.abs(A5y - Ay)), 50, 160);
        font.draw(batch, String.valueOf(Math.abs(A5z - Az)), 50, 140);
        font.draw(batch, String.valueOf(Ax), 50, 60);
        font.draw(batch, String.valueOf(Ay), 50, 40);
        font.draw(batch, String.valueOf(Az), 50, 20);

        batch.end();
        if (framecount == 30){
            framecount = 0;
        }
        framecount += 1;
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
