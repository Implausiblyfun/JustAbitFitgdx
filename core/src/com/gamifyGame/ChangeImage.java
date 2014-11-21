package com.gamifyGame;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Stephen on 11/11/2014.
 */
public class ChangeImage extends Image {

        TextureRegionDrawable textured1, textured2;
        String name1,name2;

        public ChangeImage(String file, String file2, Stage stage, int hOrigin, int vOrigin){
            name1 = file;
            name2 = file2;
            Texture text1 = new Texture(file);
            textured1 = new TextureRegionDrawable(new TextureRegion(text1));
            textured2 = new TextureRegionDrawable(new TextureRegion(new Texture(file2)));
            this.setDrawable(textured1);
            this.setPosition(hOrigin, vOrigin);
            this.setSize(text1.getWidth(), text1.getHeight());
            this.setName(file);
            stage.addActor(this);
        }

        public void swapTexture(){
            if (this.getName() == name1){
                this.setName(name2);
                this.setDrawable(textured2);
            }
            else {
                this.setName(name1);
                this.setDrawable(textured1);
            }
        }
}
