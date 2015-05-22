package com.desitum.castleWars.world;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.libraries.animation.MovementAnimator;
import com.desitum.castleWars.libraries.interpolation.Interpolation;
import com.desitum.castleWars.libraries.menu.OnClickListener;
import com.desitum.castleWars.libraries.menu.PopupButton;
import com.desitum.castleWars.libraries.menu.PopupWidget;
import com.desitum.castleWars.libraries.world.KodyWorld;
import com.desitum.castleWars.screens.MenuScreen;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public class MenuWorld extends KodyWorld {

    private MenuInterface menuInterface;

    private PopupButton playButton;
    private PopupButton settingsButton;

    public MenuWorld(Viewport cam, MenuInterface mi) {
        super();
        super.setCam(cam);

        menuInterface = mi;

        createButtons();
        setupOnClickListeners();
    }

    private void createButtons() {
        //Create the buttons!
        playButton = new PopupButton(Assets.playButtonUp, Assets.playButtonDown, MenuScreen.SCREEN_WIDTH/2-15f, 50, 30, 20);
        playButton.addIncomingAnimator(new MovementAnimator(playButton, -20, playButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        playButton.startIncomingAnimators();

        settingsButton = new PopupButton(Assets.settingsButtonUp, Assets.settingsButtonDown, MenuScreen.SCREEN_WIDTH/2 - 15f, 20, 30, 20);
        settingsButton.addIncomingAnimator(new MovementAnimator(settingsButton, -20, settingsButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        settingsButton.startIncomingAnimators();

        //Add the buttons to the ArrayList
        this.addWidget(playButton);
        this.addWidget(settingsButton);
    }

    public void update(float delta) {
        super.update(delta);
    }

    private void setupOnClickListeners() {
        playButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                menuInterface.playGame();
            }
        });

        settingsButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                menuInterface.settings();
            }
        });
    }
}
