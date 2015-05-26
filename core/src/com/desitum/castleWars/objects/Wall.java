package com.desitum.castleWars.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.desitum.castleWars.libraries.animation.Animator;

import java.util.ArrayList;

/**
 * Created by Zmyth97 on 5/18/2015.
 */
public class Wall extends Sprite {
    private float health; //Health = height in this game haha

    private ArrayList<Animator> animators;

    public Wall(Texture wallImage){
        super(wallImage, 0, 0, wallImage.getWidth(), wallImage.getHeight());
        health = 10;
    }

    public void doDamage(float damage){
        health -= damage;
        if(health <= 0){
            health = 0;
        }
    }

    public void repair(float amount){
        health += amount;
        if(health >= 100){
            health = 100;
        }
    }

    public float getHealth() {
        return health;
    }

    public void addAnimator(Animator anim) {
        animators.add(anim);
    }
}
