package com.gamifyGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import java.util.HashMap;

/**
 * Created by Stephen on 11/2/2014.
 */
public class renderHelper {

    HashMap<String,Texture> textureHash;
    Texture boxBottomFace, boxBottomLeft, boxBottomLeftFace, boxBottomRight,
            boxBottomRightFace, boxRightFace, boxTopLeft, boxTopRight, boxLeftFace;

    int scrWidth, scrHeight;

    ShapeRenderer shapes;
    ScalingViewport view;
    Stage backgroundLayer, activeLayer, topLayer;
    SpriteBatch batch;
    BitmapFont font, font2;

    final Color boxColor = new Color(new Float(56)/255,new Float(7)/255,new Float(24)/255,1);

    public renderHelper(){
        scrWidth = Gdx.graphics.getWidth();
        scrHeight = Gdx.graphics.getHeight();
        shapes = new ShapeRenderer();
        shapes.scale(Float.valueOf(scrWidth)/180,Float.valueOf(scrHeight)/296,1);
        view = new ScalingViewport(Scaling.stretch, 180, 296);

        backgroundLayer = new Stage(view);
        activeLayer = new Stage(view);
        topLayer = new Stage(view);
        batch = new SpriteBatch();


        // Load all image files
        textureHash = new HashMap<String,Texture>();

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
        /* //These likely don't need to be in the HashMap
        textureHash.put("boxBottomFace.png",imageLoad("boxBottomFace.png"));
        textureHash.put("boxBottomLeft.png",imageLoad("boxBottomLeft.png"));
        textureHash.put("boxBottomLeftFace.png",imageLoad("boxBottomLeftFace.png"));
        textureHash.put("boxBottomRight.png",imageLoad("boxBottomRight.png"));
        textureHash.put("boxBottomRightFace.png",imageLoad("boxBottomRightFace.png"));
        textureHash.put("boxLeftFace.png",imageLoad("boxLeftFace.png"));
        textureHash.put("boxRightFace.png",imageLoad("boxRightFace.png"));
        textureHash.put("boxTopLeft.png",imageLoad("boxTopLeft.png"));
        textureHash.put("boxTopRight.png",imageLoad("boxTopRight.png"));
        */
        textureHash.put("day.png",imageLoad("day.png"));
        textureHash.put("night.png",imageLoad("night.png"));
        textureHash.put("midnight.png",imageLoad("midnight.png"));
        textureHash.put("sunrise.png",imageLoad("sunrise.png"));
        textureHash.put("background.png",imageLoad("background.png"));
        textureHash.put("activeHour.png",imageLoad("ActiveHour.png"));
        textureHash.put("inactiveHour.png",imageLoad("InactiveHour.png"));
        textureHash.put("streakBox.png",imageLoad("Streakbox.png"));
        textureHash.put("stepBox.png",imageLoad("StepBox.png"));
        textureHash.put("trophyBox.png",imageLoad("Trophybox.png"));
        textureHash.put("midBox.png",imageLoad("Midbox.png"));
        textureHash.put("48Box.png",imageLoad("48box.png"));

        textureHash.put("itemBar.png",imageLoad("ItemBar.png"));
        textureHash.put("placeholder128x24.png",imageLoad("placeholder128x24.png"));
        textureHash.put("placeholder140x140.png",imageLoad("placeholder140x140.png"));
        textureHash.put("placeholder64x64.png",imageLoad("placeholder64x64.png"));

        //font3=new BitmapFont(("subway.fnt"), Gdx.files.internal("subway.png"), false);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("subFree.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 32;
        font2 = generator.generateFont(parameter); // font size 12 pixels
        font2.setColor(1.0f,1.0f,1.0f,1.0f);
        generator.dispose();
        font=new BitmapFont();
        font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.input.setInputProcessor(activeLayer);
    }

    /*
     **********Image Setup functions***********
                                              */
    public Image imageSetupCenter(String key, Stage stage, int hOffset, int vOffset){
        Texture texture = textureHash.get(key);
        Image image = new Image(texture);
        setPositionCenter(stage,image,hOffset,vOffset);
        image.setSize(texture.getWidth(),texture.getHeight());
        image.setName(key);
        stage.addActor(image);
        return image;
    }

    public void textSet(String text, int x, int y){
        font2.draw(batch, text, (x*scrWidth)/180,(y*scrHeight)/296);
    }
    public void textSetCenter(String text, int offsetx, int offsety){
        BitmapFont.TextBounds bounds = font2.getBounds(text); //TODO: Use text boundaries to center text
        font2.draw(batch, text, (scrWidth/2) + ((offsetx*scrWidth)/180),
                                (scrHeight/2) + (offsety*scrHeight)/296);
    }

    public static Texture imageLoad(String file){ return new Texture(file);}

