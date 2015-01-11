package com.gamifyGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.lang.Math;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;


public class gameScreen implements Screen {
    ActionResolver actionResolver;
    SpriteBatch batch;
    static int scrWidth,scrHeight;
    BitmapFont font;
    Preferences pref;
    Image midbox, quad1,quad2,quad3,quad4,screen1,screen2,screen3,screen4,background,itemBar, sky;
    gamifyGame game;
    Stage layer1,layer2,layer3,layer4,layer5,layer6,layer6_2,layer7;

    float Ax, A2x, A5x, Ay, A2y, A5y, Az, A2z, A5z;

    int frameCount,activeStage;
    Boolean showChallengeHours;

    public gameScreen(gamifyGame game, ActionResolver actionResolver) {
        this.game = game;
        this.actionResolver = actionResolver;
        // settings for stages
        scrWidth = Gdx.graphics.getWidth();
        scrHeight = Gdx.graphics.getHeight();

        // Stage setup
        // Could have multiple viewports for rendering at different resolutions
        ScalingViewport view = new ScalingViewport(Scaling.stretch, 180, 296);
        layer1 = new Stage(view);
        layer2 = new Stage(view);
        layer3 = new Stage(view);
        layer4 = new Stage(view);
        layer5 = new Stage(view);
        layer6 = new Stage(view);
        layer6_2 = new Stage(view);
        layer7 = new Stage(view);
        batch = new SpriteBatch();

        // Layer assignments
        sky = renderHelp.imageSetup("day.png", layer1, 0, 0);
        background = renderHelp.imageSetup("background.png", layer1, 0, 0);

        itemBar = renderHelp.imageSetup("ItemBar.png", layer2, 0, 254);
        Image placeholderbar = renderHelp.imageSetup("placeholder128x24.png",layer2,26,8);

        quad1 = renderHelp.imageSetupCenter("StepBox.png", layer3, 37, 50);
        quad2 = renderHelp.imageSetupCenter("Streakbox.png", layer3, -37, 50);
        quad3 = renderHelp.imageSetupCenter("Trophybox.png", layer3, -37, -25);
        quad4 = renderHelp.imageSetupCenter("48box.png", layer3, 37, -25);
        midbox = renderHelp.imageSetupCenter("Midbox.png", layer3, 0, 12);

        Image screen1 = renderHelp.imageSetup("StepBox.png", layer4, 0,0);
        Image placeholder4 = renderHelp.imageSetupCenter("placeholder140x140.png",layer4,0,0);
        Image screen2 = renderHelp.imageSetup("Streakbox.png", layer5, 132,0);
        Image placeholder5 = renderHelp.imageSetupCenter("placeholder140x140.png",layer5,0,0);
        Image screen3 = renderHelp.imageSetup("Trophybox.png", layer6, 132,248);
        Image placeholder6 = renderHelp.imageSetupCenter("placeholder140x140.png",layer6,0,0);
        Image placeholder62 = renderHelp.imageSetup("placeholder64x64.png",layer6,0,0);
        Image placeholder7 = renderHelp.imageSetupCenter("placeholder140x140.png",layer7,0,0);
        screen4 = renderHelp.imageSetupCenter("48box.png", layer7, 37,-25);

        showChallengeHours = false;

        // *********** LISTENERS *******
        // SHOULD EVENTUALLY MOVE THESE THINGS SOMEWHERE ELSE
        ClickListener returnListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                if (activeStage == 7){
                    renderHelp.setPositionCenter(layer7,screen4,37,-25);
                }
                activeStage = 3;
                Gdx.input.setInputProcessor(layer3);
                return true;
            }
        };
        screen1.addListener(returnListener);
        screen2.addListener(returnListener);
        screen3.addListener(returnListener);
        screen4.addListener(returnListener);

        // This listener tests storing and retrieving data
        placeholder62.addListener(new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                int accumulator = pref.getInteger("TestValue");
                if (accumulator == 0) accumulator = 1;
                else accumulator = accumulator + accumulator;
                pref.putInteger("TestValue",accumulator + accumulator);
                if (showChallengeHours == true){
                    Gdx.input.setInputProcessor(layer6);
                    showChallengeHours = false;
                }
                else showChallengeHours = true;
                Gdx.input.setInputProcessor(layer6_2);
                return true;
            }
        });

        quad1.addListener(new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                activeStage = 4;
                Gdx.input.setInputProcessor(layer4);
                return true;
            }
        });
        quad2.addListener(new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                activeStage = 5;
                Gdx.input.setInputProcessor(layer5);
                return true;
            }
        });
        quad3.addListener(new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                activeStage = 6;
                Gdx.input.setInputProcessor(layer6);
                return true;
            }
        });
        quad4.addListener(new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                activeStage = 7;
                Gdx.input.setInputProcessor(layer7);
                return true;
            }
        });
        midbox.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                activeStage = 2;
                Gdx.input.setInputProcessor(layer2);
                return true;
            }
        });
        itemBar.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                activeStage = 3;
                Gdx.input.setInputProcessor(layer3);
                return true;
            }
        });

        // More settings
        font=new BitmapFont();
        frameCount=0;
        Ax=Gdx.input.getAccelerometerX();
        Ay=Gdx.input.getAccelerometerY();
        Az=Gdx.input.getAccelerometerZ();
        activeStage=3;
        Gdx.input.setInputProcessor(layer3);

        int borderX = 19;
        int borderY = 20;
        Image border = renderHelp.imageSetup("LargeScreenBox.png",layer6_2,borderX,borderY);

        ChangeImage[][] Week = new ChangeImage[7][24];
        for (int i = 0; i < 7; i++ ){
            int newX = borderX + 2 + (i*20);
            for (int j = 0; j < 24; j++){
                int newY = borderY + 230 - (j*10);
                ChangeImage tempImage = new ChangeImage("InactiveHour.png","ActiveHour.png",layer6_2,newX,newY);
                tempImage.addListener(new ClickListener(){
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        ChangeImage eventImage = (ChangeImage) event.getListenerActor();
                        eventImage.swapTexture();
                        return true;
                    }
                });
                Week[i][j] = tempImage;
            }
        }
        //debug lines (is there a way to do this like with an array that is less dumb?)
        // not high priority considering not in final make of app
        layer1.setDebugAll(true);
        layer2.setDebugAll(true);
        layer3.setDebugAll(true);
        layer4.setDebugAll(true);
        layer5.setDebugAll(true);
        layer6.setDebugAll(true);
        layer6_2.setDebugAll(true);
        layer7.setDebugAll(true);
    }
    // Setter(s)
    public void setPref(Preferences preferences){
        pref = preferences;
    }

    public void render(float delta) {
        // Undraw the last screen
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Stage drawing
        // REPLACE THIS WITH A SWITCH
        // CONSIDER USING SOMETHING EVEN LESS DUMB
        layer1.draw(); // currently background, always drawn
        if (activeStage == 2) {layer2.draw();}
        else if (activeStage == 3) {layer3.draw();}
        else if (activeStage == 4) {layer4.draw();}
        else if (activeStage == 5) {layer5.draw();}
        else if (activeStage == 6) {
            layer6.draw();
            if (showChallengeHours == true){
                layer6_2.draw();
            }
        }
        else if (activeStage == 7) {
            layer7.draw();
            // Dummy quad transition code, not effective
            if (screen4.getX() > 0){
                screen4.moveBy(-3,0);
                actionResolver.scanAct("HI");
            }
            if (screen4.getY() < 248){
                screen4.moveBy(0,5);
            }
        }
        // Do we need to do this every frame? Probably not?
        font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        batch.begin();

        // ***** DEBUG PRINTING ***** //
        if (frameCount % 5 == 0) {
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
        font.draw(batch, String.valueOf(pref.getInteger("TestValue")),50,200);

        batch.end();
        // If we want to do more things with frame counting in groups of 30
        if (frameCount == 30) {
            frameCount = 0;
        }
        frameCount += 1;
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

