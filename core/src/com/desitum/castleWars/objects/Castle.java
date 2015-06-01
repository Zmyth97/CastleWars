package com.desitum.castleWars.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.libraries.animation.Animator;
import com.desitum.castleWars.libraries.animation.MovementAnimator;
import com.desitum.castleWars.libraries.animation.OnAnimationFinishedListener;
import com.desitum.castleWars.libraries.interpolation.Interpolation;
import com.desitum.castleWars.libraries.particles.ParticleEmitter;
import com.desitum.castleWars.screens.GameScreen;
import com.desitum.castleWars.world.GameInterface;
import com.desitum.castleWars.world.GameWorld;

import java.util.ArrayList;

/**
 * Created by Zmyth97 on 5/18/2015.
 */
public class Castle extends Sprite {
    private GameInterface gi;

    private float health; //Health = height in this game haha
    private float ZERO = GameScreen.SCREEN_HEIGHT / 4 + 8;
    private float ratio;

    private Animator animators;
    private Wall wall;

    private ParticleEmitter particles;

    public Castle(Texture castleImage, GameInterface gi, float x){
        super(castleImage, 0, 0, castleImage.getWidth(), castleImage.getHeight());
        health = 40;
        wall = new Wall(Assets.cancelButtonUp, gi);

        particles = new ParticleEmitter(getX(), ZERO, "game/particles/particles.prt");


        this.setSize(25, 100);
        setPosition(x, ZERO - getHeight());
        ratio = getHeight() / (GameScreen.SCREEN_HEIGHT - GameScreen.SCREEN_HEIGHT / 4 + 8 - 20);

        animators = new MovementAnimator(this, this.getY(), ZERO - getHeight() + health * ratio, 1, 0, Interpolation.LINEAR_INTERPOLATOR, false, true);
        animators.start(false);
        this.gi = gi;


    }

    public void update(float delta) {
        System.out.println("WHY?");
        if (animators != null) {
            System.out.println("WHY?");
            if (animators.isRunning()) animators.update(delta);
            System.out.println("WHY?------------------");
        }
        particles.update(delta);
    }

    public void doDamage(float damage){
        if(wall.getHealth() > 0){
            if(damage > wall.getHealth()){
                float extraDamage = damage - wall.getHealth();
                wall.doDamage(damage);
                doDamage(extraDamage);
                if (this.equals(gi.getPlayer1())) {
                    System.out.println("Called GameInter for Castle Extra-Damage: " + extraDamage);
                    gi.setPlayerCastleLabelChangeText((int)extraDamage);
                } else {
                    gi.setComputerCastleLabelChangeText((int)extraDamage);
                }
            } else {
                health -= damage;
                if (this.equals(gi.getPlayer1())) {
                    System.out.println("Called GameInter for Castle Damage: " + damage);
                    gi.setPlayerCastleLabelChangeText((int)damage);
                } else {
                    gi.setComputerCastleLabelChangeText((int)damage);
                }
                animators = new MovementAnimator(this, this.getY(), ZERO - getHeight() + health * ratio, 1, 0, Interpolation.LINEAR_INTERPOLATOR, false, true);
                particles.turnOn();
                animators.setOnFinishedListener(new OnAnimationFinishedListener() {
                    @Override
                    public void onAnimationFinished(Animator anim) {
                        particles.turnOff();
                    }
                });
            }
        }
        if(health <= 0){
            //End game!
        }
    }

    public void repair(float amount){
        health += amount;
        if (this.equals(gi.getPlayer1().getCastle())) {
            gi.setPlayerCastleLabelChangeText((int)amount);
        } else {
            gi.setComputerCastleLabelChangeText((int)amount);
        }
        animators = new MovementAnimator(this, this.getY(), ZERO - getHeight() + health * ratio, 1, 0, Interpolation.LINEAR_INTERPOLATOR, false, true);
        particles.turnOn();
        animators.setOnFinishedListener(new OnAnimationFinishedListener() {
            @Override
            public void onAnimationFinished(Animator anim) {
                particles.turnOff();
            }
        });
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
        animators = anim;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void draw(SpriteBatch gameBatch) {
        super.draw(gameBatch);
        wall.draw(gameBatch);
        particles.draw(gameBatch);
    }
}
