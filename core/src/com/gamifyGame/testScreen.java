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
 * Created by Patrick Stephen on 2/1/2015.
 */
public class testScreen implements Screen {

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
    String activity = "dummyvalue";

    public testScreen(gamifyGame game, ActionResolver actionResolver, renderHelper rendererPassed,
                      listenerHelper listenerHPassed, Preferences pref) {
        this.game = game;
        this.actionResolver = actionResolver;
        this.pref = pref;
        renderer = rendererPassed;
        listenerH = listenerHPassed;

        shapes = renderer.getShapeRenderer();
        batch = renderer.getBatch();
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

        // Stage drawing
        // REPLACE THIS WITH A SWITCH
        // CONSIDER USING SOMETHING EVEN LESS DUMB
        layer0.draw(); // currently background, always drawn
        layer1.draw();
        layer2.draw();

        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.end();

        activity = pref.getString("curActivity");

        batch.begin();
        renderer.textSetCenter("Yes", -40, 6);
        renderer.textSetCenter("No", 26, 6);
        renderer.textSetCenter("Have you been " + activity + " recently?", -80, -40);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        // called when this screen is set as the screen with game.setScreen();

        Stage layer0 = renderer.getLayer(0);
        Stage layer1 = renderer.getLayer(1);
        Stage layer2 = renderer.getLayer(2);

        // Only items that need listeners should be maintained as Images I.E
        // These two don't need listeners--
        renderer.imageSetup("day.png", layer0, 0, 0);
        renderer.imageSetup("background.png", layer0, 0, 0);

        Image Yes = renderer.imageSetupCenter("48Box.png", layer1, -32,0);
        Image No = renderer.imageSetupCenter("48Box.png", layer1, 32,0);

        // MAKE SOME LISTENERS THAT SEND DATA TO THE SERVER?!?!?!?
    }

    @Override
    public void hide() {
        // called when current screen changes from this to a different screen
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
