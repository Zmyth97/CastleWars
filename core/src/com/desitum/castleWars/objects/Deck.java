package com.desitum.castleWars.objects;

import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.data.CardActions;
import com.desitum.castleWars.data.Settings;
import com.desitum.castleWars.world.GameWorld;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Zmyth97 on 5/21/2015.
 */
public class Deck {

    private ArrayList<Card> cardList;

    public Deck() {
        cardList = new ArrayList<Card>();
        fillDeck();
    }

    private void fillDeck() {
        //BUILD CARDS
        for (int i = 0; i < Settings.REINFORCE_AMOUNT; i++) {
            cardList.add(new Card(Assets.buildCardReinforce, Card.BUILD, CardActions.REINFORCE, CardActions.REINFORCE_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.FORTIFY_AMOUNT; i++) {
            cardList.add(new Card(Assets.buildCardFortify, Card.BUILD, CardActions.FORTIFY, CardActions.FORTIFY_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.CASTLE_AMOUNT; i++) {
            cardList.add(new Card(Assets.buildCardCastle, Card.BUILD, CardActions.CASTLE, CardActions.CASTLE_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.BARRIER_AMOUNT; i++) {
            cardList.add(new Card(Assets.buildCardBarrier, Card.BUILD, CardActions.BARRIER, CardActions.BARRIER_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.WALL_AMOUNT; i++) {
            cardList.add(new Card(Assets.buildCardWall, Card.BUILD, CardActions.WALL, CardActions.WALL_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.GREATWALL_AMOUNT; i++) {
            cardList.add(new Card(Assets.buildCardGreatWall, Card.BUILD, CardActions.GREATWALL, CardActions.GREATWALL_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.ARCHITECT_AMOUNT; i++) {
            cardList.add(new Card(Assets.buildCardArchitect, Card.BUILD, CardActions.ARCHITECT, CardActions.ARCHITECT_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.RESERVE_AMOUNT; i++) {
            cardList.add(new Card(Assets.buildCardReserve, Card.BUILD, CardActions.RESERVE, CardActions.RESERVE_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.SABOTAGE_AMOUNT; i++) {
            cardList.add(new Card(Assets.buildCardSabotage, Card.BUILD, CardActions.SABOTAGE, CardActions.SABOTAGE_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.STRONGHOLD_AMOUNT; i++) {
            cardList.add(new Card(Assets.buildCardStronghold, Card.BUILD, CardActions.STRONGHOLD, CardActions.STRONGHOLD_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }

        //ATTACK CARDS
        for (int i = 0; i < Settings.RECRUITER_AMOUNT; i++) {
            cardList.add(new Card(Assets.attackCardRecruiter, Card.ATTACK, CardActions.RECRUITER, CardActions.RECRUITER_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.SPEARMAN_AMOUNT; i++) {
            cardList.add(new Card(Assets.attackCardSpearman, Card.ATTACK, CardActions.SPEARMAN, CardActions.SPEARMAN_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.RAM_AMOUNT; i++) {
            cardList.add(new Card(Assets.attackCardRam, Card.ATTACK, CardActions.RAM, CardActions.RAM_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.CATAPULT_AMOUNT; i++) {
            cardList.add(new Card(Assets.attackCardCatapult, Card.ATTACK, CardActions.CATAPULT, CardActions.CATAPULT_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.LEGION_AMOUNT; i++) {
            cardList.add(new Card(Assets.attackCardLegion, Card.ATTACK, CardActions.LEGION, CardActions.LEGION_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.TREBUCHET_AMOUNT; i++) {
            cardList.add(new Card(Assets.attackCardTrebuchet, Card.ATTACK, CardActions.TREBUCHET, CardActions.TREBUCHET_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.ASSASSIN_AMOUNT; i++) {
            cardList.add(new Card(Assets.cardBlank, Card.ATTACK, CardActions.ASSASSIN, CardActions.ASSASSIN_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.BURGLAR_AMOUNT; i++) {
            cardList.add(new Card(Assets.cardBlank, Card.ATTACK, CardActions.BURGLAR, CardActions.BURGLAR_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.THIEF_AMOUNT; i++) {
            cardList.add(new Card(Assets.cardBlank, Card.ATTACK, CardActions.THIEF, CardActions.THIEF_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.RAID_AMOUNT; i++) {
            cardList.add(new Card(Assets.cardBlank, Card.ATTACK, CardActions.RAID, CardActions.RAID_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }

        //MAGIC CARDS
        for (int i = 0; i < Settings.CREATE_STONES_AMOUNT; i++) {
            cardList.add(new Card(Assets.gemCardCreateStones, Card.MAGIC, CardActions.CREATE_STONES, CardActions.CREATE_STONES_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.CREATE_WEAPONS_AMOUNT; i++) {
            cardList.add(new Card(Assets.gemCardCreateWeapons, Card.MAGIC, CardActions.CREATE_WEAPONS, CardActions.CREATE_WEAPONS_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.CREATE_GEMS_AMOUNT; i++) {
            cardList.add(new Card(Assets.gemCardCreateGems, Card.MAGIC, CardActions.CREATE_GEMS, CardActions.CREATE_GEMS_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.DESTROY_STONES_AMOUNT; i++) {
            cardList.add(new Card(Assets.gemCardDestroyStones, Card.MAGIC, CardActions.DESTROY_STONES, CardActions.DESTROY_STONES_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.DESTROY_WEAPONS_AMOUNT; i++) {
            cardList.add(new Card(Assets.gemCardDestroyWeapons, Card.MAGIC, CardActions.DESTROY_WEAPONS, CardActions.DESTROY_WEAPONS_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.DESTROY_GEMS_AMOUNT; i++) {
            cardList.add(new Card(Assets.gemCardDestroyGems, Card.MAGIC, CardActions.DESTROY_GEMS, CardActions.DESTROY_GEMS_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.MAGE_AMOUNT; i++) {
            cardList.add(new Card(Assets.gemCardMage, Card.MAGIC, CardActions.MAGE, CardActions.MAGE_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.HAT_TRICK_AMOUNT; i++) {
            cardList.add(new Card(Assets.gemCardHatTrick, Card.MAGIC, CardActions.HAT_TRICK, CardActions.HAT_TRICK_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.LIGHTNING_STRIKE_AMOUNT; i++) {
            cardList.add(new Card(Assets.gemCardLightningStrike, Card.MAGIC, CardActions.LIGHTNING_STRIKE, CardActions.LIGHTNING_STRIKE_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.BLAST_AMOUNT; i++) {
            cardList.add(new Card(Assets.gemCardBlast, Card.MAGIC, CardActions.BLAST, CardActions.BLAST_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }

        //GOLDEN CARDS
        for (int i = 0; i < Settings.JERICHO_AMOUNT; i++) {
            cardList.add(new Card(Assets.cardBlank, Card.ATTACK, CardActions.JERICHO, CardActions.JERICHO_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.TROJAN_HORSE_AMOUNT; i++) {
            cardList.add(new Card(Assets.cardBlank, Card.ATTACK, CardActions.TROJAN_HORSE, CardActions.TROJAN_HORSE_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.BLACK_PLAGUE_AMOUNT; i++) {
            cardList.add(new Card(Assets.cardBlank, Card.MAGIC, CardActions.BLACK_PLAGUE, CardActions.BLACK_PLAGUE_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.MERLIN_AMOUNT; i++) {
            cardList.add(new Card(Assets.cardBlank, Card.MAGIC, CardActions.MERLIN, CardActions.MERLIN_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.DUPLICATE_AMOUNT; i++) {
            cardList.add(new Card(Assets.cardBlank, Card.BUILD, CardActions.DUPLICATE, CardActions.DUPLICATE_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
    }

    public Card drawCard() {
        Card lastCard = cardList.remove(cardList.size() - 1);
        Collections.shuffle(cardList);
        cardList.add(lastCard);
        Card drawCard = cardList.get(0);
        cardList.remove(0);
        return drawCard;
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public void addCard(Card card) {
        cardList.add(card);
    }
}
