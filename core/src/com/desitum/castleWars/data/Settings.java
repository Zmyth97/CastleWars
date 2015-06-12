package com.desitum.castleWars.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public class Settings {
    public static boolean volumeOn = true;
    public static float VOLUME = 1;
    public static int highscore;

    public static int CARDS_DEALT = 6;
    public static float COMPUTER_DELAY = 3f;

    public static boolean WANTS_FLAME_CARDS = true;
    public static boolean WANTS_JAPANESE_CARDS = true;
    public static int ASSETS_TO_USE = 1;

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


    public static void getSound() {
        Preferences prefs = Gdx.app.getPreferences("settings");
        prefs.putBoolean("soundOn", volumeOn);
        prefs.flush();
        if(Settings.volumeOn == true)
        {
            VOLUME = 1;
            Assets.menuMusic.setVolume(VOLUME);
        }
        else
        {
            VOLUME = 0;

            Assets.menuMusic.setVolume(VOLUME);
        }
    }

    public static void load(){
        Preferences prefs = Gdx.app.getPreferences("settings");
        ASSETS_TO_USE = prefs.getInteger("assetsType", 1);
        WANTS_FLAME_CARDS = prefs.getBoolean("wantsFlame", true);
        WANTS_JAPANESE_CARDS = prefs.getBoolean("wantsJapanese", true);
        volumeOn = prefs.getBoolean("soundOn", true);
        getSound();
    }

    public static void setVolume(float volume){
        VOLUME = volume;
    }

    public static void saveScore(int score) {
        if (highscore > score){
            return;
        }
        highscore = score;
        Preferences prefs = Gdx.app.getPreferences("settings");
        prefs.putInteger("highscore", score);
        prefs.flush();
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


}
