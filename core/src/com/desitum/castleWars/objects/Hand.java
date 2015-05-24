package com.desitum.castleWars.objects;

import com.desitum.castleWars.world.GameInterface;
import com.desitum.castleWars.world.GameWorld;

import java.util.ArrayList;

/**
 * Created by Zmyth97 on 5/20/2015.
 */
public class Hand {
    private Player player;
    private ArrayList<Card> cardList;
    private GameInterface gi;

    public Hand(){
        cardList = new ArrayList<Card>();
    }

    public void addCardToHand(Card card){
        cardList.add(card);
    }

    public void removeCardFromHand(Card card){
        cardList.remove(card);
    }

    private void checkCardCosts(){
        for(Card card: cardList){
            if(gi.getPlayerTurn() == GameWorld.PLAYER){
                if(card.getCardType() == Card.BUILD){
                    if(card.getCardCost() < gi.getResources().getPlayerStones()){
                        card.setAvailable(true);
                        card.restoreColor();
                    } else {
                        card.setAvailable(false);
                        card.fadeColor();
                    }
                } else if(card.getCardType() == Card.ATTACK){
                    if(card.getCardCost() < gi.getResources().getPlayerWeapons()){
                        card.setAvailable(true);
                        card.restoreColor();
                    }else {
                        card.setAvailable(false);
                        card.fadeColor();
                    }
                } else {
                    if(card.getCardCost() < gi.getResources().getPlayerGems()){
                        card.setAvailable(true);
                        card.restoreColor();
                    }else {
                        card.setAvailable(false);
                        card.fadeColor();
                    }
                }
            } else {
                if(card.getCardType() == Card.BUILD){
                    if(card.getCardCost() < gi.getResources().getComputerStones()){
                        card.setAvailable(true);
                        card.restoreColor();
                    }else {
                        card.setAvailable(false);
                        card.fadeColor();
                    }
                } else if(card.getCardType() == Card.ATTACK){
                    if(card.getCardCost() < gi.getResources().getComputerWeapons()){
                        card.setAvailable(true);
                        card.restoreColor();
                    }else {
                        card.setAvailable(false);
                        card.fadeColor();
                    }
                } else {
                    if(card.getCardCost() < gi.getResources().getComputerGems()){
                        card.setAvailable(true);
                        card.restoreColor();
                    }else {
                        card.setAvailable(false);
                        card.fadeColor();
                    }
                }
            }
        }
    }

    public ArrayList<Card> getCardsInHand(){
        return cardList;
    }


}
