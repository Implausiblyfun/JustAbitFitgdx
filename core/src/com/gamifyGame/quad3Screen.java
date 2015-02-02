package com.gamifyGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Stephen on 2/1/2015.
 */
public class quad3Screen implements Screen {

    ActionResolver actionResolver;
    SpriteBatch batch;
    BitmapFont font;
    Preferences pref;
    ShapeRenderer shapes;
    gamifyGame game;
    renderHelper renderer;
    listenerHelper listenerH;
    float Ax, A2x, A5x, Ay, A2y, A5y, Az, A2z, A5z;
    int frameCount;

    public quad3Screen(gamifyGame game, ActionResolver actionResolver, renderHelper rendererPassed,
                       listenerHelper listenerHPassed, Preferences pref) {
        this.game = game;
        this.actionResolver = actionResolver;
        renderer = rendererPassed;
        listenerH = listenerHPassed;

        shapes = renderer.getShapeRenderer();
        font = renderer.getFont();
    }

    @Override
    public void render(float delta) {
        Stage layer0 = renderer.getLayer(0);
        Stage layer1 = renderer.getLayer(1);
        Stage layer2 = renderer.getLayer(2);

        // Undraw the last screen
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        layer0.draw();
        layer1.draw();
        layer2.draw();

        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

        Stage layer0 = renderer.getLayer(0);
        Stage layer1 = renderer.getLayer(1);
        Stage layer2 = renderer.getLayer(2);

        Image retBox = renderer.imageSetup("trophyBox.png", layer1, 132, 248);
        Image placeholder62 = renderer.imageSetup("placeholder64x64.png", layer1, 0, 0);

        retBox.addListener(listenerH.goScreen(0));
        placeholder62.addListener(new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                int accumulator = pref.getInteger("TestValue");
                if (accumulator == 0) accumulator = 1;
                else accumulator = accumulator + accumulator;
                pref.putInteger("TestValue",accumulator + accumulator);
                /*if (showChallengeHours == true){
                    showChallengeHours = false;
                }
                else showChallengeHours = true;
                */
                return true;
            }
        });

    }

    @Override
    public void hide() {
        renderer.getLayer(1).clear();
        renderer.getLayer(2).clear();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
