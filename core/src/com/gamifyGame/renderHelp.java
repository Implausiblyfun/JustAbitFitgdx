package com.gamifyGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Stephen on 11/2/2014.
 */
public class renderHelp {

    Texture  boxBottomFace,boxBottomLeft, boxBottomLeftFace,
            boxBottomRight,boxBottomRightFace,
            boxLeftFace, boxRightFace, boxTopLeft, boxTopRight,
            day,night,midnight,sunrise,background, activeHour,
            inactiveHour, streakBox, stepBox, trophyBox;

    public renderHelp(){
        // Load all image files
        // Box Parts
        boxBottomFace = imageLoad("boxBottomFace.png");
        boxBottomLeft = imageLoad("boxBottomLeft.png");
        boxBottomLeftFace = imageLoad("boxBottomLeftFace.png");
        boxBottomRight = imageLoad("boxBottomRight.png");
        boxBottomRightFace = imageLoad("boxBottomRightFace.png");
        boxLeftFace = imageLoad("boxLeftFace.png");
        boxRightFace = imageLoad("boxRightFace.png");
        boxTopLeft = imageLoad("boxTopLeft.png");
        boxTopRight = imageLoad("boxTopRight.png");

        // Sky Backgrounds
        day = imageLoad("day.png");
        night = imageLoad("night.png");
        midnight = imageLoad("midnight.png");
        sunrise = imageLoad("sunrise.png");

        background = imageLoad("background.png");
        activeHour = imageLoad("ActiveHour.png");
        inactiveHour = imageLoad("InactiveHour.png");
        streakBox = imageLoad("Streakbox.png");
        stepBox = imageLoad("StepBox.png");
        trophyBox = imageLoad("Trophybox.png");


    }

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

    public static Texture imageLoad(String file){
        Texture texture = new Texture(file);
        return texture;
    }

    public static Image applyTexture(Texture texture, Stage stage, float hOrigin, float vOrigin){
        Image image = new Image(texture);
        image.setPosition(hOrigin,vOrigin);
        image.setSize(texture.getWidth(),texture.getHeight());
        image.setName(texture.toString());
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

    public static void setImageTexture(Image image, String file){
        image.setDrawable(new TextureRegionDrawable( new TextureRegion(new Texture(file))));
    }

    // This acts like the inherent Image.setPosition, but at center to pair with
    // ImageSetupCenter
    public static void setPositionCenter(Stage stage,Image image,int hOffset,int vOffset){
        image.setPosition((stage.getWidth()/2) - (image.getWidth() /2)+hOffset,
                          ((stage.getHeight()/2) - (image.getHeight() /2))+vOffset);
    }

    public static void rect(ShapeRenderer shapes, int x, int y, int w, int h){
        shapes.rect(4*x,4*y,4*w,4*h);
    }

    public void makeBox(ShapeRenderer shapes, Stage stage, int x, int y, int w, int h){
        assert w > 4;
        assert h > 4;
        Image tImage;
        rect(shapes,x,y,w,h);
        applyTexture(boxBottomLeft, stage, x, y-2);
        for (int i = 2; i < w - 2; i++) {
            if (i < (w - 2) / new Float(4)){
                applyTexture(boxBottomLeftFace, stage, x+i, y-2);
            }
            else if (i > 3*((w - 2) / new Float(4))){
                applyTexture(boxBottomRightFace, stage, x+i, y-2);
            }
            else applyTexture(boxBottomFace, stage, x+i, y-2);
        }
        applyTexture(boxBottomRight, stage, x+w-2,y-2);
        for (int i = 2; i < h - 9; i++) {
            applyTexture(boxRightFace, stage, x+w-2, y+i-2);
        }
        applyTexture(boxTopRight, stage, x+w-2,y+h-11);
        for (int i = 0; i < w - 4; i++) {
            if (i < (w - 2) / new Float(4)){
                tImage = applyTexture(boxBottomRightFace, stage, x + w -2 -i, y+h-9);
            }
            else if (i > 3*((w - 2) / new Float(4))){
                tImage = applyTexture(boxBottomLeftFace, stage, x + w -2 -i, y+h-9);
            }
            else tImage = applyTexture(boxBottomFace, stage, x + w -2 -i, y+h-9);
            tImage.setRotation(180);
        }
        applyTexture(boxTopLeft, stage, x, y+h-11);
        for (int i = 10; i < h ; i++) {
            applyTexture(boxLeftFace, stage, x, y + h -2 -i);
        }
    }
}
