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

    private float loadTiming;

    private PopupButton playButton;
    private PopupButton settingsButton;

    public MenuWorld(Viewport cam) {
        super();
        super.setCam(cam);
        createButtons();
    }

    private void createButtons() {
        //Create the buttons!
        playButton = new PopupButton(Assets.playButtonUp, Assets.playButtonDown, MenuScreen.SCREEN_WIDTH/2-1.5f, 5, 3, 2);
        playButton.addIncomingAnimator(new MovementAnimator(playButton, -2, playButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        playButton.startIncomingAnimators();

        settingsButton = new PopupButton(Assets.settingsButtonUp, Assets.settingsButtonDown, MenuScreen.SCREEN_WIDTH/2 - 1.5f, 2, 3, 2);
        settingsButton.addIncomingAnimator(new MovementAnimator(settingsButton, -2, settingsButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
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
            }
        });
    }
}
