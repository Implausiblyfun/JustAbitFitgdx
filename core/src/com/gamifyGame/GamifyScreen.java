package com.gamifyGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Andrew on 2/9/2015.
 */
public abstract class GamifyScreen implements Screen {
    protected ActionResolver actionResolver;
    //protected BitmapFont font;
    //protected ShapeRenderer shapes;
    protected gamifyGame game;
    //protected float Ax, A2x, A5x, Ay, A2y, A5y, Az, A2z, A5z;
    protected int frameCount;
    protected Image retBox;

    public GamifyScreen(gamifyGame game, ActionResolver actionResolver)
    {
        this.game = game;
        this.actionResolver = actionResolver;
    }

    @Override
    public void render(float delta) {
        Stage layer0 = renderHelper.getRenderHelper().getLayer(0);
        Stage layer1 = renderHelper.getRenderHelper().getLayer(1);
        Stage layer2 = renderHelper.getRenderHelper().getLayer(2);

        // Undraw the last screen
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        layer0.draw();
        layer1.draw();
        layer2.draw();

        //renderer.moveCorner(retBox,Corner.LOWER_LEFT,30);


        renderHelper.getRenderHelper().getShapeRenderer().begin(ShapeRenderer.ShapeType.Filled);
        renderHelper.getRenderHelper().getShapeRenderer().end();

        //batch.begin();
        //batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public abstract void show();

    @Override
    public void hide() {
        // called when current screen changes from this to a different screen
        renderHelper.getRenderHelper().getLayer(1).clear();
        renderHelper.getRenderHelper().getLayer(2).clear();
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
