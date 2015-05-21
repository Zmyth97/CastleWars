package com.desitum.castleWars.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.desitum.castleWars.objects.Card;
import com.desitum.castleWars.objects.Resources;

import kody.libgdx.libraries.animation.Animator;
import kody.libgdx.libraries.animation.MovementAnimator;
import kody.libgdx.libraries.interpolation.Interpolation;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public class GameWorld implements GameInterface {

    public static final int PLAYER = 0;
    public static final int PLAYER2 = 1;

    private Resources myResources;

    public static final float DISCARD_PILE_X = 5;
    public GameWorld() {
        myResources = new Resources();
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

    @Override
    public Resources getResources() {
        return myResources;
    }

    @Override
    public int getPlayerTurn() {
        return 0;
    }
}
