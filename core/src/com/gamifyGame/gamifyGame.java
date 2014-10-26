package com.gamifyGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class gamifyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    Texture background;
    static int scrWidth = 720;
	static int scrHeight = 1184;

	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
        background = new Texture("Background180x296.png");
	}

    public void drawCenter(Texture img, int hOffset, int vOffset){
        int iWidth = img.getWidth();
        int iHeight = img.getHeight();
        batch.draw(img, (scrWidth/2)-(iWidth/2)+hOffset, (scrHeight/2)-(iHeight/2)+vOffset);
    }


	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        batch.draw(background,0,0, scrWidth, scrHeight);
        drawCenter(img,150,200);
        drawCenter(img,-150,200);
        drawCenter(img,150,-100);
        drawCenter(img,-150,-100);
		drawCenter(img,0,50);
		batch.end();
	}
}
