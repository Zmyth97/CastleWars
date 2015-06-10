package com.desitum.castleWars;


import com.badlogic.gdx.Game;

import com.badlogic.gdx.Screen;


public class CastleWars extends Game {
    GooglePlayServicesInterface googlePlay;

    public static final int RAIDER = 0; //1 Win
    public static final int PILLAGER = 1; //5 Wins
    public static final int TEMPLAR = 2; //10 Wins
    public static final int CRUSADER = 3; //50 Wins
    public static final int DESTROYER = 4; //100 Wins

    public static final int BEGINNER_RAIDER = 4; //1 Multi Win
    public static final int NOVICE_RAIDER= 4; //3 Multi Wins
    public static final int ADVANCED_RAIDER = 4; //5 Multi Wins
    public static final int EXPERT_RAIDER = 4; //10 Multi Wins
    public static final int MASTER_RAIDER = 4; //25 Multi Wins

    public static final int DO_IT_YOURSELF = 4; //Build First Deck
    public static final int SILENT_BUT_DEADLY = 4; //First Time Muting the Game (lol)
    public static final int WORLD_CONQUEST = 4; //First Multiplayer Game
    public static final int PILLAGED = 4; //Be Beaten by the AI on Normal
    public static final int FLAMING_NINJA = 4; //Buy Both Fire and Japanese Card Packs

    public static final int FEUDAL_JAPAN = 4; //Buy the Japanese Card Pack
    public static final int DEATH_BY_FIRE = 4; //First Time Using The Dragon
    public static final int NIGHT_KILLER = 4; //Use the Ninja Card 20 Times
    public static final int RICE_FARMER = 4; //Use Rice Paddy 20 Times
    public static final int JAPANESE_MASTER = 4; //Use a Japanese Card to Win a Game (Build or Attack)

    public static final int BURN_IT_ALL = 4; //Buy the Fire Card Pack
    public static final int THE_SKY_IS_FALLING = 4; //First Time Using Meteors
    public static final int REBORN = 4; //Use the Phoenix Card 20 Times
    public static final int LET_IT_FLOW = 4; //Use Lava Flow 20 Times (hehehehe)
    public static final int ELEMENTALIST = 4; //Use a Fire Card to Win a Game (Build or Attack)

    public static final int BUILDER = 4; //First Build Win
    public static final int ATTACKER = 4; //First Attack Win
    public static final int WATCHING_CLOUDS = 4; //A total of 1,000 Clouds Float By While Playing
    public static final int TO_THE_POINT = 4; //Win a game using only 15 Cards (too hard?)
    public static final int CARD_MASTER = 4; //Use a total of 10,000 Cards (too much?)





    public CastleWars(GooglePlayServicesInterface googlePlay) {
        this.googlePlay = googlePlay;
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
    //Dev Console
        //All of it!
    //Achievements
        //Since this game is good for this and could be more fun with it, can we add lots of achievemnts? :D (Like Learn to Fly 2 kind o thing)
        //If above = true, then we probably need a place for that along with the leaderboards :P
    //Multiplayer
        //Start it :P
    //In App Purchases
        //Probably after adding Multi, but add these
    //Sound & Music
        //Are we doing music?
        //Are we going to search for sounds, or just make some ourselves at my house XD
        //Add them to game!
    //Build your Deck
        //Fix it so it works haha



}
