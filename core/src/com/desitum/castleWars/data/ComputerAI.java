package com.desitum.castleWars.data;

import com.badlogic.gdx.Game;
import com.desitum.castleWars.objects.Card;
import com.desitum.castleWars.world.GameInterface;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Zmyth97 on 5/24/2015.
 */
public class ComputerAI {
    //These static variables are info from our cards to help determine cards to use (Easier to change later this way)
    private static final int MAX_ATTACK = 32;
    private static final int MAX_BUILD = 20;

    GameInterface gi;

    private int strategy;

    ArrayList<Integer> possibleCards;

    public ComputerAI(GameInterface gi) {
        this.gi = gi;
        strategy = 0;
        possibleCards = new ArrayList<Integer>();
    }

    public Card processAI() {
        Card chosenCard = null;

        possibleCards = new ArrayList<Integer>();
        for (Card c : gi.getPlayer2().getHand().getCardsInHand()) {
            if (c.isAvailable()) {
                possibleCards.add(c.getCardID());
            }
        }
        if(possibleCards.size() > 0) {
            Collections.sort(possibleCards);
            int card = determineStrategy();
            chosenCard = chooseCard(card);
        } else {
            //TODO Method for Discard
        }

        return chosenCard;
    }

    private int determineStrategy() {
        int chosenCard = 0;
        boolean moveToNext = true;
        if (gi.getPlayer1().getCastle().getWall().getHealth() + gi.getPlayer1().getCastle().getHealth() <= MAX_ATTACK || gi.getPlayer1().getCastle().getHealth() <= 20) { //20 for Trojan Horse Card
            chosenCard = checkForAttackWin();
            if (chosenCard > 0) {
                moveToNext = false;
            }
        }
        if (gi.getPlayer2().getCastle().getHealth() >= MAX_BUILD && moveToNext) {
            chosenCard = checkForBuildWin();
            if (chosenCard > 0) {
                moveToNext = false;
            }
        }
        if (gi.getPlayer2().getCastle().getWall().getHealth() + gi.getPlayer2().getCastle().getHealth() <= MAX_ATTACK || gi.getPlayer2().getCastle().getHealth() <= 20 && moveToNext) {//20 for Trojan Horse Card
            chosenCard = checkForAttackDefeat();
            if (chosenCard > 0) {
                moveToNext = false;
            }
        }
        if (gi.getPlayer1().getCastle().getHealth() >= MAX_BUILD && moveToNext) {
            chosenCard = checkForBuildDefeat();
            if (chosenCard > 0) {
                moveToNext = false;
            }
        }
        if (moveToNext) {
            chosenCard = processPossibleCards();
        }
        return chosenCard;
    }

    private int checkForAttackWin() {
        int chosenCard = 0;
        float target = gi.getPlayer1().getCastle().getHealth() + gi.getPlayer1().getCastle().getWall().getHealth();

        if (gi.getPlayer1().getCastle().getHealth() <= 20 && possibleCards.contains(CardActions.TROJAN_HORSE)) {
            return chosenCard = CardActions.TROJAN_HORSE;
        }

        for (int card : possibleCards) {
            if (card == CardActions.SPEARMAN && target <= 2) {
                chosenCard = CardActions.SPEARMAN;
            } else if (card == CardActions.RAM && target <= 6) {
                chosenCard = CardActions.RAM;
            } else if (card == CardActions.CATAPULT && target <= 12) {
                chosenCard = CardActions.CATAPULT;
            } else if (card == CardActions.LEGION && target <= 16) {
                chosenCard = CardActions.LEGION;
            } else if (card == CardActions.TREBUCHET && target <= 20) {
                chosenCard = CardActions.TREBUCHET;
            } else if (card == CardActions.LIGHTNING_STRIKE && target <= 16) {
                chosenCard = CardActions.LIGHTNING_STRIKE;
            } else if (card == CardActions.BLAST && target <= 8) {
                chosenCard = CardActions.BLAST;
            } else if (card == CardActions.MERLIN && target <= 32) {
                chosenCard = CardActions.MERLIN;
            }
        }
        return chosenCard;
    }

