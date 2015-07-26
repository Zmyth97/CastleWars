package com.desitum.castleWars.libraries.game_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.desitum.castleWars.libraries.GameShader;
import com.desitum.castleWars.libraries.animation.Animator;

import java.util.ArrayList;

/**
 * Created by Kody.VanRy on 7/23/2015.
 */
public class GameObject extends Sprite {

    public static final int X = 0;
    public static final int Y = 1;

    public static final int WIDTH = 0;
    public static final int HEIGHT = 1;

    public static final int WRAP_CONTENT = -1;
    public static final int MATCH_PARENT = -2;

    private float velocityX;
    private float velocityY;
    private float gravityX;
    private float gravityY;
    private float srcWidth;
    private float srcHeight;

    private DrawStyle drawStyle;

    private ArrayList<Animator> animators;
    private ArrayList<Animator> animatorsToRemove;

    private GameShader gameShader;

    public GameObject(Texture texture) {
        super(texture);
    }

    public GameObject(Texture texture, float x, float y, float width, float height) {
        super(texture, texture.getWidth(), texture.getHeight());

        srcWidth = texture.getWidth();
        srcHeight = texture.getHeight();

        setX(x);
        setY(y);
        setSize(width, height);
    }

    public void setShader(GameShader gameShader) {
        this.gameShader = gameShader;
    }

    public void scaleTo(float width, float height, float duration, int interpolation) {
        scheduleScaleTo(width, height, duration, 0, interpolation);
    }

    public void scheduleScaleTo(float width, float height, float duration, float delay, int interpolation) {
        animators.add(GameObjectAnimatorBuilder.getScheduleScale(this, getWidth(), width, GameObject.WIDTH, duration, delay, interpolation));
        animators.add(GameObjectAnimatorBuilder.getScheduleScale(this, getHeight(), height, GameObject.HEIGHT, duration, delay, interpolation));
    }

    public void moveTo(float x, float y, float duration, int interpolation) {
        scheduleMoveTo(x, y, duration, 0, interpolation);
    }

    public void scheduleMoveTo(float x, float y, float duration, float waitSeconds, int interpolation) {
        animators.add(GameObjectAnimatorBuilder.getScheduleMoveTo(this, getX(), x, GameObject.X, duration, waitSeconds, interpolation));
        animators.add(GameObjectAnimatorBuilder.getScheduleMoveTo(this, getY(), y, GameObject.Y, duration, waitSeconds, interpolation));
    }

    public void draw(SpriteBatch gameBatch) {
        if (gameShader != null) {
            gameShader.setupShader();
        }

        super.draw(gameBatch);

        if (gameShader != null) {
            gameShader.takeDownShader();
        }
    }

    public void update(float delta) {
        for (Animator animator : animatorsToRemove) {
            animators.remove(animatorsToRemove);
        }
        animatorsToRemove.clear();
        for (Animator animator : animators) {
            animator.update(delta);
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void removeAnimator(Animator anim) {
        animatorsToRemove.add(anim);
    }
}
