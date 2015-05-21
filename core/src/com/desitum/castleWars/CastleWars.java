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

    //CheckList!!!//

    //Basics//
    //CardActions
    //GUI
        //GameScreen Layout
        //Deck Animations/Layout
        //Castle Texture & Animations/Layout
        //Particle Effects for Castle and Cards
        //Card Texture & Animations/Layout for Cards
        //Background
        //Card Images
        //MenuScreen Additions (Multi Button, Build Deck, Settings, Game Difficulty, Store for Purchases, etc)
    //Computer AI
    //Dev Console
    //Achievements

    //Extra//
    //Multiplayer
    //In App Purchases
    //Random Events
    //Random Cards
    //Build your Deck
    //Textures for Skin Packs
    //More Cards for Packs


}
