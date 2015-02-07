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
    ChangeImage[][] Week;
    boolean shown = false;

    Image retBox;

    public quad3Screen(gamifyGame game, ActionResolver actionResolver, renderHelper rendererPassed,
                       listenerHelper listenerHPassed, Preferences pref) {
        this.game = game;
        this.actionResolver = actionResolver;
        renderer = rendererPassed;
        this.pref = pref;
        listenerH = listenerHPassed;

        shapes = renderer.getShapeRenderer();
        font = renderer.getFont();
        batch = renderer.getBatch();
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

        renderer.moveCorner(retBox,1,30);

        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.end();

        boolean showChallengeHours = pref.getBoolean("showChallengeHours",false);
        String showText;
        if (showChallengeHours){
            showText = "Hours for\n Challenges";
        }
        else showText = "Close \n Window";
        batch.begin();
        renderer.textSet(showText,16,16);
        batch.end();
        bringChallengeScreen();
    }

    private void bringChallengeScreen(){
        boolean showChallengeHours = pref.getBoolean("showChallengeHours",false);
        Stage layer1 = renderer.getLayer(1);
        if (showChallengeHours && !shown) {
            int borderX = 19;
            int borderY = 20;
            Image border = renderer.imageSetup("largeScreenBox.png", layer1, borderX, borderY);

            Week = new ChangeImage[7][24];
            for (int i = 0; i < 7; i++) {
                int newX = borderX + 2 + (i * 20);
                for (int j = 0; j < 24; j++) {
                    int newY = borderY + 232 - (j * 10);
                    ChangeImage tempImage = new ChangeImage("InactiveHour.png", "ActiveHour.png", layer1, newX, newY);
                    tempImage.addListener(new ClickListener() {
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            ChangeImage eventImage = (ChangeImage) event.getListenerActor();
                            eventImage.swapTexture();
                            return true;
                        }
                    });
                    Week[i][j] = tempImage;
                }
            }
            shown = true;

        }
        else if (!showChallengeHours && shown){

            renderer.getLayer(1).clear();
            retBox = renderer.imageSetup("trophyBox.png", layer1, 180, 296);
            Image placeholder62 = renderer.imageSetup("48Box.png", layer1, 0, 0);

            retBox.addListener(listenerH.goScreen(0));
            placeholder62.addListener(listenerH.setBoolean("showChallengeHours",'a'));
            shown = false;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

        Stage layer0 = renderer.getLayer(0);
        Stage layer1 = renderer.getLayer(1);
        Stage layer2 = renderer.getLayer(2);

        retBox = renderer.imageSetupCenter("trophyBox.png", layer1, -37, -25);
        Image placeholder62 = renderer.imageSetup("48Box.png", layer1, 0, 0);

        retBox.addListener(listenerH.goScreen(0));
        placeholder62.addListener(listenerH.setBoolean("showChallengeHours",'a'));

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
