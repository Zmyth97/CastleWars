package com.desitum.castleWars;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.desitum.castleWars.screens.SplashScreen;


public class CastleWars extends Game {
    public static final int RAIDER = 0; //1 Win #
    public static final int PILLAGER = 1; //5 Wins #
    public static final int TEMPLAR = 2; //10 Wins #
    public static final int CRUSADER = 3; //50 Wins #
    public static final int DESTROYER = 4; //100 Wins #
    public static final int BEGINNER_RAIDER = 5; //1 Multi Win #
    public static final int NOVICE_RAIDER = 6; //3 Multi Wins #
    public static final int ADVANCED_RAIDER = 7; //5 Multi Wins #
    public static final int EXPERT_RAIDER = 8; //10 Multi Wins #
    public static final int MASTER_RAIDER = 9; //25 Multi Wins #
    public static final int DO_IT_YOURSELF = 10; //Build First Deck
    public static final int SILENT_BUT_DEADLY = 11; //First Time Muting the Game (lol)
    public static final int WORLD_CONQUEST = 12; //First Multiplayer Game
    public static final int PILLAGED = 13; //Be Beaten by the AI on Normal #
    public static final int FLAMING_NINJA = 14; //Buy Both Fire and Japanese Card Packs #
    public static final int FEUDAL_JAPAN = 15; //Buy the Japanese Card Pack #
    public static final int DEATH_BY_FIRE = 16; //First Time Using The Dragon #
    public static final int NIGHT_KILLER = 17; //Use the Ninja Card 20 Times #
    public static final int HONORABLE_SACRIFICE = 18; //Use Seppuku 20 Times #
    public static final int JAPANESE_MASTER = 19; //Use a Japanese Card to Win a Game (Build or Attack) #
    public static final int BURN_IT_ALL = 20; //Buy the Fire Card Pack #
    public static final int DEATH_FROM_ABOVE = 21; //First Time Using Meteors #
    public static final int REBORN = 22; //Use the Phoenix Card 20 Times #
    public static final int LET_IT_FLOW = 23; //Use Lava Flow 20 Times (hehehehe) #
    public static final int ELEMENTALIST = 24; //Use a Fire Card to Win a Game (Build or Attack) #
    public static final int BUILDER = 25; //First Build Win #
    public static final int ATTACKER = 26; //First Attack Win #
    public static final int CLOUD_WATCHER = 27; //A total of 1,000 Clouds Float By While Playing #
    public static final int DIDNT_SEE_THAT_COMING = 28; //Win a game using the Trojan Horse Card #
    public static final int CASTLE_MASTER = 29; //Play a Total of 200 Games #
    public static final int AN_ERA_BEGINS = 31; //First Game #
    GooglePlayServicesInterface googlePlay;


    public CastleWars(GooglePlayServicesInterface googlePlay) {
        this.googlePlay = googlePlay;
    }

    @Override
    public void create() {
        Screen splashScreen = new SplashScreen(googlePlay, this);
        this.setScreen(splashScreen);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();

        com.desitum.castleWars.data.Assets.dispose();

        getScreen().dispose();
    }

    //TODO: CheckList!!!
    //Disposals
    //Memory Leak Stuff
    // Make sure everything works (like achievements) if you aren't signed in, that it doesn't cause a crash or anything haha
    //See if In App Purchases Work
    //Save Purchases to Settings In Case of No Internet Connection (Hash?)
    //Fix Popup Scroll Area
    //Arrange Build Screen Nicely
    //Add Saving/Loading Decks
    //Make Promo Graphic, Upload Finished APK (Add a Video?)
    //Finish Adding Achievements that aren't done (They don't have a # next to them)
    //Add Networking Code and Implement
    //Test Networking

    //General Testing! Cards work, buttons, loading and unloading screens, saving stuff, purchases, signed in, signed out, achievements, etc.


}
