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
import com.gamifyGame.Corner;
import com.gamifyGame.GamifyScreen;
import com.gamifyGame.gamifyGame;
import com.gamifyGame.renderHelper;

/**
 * Created by Stephen on 2/1/2015.
 */
public class Quad4Screen extends GamifyScreen implements Screen {

    public Quad4Screen(gamifyGame game) {

        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        renderHelper.getRenderHelper().moveCorner(retBox, Corner.UPPER_LEFT, 30);
    }


    @Override
    public void show() {
        retBox = renderHelper.getRenderHelper().imageSetupCenter("48Box.png", renderHelper.getRenderHelper().getLayer(1), 37, -25);
        retBox.addListener(game.getListener().goScreen(0));

    }


}
