package com.desitum.castleWars.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.desitum.castleWars.CastleWars;
import com.desitum.castleWars.data.Assets;
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
public class Castle extends Sprite {
    private GameInterface gi;

    private float health; //Health = height in this game haha
    private float ZERO = GameScreen.SCREEN_HEIGHT / 4 + 8;
    private float ratio;

    private Animator animators;
    private Wall wall;

    private ParticleEmitter particles;

    public Castle(Texture castleImage, GameInterface gi, float x) {
        super(castleImage, 0, 0, castleImage.getWidth(), castleImage.getHeight());
        health = 40;
        if (GameScreen.SCREEN_WIDTH / 2 > x) {
            wall = new Wall(Assets.playerWall, this, x, gi);
        } else {
            wall = new Wall(Assets.computerWall, this, x, gi);

        }


        this.setOriginCenter();
        this.setSize(25.0f, 60.0f);
        this.setPosition(x, ZERO - (getHeight()));

        particles = new ParticleEmitter(getX(), ZERO, "game/particles/particles.prt");

        ratio = getHeight() / GameScreen.SCREEN_HEIGHT;

        animators = new MovementAnimator(this, this.getY(), ZERO - getHeight() + health * ratio, 1, 0, Interpolation.LINEAR_INTERPOLATOR, false, true);
        animators.start(false);
        this.gi = gi;
    }

    public void update(float delta) {
        if (animators != null) {
            if (animators.isRunning()) animators.update(delta);
        }
        particles.update(delta);
        this.getWall().update(delta);
    }

    public void doDamage(float damage) {
        if (wall.getHealth() <= 0) {
            if (this.equals(gi.getPlayer1().getCastle())) {
                gi.setPlayerCastleLabelChangeText((int) -damage);
            } else {
                gi.setComputerCastleLabelChangeText((int) -damage);
            }
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
        } else {
            this.getWall().doDamage(damage, false);
        }
        if (health <= 0) {
            health = 0;
            if (this.equals(gi.getPlayer1().getCastle())) {
                gi.lose();
            } else {
                gi.unlockAchievement(CastleWars.ATTACKER);
                gi.win();
            }
        }
    }

    public void repair(float amount) {
        health += amount;
        if (this.equals(gi.getPlayer1().getCastle())) {
            gi.setPlayerCastleLabelChangeText((int) amount);
        } else {
            gi.setComputerCastleLabelChangeText((int) amount);
        }
        if (health >= 100) {
            health = 100;
            if (this.equals(gi.getPlayer1().getCastle())) {
                gi.unlockAchievement(CastleWars.BUILDER);
                gi.win();
            } else {
                gi.lose();
            }
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

    public void castleDamage(float damage) {
        health -= damage;
        if (this.equals(gi.getPlayer1().getCastle())) {
            gi.setPlayerCastleLabelChangeText((int) -damage);
        } else {
            gi.setComputerCastleLabelChangeText((int) -damage);
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
        if (health <= 0) {
            health = 0;
        }
    }

    public Wall getWall() {
        return wall;
    }

    public void draw(SpriteBatch gameBatch) {
        super.draw(gameBatch);
        wall.draw(gameBatch);
        particles.draw(gameBatch);
    }
}
