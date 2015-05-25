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

    //CardActions
        //Fix Cards Moving Around to Show a Computer Hand Too? (Like original Castle Wars, maybe change the background color a bit between the two or something? idk)
        //Slow down card movements
        //Make sure the Draw Pile shows the Backs of Cards
        //Fix the Card Fade/Restore to be right and not backwards
    //GUI
        //Castle Texture & Animations, Particle Effects?
        //Wall Texture & Animations, Particle Effects?
        //Background (Is the one we have to keep, or just till you like another one better?)
        //Finish Card Images
        //Change Button Images? (You said you didn't like them earlier, so are we changing those?)
        //MenuScreen Additions (Animations, Store Button, Leaderboard Button in Corner, etc)?
        //Finish the Side Menu/Labels to show information
        //Maybe something at the top showing the left side is you, right side is the computer or player2?
        //Animation in background? Parralax Mapping, Clouds Moving, etc?
        //Whatever else I'm missing xD
    //Computer AI
        //Just Test it a Lot, and I'll fine tune it till its actually semi-hard to beat
        //Are we going to have an option of difficulty levels? Beginner and Expert? Easy, Medium, Hard?
    //Dev Console
        //All of it!
    //Achievements
        //Since this game is good for this and could be more fun with it, can we add lots of achievemnts? :D (Like Learn to Fly 2 kind o thing)
        //If above = true, then we probably need a place for that along with the leaderboards :P
    //Multiplayer
        //Start it :P
    //In App Purchases
        //Probably after adding Multi, but add these
    //Random Events
        //Were we still doing this?
    //Build your Deck
        //Not too hard since you added the amounts to settings, but still need to form the screen/gui for it
    //Textures/Cards for The Skin/Card Packs
        //This once all the above is done


}
