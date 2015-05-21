package com.desitum.castleWars.world;

import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.libraries.animation.MovementAnimator;
import com.desitum.castleWars.libraries.interpolation.Interpolation;
import com.desitum.castleWars.libraries.menu.PopupButton;
import com.desitum.castleWars.screens.MenuScreen;

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
        playButton = new PopupButton(Assets.playButtonUp, Assets.playButtonDown, MenuScreen.SCREEN_WIDTH/2-1.5f, 5, 3, 2);
        playButton.addIncomingAnimator(new MovementAnimator(playButton, -2, playButton.getY(), 0.5f, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true));
        playButton.startIncomingAnimators();
        //multiButton = new com.desitum.castleWars.objects.MenuButton(MainScreen.OTHER, 5.5f, 5.5f, Assets.menuButtonTexture);
        settingsButton = new PopupButton(Assets.settingsButtonUp, Assets.settingsButtonDown, MenuScreen.SCREEN_WIDTH/2 - 1.5f, 2, 3, 2);
        settingsButton.addIncomingAnimator(new MovementAnimator(settingsButton, -2, settingsButton.getY(), 0.5f, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true));
        settingsButton.startIncomingAnimators();
        //Add the buttons to the ArrayList
        menuButtons.add(playButton);
        menuButtons.add(settingsButton);

    }

    public void update(float delta) {
        for (PopupButton button: menuButtons) {
            button.update(delta);
        }
    }

    public ArrayList<PopupButton> getMenuButtons() {
        return this.menuButtons;
    }
}
