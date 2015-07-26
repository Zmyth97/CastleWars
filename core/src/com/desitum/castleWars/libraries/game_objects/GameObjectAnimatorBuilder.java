package com.desitum.castleWars.libraries.game_objects;

import com.desitum.castleWars.libraries.animation.Animator;
import com.desitum.castleWars.libraries.animation.MovementAnimator;
import com.desitum.castleWars.libraries.animation.OnAnimationFinishedListener;
import com.desitum.castleWars.libraries.animation.ScaleAnimator;

/**
 * Created by Kody.VanRy on 7/23/2015.
 */
public class GameObjectAnimatorBuilder {

    /**
     * Returns new Movement animator that will remove itself from the game object when the
     * Animation is finished
     *
     * @param gameObject    {@link GameObject} that you are planning the addition to
     * @param start         start pos (x or y)
     * @param end           end pos (x or y)
     * @param affects       whether it affects x or y {@link GameObject} X or Y
     * @param duration      duration of the animation
     * @param delay         delay til start of animation
     * @param interpolation interpolation of the animtion {@link com.desitum.castleWars.libraries.interpolation.Interpolation}
     * @return {@link MovementAnimator}
     */
    public static MovementAnimator getScheduleMoveTo(final GameObject gameObject, float start, float end, int affects, float duration, float delay, int interpolation) {
        MovementAnimator animator = new MovementAnimator(gameObject, start, end, duration, delay, interpolation, affects == GameObject.X, affects == GameObject.Y);
        animator.setOnFinishedListener(new OnAnimationFinishedListener() {
            @Override
            public void onAnimationFinished(Animator anim) {
                gameObject.removeAnimator(anim);
            }
        });
        return animator;
    }

    public static ScaleAnimator getScheduleScale(final GameObject gameObject, float start, float end, int affects, float duration, float delay, int interpolation) {
        ScaleAnimator animator = new ScaleAnimator(gameObject, duration, delay, start, end, interpolation, affects == GameObject.WIDTH, affects == GameObject.HEIGHT);
        animator.setOnFinishedListener(new OnAnimationFinishedListener() {
            @Override
            public void onAnimationFinished(Animator anim) {
                gameObject.removeAnimator(anim);
            }
        });
        return animator;
    }
}
