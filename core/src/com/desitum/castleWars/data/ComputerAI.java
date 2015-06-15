package com.desitum.castleWars.data;

import com.desitum.castleWars.objects.Card;
import com.desitum.castleWars.world.GameInterface;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Zmyth97 on 5/24/2015.
 */
public class ComputerAI {
    private static final int MAX_ATTACK = 32;
    private static final int MAX_BUILD = 20;

    GameInterface gi;
    ArrayList<Integer> possibleCards;
    private boolean discarding;

    public ComputerAI(GameInterface gi) {
        this.gi = gi;
        possibleCards = new ArrayList<Integer>();
        discarding = false;
    }

    public Card processAI() {
        Card chosenCard = null;

        possibleCards = new ArrayList<Integer>();
        for (Card c : gi.getPlayer2().getHand().getCardsInHand()) {
            if (c.isAvailable()) {
                possibleCards.add(c.getCardID());
            }
        }
        if (possibleCards.size() > 0) {
            discarding = false;
            System.out.println("Possible Cards Amonut: " + possibleCards.size());
            System.out.println("Starting AI");
            Collections.sort(possibleCards);
            System.out.println("Possible Cards: ");
            for (int card : possibleCards) {
                System.out.println(card);
            }
            int card = determineStrategy();
            System.out.println("Card ID: " + card);
            if (card == 0) {
                card = possibleCards.get(0);
                if (card == CardActions.JERICHO) {
                    discarding = true;
                }
            }
            chosenCard = chooseCard(card);
            System.out.println("Chosen Card: " + chosenCard.getCardID());
        } else {
            chosenCard = findBestToDiscard();
            System.out.println("Had to Discard");
            discarding = true;
        }

        return chosenCard;
    }

    private int determineStrategy() {
        System.out.println("Determine a Strategy");
        int chosenCard = 0;
        boolean moveToNext = true;
        if (gi.getPlayer1().getCastle().getWall().getHealth() + gi.getPlayer1().getCastle().getHealth() <= MAX_ATTACK || gi.getPlayer1().getCastle().getHealth() <= 20) { //20 for Trojan Horse Card
            chosenCard = checkForAttackWin();
            System.out.println("See if AI Can Attack to Win");
            if (chosenCard > 0) {
                moveToNext = false;
            }
        }
        if ((100 - gi.getPlayer2().getCastle().getHealth()) <= MAX_BUILD && moveToNext) {
            chosenCard = checkForBuildWin();
            System.out.println("See if AI Can Build to Win");
            if (chosenCard > 0) {
                moveToNext = false;
            }
        }
        if (gi.getPlayer2().getCastle().getWall().getHealth() + gi.getPlayer2().getCastle().getHealth() <= MAX_ATTACK || gi.getPlayer2().getCastle().getHealth() <= 20 && moveToNext) {//20 for Trojan Horse Card
            chosenCard = checkForAttackDefeat();
            System.out.println("Try to Avoid Attack Defeat by Player1");
            if (chosenCard > 0) {
                moveToNext = false;
            }
        }
        if ((100 - gi.getPlayer1().getCastle().getHealth()) <= MAX_BUILD && moveToNext) {
            chosenCard = checkForBuildDefeat();
            System.out.println("Try to Avoid Build Win by Player1");
            if (chosenCard > 0) {
                moveToNext = false;
            }
        }
        if (moveToNext) {
            System.out.println("Checking for Possible Cards");
            chosenCard = processPossibleCards();
        }
        return chosenCard;
    }

