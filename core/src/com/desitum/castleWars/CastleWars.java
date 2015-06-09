package com.desitum.castleWars;


import com.badlogic.gdx.Game;

import com.badlogic.gdx.Screen;


public class CastleWars extends Game {
    GooglePlayServicesInterface googlePlay;


    public static final int ACHIEVEMENT_ONE = 0;
    public static final int ACHIEVEMENT_TWO = 1;
    public static final int ACHIEVEMENT_THREE = 2;
    public static final int ACHIEVEMENT_FOUR = 3;
    public static final int ACHIEVEMENT_FIVE = 4;



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
    //CardActions
        //Computer Hand?
    //Computer AI
        //Just Test it a Lot, and I'll fine tune it till its actually semi-hard to beat
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
        //Are we doing music? If so we should do that before we leave C-Tech probably (Garange Band?)
        //Are we going to search for sounds, or just make some ourselves at my house XD
        //Add them to game!
    //Build your Deck
        //Fix it so it works haha



}
