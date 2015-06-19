package com.desitum.castleWars.libraries.menu;


import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Created by kody on 4/19/15.
 * can be used by kody and people in []
 */
public class PopupToggleButton extends PopupWidget {
    private Texture offTexture;
    private Texture onTexture;

    private boolean beenDown;
    private boolean on;

    private OnClickListener buttonListener;

    public PopupToggleButton(Texture onTexture, Texture offTexture, float x, float y, float width, float height, boolean on) {
        super(on ? onTexture : offTexture, width, height, x, y);

        this.onTexture = onTexture;
        this.offTexture = offTexture;

        this.setSize(width, height);
        this.setPosition(x, y);

        this.setOriginCenter();
    }

    public void onClickDown() {
        if (!beenDown) {
            this.on = !on;
            this.setTexture(on ? onTexture : offTexture);
            beenDown = true;
        }
    }

    public void onClickUp(boolean clicked) {
        if (buttonListener != null && clicked && beenDown) {
            buttonListener.onClick(this);
        }
        beenDown = false;
    }

    public void resetState() {
        this.setTexture(offTexture);
    }

    public void setButtonListener(OnClickListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    public boolean isOn() {
        return on;
    }

    public boolean toggle() {
        on = !on;
        this.setTexture(on ? onTexture : offTexture);
        return on;
    }

    public void turnOn() {
        on = true;
        this.setTexture(on ? onTexture : offTexture);
    }

    public void turnOff() {
        on = false;
        this.setTexture(on ? onTexture : offTexture);
    }

    @Override
    boolean scrollPosMatters() {
        return false;
    }

    @Override
    public ArrayList<PopupWidget> getChildren(boolean walk) {
        return new ArrayList<PopupWidget>();
    }
}
