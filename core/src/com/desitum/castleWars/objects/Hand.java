package com.desitum.castleWars.objects;

import com.desitum.castleWars.world.GameInterface;

import java.util.ArrayList;

/**
 * Created by Zmyth97 on 5/20/2015.
 */
public class Hand {
    private ArrayList<Card> cardList;
    private GameInterface gi;

    public Hand(GameInterface gameInterface) {
        this.gi = gameInterface;
        cardList = new ArrayList<Card>();
    }

    public void addCardToHand(Card card) {
        cardList.add(card);
        gi.addCardToWorld(card);
    }

    public void removeCardFromHand(Card card) {
        cardList.remove(card);
        gi.removeCardFromWorld(card);
    }

    private void checkCardCosts() {
        for (Card card : cardList) {
            if (gi.getPlayer1().getHand().equals(this)) {
                if (card.getCardType() == Card.BUILD) {
                    if (card.getCardCost() < gi.getResources().getPlayerStones()) {
                        card.enable();
                    } else {
                        card.disable();
                    }
                } else if (card.getCardType() == Card.ATTACK) {
                    if (card.getCardCost() < gi.getResources().getPlayerWeapons()) {
                        card.enable();
                    } else {
                        card.disable();
                    }
                } else {
                    if (card.getCardCost() < gi.getResources().getPlayerGems()) {
                        card.enable();
                    } else {
                        card.disable();
                    }
                }
            } else {
                if (card.getCardType() == Card.BUILD) {
                    if (card.getCardCost() < gi.getResources().getComputerStones()) {
                        card.enable();
                    } else {
                        card.disable();
                    }
                } else if (card.getCardType() == Card.ATTACK) {
                    if (card.getCardCost() < gi.getResources().getComputerWeapons()) {
                        card.enable();
                    } else {
                        card.disable();
                    }
                } else {
                    if (card.getCardCost() < gi.getResources().getComputerGems()) {
                        card.enable();
                    } else {
                        card.disable();
                    }
                }
            }
        }
    }

    public ArrayList<Card> getCardsInHand() {
        return cardList;
    }

    public void update(float delta) {
        for (Card c : cardList) {
            c.update(delta);
        }
        checkCardCosts();
    }
}
