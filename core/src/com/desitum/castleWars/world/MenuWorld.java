package com.desitum.castleWars.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.desitum.castleWars.GooglePlayServicesInterface;
import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.data.Settings;
import com.desitum.castleWars.libraries.animation.MovementAnimator;
import com.desitum.castleWars.libraries.interpolation.Interpolation;
import com.desitum.castleWars.libraries.menu.OnClickListener;
import com.desitum.castleWars.libraries.menu.PopupButtonMaterial;
import com.desitum.castleWars.libraries.menu.PopupImage;
import com.desitum.castleWars.libraries.menu.PopupMenu;
import com.desitum.castleWars.libraries.menu.PopupScrollArea;
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

    private static final String FIRE_SKU = "flame_card_pack_id";
    private static final String JAPANESE_SKU = "japanese_card_pack_id";
    private static final String EXTRA_CARD_SLOT_1_ID = "extra_slot_1_id";
    private static final String EXTRA_CARD_SLOT_2_ID = "extra_slot_2_id";

    private MenuInterface menuInterface;
    private GooglePlayServicesInterface gpgs;
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

    private PopupToggleButton flameCardsToggle;
    private PopupToggleButton japaneseCardsToggle;

    private int settingsToggle;

    private PopupMenu storeMenu;

    private PopupImage flameAd;
    private PopupImage japanAd;
    private PopupImage slotAd;

    private int currentItem = 1; //Current Item Showing in Store Menu
    //USED FOR SETTINGS IN THIS THINGY
    private boolean wantsFlame = Settings.WANTS_FLAME_CARDS;
    private boolean wantsJapanese = Settings.WANTS_JAPANESE_CARDS;
    private int assetsType = Settings.ASSETS_TO_USE;


    public MenuWorld(Viewport cam, MenuInterface mi) {
        super();
        super.setCam(cam);

        menuInterface = mi;

        createButtons();
        setupOnClickListeners();
        createStore();

        //region Settings Popup Menu

        popupMenu = new PopupMenu(Assets.popupMenuBackground, 10, -130, 130, 80);
        MovementAnimator yAnimator = new MovementAnimator(popupMenu, -130, 10, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        popupMenu.addIncomingAnimator(yAnimator);
        MovementAnimator yAnimator2 = new MovementAnimator(popupMenu, 10, -130, 1, 0, Interpolation.ANTICIPATE_INTERPOLATOR, false, true);
        popupMenu.addOutgoingAnimator(yAnimator2);

        PopupTextLabel settingsLabel = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, 40, 65, 50, 10, "Settings", BitmapFont.HAlignment.CENTER);
        popupMenu.addPopupWidget(settingsLabel);

        final PopupSlider volumeSlider = new PopupSlider(Assets.toggleButtonOff, Assets.toggleButtonOff, Settings.VOLUME, 5, 60, 120, 5, 3, 10);
        volumeSlider.setSliderListener(new PopupSliderListener() {
            @Override
            public void onChange(float pos) {
                Settings.VOLUME = pos;
                Settings.setSound();
            }
        });
        popupMenu.addPopupWidget(volumeSlider);

        PopupTextLabel originalAssetsLabel = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, 12, 55, 25, 3, "Original Textures", BitmapFont.HAlignment.CENTER);
        popupMenu.addPopupWidget(originalAssetsLabel);
        originalAssetsToggle = new PopupToggleButton(Assets.toggleButtonOn, Assets.toggleButtonOff, 16, 34, 16, 16, false);
        originalAssetsToggle.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                originalAssetsToggle.turnOn();
                if (flameAssetsToggle != null) flameAssetsToggle.turnOff();
                if (japaneseAssetsToggle != null) japaneseAssetsToggle.turnOff();
                assetsType = 1;
            }
        });
        popupMenu.addPopupWidget(originalAssetsToggle);

        if (Settings.BOUGHT_FlAME_PACK) {
            PopupTextLabel flameAssetsLabel = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, 54, 55, 25, 3, "Flame Textures", BitmapFont.HAlignment.CENTER);
            popupMenu.addPopupWidget(flameAssetsLabel);
            flameAssetsToggle = new PopupToggleButton(Assets.toggleButtonOn, Assets.toggleButtonOff, 59, 34, 16, 16, false);
            flameAssetsToggle.setButtonListener(new OnClickListener() {
                @Override
                public void onClick(PopupWidget widget) {
                    Assets.buttonSound.play(Settings.VOLUME);
                    flameAssetsToggle.turnOn();
                    originalAssetsToggle.turnOff();
                    if (japaneseAssetsToggle != null) japaneseAssetsToggle.turnOff();
                    assetsType = 2;
                }
            });
            popupMenu.addPopupWidget(flameAssetsToggle);

            PopupTextLabel flameCardsLabel = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, 12, 27, 25, 3, "Flame Cards In Game", BitmapFont.HAlignment.CENTER);
            popupMenu.addPopupWidget(flameCardsLabel);
            flameCardsToggle = new PopupToggleButton(Assets.toggleButtonOn, Assets.toggleButtonOff, 16, 7, 16, 16, false);
            if (wantsFlame) flameCardsToggle.turnOn(); //Appearance For Load Settings
            flameCardsToggle.setButtonListener(new OnClickListener() {
                @Override
                public void onClick(PopupWidget widget) {
                    Assets.buttonSound.play(Settings.VOLUME);
                    if (flameCardsToggle.isOn()) {
                        wantsFlame = true;
                    } else {
                        wantsFlame = false;
                    }
                }
            });
            popupMenu.addPopupWidget(flameCardsToggle);
        }

        if (Settings.BOUGHT_JAPANESE_PACK) {
            PopupTextLabel japaneseAssetsLabel = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, 96, 55, 25, 3, "Japanese Textures", BitmapFont.HAlignment.CENTER);
            popupMenu.addPopupWidget(japaneseAssetsLabel);

            japaneseAssetsToggle = new PopupToggleButton(Assets.toggleButtonOn, Assets.toggleButtonOff, 101, 34, 16, 16, false);
            japaneseAssetsToggle.setButtonListener(new OnClickListener() {
                @Override
                public void onClick(PopupWidget widget) {
                    Assets.buttonSound.play(Settings.VOLUME);
                    japaneseAssetsToggle.turnOn();
                    originalAssetsToggle.turnOff();
                    if (flameAssetsToggle != null) flameAssetsToggle.turnOff();
                    assetsType = 3;
                }
            });
            popupMenu.addPopupWidget(japaneseAssetsToggle);

            PopupTextLabel japaneseCardsLabel = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, 95, 27, 26, 3, "Japanese Cards In Game", BitmapFont.HAlignment.CENTER);
            popupMenu.addPopupWidget(japaneseCardsLabel);
            japaneseCardsToggle = new PopupToggleButton(Assets.toggleButtonOn, Assets.toggleButtonOff, 101, 7, 16, 16, false);
            if (wantsJapanese) japaneseCardsToggle.turnOn(); //Appearance For Load Settings
            japaneseCardsToggle.setButtonListener(new OnClickListener() {
                @Override
                public void onClick(PopupWidget widget) {
                    Assets.buttonSound.play(Settings.VOLUME);
                    if (japaneseCardsToggle.isOn()) {
                        wantsJapanese = true;
                    }
                    if (japaneseCardsToggle.isOn() == false) {
                        wantsJapanese = false;
                    }
                }
            });
            popupMenu.addPopupWidget(japaneseCardsToggle);
        }

        if (assetsType == 1) { //These are for appearances on load based off loaded settings
            originalAssetsToggle.turnOn();
        } else if (assetsType == 2 && Settings.BOUGHT_FlAME_PACK) {
            flameAssetsToggle.turnOn();
        } else if (Settings.BOUGHT_JAPANESE_PACK) {
            japaneseAssetsToggle.turnOn();
        }

        //Quality Checkers (Should Never be needed since if you have bought them you should always have them, but causes crash if done wrong, so just in case)
        if (assetsType == 2 && !Settings.BOUGHT_FlAME_PACK) {
            originalAssetsToggle.turnOn();
            assetsType = 1;
        } else if (assetsType == 3 && !Settings.BOUGHT_JAPANESE_PACK) {
            originalAssetsToggle.turnOn();
            assetsType = 1;
        }
        if (wantsFlame && !Settings.BOUGHT_FlAME_PACK) {
            wantsFlame = false;
        }
        if (wantsJapanese && !Settings.BOUGHT_JAPANESE_PACK) {
            wantsJapanese = false;
        }

        PopupButtonMaterial okButton = new PopupButtonMaterial(Assets.okButton, 45, 7, BUTTON_HEIGHT, 40, 15);
        okButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                Settings.setVolume(volumeSlider.getPosition());
                Settings.savePackSettings(assetsType, wantsFlame, wantsJapanese);
                Assets.buttonSound.play(Settings.VOLUME);
                settingsToggle--;
                popupMenu.moveOut();
            }
        });
        popupMenu.addPopupWidget(okButton);

        this.addPopupMenu(popupMenu);
        //endregion
    }

    public void getMenuMove() {
        popupMenu.moveIn();
    }

    public void getStoreMove() {
        storeMenu.moveIn();
    }

    private void createButtons() {
        //Create the buttons!
        playButton = new PopupButtonMaterial(Assets.playButton, ((MenuScreen.SCREEN_WIDTH / 2) * .35f) - 15f, 38, BUTTON_HEIGHT, 60, 20);
        playButton.addIncomingAnimator(new MovementAnimator(playButton, -20, playButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        playButton.moveIn();

        leaderboardButton = new PopupButtonMaterial(Assets.leaderboardButton, ((MenuScreen.SCREEN_WIDTH / 4) * 2.5f) - 15f, 38, BUTTON_HEIGHT, 60, 20);
        leaderboardButton.addIncomingAnimator(new MovementAnimator(leaderboardButton, -20, leaderboardButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        leaderboardButton.moveIn();

        multiButton = new PopupButtonMaterial(Assets.multiButton, ((MenuScreen.SCREEN_WIDTH / 4) * 2.5f) - 15f, 10, BUTTON_HEIGHT, 60, 20);
        multiButton.addIncomingAnimator(new MovementAnimator(multiButton, -20, multiButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        multiButton.moveIn();

        deckButton = new PopupButtonMaterial(Assets.buildDeckButton, ((MenuScreen.SCREEN_WIDTH / 2) * .35f) - 15f, 10, BUTTON_HEIGHT, 60, 20);
        deckButton.addIncomingAnimator(new MovementAnimator(deckButton, -20, deckButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        deckButton.moveIn();

        settingsButton = new PopupButtonMaterial(Assets.settingsButtonRound, ((MenuScreen.SCREEN_WIDTH)) - 22f, MenuScreen.SCREEN_HEIGHT - 22f, BUTTON_HEIGHT, 20, 20);
        settingsButton.addIncomingAnimator(new MovementAnimator(settingsButton, -20, settingsButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        settingsButton.moveIn();

        storeButton = new PopupButtonMaterial(Assets.storeButtonRound, 2, MenuScreen.SCREEN_HEIGHT - 22f, BUTTON_HEIGHT, 20, 20);
        storeButton.addIncomingAnimator(new MovementAnimator(storeButton, -20, storeButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        storeButton.moveIn();

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
                if (settingsToggle == 0) {
                    settingsToggle++;
                    menuInterface.settings();
                } else {
                    settingsToggle--;
                    popupMenu.moveOut();
                }
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

    private void createStore() {
        storeMenu = new PopupMenu(Assets.blur, 0, 0, MenuScreen.SCREEN_WIDTH, MenuScreen.SCREEN_HEIGHT);
        storeMenu.addFadeInAnimator(1, 0);
        storeMenu.addFadeOutAnimator(1, 0);

        final float AD_WIDTH = 45;
        float AD_HEIGHT = 60;
        float AD_X = MenuScreen.SCREEN_WIDTH / 2 - AD_WIDTH / 2;
        float AD_Y = MenuScreen.SCREEN_HEIGHT / 2 - 15;

        final PopupScrollArea storeScroll = new PopupScrollArea(Assets.invisible, 0, AD_Y, MenuScreen.SCREEN_WIDTH, AD_HEIGHT, MenuScreen.SCREEN_WIDTH, AD_HEIGHT, PopupScrollArea.HORIZONTAL, 3, MenuScreen.SCREEN_WIDTH, AD_WIDTH);

        flameAd = new PopupImage(Assets.flameAd, Assets.invisible, 0, 0, AD_WIDTH, AD_HEIGHT, false); //item 1
        japanAd = new PopupImage(Assets.japaneseAd, Assets.invisible, 0, 0, AD_WIDTH, AD_HEIGHT, false); //item 2
        slotAd = new PopupImage(Assets.extraSlotAd, Assets.invisible, 0, 0, AD_WIDTH, AD_HEIGHT, false); //item 3

        storeScroll.addPopupWidget(flameAd);
        storeScroll.addPopupWidget(japanAd);
        storeScroll.addPopupWidget(slotAd);
        storeMenu.addPopupWidget(storeScroll);

        PopupButtonMaterial buyButton = new PopupButtonMaterial(Assets.buyButton, MenuScreen.SCREEN_WIDTH / 2 - 20, MenuScreen.SCREEN_HEIGHT / 6, BUTTON_HEIGHT, 40, 15);
        buyButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                if (currentItem == 1) {
                    menuInterface.buyItem(FIRE_SKU);
                } else if (currentItem == 2) {
                    menuInterface.buyItem(JAPANESE_SKU);
                } else if (currentItem == 3) {
                    //Need to Add Second Card Slot Thingy
                    menuInterface.buyItem(EXTRA_CARD_SLOT_1_ID);
                }
            }
        });
        storeMenu.addPopupWidget(buyButton);

        PopupButtonMaterial leftButton = new PopupButtonMaterial(Assets.leftArrow, 2, MenuScreen.SCREEN_HEIGHT / 2 - 10, BUTTON_HEIGHT, 20, 20);
        leftButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                if (currentItem == 1) {
                    storeScroll.goToWidget(2);
                    currentItem = 2;
                } else if (currentItem == 2) {
                    storeScroll.goToWidget(3);
                    currentItem = 3;
                }
            }
        });
        storeMenu.addPopupWidget(leftButton);

        PopupButtonMaterial rightButton = new PopupButtonMaterial(Assets.rightArrow, MenuScreen.SCREEN_WIDTH - 22, MenuScreen.SCREEN_HEIGHT / 2 - 10, BUTTON_HEIGHT, 20, 20);
        rightButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                if (currentItem == 3) {
                    storeScroll.goToWidget(2);
                    currentItem = 2;
                } else if (currentItem == 2) {
                    storeScroll.goToWidget(1);
                    currentItem = 1;
                }
            }
        });
        storeMenu.addPopupWidget(rightButton);


        PopupButtonMaterial okButton = new PopupButtonMaterial(Assets.okButtonRound, 2, 2, BUTTON_HEIGHT, 20, 20);
        okButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                storeMenu.moveOut();
            }
        });
        storeMenu.addPopupWidget(okButton);

        this.addPopupMenu(storeMenu);
    }
}