    private int checkForAttackWin() {
        int chosenCard = 0;
        float target = gi.getPlayer1().getCastle().getHealth() + gi.getPlayer1().getCastle().getWall().getHealth();

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

        if (gi.getPlayer1().getCastle().getHealth() <= 20 && possibleCards.contains(CardActions.TROJAN_HORSE)) {
            chosenCard = CardActions.TROJAN_HORSE;
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
        System.out.println("See if AI can recruit people");
        chosenCard = checkForPeople();

        if (chosenCard == 0) {
            System.out.println("Improve Economy Chosen");
            chosenCard = improveEconomy();
        }
        return chosenCard;
    }

    private int improveEconomy() {
        int card = 0;
        boolean attack = false;

        if (gi.getPlayer1().getCastle().getHealth() < 40 || gi.getPlayer1().getCastle().getHealth() > 70) {
            System.out.println("Attack Economy");
            card = attackEconomy();
            attack = true;
        } else {
            System.out.println("Build Economy");
            card = buildEconomy();
        }

        if (card == 0) {
            if (attack) {
                System.out.println("Attack Failed, On to Build");
                card = buildEconomy();
            } else {
                System.out.println("Build failed, on to attack");
                card = attackEconomy();
            }
        }
        System.out.println("Card After Improve Economy: " + card);
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
        } else if (possibleCards.contains(CardActions.RAID)) {
            chosenCard = CardActions.RAID;
        } else if (possibleCards.contains(CardActions.BLAST)) {
            chosenCard = CardActions.BLAST;
        } else if (possibleCards.contains(CardActions.JERICHO) && gi.getPlayer1().getCastle().getWall().getHealth() > 40) {
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
        } else if (possibleCards.contains(CardActions.RESERVE)) {
            chosenCard = CardActions.RESERVE;
        } else if (possibleCards.contains(CardActions.WALL)) {
            chosenCard = CardActions.WALL;
        } else if (possibleCards.contains(CardActions.BARRIER)) {
            chosenCard = CardActions.BARRIER;
        } else if (possibleCards.contains(CardActions.CREATE_STONES)) {
            chosenCard = CardActions.CREATE_STONES;
        } else if (possibleCards.contains(CardActions.CREATE_GEMS)) {
            chosenCard = CardActions.CREATE_GEMS;
        } else if (possibleCards.contains(CardActions.CREATE_WEAPONS)) {
            chosenCard = CardActions.CREATE_WEAPONS;
        } else if (possibleCards.contains(CardActions.HAT_TRICK)) {
            chosenCard = CardActions.HAT_TRICK;
        }
        System.out.println("Chosen Card in Build Eco: " + chosenCard);
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
        System.out.println("Choose The Card for ID " + card);
        for (Card c : gi.getPlayer2().getHand().getCardsInHand()) {
            if (c.isAvailable()) {
                if (c.getCardID() == card) {
                    theCard = c;
                }
            }
        }
        return theCard;
    }

    private Card findBestToDiscard() {
        Card toDiscard = null;
        boolean attackWin = false;
        boolean buildWin = false;
        boolean attackDeafeat = false;
        boolean buildDefeat = false;

        System.out.println("Needs to Discard");
        System.out.println("See if AI is Close to Attack Winning");
        if (gi.getPlayer1().getCastle().getWall().getHealth() + gi.getPlayer1().getCastle().getHealth() <= MAX_ATTACK || gi.getPlayer1().getCastle().getHealth() <= 20) { //20 for Trojan Horse Card
            attackWin = true;
        }
        System.out.println("See if AI is Close to  a Build Winning");
        if ((100 - gi.getPlayer2().getCastle().getHealth()) <= MAX_BUILD) {
            buildWin = true;
        }
        System.out.println("See if AI is Close to Attack Defeat");
        if (gi.getPlayer2().getCastle().getWall().getHealth() + gi.getPlayer2().getCastle().getHealth() <= MAX_ATTACK || gi.getPlayer2().getCastle().getHealth() <= 20) {//20 for Trojan Horse Card
            attackDeafeat = true;
        }
        System.out.println("See if AI is Close to Build Defeat");
        if ((100 - gi.getPlayer1().getCastle().getHealth()) <= MAX_BUILD) {
            buildDefeat = true;
        }

        System.out.println("Choose Best Basic Cards to Discard Based on Above Queries");
        if (attackDeafeat || buildWin) {
            for (Card card : gi.getPlayer2().getHand().getCardsInHand()) {
                if (card.getCardType() == Card.ATTACK) {
                    toDiscard = card;
                }
            }
        } else if (attackWin || buildDefeat) {
            for (Card card : gi.getPlayer2().getHand().getCardsInHand()) {
                if (card.getCardType() == Card.BUILD) {
                    toDiscard = card;
                }
            }
        } else {
            for (Card card : gi.getPlayer2().getHand().getCardsInHand()) {
                if (card.getCardType() == Card.MAGIC) {
                    toDiscard = card;
                }
            }
        }

        if (toDiscard == null) {
            System.out.println("Discard Logic Found No Good Card to Discard, Chose First Card Instead");
            toDiscard = gi.getPlayer2().getHand().getCardsInHand().get(0);
        }

        return toDiscard;
    }

    public boolean isDiscarding() {
        return discarding;
    }
}