    public static Image applyTexture(Texture texture, Stage stage, float hOrigin, float vOrigin){
        Image image = new Image(texture);
        image.setPosition(hOrigin,vOrigin);
        image.setSize(texture.getWidth(),texture.getHeight());
        image.setName(texture.toString());
        stage.addActor(image);
        return image;
    }

    public Image imageSetup(String key, Stage stage, float hOrigin, float vOrigin){
        Texture texture = textureHash.get(key);
        Image image = new Image(texture);
        image.setPosition(hOrigin,vOrigin);
        image.setSize(texture.getWidth(),texture.getHeight());
        image.setName(key);
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

    // Moves an image to a corner;
    // quad = corner (1,2,3 or 4, quadrant definition)
    // frames = estimated # of frames to complete action
    public void moveCorner(Image toMove, Corner quad, int frames){
        float x = toMove.getX();
        float y = toMove.getY();
        float a,b;
        if (quad == Corner.UPPER_RIGHT){
            if (x + toMove.getImageWidth() == 180 && y + toMove.getImageHeight() == 296){return;}
            if (x + toMove.getImageWidth() > 179){
                toMove.setPosition(180-toMove.getImageWidth(),toMove.getY());
                a = 0;
            }
            else { a = Math.abs(x - 180) / frames;}
            if (y + toMove.getImageHeight() > 295){
                toMove.setPosition(toMove.getX(),296-toMove.getImageHeight());
                b = 0;
            }
            else{b = Math.abs(y - 296) / frames;}
            toMove.moveBy(a,b);
        }
        else if (quad == Corner.UPPER_LEFT){
            if (x == 0 && y + toMove.getImageHeight() == 296){return;}
            if (x < 1){
                toMove.setPosition(0,toMove.getY());
                a = 0;
            }
            else { a = Math.max(Math.abs(x - 0) / frames,2);}
            if (y + toMove.getImageHeight() > 295){
                toMove.setPosition(toMove.getX(),296-toMove.getImageHeight());
                b = 0;
            }
            else{b = Math.max(Math.abs(y - 296) / frames,2);}
            toMove.moveBy(a*-1,b);
        }
        else if (quad == Corner.LOWER_LEFT){
            if (x == 0 && y == 0){return;}
            if (x < 1){
                toMove.setPosition(0,toMove.getY());
                a = 0;
            }
            else { a = Math.max(Math.abs(x - 0) / frames,2);}
            if (y < 1){
                toMove.setPosition(toMove.getX(),0);
                b = 0;
            }
            else{b = Math.max(Math.abs(y - 0) / frames,2);}
            toMove.moveBy(a*-1,b*-1);
        }
        else {
            if (x + toMove.getImageWidth() == 180 && y == 0){return;}
            if (x + toMove.getImageWidth() > 179){
                toMove.setPosition(180-toMove.getImageWidth(),toMove.getY());
                a = 0;
            }
            else { a = Math.abs(x - 180) / frames;}
            if (y < 1){
                toMove.setPosition(toMove.getX(),0);
                b = 0;
            }
            else{b = Math.max(Math.abs(y - 0) / frames,2);}
            toMove.moveBy(a,b*-1);
        }
    }

    public int getWidth(){return scrWidth;}
    public int getHeight(){return scrHeight;}
    public ShapeRenderer getShapeRenderer(){return shapes;}
    public ScalingViewport getView(){return view;}
    public SpriteBatch getBatch(){return batch;}
    public Color getBoxColor(){return boxColor;}
    public BitmapFont getFont(){return font2;}
    public Stage getLayer(int level){
        if (level == 0){return backgroundLayer;}
        else if (level == 1){return activeLayer;}
        else return topLayer;
    }

    public void makeBox(ShapeRenderer shapes, Stage stage, int x, int y, int w, int h){
        assert w > 4;
        assert h > 4;
        Image tImage;
        shapes.rect(x,y,w,h);
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
        for (int i = y; i < y+h - 2; i++) {
            applyTexture(boxRightFace, stage, x+w-2, i);
        }
        applyTexture(boxTopRight, stage, x+w-2,y+h-11);
        for (int i = 0; i < w - 4; i++) {
            if (i < (w - 2) / new Float(4)){
                tImage = applyTexture(boxBottomRightFace, stage, x + w -2 -i, y+h-2);
            }
            else if (i > 3*((w - 2) / Float.valueOf(4))){
                tImage = applyTexture(boxBottomLeftFace, stage, x + w -2 -i, y+h-2);
            }
            else tImage = applyTexture(boxBottomFace, stage, x + w -2 -i, y+h-2);
            tImage.setRotation(180);
        }
        applyTexture(boxTopLeft, stage, x, y+h-11);
        for (int i = y+h-2; i > y - 2; i--) {
            applyTexture(boxLeftFace, stage, x, i);
        }
    }
}
