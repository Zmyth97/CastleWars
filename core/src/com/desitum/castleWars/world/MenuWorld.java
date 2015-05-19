package com.desitum.castleWars.world;

import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.libraries.animation.MovementAnimator;
import com.desitum.castleWars.libraries.interpolation.Interpolation;
import com.desitum.castleWars.screens.MenuScreen;

import java.util.ArrayList;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public class MenuWorld {
    private ArrayList<com.desitum.castleWars.objects.MenuButton> menuButtons;

    private float loadTiming;

    private com.desitum.castleWars.objects.MenuButton playButton;
    //private com.desitum.castleWars.objects.MenuButton multiButton?;
    //TODO: Do you think we'd want to try and get multiplayer on it? Might be worth it, helps spread the game if people want their friends to install it to play with them.
    private com.desitum.castleWars.objects.MenuButton scoreButton;
    private com.desitum.castleWars.objects.MenuButton settingsButton;

    public MenuWorld() {
        menuButtons = new ArrayList<com.desitum.castleWars.objects.MenuButton>();
        createButtons();
    }

    private void createButtons() {
        //Create the buttons!
        playButton = new com.desitum.castleWars.objects.MenuButton(MenuScreen.PLAY, 3, 7.5f, Assets.playButtonUp);
        //multiButton = new com.desitum.castleWars.objects.MenuButton(MainScreen.OTHER, 5.5f, 5.5f, Assets.menuButtonTexture);
        scoreButton = new com.desitum.castleWars.objects.MenuButton(MenuScreen.SCORE, 3, 4f, Assets.exitButtonUp);
        settingsButton = new com.desitum.castleWars.objects.MenuButton(MenuScreen.SETTINGS, 3, .5f, Assets.settingsButtonUp);

        //Add the buttons to the ArrayList
        menuButtons.add(playButton);
        //menuButtons.add(multiButton);
        menuButtons.add(scoreButton);
        menuButtons.add(settingsButton);

    }

    public void update(float delta) {

    }

    public ArrayList<com.desitum.castleWars.objects.MenuButton> getMenuButtons() {
        return this.menuButtons;
    }
}
