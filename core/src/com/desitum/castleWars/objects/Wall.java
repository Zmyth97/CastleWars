package com.desitum.castleWars.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.desitum.castleWars.libraries.animation.Animator;
import com.desitum.castleWars.libraries.animation.MovementAnimator;
import com.desitum.castleWars.libraries.animation.OnAnimationFinishedListener;
import com.desitum.castleWars.libraries.interpolation.Interpolation;
import com.desitum.castleWars.libraries.particles.ParticleEmitter;
import com.desitum.castleWars.screens.GameScreen;
import com.desitum.castleWars.world.GameInterface;

/**
 * Created by Zmyth97 on 5/18/2015.
 */
public class Wall extends Sprite {
    private static float DISTANCE_FROM_CASTLE = 7;
    GameInterface gi;
    private float health; //Health = height in this game
    private float ZERO = GameScreen.SCREEN_HEIGHT / 4 + 8;
    private float ratio;

    private Castle castle;

    private Animator animators;

    private ParticleEmitter particles;

    public Wall(Texture wallImage, Castle castle, float x, GameInterface gi) {
        super(wallImage, 0, 0, wallImage.getWidth(), wallImage.getHeight());

        this.gi = gi;

        this.setOriginCenter();
        this.setSize(5.0f, 60.0f);

        health = 10;

        if (GameScreen.SCREEN_WIDTH / 2 > x) {
            this.setPosition((x + 25 + DISTANCE_FROM_CASTLE), ZERO - (getHeight()));
        } else {
            this.setPosition((x - this.getWidth() - DISTANCE_FROM_CASTLE), ZERO - (getHeight()));

        }

        this.castle = castle;
        particles = new ParticleEmitter(getX() - 3, ZERO, "game/particles/wallParticles.prt");

        ratio = getHeight() / GameScreen.SCREEN_HEIGHT;

        animators = new MovementAnimator(this, this.getY(), ZERO - getHeight() + health * ratio, 1, 0, Interpolation.LINEAR_INTERPOLATOR, false, true);
        animators.start(false);
    }

    public void update(float delta) {
        if (animators != null) {
            if (animators.isRunning()) animators.update(delta);
        }
        particles.update(delta);
    }

    public void doDamage(float damage, boolean justWalls) {
        health -= damage;
        animators = new MovementAnimator(this, this.getY(), ZERO - getHeight() + health * ratio, 1, 0, Interpolation.LINEAR_INTERPOLATOR, false, true);
        particles.turnOn();
        animators.setOnFinishedListener(new OnAnimationFinishedListener() {
            @Override
            public void onAnimationFinished(Animator anim) {
                particles.turnOff();
            }
        });
        animators.start(false);
        if (this.equals(gi.getPlayer1().getCastle().getWall())) {
            gi.setPlayerWallLabelChangeText(-(int) damage + (int) -health);
        } else {
            gi.setComputerWallLabelChangeText(-(int) damage + (int) -health);
        }
        if (health <= 0 && !justWalls) {
            castle.doDamage(-health);
            health = 0;
        } else if (health <= 0 && justWalls) {
            health = 0;
        }
    }

    public void repair(float amount) {
        health += amount;
        if (this.equals(gi.getPlayer1().getCastle().getWall())) {
            gi.setPlayerWallLabelChangeText((int) amount);
        } else {
            gi.setComputerWallLabelChangeText((int) amount);
        }
        if (health >= 100) {
            health = 100;
        }
        animators = new MovementAnimator(this, this.getY(), ZERO - getHeight() + health * ratio, 1, 0, Interpolation.LINEAR_INTERPOLATOR, false, true);
        particles.turnOn();
        animators.setOnFinishedListener(new OnAnimationFinishedListener() {
            @Override
            public void onAnimationFinished(Animator anim) {
                particles.turnOff();
            }
        });
        animators.start(false);
    }

    public float getHealth() {
        return health;
    }

    public void draw(SpriteBatch gameBatch) {
        super.draw(gameBatch);
        particles.draw(gameBatch);
    }

}
