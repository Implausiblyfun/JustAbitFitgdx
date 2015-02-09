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
public class buyScreen extends GamifyScreen implements Screen
{
    DragListener dragHandle;

    public buyScreen(gamifyGame game, ActionResolver actionResolver) {
       super(game, actionResolver);
    }

    @Override
    public void show()
    {
        //Image itemBar = renderer.imageSetup("ItemBar.png", layer1, 0, 254);
        Image placeHold = renderHelper.getRenderHelper().imageSetup("placeholder128x24.png", renderHelper.getRenderHelper().getLayer(1), 26, 8);
        placeHold.addListener(game.getListener().goScreen(0));


        Image buyBar = renderHelper.getRenderHelper().imageSetup("buyBar.png", renderHelper.getRenderHelper().getLayer(1), 0, 254);

        // TODO: generate this better and make them interactable.
        String[] buyList = {"Armory1.png","Computer1.png", "Costume1.png", "Forgery1.png",
                "Garage1.png", "Generator1.png", "HQ1.png", "Lab1.png", "Smuggler1.png"};
        Image[] imageHandles = renderHelper.getRenderHelper().makeScroll(renderHelper.getRenderHelper().getLayer(1), buyList, 0, 254);

        //Make the scroll bar actually scroll
        dragHandle = game.getListener().scroll(imageHandles);
        game.getListener().dragListeners(imageHandles);
        buyBar.addListener(dragHandle);

        // Make a new instance of the buildings that is interactable
        String[] imgs = {"Armory1.png","HQ1.png", "Computer1.png", "Costume1.png", "Forgery1.png",
                "Garage1.png", "Forgery1.png","Forgery1.png","Forgery1.png"};

        int[] bridges = {1, 1, 2, 2};

        ChangingImage[] undergroundBuild = renderHelper.getRenderHelper().makeUnderground(renderHelper.getRenderHelper().getLayer(1), imgs);
        renderHelper.getRenderHelper().makeBridges(renderHelper.getRenderHelper().getLayer(1), bridges);
        game.getListener().buildingListeners(undergroundBuild);
    }

    @Override
    public void hide() {
        // called when current screen changes from this to a different screen
        //renderer.getLayer(1).removeListener(dragHandle);
        renderHelper.getRenderHelper().getLayer(0).clear();
        super.hide();
    }
}
