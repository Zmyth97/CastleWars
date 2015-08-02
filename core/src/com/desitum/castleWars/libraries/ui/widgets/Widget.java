package com.desitum.castleWars.libraries.ui.widgets;

import com.badlogic.gdx.graphics.OrthographicCamera;
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
public abstract class Widget extends Sprite {

    public static final int FILL_PARENT = -1;
    public static final int WRAP_CONTENT = -2;

    private ArrayList<Animator> comingInAnimators;
    private ArrayList<Animator> goingOutAnimators;

    private static int currentID;

    private int id;

    private boolean enabled;

    private Widget parent;

    private OrthographicCamera camera;

    public Widget(Texture texture, float width, float height, float x, float y) {
        super(texture, 0, 0, texture.getWidth(), texture.getHeight());

        currentID += 1;
        id = Integer.valueOf(currentID);

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

    public abstract boolean scrollPosMatters();

    public abstract void updateTouchInput(Vector3 touchPos, boolean clickDown);

    public abstract ArrayList getChildren(boolean walk);

    public OrthographicCamera getCamera() {
        return camera;
    }

    @Override
    public void setX(float x) {
        if (parent != null) {
            super.setX(parent.getX() + x);
        } else {
            super.setX(x);
        }
    }

    @Override
    public void setY (float y) {
        if (parent != null) {
            super.setY(parent.getY() + y);
        } else {
            super.setY(y);
        }
    }

    @Override
    public float getX() {
        if (parent != null) {
            return super.getX() - parent.getX();
        } else {
            return super.getX();
        }
    }

    @Override
    public float getY() {
        if (parent != null) {
            return super.getY() - parent.getY();
        } else {
            return super.getY();
        }
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public int getID() {
        return id;
    }

    public Widget getParent() {
        return parent;
    }

    public void setParent(Widget parent) {
        this.parent = parent;
    }
}
