package com.desitum.castleWars.libraries.menu;


import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.desitum.castleWars.libraries.animation.Animator;

import java.util.ArrayList;

/**
 * Created by kody on 4/19/15.
 * can be used by kody and people in []
 */
public class PopupButtonMaterial extends PopupWidget {
    private Texture upTexture;
    private Sprite shadow;

    private ArrayList<Animator> comingInAnimators;
    private ArrayList<Animator> goingOutAnimators;

    private boolean beenDown;

    private float x;
    private float y;
    private float z;

    private float originalX;
    private float originalY;

    private OnClickListener buttonListener;

    public PopupButtonMaterial(Texture upTexture, float x, float y, float z, float width, float height) {
        super(upTexture, width, height, x, y);

        this.upTexture = upTexture;
        this.shadow = new Sprite(new Texture(new FileHandle("shadow.png")));
        shadow.setSize(width, height);
        shadow.setPosition(x - z * 0.5f, y - z * 0.5f);

        this.setSize(width, height);
        this.setPosition(x, y);

        this.setOriginCenter();

        this.comingInAnimators = new ArrayList<Animator>();
        this.goingOutAnimators = new ArrayList<Animator>();
    }

    public void onClickDown(Vector3 pos){
        setPosition(x - z * 0.4f, y - z * 0.4f);
        beenDown = true;
    }

    public void onClickUp(boolean clicked){
        this.setTexture(upTexture);
        if (buttonListener != null && clicked && beenDown){
            buttonListener.onClick(this);
        }
        beenDown = false;
    }

    @Override
    public void update(float delta){
        for (Animator anim: comingInAnimators){
            anim.update(delta);
        }

        for (Animator anim: goingOutAnimators){
            anim.update(delta);
        }
    }

    @Override
    public void addIncomingAnimator(Animator anim){
        anim.setSprite(this, anim.updateX(), anim.updateY());
        this.comingInAnimators.add(anim);
    }

    @Override
    public void addOutgoingAnimator(Animator anim){
        anim.setSprite(this, anim.updateX(), anim.updateY());
        this.goingOutAnimators.add(anim);
    }

    @Override
    public void startIncomingAnimators(){
        for (Animator anim: comingInAnimators){
            anim.start(false);
        }
    }

    @Override
    public void startOutgoingAnimators(){
        for (Animator anim: goingOutAnimators){
            anim.start(false);
        }
    }

    public void setButtonListener(OnClickListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
        shadow.draw(batch);
    }
}
