package com.desitum.castleWars.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.data.Settings;
import com.desitum.castleWars.libraries.animation.MovementAnimator;
import com.desitum.castleWars.libraries.interpolation.Interpolation;
import com.desitum.castleWars.libraries.menu.OnClickListener;
import com.desitum.castleWars.libraries.menu.PopupButtonMaterial;
import com.desitum.castleWars.libraries.menu.PopupMenu;
import com.desitum.castleWars.libraries.menu.PopupSlider;
import com.desitum.castleWars.libraries.menu.PopupSliderListener;
import com.desitum.castleWars.libraries.menu.PopupTextLabel;
import com.desitum.castleWars.libraries.menu.PopupToggleButton;
import com.desitum.castleWars.libraries.menu.PopupWidget;
import com.desitum.castleWars.libraries.world.KodyWorld;
import com.desitum.castleWars.screens.MenuScreen;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public class MenuWorld extends KodyWorld {

    public static final float BUTTON_HEIGHT = 0.5f;
    private MenuInterface menuInterface;
    private PopupMenu popupMenu;
    private PopupButtonMaterial playButton;
    private PopupButtonMaterial leaderboardButton;
    private PopupButtonMaterial multiButton;
    private PopupButtonMaterial deckButton;
    private PopupButtonMaterial settingsButton;
    private PopupButtonMaterial storeButton;

    private PopupToggleButton originalAssetsToggle;
    private PopupToggleButton flameAssetsToggle;
    private PopupToggleButton japaneseAssetsToggle;

    public MenuWorld(Viewport cam, MenuInterface mi) {
        super();
        super.setCam(cam);

        menuInterface = mi;

        createButtons();
        setupOnClickListeners();

        //region Settings Popup Menu
        popupMenu = new PopupMenu(Assets.popupMenuBackground, 10, -130, 130, 80);
        MovementAnimator yAnimator = new MovementAnimator(popupMenu, -130, 10, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        popupMenu.addIncomingAnimator(yAnimator);
        MovementAnimator yAnimator2 = new MovementAnimator(popupMenu, 10, -130, 1, 0, Interpolation.ANTICIPATE_INTERPOLATOR, false, true);
        popupMenu.addOutgoingAnimator(yAnimator2);


        PopupButtonMaterial cancelButton = new PopupButtonMaterial(Assets.cancelButton, 5, 5, BUTTON_HEIGHT, 30, 10);
        cancelButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                Assets.buttonSound.play(Settings.VOLUME); //Please don't kill me, this is a nice place for it!
                popupMenu.startOutgoingAnimators();
            }
        });
        popupMenu.addPopupWidget(cancelButton);

        final PopupSlider volumeSlider = new PopupSlider(Assets.toggleButtonOff, Assets.toggleButtonOff, 5, 60, 120, 5, 3, 10);
        volumeSlider.setSliderListener(new PopupSliderListener() {
            @Override
            public void onChange(float pos) {
                Settings.VOLUME = pos;
            }
        });
        popupMenu.addPopupWidget(volumeSlider);

        PopupButtonMaterial okButton = new PopupButtonMaterial(Assets.okButton, 30, 5, BUTTON_HEIGHT, 30, 10);
        okButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                Settings.setVolume(volumeSlider.getPosition());
                Assets.buttonSound.play(Settings.VOLUME); //Please don't kill me, this is a nice place for it!
                popupMenu.startOutgoingAnimators();
            }
        });
        popupMenu.addPopupWidget(okButton);

        PopupTextLabel originalAssetsLabel = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, 12, 50, 25, 3, "Original Textures", BitmapFont.HAlignment.CENTER);
        popupMenu.addPopupWidget(originalAssetsLabel);
        originalAssetsToggle = new PopupToggleButton(Assets.toggleButtonOn, Assets.toggleButtonOff, 15, 25, 20, 20, true);
        originalAssetsToggle.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                originalAssetsToggle.turnOn();
                flameAssetsToggle.turnOff();
                japaneseAssetsToggle.turnOff();
                GameRenderer.ASSETS_TO_USE = 1;
            }
        });
        popupMenu.addPopupWidget(originalAssetsToggle);

        if(GameWorld.BOUGHT_FlAME_PACK) {
            PopupTextLabel flameAssetsLabel = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, 52, 50, 25, 3, "Flame Textures", BitmapFont.HAlignment.CENTER);
            popupMenu.addPopupWidget(flameAssetsLabel);
            flameAssetsToggle = new PopupToggleButton(Assets.toggleButtonOn, Assets.toggleButtonOff, 55, 25, 20, 20, false);
            flameAssetsToggle.setButtonListener(new OnClickListener() {
                @Override
                public void onClick(PopupWidget widget) {
                    flameAssetsToggle.turnOn();
                    originalAssetsToggle.turnOff();
                    japaneseAssetsToggle.turnOff();
                    GameRenderer.ASSETS_TO_USE = 2;
                }
            });
            popupMenu.addPopupWidget(flameAssetsToggle);
        }

        if(GameWorld.BOUGHT_JAPANESE_PACK) {
            PopupTextLabel japaneseAssetsLabel = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, 92, 50, 25, 3, "Japanese Textures", BitmapFont.HAlignment.CENTER);
            popupMenu.addPopupWidget(japaneseAssetsLabel);
            japaneseAssetsToggle = new PopupToggleButton(Assets.toggleButtonOn, Assets.toggleButtonOff, 95, 25, 20, 20, false);
            japaneseAssetsToggle.setButtonListener(new OnClickListener() {
                @Override
                public void onClick(PopupWidget widget) {
                    japaneseAssetsToggle.turnOn();
                    originalAssetsToggle.turnOff();
                    flameAssetsToggle.turnOff();
                    GameRenderer.ASSETS_TO_USE = 3;
                }
            });
            popupMenu.addPopupWidget(japaneseAssetsToggle);
        }

        this.addPopupMenu(popupMenu);
        //endregion
    }

    public void getMenuMove(){
        popupMenu.startIncomingAnimators();
    }

    private void createButtons() {
        //Create the buttons!
        playButton = new PopupButtonMaterial(Assets.playButton, ((MenuScreen.SCREEN_WIDTH/2)* .5f) - 15f, 38, BUTTON_HEIGHT, 30, 20);
        playButton.addIncomingAnimator(new MovementAnimator(playButton, -20, playButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        playButton.startIncomingAnimators();

        leaderboardButton = new PopupButtonMaterial(Assets.leaderboardButton,((MenuScreen.SCREEN_WIDTH/4) * 3)-15f, 38, BUTTON_HEIGHT, 30, 20);
        leaderboardButton.addIncomingAnimator(new MovementAnimator(leaderboardButton, -20, leaderboardButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        leaderboardButton.startIncomingAnimators();

        multiButton = new PopupButtonMaterial(Assets.multiButton,((MenuScreen.SCREEN_WIDTH/4) * 3) - 15f, 10, BUTTON_HEIGHT, 30, 20);
        multiButton.addIncomingAnimator(new MovementAnimator(multiButton, -20, multiButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        multiButton.startIncomingAnimators();

        deckButton = new PopupButtonMaterial(Assets.buildDeckButton, ((MenuScreen.SCREEN_WIDTH/2) * .5f) - 15f, 10, BUTTON_HEIGHT, 30, 20);
        deckButton.addIncomingAnimator(new MovementAnimator(deckButton, -20, deckButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        deckButton.startIncomingAnimators();

        settingsButton = new PopupButtonMaterial(Assets.settingsButtonRound, ((MenuScreen.SCREEN_WIDTH)) - 22f, MenuScreen.SCREEN_HEIGHT - 22f, BUTTON_HEIGHT, 20, 20);
        settingsButton.addIncomingAnimator(new MovementAnimator(settingsButton, -20, settingsButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        settingsButton.startIncomingAnimators();

        storeButton = new PopupButtonMaterial(Assets.storeButtonRound, 2, MenuScreen.SCREEN_HEIGHT - 22f, BUTTON_HEIGHT, 20, 20);
        storeButton.addIncomingAnimator(new MovementAnimator(storeButton, -20, storeButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        storeButton.startIncomingAnimators();

        //Add the buttons to the ArrayList
        this.addWidget(playButton);
        this.addWidget(leaderboardButton);
        this.addWidget(multiButton);
        this.addWidget(deckButton);
        this.addWidget(settingsButton);
        this.addWidget(storeButton);
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

        leaderboardButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                menuInterface.leaderboard();
            }
        });

        settingsButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                menuInterface.settings();
            }
        });

       multiButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                menuInterface.multiplayer();
            }
        });

        deckButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                menuInterface.buildDeck();
            }
        });

        storeButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                menuInterface.store();
            }
        });
    }
}
