package com.desitum.castleWars.libraries.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

/**
 * Created by kody on 4/21/15.
 * can be used by kody and people in []
 */
public class PopupSlider extends PopupWidget {
    private Texture barTexture;
    private Texture sliderTexture;

    private PopupSliderListener sliderListener;

    private float sliderX;
    private float sliderWidth;
    private float sliderHeight;

    private boolean beingMoved;

    public PopupSlider(Texture barTexture, Texture sliderTexture, float sliderX, float x, float y, float width, float height, float sliderWidth, float sliderHeight) {
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

    public void setSliderListener(PopupSliderListener sliderListener) {
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
    public ArrayList<PopupWidget> getChildren(boolean walk) {
        return new ArrayList<PopupWidget>();
    }
}
