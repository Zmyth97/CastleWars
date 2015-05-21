package com.desitum.castleWars.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

import kody.libgdx.libraries.animation.Animator;

/**
 * Created by Zmyth97 on 5/18/2015.
 */
public class Castle extends Sprite {
    private float health; //Health = height in this game haha

    private ArrayList<Animator> animators;

    public Castle(Texture castleImage){
        super(castleImage, 0, 0, castleImage.getWidth(), castleImage.getHeight());
        health = 30;
    }

    public void doDamage(float damage){
        health -= damage;
        if(health <= 0){
            //End game!
        }
    }

    public void repair(float amount){
        health += amount;
        if(health >= 100){
            //End Game!
        }
    }

    public void addAnimator(Animator anim) {
        animators.add(anim);
    }
}
