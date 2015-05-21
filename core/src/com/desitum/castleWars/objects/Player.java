package com.desitum.castleWars.objects;

import com.desitum.castleWars.data.Assets;

/**
 * Created by Zmyth97 on 5/20/2015.
 */
public class Player {
    private Castle castle;
    private Hand hand;

    public Player(){
        castle = new Castle(Assets.cancelButtonDown);
        hand = new Hand();
    }

    public Castle getCastle() {
        return castle;
    }


    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}
