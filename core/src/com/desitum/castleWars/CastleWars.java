package com.desitum.castleWars;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;


public class CastleWars extends Game {
    GooglePlayServicesInterface googlePlay;

    public static final int RAIDER = 0; //1 Win #
    public static final int PILLAGER = 1; //5 Wins #
    public static final int TEMPLAR = 2; //10 Wins #
    public static final int CRUSADER = 3; //50 Wins #
    public static final int DESTROYER = 4; //100 Wins #

    public static final int BEGINNER_RAIDER = 5; //1 Multi Win #
    public static final int NOVICE_RAIDER= 6; //3 Multi Wins #
    public static final int ADVANCED_RAIDER = 7; //5 Multi Wins #
    public static final int EXPERT_RAIDER = 8; //10 Multi Wins #
    public static final int MASTER_RAIDER = 9; //25 Multi Wins #

    public static final int DO_IT_YOURSELF = 10; //Build First Deck
    public static final int SILENT_BUT_DEADLY = 11; //First Time Muting the Game (lol)
    public static final int WORLD_CONQUEST = 12; //First Multiplayer Game
    public static final int PILLAGED = 13; //Be Beaten by the AI on Normal #
    public static final int FLAMING_NINJA = 14; //Buy Both Fire and Japanese Card Packs

    public static final int FEUDAL_JAPAN = 15; //Buy the Japanese Card Pack
    public static final int DEATH_BY_FIRE = 16; //First Time Using The Dragon #
    public static final int NIGHT_KILLER = 17; //Use the Ninja Card 20 Times #
    public static final int HONORABLE_SACRIFICE = 18; //Use Seppuku 20 Times #
    public static final int JAPANESE_MASTER = 19; //Use a Japanese Card to Win a Game (Build or Attack) #

    public static final int BURN_IT_ALL = 20; //Buy the Fire Card Pack
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





    public CastleWars(GooglePlayServicesInterface googlePlay) {
        this.googlePlay = googlePlay;
    }

    public void submitWin() {

    }

    public void submitBuildWin() {
        submitWin();
        //Needs a way to submit build alone //Why? ~Zack
    }

    public void submitCloud() {

    }

    public void submitAttackWin() {
        submitWin();
        //Needs way to submit attack alone //Why? ~Zack
    }

    @Override
    public void create () {
        Screen splashScreen = new com.desitum.castleWars.screens.SplashScreen(googlePlay, this);
        this.setScreen(splashScreen);
    }

    @Override
    public void render () {
        super.render();
    }

    @Override
    public void dispose () {
        super.dispose();

        com.desitum.castleWars.data.Assets.dispose();

        getScreen().dispose();
    }

    //TODO: CheckList!!!
    //Misc
        //Disposals
        //Memory Leak Stuff
        //Make sure everything works (like achievements) if you aren't signed in, that it doesn't cause a crash or anything haha
    //Build your Deck
        //Fix it so it works haha
    //Dev Console
        //All of it!
    //Achievements
        //Finish Adding Achievements that aren't done (They don't have a # next to them)
    //Multiplayer
        //Start it :P
    //In App Purchases
        //Add Card Packs
        //Add Code to Change Assets/Cards if They Have Bought Them
        //Add Options to Settings Menu To Change The Above Stuff



}
