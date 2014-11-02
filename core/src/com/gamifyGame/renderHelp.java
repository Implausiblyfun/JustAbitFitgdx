package com.gamifyGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Stephen on 11/2/2014.
 */
public class renderHelp {

    /*
     **********Image Setup functions***********
                                              */
    public static Image imageSetupCenter(String file, Stage stage, int hOffset, int vOffset){
        Texture texture = new Texture(file);
        Image image = new Image(texture);
        image.setPosition((stage.getWidth()/2 - texture.getWidth() /2)+hOffset,
                          (stage.getHeight()/2 - texture.getHeight() /2)+vOffset);
        image.setSize(texture.getWidth(),texture.getHeight());
        image.setName(file);
        stage.addActor(image);
        return image;
    }

    public static Image imageSetup(String file, Stage stage, float hOrigin, float vOrigin){
        Texture texture = new Texture(file);
        Image image = new Image(texture);
        image.setPosition(hOrigin,vOrigin);
        image.setSize(texture.getWidth(),texture.getHeight());
        image.setName(file);
        stage.addActor(image);
        return image;
    }
}
