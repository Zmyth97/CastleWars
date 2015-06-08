package com.desitum.castleWars.libraries.effects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.desitum.castleWars.libraries.animation.Animator;
import com.desitum.castleWars.libraries.animation.OnAnimationFinishedListener;
import com.desitum.castleWars.libraries.animation.ScaleAnimator;
import com.desitum.castleWars.libraries.interpolation.Interpolation;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kody on 6/7/15.
 * can be used by kody and people in [kody}]
 */
public class FlipEffect implements Animator {

    public static final int VERTICAL = 0;
    public static final int HORIZONTAl = 1;

    private static final Integer[] directions = new Integer[]{VERTICAL, HORIZONTAl};

    private Sprite sprite;
    private Texture texture;
    private Texture toTexture;

    private float duration;
    private float currentState;

    private int direction;

    private boolean running;
    private boolean ran;

    private Rectangle rectangle;

    private ArrayList<Animator> animators;

    private OnAnimationFinishedListener finishedListener;

    public FlipEffect(Sprite sprite1, final Texture toTexture1, float duration, int flipDirection) {
        if (!contains(directions, flipDirection)) {
            throw new EnumConstantNotPresentException(Directions.class, "flipDirection");
        }

        this.animators = new ArrayList<Animator>();

        this.sprite = sprite1;
        this.toTexture = toTexture1;
        this.duration = duration;
        this.currentState = 0;
        this.running = false;
        this.ran = false;

        if (sprite != null) {
            ScaleAnimator firstScale = new ScaleAnimator(sprite, duration / 2, 0, 1, 0, Interpolation.ACCELERATE_INTERPOLATOR, flipDirection == HORIZONTAl, flipDirection == VERTICAL);
            firstScale.setOnFinishedListener(new OnAnimationFinishedListener() {
                @Override
                public void onAnimationFinished(Animator anim) {
                    sprite.setTexture(toTexture);
                }
            });
            animators.add(firstScale);
            animators.add(new ScaleAnimator(sprite, duration / 2, duration / 2, 0, 1, Interpolation.DECELERATE_INTERPOLATOR, flipDirection == HORIZONTAl, flipDirection == VERTICAL));
        }
    }

    public FlipEffect(Sprite sprite, Texture fromTexture, Texture toTexture, float duration, int flipDirection) {
        this(sprite, toTexture, duration, flipDirection);
        sprite.setTexture(fromTexture);
    }

    public FlipEffect(Texture fromTexture, final Texture toTexture, Rectangle rectangle, float duration, int flipDirection) {
        this(null, toTexture, duration, flipDirection);

        this.texture = texture;
        this.rectangle = rectangle;

        ScaleAnimator firstScale = new ScaleAnimator(duration / 2, 1, 0, Interpolation.ACCELERATE_INTERPOLATOR);
        firstScale.setOnFinishedListener(new OnAnimationFinishedListener() {
            @Override
            public void onAnimationFinished(Animator anim) {
                texture = toTexture;
            }
        });
        animators.add(firstScale);
        animators.add(new ScaleAnimator(duration / 2, duration / 2, 0, 1, Interpolation.DECELERATE_INTERPOLATOR));
    }

    @Override
    public void update(float delta) {
        if (running) {
            currentState += delta;

            if (currentState >= duration) {
                if (finishedListener != null) {
                    finishedListener.onAnimationFinished(this);
                    running = false;
                }
            }
        }

        for (Animator animator : animators) {
            animator.update(delta);
        }
    }

    @Override
    public void start(boolean isProtected) {
        if (isProtected && !ran) {
            ran = true;
            running = true;

            currentState = 0;

            for (Animator animator : animators) {
                animator.start(isProtected);
            }
        } else if (!isProtected) {
            ran = true;
            running = true;

            currentState = 0;

            for (Animator animator : animators) {
                animator.start(isProtected);
            }
        }
    }

    @Override
    public void setSprite(Sprite control, boolean controlx, boolean controly) {
        this.sprite = control;
        if (controlx) {
            this.direction = HORIZONTAl;
        } else if (controly) {
            this.direction = VERTICAL;
        }
    }

    @Override
    public boolean updateY() {
        return direction == VERTICAL;
    }

    @Override
    public boolean updateX() {
        return direction == HORIZONTAl;
    }

    @Override
    public float getAmount() {
        return currentState;
    }

    @Override
    public Animator duplicate() {
        return null;
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
        return this.sprite;
    }

    public <T> boolean contains(final T[] array, final T key) {
        return Arrays.asList(array).contains(key);
    }

    public void draw(SpriteBatch spriteBatch) {
        if (sprite != null) {
            sprite.draw(spriteBatch);
        } else {
            if (direction == HORIZONTAl && animators.get(0).isRunning())
                spriteBatch.draw(texture, rectangle.getX() + animators.get(0).getAmount() * (rectangle.getWidth() / 2), rectangle.getY(), rectangle.getWidth() * animators.get(0).getAmount(), rectangle.getHeight());
            else if (direction == HORIZONTAl && animators.get(1).isRunning())
                spriteBatch.draw(texture, rectangle.getX() + rectangle.getWidth() / 2 - animators.get(1).getAmount() * rectangle.getWidth() / 2, rectangle.getY(), rectangle.getWidth() * animators.get(1).getAmount(), rectangle.getHeight());
            else if (direction == VERTICAL && animators.get(0).isRunning())
                spriteBatch.draw(texture, rectangle.getX(), rectangle.getY() + animators.get(0).getAmount() * (rectangle.getHeight() / 2), rectangle.getWidth(), rectangle.getHeight() * animators.get(0).getAmount());
            else if (direction == HORIZONTAl && animators.get(1).isRunning())
                spriteBatch.draw(texture, rectangle.getX(), rectangle.getY() + rectangle.getHeight() / 2 - animators.get(1).getAmount() * rectangle.getHeight() / 2, rectangle.getWidth(), rectangle.getHeight() * animators.get(1).getAmount());
        }
    }

    private enum Directions {

    }
}
