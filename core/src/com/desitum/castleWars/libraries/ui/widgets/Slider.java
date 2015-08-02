package com.desitum.castleWars.libraries.ui.widgets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.desitum.castleWars.libraries.CollisionDetection;
import com.desitum.castleWars.libraries.ui.listeners.SliderListener;

import java.util.ArrayList;

/**
 * Created by kody on 4/21/15.
 * can be used by kody and people in []
 */
public class Slider extends Widget {
    private Texture barTexture;
    private Texture sliderTexture;

    private SliderListener sliderListener;

    private float sliderX;
    private float sliderWidth;
    private float sliderHeight;

    private boolean beingMoved;

    public Slider(Texture barTexture, Texture sliderTexture, float sliderX, float x, float y, float width, float height, float sliderWidth, float sliderHeight) {
        super(barTexture, width, height, x, y);

        this.beingMoved = false;

        this.sliderTexture = sliderTexture;
        this.barTexture = barTexture;

        this.sliderWidth = sliderWidth;
        this.sliderHeight = sliderHeight;

        this.setSize(width, height);
        this.setPosition(x, y);

        this.setOriginCenter();

        this.sliderX = sliderX;
    }

    public void onClickDown(Vector3 pos) {
        sliderX = pos.x - getX();
        beingMoved = true;
    }

    public void onClickUp() {
        if (sliderListener != null && beingMoved) {
            sliderListener.onChange(getPosition());
        }
        beingMoved = false;
    }

    public void resetState() {
        this.setTexture(barTexture);
    }

    public void setSliderListener(SliderListener sliderListener) {
        this.sliderListener = sliderListener;
    }

    public float getPosition() {
        return sliderX / getWidth();
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
        batch.draw(sliderTexture, getX() + sliderX - sliderWidth / 2, getY() + getHeight() / 2 - sliderHeight / 2, sliderWidth, sliderHeight);
    }

    @Override
    boolean scrollPosMatters() {
        return true;
    }

    @Override
    public void updateTouchInput(Vector3 touchPos, boolean clickDown) {
        boolean clickInArea = CollisionDetection.pointInRectangle(getBoundingRectangle(), touchPos);
        if (clickInArea && clickDown) {
            onClickDown(touchPos);
        } else if (clickInArea) {
            onClickUp();
        } else {
            onClickUp();
        }
    }

    @Override
    public ArrayList getChildren(boolean walk) {
        return new ArrayList<Widget>();
    }
}