    private int checkForBuildWin() {
        int chosenCard = 0;
        float target = 100 - gi.getPlayer2().getCastle().getHealth();

        for (int card : possibleCards) {
            if (card == CardActions.REINFORCE && target <= 6) {
                chosenCard = CardActions.REINFORCE;
            } else if (card == CardActions.FORTIFY && target <= 12) {
                chosenCard = CardActions.FORTIFY;
            } else if (card == CardActions.CASTLE && target <= 20) {
                chosenCard = CardActions.CASTLE;
            } else if (card == CardActions.RESERVE && target <= 8) {
                chosenCard = CardActions.RESERVE;
            } else if (card == CardActions.SABOTAGE && target <= 8) {
                chosenCard = CardActions.SABOTAGE;
            } else if (card == CardActions.STRONGHOLD && target <= 10) {
                chosenCard = CardActions.STRONGHOLD;
            }
        }
        return chosenCard;
    }

    private int checkForAttackDefeat() {
        int chosenCard = 0;

        if (possibleCards.contains(CardActions.CASTLE)) {
            chosenCard = CardActions.CASTLE;
        } else if (possibleCards.contains(CardActions.STRONGHOLD)) {
            chosenCard = CardActions.STRONGHOLD;
        } else if (possibleCards.contains(CardActions.FORTIFY)) {
            chosenCard = CardActions.FORTIFY;
        } else if (possibleCards.contains(CardActions.DUPLICATE) && gi.getPlayer2().getCastle().getWall().getHealth() > 24) {
            chosenCard = CardActions.DUPLICATE;
        } else if (possibleCards.contains(CardActions.REINFORCE)) {
            chosenCard = CardActions.REINFORCE;
        } else if (possibleCards.contains(CardActions.SABOTAGE)) {
            chosenCard = CardActions.SABOTAGE;
        } else if (possibleCards.contains(CardActions.RESERVE)) {
            chosenCard = CardActions.RESERVE;
        } else if (possibleCards.contains(CardActions.GREATWALL)) {
            chosenCard = CardActions.GREATWALL;
        } else if (possibleCards.contains(CardActions.WALL)) {
            chosenCard = CardActions.WALL;
        } else if (possibleCards.contains(CardActions.BARRIER)) {
            chosenCard = CardActions.BARRIER;
        }


        return chosenCard;
    }

    private int checkForBuildDefeat() {
        int chosenCard = 0;

        if (possibleCards.contains(CardActions.TROJAN_HORSE)) {
            chosenCard = CardActions.TROJAN_HORSE;
        } else if (possibleCards.contains(CardActions.MERLIN)) {
            chosenCard = CardActions.MERLIN;
        } else if (possibleCards.contains(CardActions.LIGHTNING_STRIKE)) {
            chosenCard = CardActions.LIGHTNING_STRIKE;
        } else if (possibleCards.contains(CardActions.LEGION)) {
            chosenCard = CardActions.LEGION;
        } else if (possibleCards.contains(CardActions.CATAPULT)) {
            chosenCard = CardActions.CATAPULT;
        } else if (possibleCards.contains(CardActions.BLAST)) {
            chosenCard = CardActions.BLAST;
        } else if (possibleCards.contains(CardActions.RAM)) {
            chosenCard = CardActions.RAM;
        } else if (possibleCards.contains(CardActions.SPEARMAN)) {
            chosenCard = CardActions.SPEARMAN;
        } else if (possibleCards.contains(CardActions.RECRUITER)) {
            chosenCard = CardActions.RECRUITER;
        }
        return chosenCard;
    }

    private int processPossibleCards() {
        int chosenCard = 0;
        checkForPeople();

        if (chosenCard == 0) {
            improveEconomy();
        }
        return chosenCard;
    }

    private int improveEconomy() {
        int card = 0;
        boolean attack = false;

        if (gi.getPlayer1().getCastle().getHealth() < 40 || gi.getPlayer1().getCastle().getHealth() > 70) {
            card = attackEconomy();
            attack = true;
        } else {
            card = buildEconomy();
        }

        if(card == 0){
            if(attack) {
                card = buildEconomy();
            } else {
                card = attackEconomy();
            }
        }
        return card;
    }

