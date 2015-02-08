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
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import java.util.EventListener;

/**
 * Created by Stephen on 2/1/2015.
 */
public class buyScreen implements Screen {

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

    DragListener dragHandle;

    public buyScreen(gamifyGame game, ActionResolver actionResolver, renderHelper rendererPassed,
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

        //Image itemBar = renderer.imageSetup("ItemBar.png", layer1, 0, 254);
        Image placeHold = renderer.imageSetup("placeholder128x24.png", layer1, 26, 8);
        placeHold.addListener(listenerH.goScreen(0));


        Image buyBar = renderer.imageSetup("buyBar.png", layer1, 0, 254);

        // TODO: generate this better and make them interactable.
        String[] buyList = {"Armory1.png","Computer1.png", "Costume1.png", "Forgery1.png",
                "Garage1.png", "Generator1.png", "HQ1.png", "Lab1.png", "Smuggler1.png"};
        Image[] imageHandles = renderer.makeScroll(layer1, buyList, 0, 254);

        //Make the scroll bar actually scroll
        dragHandle = listenerH.scroll(imageHandles);
        listenerH.dragListeners(imageHandles);
        buyBar.addListener(dragHandle);

        // Make a new instance of the buildings that is interactable
        String[] imgs = {"Armory1.png","HQ1.png", "Computer1.png", "Costume1.png", "Forgery1.png",
                "Garage1.png", "Forgery1.png","Forgery1.png","Forgery1.png"};

        int[] bridges = {1, 1, 2, 2};

        ChangingImage[] undergroundBuild = renderer.makeUnderground(layer1, imgs);
        renderer.makeBridges(layer1, bridges);
        listenerH.buildingListeners(undergroundBuild);
    }

    @Override
    public void hide() {
        // called when current screen changes from this to a different screen
        //renderer.getLayer(1).removeListener(dragHandle);
        renderer.getLayer(0).clear();
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
