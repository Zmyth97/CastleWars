package com.desitum.castleWars.world;

import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.libraries.menu.PopupButton;

import java.util.ArrayList;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public class MenuWorld {
    private ArrayList<PopupButton> menuButtons;

    private float loadTiming;

    private PopupButton playButton;
    private PopupButton scoreButton;
    private PopupButton settingsButton;

    public MenuWorld() {
        menuButtons = new ArrayList<PopupButton>();
        createButtons();
    }

    private void createButtons() {
        //Create the buttons!
        playButton = new PopupButton(Assets.playButtonUp, Assets.playButtonDown, 1, 5, 3, 2);
        //multiButton = new com.desitum.castleWars.objects.MenuButton(MainScreen.OTHER, 5.5f, 5.5f, Assets.menuButtonTexture);
        settingsButton = new PopupButton(Assets.settingsButtonUp, Assets.settingsButtonDown, 1, 2, 3, 2);

        //Add the buttons to the ArrayList
        menuButtons.add(playButton);
        menuButtons.add(settingsButton);

    }

    public void update(float delta) {

    }

    public ArrayList<PopupButton> getMenuButtons() {
        return this.menuButtons;
    }
}
