package com.desitum.castleWars.objects;

import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.data.CardActions;
import com.desitum.castleWars.data.Settings;
import com.desitum.castleWars.packs.FlamePack;
import com.desitum.castleWars.packs.JapanesePack;
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
        if (Settings.BOUGHT_FlAME_PACK && Settings.WANTS_FLAME_CARDS) {
            addFlameCards();
        }
        if (Settings.BOUGHT_JAPANESE_PACK && Settings.WANTS_JAPANESE_CARDS) {
            addJapaneseCards();
        }
    }

    private void fillDeck() {
        //BUILD CARDS
        for (int i = 0; i < Settings.getCardAmount(CardActions.REINFORCE); i++) { // TODO look at this line! Settings.getCardAmount vs Settings.REINFORCE_AMOUNT
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
            cardList.add(new Card(Assets.attackCardAssassin, Card.ATTACK, CardActions.ASSASSIN, CardActions.ASSASSIN_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.BURGLAR_AMOUNT; i++) {
            cardList.add(new Card(Assets.attackCardBurglar, Card.ATTACK, CardActions.BURGLAR, CardActions.BURGLAR_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.THIEF_AMOUNT; i++) {
            cardList.add(new Card(Assets.attackCardThief, Card.ATTACK, CardActions.THIEF, CardActions.THIEF_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.RAID_AMOUNT; i++) {
            cardList.add(new Card(Assets.attackCardRaid, Card.ATTACK, CardActions.RAID, CardActions.RAID_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
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
            cardList.add(new Card(Assets.goldCardJericho, Card.ATTACK, CardActions.JERICHO, CardActions.JERICHO_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.TROJAN_HORSE_AMOUNT; i++) {
            cardList.add(new Card(Assets.goldCardTrojanHorse, Card.ATTACK, CardActions.TROJAN_HORSE, CardActions.TROJAN_HORSE_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.BLACK_PLAGUE_AMOUNT; i++) {
            cardList.add(new Card(Assets.goldCardBlackPlague, Card.MAGIC, CardActions.BLACK_PLAGUE, CardActions.BLACK_PLAGUE_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.MERLIN_AMOUNT; i++) {
            cardList.add(new Card(Assets.goldCardMerlin, Card.MAGIC, CardActions.MERLIN, CardActions.MERLIN_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < Settings.DUPLICATE_AMOUNT; i++) {
            cardList.add(new Card(Assets.goldCardDuplicate, Card.BUILD, CardActions.DUPLICATE, CardActions.DUPLICATE_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
    }

    private void addFlameCards() {
        //BUILD CARDS
        for (int i = 0; i < FlamePack.WALL_OF_FIRE_AMOUNT; i++) {
            cardList.add(new Card(Assets.flameBuildCardWallOfFire, Card.BUILD, FlamePack.WALL_OF_FIRE, FlamePack.WALL_OF_FIRE_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < FlamePack.CAMPFIRE_AMOUNT; i++) {
            cardList.add(new Card(Assets.flameBuildCardCampfire, Card.BUILD, FlamePack.CAMPFIRE, FlamePack.CAMPFIRE_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < FlamePack.FORGE_AMOUNT; i++) {
            cardList.add(new Card(Assets.flameBuildCardForge, Card.BUILD, FlamePack.FORGE, FlamePack.FORGE_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < FlamePack.BOILING_OIL_AMOUNT; i++) {
            cardList.add(new Card(Assets.flameBuildCardBoilingOil, Card.BUILD, FlamePack.BOILING_OIL, FlamePack.BOILING_OIL_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < FlamePack.BONFIRE_AMOUNT; i++) {
            cardList.add(new Card(Assets.flameBuildCardBonfire, Card.BUILD, FlamePack.BONFIRE, FlamePack.BONFIRE_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }

        //ATTACK CARDS
        for (int i = 0; i < FlamePack.FIRE_AMOUNT; i++) {
            cardList.add(new Card(Assets.flameAttackCardFire, Card.ATTACK, FlamePack.FIRE, FlamePack.FIRE_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < FlamePack.FIRE_ARROWS_AMOUNT; i++) {
            cardList.add(new Card(Assets.flameAttackCardFireArrows, Card.ATTACK, FlamePack.FIRE_ARROWS, FlamePack.FIRE_ARROWS_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < FlamePack.FLAMING_AXE_AMOUNT; i++) {
            cardList.add(new Card(Assets.flameAttackCardFlamingAxe, Card.ATTACK, FlamePack.FLAMING_AXE, FlamePack.FLAMING_AXE_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < FlamePack.FLAMING_SHOT_AMOUNT; i++) {
            cardList.add(new Card(Assets.flameAttackCardFlamingShot, Card.ATTACK, FlamePack.FLAMING_SHOT, FlamePack.FLAMING_SHOT_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < FlamePack.FLAME_LEGION_AMOUNT; i++) {
            cardList.add(new Card(Assets.flameAttackCardFlameLegion, Card.ATTACK, FlamePack.FLAME_LEGION, FlamePack.FLAME_LEGION_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }

        //MAGIC CARDS
        for (int i = 0; i < FlamePack.FIRE_SHAMAN_AMOUNT; i++) {
            cardList.add(new Card(Assets.flameGemCardFireShaman, Card.MAGIC, FlamePack.FIRE_SHAMAN, FlamePack.FIRE_SHAMAN_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < FlamePack.LAVA_FLOW_AMOUNT; i++) {
            cardList.add(new Card(Assets.flameGemCardLavaFlow, Card.MAGIC, FlamePack.LAVA_FLOW, FlamePack.LAVA_FLOW_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < FlamePack.COAL_AMOUNT; i++) {
            cardList.add(new Card(Assets.flameGemCardCoal, Card.MAGIC, FlamePack.COAL, FlamePack.COAL_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < FlamePack.BLACKSMITH_AMOUNT; i++) {
            cardList.add(new Card(Assets.flameGemCardBlacksmith, Card.MAGIC, FlamePack.BLACKSMITH, FlamePack.BLACKSMITH_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < FlamePack.FIREBALL_AMOUNT; i++) {
            cardList.add(new Card(Assets.flameGemCardFireball, Card.MAGIC, FlamePack.FIREBALL, FlamePack.FIREBALL_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }

        //GOLDEN CARDS
        for (int i = 0; i < FlamePack.PHOENIX_AMOUNT; i++) {
            cardList.add(new Card(Assets.flameGoldCardPhoenix, Card.ATTACK, FlamePack.PHEONIX, FlamePack.PHEONIX_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < FlamePack.METEORS_AMOUNT; i++) {
            cardList.add(new Card(Assets.flameGoldCardMeteors, Card.MAGIC, FlamePack.METEORS, FlamePack.METEORS_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < FlamePack.INFERNO_AMOUNT; i++) {
            cardList.add(new Card(Assets.flameGoldCardInferno, Card.MAGIC, FlamePack.INFERNO, FlamePack.INFERNO_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
    }

    private void addJapaneseCards() {
        //BUILD CARDS
        for (int i = 0; i < JapanesePack.STOCKADE_AMOUNT; i++) {
            cardList.add(new Card(Assets.japaneseBuildCardStockade, Card.BUILD, JapanesePack.STOCKADE, JapanesePack.STOCKADE_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < JapanesePack.FORTRESS_AMOUNT; i++) {
            cardList.add(new Card(Assets.japaneseBuildCardFortress, Card.BUILD, JapanesePack.FORTRESS, JapanesePack.FORTRESS_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < JapanesePack.MONASTERY_AMOUNT; i++) {
            cardList.add(new Card(Assets.japaneseBuildCardMonastery, Card.BUILD, JapanesePack.MONASTERY, JapanesePack.MONASTERY_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < JapanesePack.RAMPART_AMOUNT; i++) {
            cardList.add(new Card(Assets.japaneseBuildCardRampart, Card.BUILD, JapanesePack.RAMPART, JapanesePack.RAMPART_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < JapanesePack.CITADEL_AMOUNT; i++) {
            cardList.add(new Card(Assets.japaneseBuildCardCitadel, Card.BUILD, JapanesePack.CITADEL, JapanesePack.CITADEL_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }

        //ATTACK CARDS
        for (int i = 0; i < JapanesePack.ASHIGARU_AMOUNT; i++) {
            cardList.add(new Card(Assets.japaneseAttackCardAshigaru, Card.ATTACK, JapanesePack.ASHIGARU, JapanesePack.ASHIGARU_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < JapanesePack.SHURIKEN_AMOUNT; i++) {
            cardList.add(new Card(Assets.japaneseAttackCardShuriken, Card.ATTACK, JapanesePack.SHURIKEN, JapanesePack.SHURIKEN_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < JapanesePack.KATANA_AMOUNT; i++) {
            cardList.add(new Card(Assets.japaneseAttackCardKatana, Card.ATTACK, JapanesePack.KATANA, JapanesePack.KATANA_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < JapanesePack.SAMURAI_AMOUNT; i++) {
            cardList.add(new Card(Assets.japaneseAttackCardSamurai, Card.ATTACK, JapanesePack.SAMURAI, JapanesePack.SAMURAI_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < JapanesePack.DOJO_AMOUNT; i++) {
            cardList.add(new Card(Assets.japaneseAttackCardDojo, Card.ATTACK, JapanesePack.DOJO, JapanesePack.DOJO_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }

        //MAGIC CARDS
        for (int i = 0; i < JapanesePack.SHRINE_AMOUNT; i++) {
            cardList.add(new Card(Assets.japaneseGemCardShrine, Card.MAGIC, JapanesePack.SHRINE, JapanesePack.SHRINE_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < JapanesePack.QUARRY_AMOUNT; i++) {
            cardList.add(new Card(Assets.japaneseGemCardQuarry, Card.MAGIC, JapanesePack.QUARRY, JapanesePack.QUARRY_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < JapanesePack.TEMPLE_AMOUNT; i++) {
            cardList.add(new Card(Assets.japaneseGemCardTemple, Card.MAGIC, JapanesePack.TEMPLE, JapanesePack.TEMPLE_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < JapanesePack.RICE_PADDY_AMOUNT; i++) {
            cardList.add(new Card(Assets.japaneseGemCardRicePaddy, Card.MAGIC, JapanesePack.RICE_PADDY, JapanesePack.RICE_PADDY_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < JapanesePack.SEPPUKU_AMOUNT; i++) {
            cardList.add(new Card(Assets.japaneseGemCardSeppuku, Card.MAGIC, JapanesePack.SEPPUKU, JapanesePack.SEPPUKU_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }

        //GOLDEN CARDS
        for (int i = 0; i < JapanesePack.NINJA_AMOUNT; i++) {
            cardList.add(new Card(Assets.japaneseGoldCardNinja, Card.ATTACK, JapanesePack.NINJA, JapanesePack.NINJA_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < JapanesePack.DRAGON_AMOUNT; i++) {
            cardList.add(new Card(Assets.japaneseGoldCardDragon, Card.MAGIC, JapanesePack.DRAGON, JapanesePack.DRAGON_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
        for (int i = 0; i < JapanesePack.SHOGUN_AMOUNT; i++) {
            cardList.add(new Card(Assets.japaneseGoldCardShogun, Card.MAGIC, JapanesePack.SHOGUN, JapanesePack.SHOGUN_COST, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y));
        }
    }

    public Card drawCard(boolean isComputer) {
        Card lastCard = cardList.remove(cardList.size() - 1);
        Card secondToLastCard = cardList.remove(cardList.size() - 1);
        Collections.shuffle(cardList);
        cardList.add(secondToLastCard);
        cardList.add(lastCard);
        Card cardDrawn = cardList.get(0);
        cardList.remove(0);

        if (isComputer && cardDrawn.getCardID() > 399) {
            System.out.println("Comp Drawing");
            while (cardDrawn.getCardID() > 399) {
                cardList.add(0, cardDrawn);
                int randomCard = (int) (Math.random() * (cardList.size() - 5)); //I did it this way instead of the first way because shuffling it messed up the last card in the pile and etc. (Bugs)
                cardDrawn = cardList.get(randomCard);
                cardList.remove(randomCard);
            }
        }
        return cardDrawn;
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public void addCard(Card card) {
        cardList.add(card);
    }
}
