package com.desitum.castleWars.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public class Settings {
    public static boolean volumeOn = true;
    public static float volume = 1;
    public static int highscore;

    //region CardAmounts
    public static int REINFORCE_AMOUNT = 2;
    public static int FORTIFY_AMOUNT = 2;
    public static int CASTLE_AMOUNT = 2;
    public static int BARRIER_AMOUNT = 2;
    public static int WALL_AMOUNT = 2;
    public static int GREATWALL_AMOUNT = 2;
    public static int ARCHITECT_AMOUNT = 2;
    public static int RESERVE_AMOUNT = 2;
    public static int SABOTAGE_AMOUNT = 2;
    public static int STRONGHOLD_AMOUNT = 2;

    public static int RECRUITER_AMOUNT = 2;
    public static int SPEARMAN_AMOUNT = 2;
    public static int RAM_AMOUNT = 2;
    public static int CATAPULT_AMOUNT = 2;
    public static int LEGION_AMOUNT = 2;
    public static int TREBUCHET_AMOUNT = 2;
    public static int ASSASSIN_AMOUNT = 2;
    public static int BURGLAR_AMOUNT = 2;
    public static int THIEF_AMOUNT = 2;
    public static int RAID_AMOUNT = 2;

    public static int CREATE_STONES_AMOUNT = 2;
    public static int CREATE_WEAPONS_AMOUNT = 2;
    public static int CREATE_GEMS_AMOUNT = 2;
    public static int DESTROY_STONES_AMOUNT = 2;
    public static int DESTROY_WEAPONS_AMOUNT = 2;
    public static int DESTROY_GEMS_AMOUNT = 2;
    public static int MAGE_AMOUNT = 2;
    public static int HAT_TRICK_AMOUNT = 2;
    public static int LIGHTNING_STRIKE_AMOUNT = 2;
    public static int BLAST_AMOUNT = 2;

    public static int JERICHO_AMOUNT = 2;
    public static int TROJAN_HORSE_AMOUNT = 2;
    public static int BLACK_PLAGUE_AMOUNT = 2;
    public static int MERLIN_AMOUNT = 2;
    //endregion

    public static void getSound() {
        Preferences prefs = Gdx.app.getPreferences("settings");
        prefs.putBoolean("soundOn", volumeOn);
        prefs.flush();
        if(Settings.volumeOn == true)
        {
            //Assets.menuMusic.setVolume(1);
            volume = 1;
        }
        else
        {
            //Assets.menuMusic.setVolume(0);
            volume = 0;
        }
    }

    public static void load(){
        Preferences prefs = Gdx.app.getPreferences("settings");
        volumeOn = prefs.getBoolean("soundOn", true);
        highscore = prefs.getInteger("highscore", 0);
        getSound();
    }

    public static void setVolume(float volume){
        //TODO need to actually do something
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
}
