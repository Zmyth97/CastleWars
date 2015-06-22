package com.desitum.castleWars.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.HashMap;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public class Settings {

    public static final float BUTTON_HEIGHT = 0.5f;

    public static final String FIRE_SKU = "flame_card_pack_id";
    public static final String JAPANESE_SKU = "japanese_card_pack_id";
    public static final String EXTRA_CARD_SLOT_1_ID = "extra_slot_1_id";
    public static final String EXTRA_CARD_SLOT_2_ID = "extra_slot_2_id";

    public static boolean volumeOn = true;
    public static float VOLUME = 1;
    public static int highscore;

    public static int CARDS_DEALT = 6;
    public static float COMPUTER_DELAY = 3f;

    public static boolean WANTS_FLAME_CARDS = true;
    public static boolean WANTS_JAPANESE_CARDS = true;
    public static int ASSETS_TO_USE = 1;

    public static boolean BOUGHT_FlAME_PACK = false;
    public static boolean BOUGHT_JAPANESE_PACK = false;
    public static boolean EXTRA_CARD_SLOT_1 = false;
    public static boolean EXTRA_CARD_SLOT_2 = false;

    //region CardAmounts
    public static int REINFORCE_AMOUNT = 3;
    public static int FORTIFY_AMOUNT = 3;
    public static int CASTLE_AMOUNT = 3;
    public static int BARRIER_AMOUNT = 3;
    public static int WALL_AMOUNT = 2;
    public static int GREATWALL_AMOUNT = 2;
    public static int ARCHITECT_AMOUNT = 2;
    public static int RESERVE_AMOUNT = 1;
    public static int SABOTAGE_AMOUNT = 1;
    public static int STRONGHOLD_AMOUNT = 1;

    public static int RECRUITER_AMOUNT = 2;
    public static int SPEARMAN_AMOUNT = 3;
    public static int RAM_AMOUNT = 3;
    public static int CATAPULT_AMOUNT = 3;
    public static int LEGION_AMOUNT = 2;
    public static int TREBUCHET_AMOUNT = 1;
    public static int ASSASSIN_AMOUNT = 1;
    public static int BURGLAR_AMOUNT = 1;
    public static int THIEF_AMOUNT = 1;
    public static int RAID_AMOUNT = 1;

    public static int CREATE_STONES_AMOUNT = 3;
    public static int CREATE_WEAPONS_AMOUNT = 3;
    public static int CREATE_GEMS_AMOUNT = 3;
    public static int DESTROY_STONES_AMOUNT = 2;
    public static int DESTROY_WEAPONS_AMOUNT = 2;
    public static int DESTROY_GEMS_AMOUNT = 2;
    public static int MAGE_AMOUNT = 2;
    public static int HAT_TRICK_AMOUNT = 3;
    public static int LIGHTNING_STRIKE_AMOUNT = 1;
    public static int BLAST_AMOUNT = 1;

    public static int JERICHO_AMOUNT = 1;
    public static int TROJAN_HORSE_AMOUNT = 1;
    public static int BLACK_PLAGUE_AMOUNT = 1;
    public static int MERLIN_AMOUNT = 1;
    public static int DUPLICATE_AMOUNT = 1;
    //endregion

    private static HashMap<Integer, Integer> cardHashMap = new HashMap<Integer, Integer>();

    public static void setSound(float volume) {
        Preferences prefs = Gdx.app.getPreferences("settings");
        prefs.putFloat("sound", volume);
        prefs.flush();
        Assets.menuMusic.setVolume(VOLUME);
    }

    public static void load() {
        Preferences prefs = Gdx.app.getPreferences("settings");
        ASSETS_TO_USE = prefs.getInteger("assetsType", 1);
        WANTS_FLAME_CARDS = prefs.getBoolean("wantsFlame", true);
        WANTS_JAPANESE_CARDS = prefs.getBoolean("wantsJapanese", true);
        EXTRA_CARD_SLOT_1 = prefs.getBoolean("extraSlot1", false);
        EXTRA_CARD_SLOT_2 = prefs.getBoolean("extraSlot2", false);
        BOUGHT_FlAME_PACK = prefs.getBoolean("boughtFlame", false);
        BOUGHT_JAPANESE_PACK = prefs.getBoolean("boughtJapanese", false);
        VOLUME = prefs.getFloat("sound", 0.5f);

        loadDefaultString();

        String cards = prefs.getString("cardAmounts");
        String[] cardsA = cards.split(";");
        loadCardsFromStringArray(cardsA);
    }

    private static void loadCardsFromStringArray(String[] cardsA) {
        for (String cardB: cardsA) {
            String[] cardInfoString = cardB.split(":");
            int[] cardInfo = new int[]{Integer.parseInt(cardInfoString[0]), Integer.parseInt(cardInfoString[1])};
            cardHashMap.put(cardInfo[0], cardInfo[1]);
        }
    }

    public static void setVolume(float volume) {
        VOLUME = volume;
    }

    public static void savePackSettings(int assetsType, boolean wantsFlame, boolean wantsJapanese) {
        ASSETS_TO_USE = assetsType;
        WANTS_FLAME_CARDS = wantsFlame;
        WANTS_JAPANESE_CARDS = wantsJapanese;
        Preferences prefs = Gdx.app.getPreferences("settings");
        prefs.putInteger("assetsType", assetsType);
        prefs.putBoolean("wantsFlame", wantsFlame);
        prefs.putBoolean("wantsJapanese", wantsJapanese);
        prefs.flush();
    }

    public static void saveExtraSlots(boolean extraSlot1, boolean extraSlot2){
        EXTRA_CARD_SLOT_1 = extraSlot1;
        EXTRA_CARD_SLOT_2 = extraSlot2;
        Preferences prefs = Gdx.app.getPreferences("settings");
        prefs.putBoolean("extraSlot1", extraSlot1);
        prefs.putBoolean("extraSlot2", extraSlot2);
    }

    public static void saveCardPacks(boolean boughtFlame, boolean boughtJapanese){
        BOUGHT_FlAME_PACK = boughtFlame;
        BOUGHT_JAPANESE_PACK = boughtJapanese;
        Preferences prefs = Gdx.app.getPreferences("settings");
        prefs.putBoolean("boughtFlame", boughtFlame);
        prefs.putBoolean("boughtJapanese", boughtJapanese);
    }

    public static void saveDeck() {
        String saveString = "";
        boolean isFirst = true;
        for (Integer integer: cardHashMap.keySet()) {
            if (isFirst) {
                saveString += integer + ":" + cardHashMap.get(integer);
            } else {
                saveString += ";" + integer + ":" + cardHashMap.get(integer);
            }
        }
        Preferences prefs = Gdx.app.getPreferences("settings");
        prefs.putString("cardAmounts", saveString);
        prefs.flush();
    }

    public static String loadDefaultString() {
        String returnString = "";
        //TODO for you amigo, fix all of these amounts
        returnString += CardActions.BARRIER                   + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.WALL                      + ":" + WALL_AMOUNT                 + ";";
        returnString += CardActions.GREATWALL                 + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.REINFORCE                 + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.FORTIFY                   + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.CASTLE                    + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.ARCHITECT                 + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.RESERVE                   + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.SABOTAGE                  + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.STRONGHOLD                + ":" + BARRIER_AMOUNT              + ";";

        returnString += CardActions.RECRUITER                 + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.SPEARMAN                  + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.RAM                       + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.CATAPULT                  + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.LEGION                    + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.TREBUCHET                 + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.ASSASSIN                  + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.BURGLAR                   + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.THIEF                     + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.RAID                      + ":" + BARRIER_AMOUNT              + ";";

        returnString += CardActions.CREATE_STONES             + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.CREATE_WEAPONS            + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.CREATE_GEMS               + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.DESTROY_STONES            + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.DESTROY_WEAPONS           + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.DESTROY_GEMS              + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.MAGE                      + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.HAT_TRICK                 + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.LIGHTNING_STRIKE          + ":" + BARRIER_AMOUNT              + ";";
        returnString += CardActions.BLAST                     + ":" + BARRIER_AMOUNT              + ";";

        //TODO do we want all of the extra packs in here?
        //TODO if so just follow the pattern I made
        return returnString;
    }

    public static int getCardAmount(int cardType) {
        return cardHashMap.get(cardType);
    }
}