    private int attackEconomy() {
        int chosenCard = 0;
        if (gi.getPlayer2().getCastle().getHealth() > 30 && possibleCards.contains(CardActions.ASSASSIN)) {
            chosenCard = CardActions.ASSASSIN;
        } else if (possibleCards.contains(CardActions.MERLIN)) {
            chosenCard = CardActions.MERLIN;
        } else if (possibleCards.contains(CardActions.TREBUCHET)) {
            chosenCard = CardActions.TREBUCHET;
        } else if (possibleCards.contains(CardActions.LEGION)) {
            chosenCard = CardActions.LEGION;
        } else if (possibleCards.contains(CardActions.LIGHTNING_STRIKE)) {
            chosenCard = CardActions.LIGHTNING_STRIKE;
        } else if (possibleCards.contains(CardActions.CATAPULT)) {
            chosenCard = CardActions.CATAPULT;
        }  else if (possibleCards.contains(CardActions.RAID)) {
            chosenCard = CardActions.RAID;
        }else if (possibleCards.contains(CardActions.BLAST)) {
            chosenCard = CardActions.BLAST;
        } else if (possibleCards.contains(CardActions.JERICHO ) && gi.getPlayer1().getCastle().getWall().getHealth() > 40) {
            chosenCard = CardActions.JERICHO;
        } else if (possibleCards.contains(CardActions.RAM)) {
            chosenCard = CardActions.RAM;
        } else if (possibleCards.contains(CardActions.BLACK_PLAGUE)) {
            chosenCard = CardActions.BLACK_PLAGUE;
        } else if (possibleCards.contains(CardActions.THIEF)) {
            chosenCard = CardActions.THIEF;
        } else if (possibleCards.contains(CardActions.SPEARMAN)) {
            chosenCard = CardActions.SPEARMAN;
        } else if (possibleCards.contains(CardActions.BURGLAR)) {
            chosenCard = CardActions.BURGLAR;
        } else if (possibleCards.contains(CardActions.DESTROY_WEAPONS)) {
            chosenCard = CardActions.DESTROY_WEAPONS;
        } else if (possibleCards.contains(CardActions.CREATE_WEAPONS)) {
            chosenCard = CardActions.CREATE_WEAPONS;
        } else if (possibleCards.contains(CardActions.TROJAN_HORSE)) {
            chosenCard = CardActions.TROJAN_HORSE;
        } else if (possibleCards.contains(CardActions.DESTROY_STONES)) {
            chosenCard = CardActions.DESTROY_STONES;
        } else if (possibleCards.contains(CardActions.DESTROY_GEMS)) {
            chosenCard = CardActions.DESTROY_GEMS;
        } else if (possibleCards.contains(CardActions.CREATE_GEMS)) {
            chosenCard = CardActions.CREATE_GEMS;
        }
        return chosenCard;
    }

    private int buildEconomy() {
        int chosenCard = 0;
        if (possibleCards.contains(CardActions.CASTLE)) {
            chosenCard = CardActions.CASTLE;
        } else if (possibleCards.contains(CardActions.FORTIFY)) {
            chosenCard = CardActions.FORTIFY;
        } else if (possibleCards.contains(CardActions.REINFORCE)) {
            chosenCard = CardActions.REINFORCE;
        } else if (possibleCards.contains(CardActions.DUPLICATE) && gi.getPlayer2().getCastle().getWall().getHealth() > 40) {
            chosenCard = CardActions.DUPLICATE;
        } else if (possibleCards.contains(CardActions.STRONGHOLD)) {
            chosenCard = CardActions.STRONGHOLD;
        } else if (possibleCards.contains(CardActions.GREATWALL)) {
            chosenCard = CardActions.GREATWALL;
        }  else if (possibleCards.contains(CardActions.WALL)) {
            chosenCard = CardActions.WALL;
        } else if (possibleCards.contains(CardActions.BARRIER)) {
            chosenCard = CardActions.BARRIER;
        } else if (possibleCards.contains(CardActions.CREATE_STONES )) {
            chosenCard = CardActions.CREATE_STONES;
        } else if (possibleCards.contains(CardActions.CREATE_GEMS)) {
            chosenCard = CardActions.CREATE_GEMS;
        } else if (possibleCards.contains(CardActions.CREATE_WEAPONS)) {
            chosenCard = CardActions.CREATE_WEAPONS;
        } else if (possibleCards.contains(CardActions.HAT_TRICK)) {
            chosenCard = CardActions.HAT_TRICK;
        }
        return chosenCard;
    }

    private int checkForPeople() {
        int chosenCard = 0;

        for (int card : possibleCards) {
            if (card == CardActions.ARCHITECT) {
                chosenCard = CardActions.ARCHITECT;
            } else if (card == CardActions.RECRUITER) {
                chosenCard = CardActions.RECRUITER;
            } else if (card == CardActions.MAGE) {
                chosenCard = CardActions.MAGE;
            }
        }
        return chosenCard;
    }


    private Card chooseCard(int card) {
        Card theCard = null;
        for (Card c : gi.getPlayer2().getHand().getCardsInHand()) {
            if (c.isAvailable()) {
                if (c.getCardID() == card) {
                    theCard = c;
                }
            }
        }
        return theCard;
    }
}
