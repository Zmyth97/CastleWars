package com.desitum.castleWars.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.desitum.castleWars.libraries.animation.Animator;
import com.desitum.castleWars.world.GameInterface;

import java.util.ArrayList;

/**
 * Created by Zmyth97 on 5/18/2015.
 */
public class Wall extends Sprite {
    GameInterface gi;

    private float health; //Health = height in this game haha
    private Castle castle;

    private ArrayList<Animator> animators;

    public Wall(Texture wallImage, Castle castle, GameInterface gi) {
        super(wallImage, 0, 0, wallImage.getWidth(), wallImage.getHeight());

        this.gi = gi;

        health = 10;
    }

    public void doDamage(float damage, boolean justWalls) {
        health -= damage;
        if (this.equals(gi.getPlayer1().getCastle().getWall())) {
            gi.setPlayerWallLabelChangeText(-(int)damage);
        } else {
            gi.setComputerWallLabelChangeText(-(int)damage);
        }
        if(health <= 0){
            castle.doDamage(-health);
            health = 0;
        }
    }

    public void repair(float amount){
        health += amount;
        if (this.equals(gi.getPlayer1().getCastle().getWall())) {
            gi.setPlayerWallLabelChangeText((int)amount);
        } else {
            gi.setComputerWallLabelChangeText((int)amount);
        }
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
