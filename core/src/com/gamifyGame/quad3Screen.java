package com.gamifyGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

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
    int frameCount;
    ChangingImage[][] Week;
    boolean shown = false;

    Image retBox, border;

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

        renderer.moveCorner(retBox,Corner.UPPER_RIGHT,30);
        boolean showChallengeHours = pref.getBoolean("showChallengeHours",false);


        String showText;
        if (!showChallengeHours){
            showText = "Hours for\nChallenges";
            int challengeProgress = pref.getInteger("challengeProgress",50);

            shapes.begin(ShapeRenderer.ShapeType.Filled);
            if (challengeProgress == 100){shapes.setColor(new Color(0.99f,0.99f,0.0f,1.0f));}
            else shapes.setColor(new Color(0.30f,1.0f,0.0f,1.0f));
            shapes.box(retBox.getX()+4,retBox.getY()+4,0,(float)(challengeProgress/2.5),3,0);
            shapes.end();
            if (frameCount % 5 == 0) {
                challengeProgress--;
                if (challengeProgress < 0) {
                    challengeProgress = 100;
                }
                pref.putInteger("challengeProgress", challengeProgress);
                pref.flush();
            }
        }
        else showText = "Close \nWindow";

        batch.begin();
        renderer.textSet(showText,2,16);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 24; j++) {
                String text = Week[i][j].getString("time");
                showText = text.substring(2);
                int showTextInt = Integer.valueOf(showText);
                if (showTextInt < 12){
                    if (showTextInt == 0){
                        showText = "12AM";
                    }
                    else showText = showText + "AM";
                }
                else if (showTextInt == 12){
                    showText = "12PM";
                }
                else showText = String.valueOf(showTextInt%12) + "PM";
                renderer.textSet(showText,(int)Week[i][j].getX(),(int)Week[i][j].getY()+10, "small");
                if (j + i == 0){
                    renderer.textSet("SUN",(int)Week[i][j].getX(),(int)Week[i][j].getY()+20, "small");
                    renderer.textSet("MON",(int)Week[i][j].getX()+20,(int)Week[i][j].getY()+20, "small");
                    renderer.textSet("TUE",(int)Week[i][j].getX()+40,(int)Week[i][j].getY()+20, "small");
                    renderer.textSet("WED",(int)Week[i][j].getX()+60,(int)Week[i][j].getY()+20, "small");
                    renderer.textSet("THU",(int)Week[i][j].getX()+80,(int)Week[i][j].getY()+20, "small");
                    renderer.textSet("FRI",(int)Week[i][j].getX()+100,(int)Week[i][j].getY()+20, "small");
                    renderer.textSet("SAT",(int)Week[i][j].getX()+120,(int)Week[i][j].getY()+20, "small");
                }
            }
        }
        bringChallengeScreen();
        batch.end();
        frameCount = (frameCount + 1) % 30;
    }

    private void bringChallengeScreen(){
        boolean showChallengeHours = pref.getBoolean("showChallengeHours",false);
        if (showChallengeHours && !shown) {

            border.moveBy(-300, 0);
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 24; j++) {
                    Week[i][j].moveBy(-300, 0);
                }
            }

            shown = true;

        }
        else if (!showChallengeHours && shown){

            border.moveBy(300,0);
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 24; j++) {
                    Week[i][j].moveBy(300,0);
                }
            }
            shown = false;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

        Stage layer1 = renderer.getLayer(1);

        retBox = renderer.imageSetupCenter("trophyBox.png", layer1, -37, -25);
        Image placeholder62 = renderer.imageSetup("48Box.png", layer1, 0, 0);

        int borderX = 19;
        int borderY = 20;
        int day = 0;
        int hour = 0;

        border = renderer.imageSetup("largeScreenBox.png", layer1, borderX+300, borderY);
        Week = new ChangingImage[7][24];
        for (int i = 0; i < 7; i++) {
            int newX = borderX + 2 + (i * 20);
            for (int j = 0; j < 24; j++) {
                int newY = borderY + 232 - (j * 10);
                ChangingImage tempImage = new ChangingImage("InactiveHour.png", "ActiveHour.png", layer1, newX+300, newY);
                String representation = String.valueOf(day) + ',' + String.valueOf(hour);
                tempImage.putExtra("time", representation);
                if (pref.getBoolean(representation,true)){tempImage.swapTexture();}
                tempImage.addListener(listenerH.challengeListener);
                Week[i][j] = tempImage;
                hour = (hour + 1) % 24;
            }
            day++;
        }

        retBox.addListener(listenerH.goScreen(0));
        placeholder62.addListener(listenerH.setBoolean("showChallengeHours",'a'));

    }

    @Override
    public void hide() {
        renderer.getLayer(1).clear();
        renderer.getLayer(2).clear();
        border.moveBy(-300,0);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 24; j++) {
                Week[i][j].moveBy(-300,0);
            }
        }
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
