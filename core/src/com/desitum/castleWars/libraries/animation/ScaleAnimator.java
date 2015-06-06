package com.desitum.castleWars.libraries.animation;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.desitum.castleWars.libraries.interpolation.AccelerateDecelerateInterpolator;
import com.desitum.castleWars.libraries.interpolation.AccelerateInterpolator;
import com.desitum.castleWars.libraries.interpolation.AnticipateInterpolator;
import com.desitum.castleWars.libraries.interpolation.BounceInterpolator;
import com.desitum.castleWars.libraries.interpolation.DecelerateInterpolator;
import com.desitum.castleWars.libraries.interpolation.Interpolation;
import com.desitum.castleWars.libraries.interpolation.Interpolator;
import com.desitum.castleWars.libraries.interpolation.LinearInterpolator;
import com.desitum.castleWars.libraries.interpolation.OvershootInterpolator;

/**
 * Created by dvan6234 on 2/24/2015.
 */
public class ScaleAnimator implements Animator {

    private float duration;
    private float startScale;
    private float endScale;

    private float scaleSize;

    private float timeInAnimation;
    private float animationDelay;
    private float currentDelay;

    private boolean running;
    private boolean ran;
    private boolean growing;

    private Interpolator interpolator;

    private Sprite controllingSprite;
    private boolean controllingX;
    private boolean controllingY;

    private OnAnimationFinishedListener finishedListener;

    public ScaleAnimator(float duration, float startScale, float endScale, int interpolator) {
        this.duration = duration;
        this.startScale = startScale;
        this.endScale = endScale;

        scaleSize = startScale;

        this.controllingSprite = null;

        timeInAnimation = 0;
        currentDelay = 0;
        animationDelay = 0;

        if (startScale > endScale) {
            growing = false;
        } else {
            growing = true;
        }

        setupInterpolator(interpolator);
    }

    public ScaleAnimator(float duration, float delay, float startScale, float endScale, int interpolator) {
        this.duration = duration;
        this.startScale = startScale;
        this.endScale = endScale;

        this.controllingSprite = null;

        timeInAnimation = 0;
        currentDelay = 0;
        animationDelay = delay;

        if (startScale > endScale) {
            growing = false;
        } else {
            growing = true;
        }

        setupInterpolator(interpolator);
    }

    public ScaleAnimator(Sprite sprite, float duration, float delay, float startScale, float endScale, int interpolator, boolean controlWidth, boolean controlHeight) {
        this.duration = duration;
        this.startScale = startScale;
        this.endScale = endScale;

        this.controllingSprite = sprite;
        this.controllingX = controlWidth;
        this.controllingY = controlHeight;

        timeInAnimation = 0;
        currentDelay = 0;
        animationDelay = delay;

        if (startScale > endScale) {
            growing = false;
        } else {
            growing = true;
        }

        setupInterpolator(interpolator);
    }

    @Override
    public void update(float delta) {
        if (!running) {
            return;
        }
        if (currentDelay < animationDelay) {
            currentDelay += delta;
            return;
        }

        timeInAnimation += delta / duration;

        if (timeInAnimation >= 1) {
            timeInAnimation = 1;
            stop();
        }

        if (growing) {
            scaleSize = startScale + (endScale - startScale) * interpolator.getInterpolation(timeInAnimation);
        } else {
            scaleSize = startScale - (startScale - endScale) * interpolator.getInterpolation(timeInAnimation);
        }

        if (controllingSprite != null) {
            if (controllingY) {
                this.controllingSprite.setScale(this.controllingSprite.getScaleX(), this.getScaleSize());
            }
            if (controllingX) {
                this.controllingSprite.setScale(this.getScaleSize(), this.controllingSprite.getScaleY());
            }
        }
    }

    public void setSprite(Sprite control, boolean controlX, boolean controlY) {
        this.controllingSprite = control;

        this.controllingX = controlX;
        this.controllingY = controlY;
    }

    @Override
    public boolean updateY() {
        return controllingY;
    }

    @Override
    public boolean updateX() {
        return controllingX;
    }

    @Override
    public float getAmount() {
        return getScaleSize();
    }

    public float getScaleSize() {
        return scaleSize;
    }

    public void start(boolean isProtected) {
        if (isProtected && !ran) {
            running = true;
        } else if (!isProtected) {
            running = true;
            currentDelay = animationDelay;
            timeInAnimation = 0;
        }
        ran = true;
    }

    public void stop() {
        if (finishedListener != null) {
            finishedListener.onAnimationFinished(this);
        }
        running = false;
    }

    public boolean didFinish() {
        if (ran && !running) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void setOnFinishedListener(OnAnimationFinishedListener listener) {
        this.finishedListener = listener;
    }

    @Override
    public Sprite getSprite() {
        return controllingSprite;
    }

    private void setupInterpolator(int interpolator) {
        if (interpolator == Interpolation.ACCELERATE_INTERPOLATOR) {
            this.interpolator = AccelerateInterpolator.$();
        } else if (interpolator == Interpolation.DECELERATE_INTERPOLATOR) {
            this.interpolator = DecelerateInterpolator.$();
        } else if (interpolator == Interpolation.ANTICIPATE_INTERPOLATOR) {
            this.interpolator = AnticipateInterpolator.$();
        } else if (interpolator == Interpolation.OVERSHOOT_INTERPOLATOR) {
            this.interpolator = OvershootInterpolator.$();
        } else if (interpolator == Interpolation.ACCELERATE_DECELERATE_INTERPOLATOR) {
            this.interpolator = AccelerateDecelerateInterpolator.$();
        } else if (interpolator == Interpolation.BOUNCE_INTERPOLATOR) {
            this.interpolator = BounceInterpolator.$();
        } else if (interpolator == Interpolation.LINEAR_INTERPOLATOR) {
            this.interpolator = LinearInterpolator.$();
        }
    }

    @Override
    public Animator duplicate() {
        return new ScaleAnimator(controllingSprite, duration, animationDelay, startScale, endScale, Interpolation.getInterpolatorNum(interpolator), controllingX, controllingY);
    }
}
