package com.desitum.castleWars.libraries.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.desitum.castleWars.libraries.animation.Animator;
import com.desitum.castleWars.libraries.animation.ColorEffects;
import com.desitum.castleWars.libraries.animation.MovementAnimator;
import com.desitum.castleWars.libraries.animation.ScaleAnimator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by kody on 4/18/15.
 * can be used by kody and people in []
 */
public abstract class PopupWidget extends Sprite {

    private ArrayList<Animator> comingInAnimators;
    private ArrayList<Animator> goingOutAnimators;

    private boolean enabled;

    public PopupWidget(Texture texture, float width, float height, float x, float y) {
        super(texture, 0, 0, texture.getWidth(), texture.getHeight());

        setSize(width, height);
        setX(x);
        setY(y);

        this.enabled = true;

        this.comingInAnimators = new ArrayList<Animator>();
        this.goingOutAnimators = new ArrayList<Animator>();
    }

    public void update(float delta) {
        for (Animator anim : comingInAnimators) {
            anim.update(delta);
        }

        for (Animator anim : goingOutAnimators) {
            anim.update(delta);
        }
    }

    public void addIncomingAnimator(Animator anim) {
        anim.setSprite(this, anim.updateX(), anim.updateY());
        this.comingInAnimators.add(anim);
    }

    public void addOutgoingAnimator(Animator anim) {
        anim.setSprite(this, anim.updateX(), anim.updateY());
        this.goingOutAnimators.add(anim);
    }

    public void moveIn() {
        for (Animator anim : comingInAnimators) {
            anim.start(false);
        }

        this.enabled = true;
    }

    public void moveOut() {
        for (Animator anim : goingOutAnimators) {
            anim.start(false);
        }

        this.enabled = false;
    }

    public void setOriginCenter() {
        this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
    }

    public void setAlpha(float alpha) {
        super.setColor(1, 1, 1, alpha);
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    public void clearIncomingAnimators() {
        this.comingInAnimators = new ArrayList<Animator>();
    }

    public void clearOutgoingAnimators() {
        this.comingInAnimators = new ArrayList<Animator>();
    }

    public void clearAllAnimators() {
        this.clearIncomingAnimators();
        this.clearOutgoingAnimators();
    }

    public void clearColorEffects() {
        Iterator<Animator> iter = comingInAnimators.iterator();
        while (iter.hasNext()) {
            Animator anim = iter.next();
            if (anim instanceof ColorEffects) {
                iter.remove();
            }
        }
        iter = goingOutAnimators.iterator();
        while (iter.hasNext()) {
            Animator anim = iter.next();
            if (anim instanceof ColorEffects) {
                iter.remove();
            }
        }
    }

    public void clearMovementAnimators() {
        Iterator<Animator> iter = comingInAnimators.iterator();
        while (iter.hasNext()) {
            Animator anim = iter.next();
            if (anim instanceof MovementAnimator) {
                iter.remove();
            }
        }
        iter = goingOutAnimators.iterator();
        while (iter.hasNext()) {
            Animator anim = iter.next();
            if (anim instanceof MovementAnimator) {
                iter.remove();
            }
        }
    }

    public void clearScaleAnimators() {
        Iterator<Animator> iter = comingInAnimators.iterator();
        while (iter.hasNext()) {
            Animator anim = iter.next();
            if (anim instanceof ScaleAnimator) {
                iter.remove();
            }
        }
        iter = goingOutAnimators.iterator();
        while (iter.hasNext()) {
            Animator anim = iter.next();
            if (anim instanceof ScaleAnimator) {
                iter.remove();
            }
        }
    }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    public void toggleEnabled() {
        enabled = !enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void updateScroll(float amount, Vector3 mousePos) {

    }

    abstract boolean scrollPosMatters();
}
