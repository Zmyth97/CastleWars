package com.desitum.castleWars.libraries.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.desitum.castleWars.libraries.animation.Animator;

import java.util.ArrayList;

/**
 * Created by kody on 4/18/15.
 * can be used by kody and people in []
 */
public abstract class PopupWidget extends Sprite {

    private ArrayList<Animator> comingInAnimators;
    private ArrayList<Animator> goingOutAnimators;

    public PopupWidget(Texture texture, float width, float height, float x, float y) {
        super(texture, 0, 0, texture.getWidth(), texture.getHeight());

        setSize(width, height);
        setX(x);
        setY(y);

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

    public void startIncomingAnimators() {
        for (Animator anim : comingInAnimators) {
            anim.start(false);
        }
    }

    public void startOutgoingAnimators() {
        for (Animator anim : goingOutAnimators) {
            anim.start(false);
        }
    }

    public void setOriginCenter(){
        this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
    }
    
    public void setAlpha(float alpha){
    	super.setColor(1, 1, 1, alpha);
    }

    public void draw(SpriteBatch batch){
        super.draw(batch);
    }

    public void clearIncomingAnimators() {
        this.comingInAnimators = new ArrayList<Animator>();
    }

    public void clearOutgoingAnimators() {
        this.comingInAnimators = new ArrayList<Animator>();
    }

    public void clearAllAnimators() {
        this.comingInAnimators = new ArrayList<Animator>();
    }
}
