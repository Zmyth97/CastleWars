package com.desitum.castleWars.libraries.animation;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by dvan6234 on 2/17/2015.
 */
public class ColorEffects implements Animator{
    private Sprite controllingSprite;

    private float pointInTransition;
    private float duration;

    private float startRed;
    private float startGreen;
    private float startBlue;
    private float startAlpha;

    private float slopeRed;
    private float slopeGreen;
    private float slopeBlue;
    private float slopeAlpha;

    private float endRed;
    private float endGreen;
    private float endBlue;
    private float endAlpha;

    private float currentRed;
    private float currentGreen;
    private float currentBlue;
    private float currentAlpha;

    private boolean transforming;

    private OnAnimationFinishedListener finishedListener;

    public ColorEffects(Color startColor, Color endColor, float duration) {
        transforming = false;

        if (duration <= 0) {
            currentRed = endColor.r;
            endRed = (int) endColor.r;
            slopeRed = 0;
            currentGreen = endColor.r;
            endGreen = (int) endColor.g;
            slopeGreen = 0;
            currentBlue = endColor.b;
            endBlue = (int) endColor.b;
            slopeBlue = 0;
            currentAlpha = endColor.a;
            endAlpha = (int) endColor.a;
            slopeAlpha = 0;
            return;
        }


        this.duration = duration;

        startRed = startColor.r;
        startGreen = startColor.g;
        startBlue = startColor.b;
        startAlpha = startColor.a;

        slopeRed = (endColor.r - startColor.r);
        slopeGreen = (endColor.g - startColor.g);
        slopeBlue = (endColor.b - startColor.b);
        slopeAlpha = (endColor.a - startColor.a);

        currentRed = startColor.r;
        currentGreen = startColor.g;
        currentBlue = startColor.b;
        currentAlpha = startColor.a;

        endRed = endColor.r;
        endGreen = endColor.g;
        endBlue = endColor.b;
        endAlpha = endColor.a;
    }

    public void update(float delta) {
        if (transforming) {
            pointInTransition += delta / duration;

            currentRed = slopeRed * pointInTransition + startRed;
            currentGreen = slopeGreen * pointInTransition + startGreen;
            currentBlue = slopeBlue * pointInTransition + startBlue;
            currentAlpha = slopeAlpha * pointInTransition + startAlpha;

            if (pointInTransition >= 1) {
                transforming = false;

                if (finishedListener != null) {
                    finishedListener.onAnimationFinished(this);
                }

                currentRed = endRed;
                currentGreen = endGreen;
                currentBlue = endBlue;
                currentAlpha = endAlpha;
            }
        }
        if (controllingSprite != null) {
            controllingSprite.setColor(getCurrentColor());
        }
    }

    @Override
    public void start(boolean isProtected) {
        transforming = true;
    }

    public Color getCurrentColor() {
        return new Color(currentRed, currentGreen, currentBlue, currentAlpha);
    }

    @Override
    public void setSprite(Sprite control, boolean x, boolean y){
        controllingSprite = control;
    }

    @Override
    public boolean updateY() {
        return false;
    }

    @Override
    public boolean updateX() {
        return false;
    }

    @Override
    public float getAmount() {
        return 0;
    }

    @Override
    public Animator duplicate() {
        return new ColorEffects(new Color(startRed, startGreen, startBlue, 1), new Color(endRed, endGreen, endBlue, 1), duration);
    }

    @Override
    public boolean isRunning(){
        return transforming;
    }

    @Override
    public void setOnFinishedListener(OnAnimationFinishedListener listener) {
        this.finishedListener = listener;
    }
}

