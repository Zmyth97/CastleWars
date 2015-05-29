package com.desitum.castleWars.world;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.data.Settings;
import com.desitum.castleWars.libraries.animation.MovementAnimator;
import com.desitum.castleWars.libraries.interpolation.Interpolation;
import com.desitum.castleWars.libraries.menu.OnClickListener;
import com.desitum.castleWars.libraries.menu.PopupButton;
import com.desitum.castleWars.libraries.menu.PopupButtonMaterial;
import com.desitum.castleWars.libraries.menu.PopupMenu;
import com.desitum.castleWars.libraries.menu.PopupSlider;
import com.desitum.castleWars.libraries.menu.PopupSliderListener;
import com.desitum.castleWars.libraries.menu.PopupWidget;
import com.desitum.castleWars.libraries.world.KodyWorld;
import com.desitum.castleWars.screens.MenuScreen;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public class MenuWorld extends KodyWorld {

    private MenuInterface menuInterface;
    private PopupMenu popupMenu;
    private PopupButton playButton;
    private PopupButton settingsButton;
    private PopupButton multiButton;
    private PopupButton deckButton;

    public MenuWorld(Viewport cam, MenuInterface mi) {
        super();
        super.setCam(cam);

        menuInterface = mi;

        createButtons();
        setupOnClickListeners();

        //region Settings Popup Menu
        popupMenu = new PopupMenu(Assets.textFieldBackground, 10, -130, 130, 80);
        MovementAnimator yAnimator = new MovementAnimator(popupMenu, -130, 10, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        popupMenu.addIncomingAnimator(yAnimator);
        MovementAnimator yAnimator2 = new MovementAnimator(popupMenu, 10, -130, 1, 0, Interpolation.ANTICIPATE_INTERPOLATOR, false, true);
        popupMenu.addOutgoingAnimator(yAnimator2);

        PopupButton cancelButton = new PopupButton(Assets.cancelButtonUp, Assets.cancelButtonDown, 5, 5, 57.5f, 15);
        cancelButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                popupMenu.moveOut();
            }
        });
        popupMenu.addPopupWidget(cancelButton);

        final PopupSlider volumeSlider = new PopupSlider(Assets.textFieldBackground, Assets.textFieldBackground, 5, 60, 120, 5, 3, 10);
        volumeSlider.setSliderListener(new PopupSliderListener() {
            @Override
            public void onChange(float pos) {
            }
        });
        popupMenu.addPopupWidget(volumeSlider);

        PopupButton okButton = new PopupButton(Assets.okButtonUp, Assets.okButtonDown, 67.5f, 5, 57.5f, 15);
        okButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                Settings.setVolume(volumeSlider.getPosition());
                popupMenu.moveOut();
            }
        });
        popupMenu.addPopupWidget(okButton);
        this.addPopupMenu(popupMenu);
        //endregion

        PopupButtonMaterial material = new PopupButtonMaterial(Assets.cardBlank, 0, 5, 5, 25, 15);
        this.addWidget(material);
    }

    public void getMenuMove(){
        popupMenu.moveIn();
    }

    private void createButtons() {
        //Create the buttons!
        playButton = new PopupButton(Assets.playButtonUp, Assets.playButtonDown, ((MenuScreen.SCREEN_WIDTH/2)* .5f) - 15f, 50, 30, 20);
        playButton.addIncomingAnimator(new MovementAnimator(playButton, -20, playButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        playButton.startIncomingAnimators();

        settingsButton = new PopupButton(Assets.settingsButtonUp, Assets.settingsButtonDown, ((MenuScreen.SCREEN_WIDTH/4) * 3)-15f, 50, 30, 20);
        settingsButton.addIncomingAnimator(new MovementAnimator(settingsButton, -20, settingsButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        settingsButton.startIncomingAnimators();

        multiButton = new PopupButton(Assets.okButtonUp, Assets.okButtonDown, ((MenuScreen.SCREEN_WIDTH/4) * 3) - 15f, 20, 30, 20);
        multiButton.addIncomingAnimator(new MovementAnimator(multiButton, -20, multiButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        multiButton.startIncomingAnimators();

        deckButton = new PopupButton(Assets.cancelButtonUp, Assets.cancelButtonDown, ((MenuScreen.SCREEN_WIDTH/2) * .5f) - 15f, 20, 30, 20);
        deckButton.addIncomingAnimator(new MovementAnimator(deckButton, -20, deckButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        deckButton.startIncomingAnimators();

        //Add the buttons to the ArrayList
        this.addWidget(playButton);
        this.addWidget(settingsButton);
        this.addWidget(multiButton);
        this.addWidget(deckButton);
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

       multiButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                menuInterface.multiplayer();
            }
        });

        deckButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                menuInterface.buildDeck();
            }
        });
    }
}
