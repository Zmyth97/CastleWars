package com.desitum.castleWars.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.libraries.animation.Animator;
import com.desitum.castleWars.world.GameInterface;
import com.desitum.castleWars.world.GameWorld;

import java.util.ArrayList;

/**
 * Created by Zmyth97 on 5/18/2015.
 */
public class Castle extends Sprite {
    private GameInterface gi;

    private float health; //Health = height in this game haha

    private ArrayList<Animator> animators;
    private Wall wall;

    public Castle(Texture castleImage, GameInterface gi){
        super(castleImage, 0, 0, castleImage.getWidth(), castleImage.getHeight());
        health = 40;
        wall = new Wall(Assets.cancelButtonUp, gi);

        this.gi = gi;
    }

    public void doDamage(float damage){
        if(wall.getHealth() > 0){
            if(damage > wall.getHealth()){
                float extraDamage = damage - wall.getHealth();
                wall.doDamage(damage);
                doDamage(extraDamage);
                if (this.equals(gi.getPlayer1())) {
                    gi.setPlayerCastleLabelChangeText((int)extraDamage);
                } else {
                    gi.setComputerCastleLabelChangeText((int)extraDamage);
                }
            } else {
                health -= damage;
                if (this.equals(gi.getPlayer1())) {
                    gi.setPlayerCastleLabelChangeText((int)damage);
                } else {
                    gi.setComputerCastleLabelChangeText((int)damage);
                }
            }

        }
        if(health <= 0){
            //End game!
        }
    }

    public void repair(float amount){
        health += amount;
        if (this.equals(gi.getPlayer1())) {
            gi.setPlayerCastleLabelChangeText((int)amount);
        } else {
            gi.setComputerCastleLabelChangeText((int)amount);
        }
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

    public void setHealth(float health) {
        this.health = health;
    }
}
