package com.gamifyGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class gamifyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture midbox;
    Texture quad1;
    Texture quad2;
    Texture quad3;
    Texture quad4;
    Texture itemBar;
    Texture background;
    static int scrWidth = 720;
	static int scrHeight = 1184;

	public void create () {
		batch = new SpriteBatch();
		midbox = new Texture("MidBox64x64.png");
        quad1 = new Texture("Quad1Box48x48.png");
        quad2 = new Texture("Quad2Box48x48.png");
        quad3 = new Texture("Quad3Box48x48.png");
        quad4 = new Texture("Quad4Box48x48.png");
        itemBar = new Texture("ItemBar.png");
        background = new Texture("Background180x296.png");
	}

    public void drawCenter(Texture img, int hOffset, int vOffset){
        // Default WMult and HMult are 4 because we are rendering everything
        // at x4 their default dimensions.
        drawCenter(img,hOffset,vOffset,4,4);
    }

    public void drawCenter(Texture img, int hOffset, int vOffset, int wMult, int hMult){
        int iWidth = img.getWidth();
        int iHeight = img.getHeight();
        batch.draw(img, (scrWidth/2)-(iWidth*wMult/2)+hOffset,
                (scrHeight/2)-(iHeight*hMult/2)+vOffset,iWidth*wMult, iHeight*hMult);
    }


	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        batch.draw(background,0,0, scrWidth, scrHeight);
        batch.draw(itemBar,0,scrHeight-(itemBar.getHeight()*4),
                scrWidth,itemBar.getHeight()*4);
        drawCenter(quad1,150,200);
        drawCenter(quad2,-150,200);
        drawCenter(quad4,150,-100);
        drawCenter(quad3,-150,-100);
		drawCenter(midbox,0,50);
		batch.end();
	}
}
