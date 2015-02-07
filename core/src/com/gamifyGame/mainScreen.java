package com.gamifyGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Calendar;

/**
 * Created by Patrick Stephen on 2/1/2015.
 */
public class mainScreen implements Screen {

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

    public mainScreen(gamifyGame game, ActionResolver actionResolver, renderHelper rendererPassed,
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

            int challengeProgress = pref.getInteger("challengeProgress",50);

            shapes.begin(ShapeRenderer.ShapeType.Filled);
            if (challengeProgress == 100){shapes.setColor(new Color(0.99f,0.99f,0.0f,1.0f));}
            else shapes.setColor(new Color(0.30f,1.0f,0.0f,1.0f));
            shapes.box(33,103,0,(float)(challengeProgress/2.5),3,0);
            shapes.end();
            batch.begin();



            // ***** DEBUG PRINTING ***** //
            if (frameCount % 5 == 0) {
                challengeProgress--;
                if (challengeProgress < 0){
                    challengeProgress = 100;
                }
                pref.putInteger("challengeProgress", challengeProgress);
                pref.flush();
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
            if (Ax < 5 && Ax > 3 && Ay > 1 && Ay < 4 && Az > 6 && Az < 9){
                font.draw(batch, "Sitting",50,260);
            }
            else if (Ax < 0 && Ax > -4 && Ay > 7 && Ay < 10 && Az > -1 && Az < 4){
                font.draw(batch, "Standing",50,260);
            }
            else font.draw(batch, "Neithering",50,260);

            font.draw(batch, String.valueOf(Math.abs(A5x - Ax)), 50, 180);
            font.draw(batch, String.valueOf(Math.abs(A5y - Ay)), 50, 160);
            font.draw(batch, String.valueOf(Math.abs(A5z - Az)), 50, 140);
            font.draw(batch, String.valueOf(Ax), 50, 60);
            font.draw(batch, String.valueOf(Ay), 50, 40);
            font.draw(batch, String.valueOf(Az), 50, 20);
            font.draw(batch, String.valueOf(frameCount),50,200);
            batch.end();
            // If we want to do more things with frame counting in groups of 30
            frameCount = (frameCount + 1) % 30;
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
            renderer.imageSetup(timeOfDay(), layer0, 0, 0);
            renderer.imageSetup("background.png", layer0, 0, 0);
            // These five do.
            Image quad1 = renderer.imageSetupCenter("stepBox.png", layer1, 37, 50);
            Image quad2 = renderer.imageSetupCenter("streakBox.png", layer1, -37, 50);
            Image quad3 = renderer.imageSetupCenter("trophyBox.png", layer1, -37, -25);
            Image quad4 = renderer.imageSetupCenter("48Box.png", layer1, 37, -25);
            Image midbox = renderer.imageSetupCenter("midBox.png", layer1, 0, 12);

            // Assign items their listeners
            quad1.addListener(listenerH.goScreen(1));
            //quad1.addListener(listenerH.setInt("toScreen",1));
            quad2.addListener(listenerH.goScreen(2));
            quad3.addListener(listenerH.goScreen(3));
            quad4.addListener(listenerH.goScreen(4));
            midbox.addListener(listenerH.goScreen(5));
            frameCount = 0;
            //pref.putInteger("toScreen",0);
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

        private String timeOfDay(){
            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            if (hour < 5){ return "midnight.png";}
            else if (hour < 9){ return "sunrise.png";}
            else if (hour < 17){ return "day.png";}
            else return "night.png";
        }
    }
