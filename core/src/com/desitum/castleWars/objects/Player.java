package com.desitum.castleWars.objects;

import com.badlogic.gdx.Game;
import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.screens.GameScreen;
import com.desitum.castleWars.world.GameInterface;
import com.desitum.castleWars.world.GameRenderer;

/**
 * Created by Zmyth97 on 5/20/2015.
 */
public class Player {
    private Castle castle;
    private Hand hand;

    public Player(GameInterface gameInterface, float castleX){
        if(GameScreen.SCREEN_WIDTH/2 > castleX) {
            if(GameRenderer.ASSETS_TO_USE ==1 || GameRenderer.ASSETS_TO_USE == 3) {
                castle = new Castle(Assets.playerCastle, gameInterface, castleX);
            } else if(GameRenderer.ASSETS_TO_USE == 2) {
                castle = new Castle(Assets.playerFlameCastle, gameInterface, castleX);
            }
        } else {
            if(GameRenderer.ASSETS_TO_USE ==1 || GameRenderer.ASSETS_TO_USE == 3) {
                castle = new Castle(Assets.computerCastle, gameInterface, castleX);
            } else if(GameRenderer.ASSETS_TO_USE == 2) {
                castle = new Castle(Assets.computerFlameCastle, gameInterface, castleX);
            }
        }

        hand = new Hand(gameInterface);
    }

    public Castle getCastle() {
        return castle;
    }

    public boolean containsCard(Card card) {
        boolean contains = false;
        for (Card c: hand.getCardsInHand()) {
            if (c.equals(card)) {
                contains = true;
            }
        }
        return contains;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void update(float delta) {
        hand.update(delta);
        castle.update(delta);
    }
}
