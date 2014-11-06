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
        setPositionCenter(stage,image,hOffset,vOffset);
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

    // This acts like the inherent Image.setPosition, but at center to pair with
    // ImageSetupCenter
    public static void setPositionCenter(Stage stage,Image image,int hOffset,int vOffset){
        image.setPosition((stage.getWidth()/2) - (image.getWidth() /2)+hOffset,
                          ((stage.getHeight()/2) - (image.getHeight() /2))+vOffset);
    }
}