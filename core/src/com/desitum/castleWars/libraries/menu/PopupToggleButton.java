package com.desitum.castleWars.libraries.menu;


import com.badlogic.gdx.graphics.Texture;
import com.desitum.castleWars.libraries.animation.Animator;

import java.util.ArrayList;

/**
 * Created by kody on 4/19/15.
 * can be used by kody and people in []
 */
public class PopupToggleButton extends PopupWidget {
    private Texture offTexture;
    private Texture onTexture;

    private ArrayList<Animator> comingInAnimators;
    private ArrayList<Animator> goingOutAnimators;

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

        this.comingInAnimators = new ArrayList<Animator>();
        this.goingOutAnimators = new ArrayList<Animator>();
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

    @Override
    public void update(float delta) {
        for (Animator anim : comingInAnimators) {
            anim.update(delta);
        }

        for (Animator anim : goingOutAnimators) {
            anim.update(delta);
        }
    }

    @Override
    public void addIncomingAnimator(Animator anim) {
        anim.setSprite(this, anim.updateX(), anim.updateY());
        this.comingInAnimators.add(anim);
    }

    @Override
    public void addOutgoingAnimator(Animator anim) {
        anim.setSprite(this, anim.updateX(), anim.updateY());
        this.goingOutAnimators.add(anim);
    }

    @Override
    public void startIncomingAnimators() {
        for (Animator anim : comingInAnimators) {
            anim.start(false);
        }
    }

    @Override
    public void startOutgoingAnimators() {
        for (Animator anim : goingOutAnimators) {
            anim.start(false);
        }
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
}
