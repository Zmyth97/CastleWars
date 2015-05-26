package com.desitum.castleWars.objects;

import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.world.GameInterface;

/**
 * Created by Zmyth97 on 5/20/2015.
 */
public class Player {
    private Castle castle;
    private Hand hand;

    public Player(GameInterface gameInterface){
        castle = new Castle(Assets.playerCastle, gameInterface);
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
    }
}
