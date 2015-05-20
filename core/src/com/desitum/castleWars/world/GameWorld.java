package com.desitum.castleWars.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.desitum.castleWars.objects.Card;

import kody.libgdx.libraries.animation.Animator;
import kody.libgdx.libraries.animation.MovementAnimator;
import kody.libgdx.libraries.interpolation.Interpolation;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public class GameWorld implements GameInterface {

    public static final float DISCARD_PILE_X = 5;
    public GameWorld() {

    }

    public void setGameMode() {

    }

    public void update(int state, OrthographicCamera cam, float delta) {

    }

    public void reset() {

    }

    @Override
    public void onClickCard(Card card, int cardID) {
        card.addMoveAnimtor((Animator) new MovementAnimator(card, card.getX(), DISCARD_PILE_X, 0.5f, 0, Interpolation.DECELERATE_INTERPOLATOR, true, false));
        //insert call to card actions here
    }
}
