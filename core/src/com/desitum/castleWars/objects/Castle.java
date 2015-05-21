package com.desitum.castleWars.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.libraries.animation.Animator;

import java.util.ArrayList;

/**
 * Created by Zmyth97 on 5/18/2015.
 */
public class Castle extends Sprite {
    private float health; //Health = height in this game haha

    private ArrayList<Animator> animators;
    private Wall wall;

    public Castle(Texture castleImage){
        super(castleImage, 0, 0, castleImage.getWidth(), castleImage.getHeight());
        health = 30;
        wall = new Wall(Assets.cancelButtonUp);
    }

    public void doDamage(float damage){
        if(wall.getHealth() > 0){
            if(damage > wall.getHealth()){
                float extraDamage = damage - wall.getHealth();
                wall.doDamage(damage);
                doDamage(extraDamage);
            } else {
                health -= damage;
            }

        }
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

    public float getHealth() {
        return health;
    }

    public Wall getWall() {
        return wall;
    }

    public void addAnimator(Animator anim) {
        animators.add(anim);
    }
}
