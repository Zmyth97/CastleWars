package com.desitum.castleWars.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.desitum.castleWars.CastleWars;
import com.desitum.castleWars.GooglePlayServicesInterface;
import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.data.Settings;
import com.desitum.castleWars.libraries.animation.MovementAnimator;
import com.desitum.castleWars.libraries.interpolation.Interpolation;
import com.desitum.castleWars.libraries.ui.Image;
import com.desitum.castleWars.libraries.ui.Layout;
import com.desitum.castleWars.libraries.ui.OnClickListener;
import com.desitum.castleWars.libraries.ui.ButtonMaterial;
import com.desitum.castleWars.libraries.ui.ScrollArea;
import com.desitum.castleWars.libraries.ui.Slider;
import com.desitum.castleWars.libraries.ui.SliderListener;
import com.desitum.castleWars.libraries.ui.TextLabel;
import com.desitum.castleWars.libraries.ui.ToggleButton;
import com.desitum.castleWars.libraries.ui.Widget;
import com.desitum.castleWars.libraries.world.KodyWorld;
import com.desitum.castleWars.screens.MenuScreen;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public class MenuWorld extends KodyWorld {

    private MenuInterface menuInterface;
    private GooglePlayServicesInterface gpgs;
    private Layout popupLayout;
    private ButtonMaterial playButton;
    private ButtonMaterial achievementButton;
    private ButtonMaterial deckButton;
    private ButtonMaterial settingsButton;
    private ButtonMaterial storeButton;

    private ToggleButton originalAssetsToggle;
    private ToggleButton flameAssetsToggle;
    private ToggleButton japaneseAssetsToggle;

    private ToggleButton flameCardsToggle;
    private ToggleButton japaneseCardsToggle;

    private ScrollArea storeScroll;

    private int settingsToggle;

    private Layout storeLayout;

    private Image flameAd;
    private Image japanAd;
    private Image slotAd;

    private int currentItem = 0; //Current Item Showing in Store Layout
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
        createPopupMenu();
    }

    private void createPopupMenu() {
        //region Settings Popup Layout

        Layout myLayout = new Layout(Assets.popupMenuBackground, 0, 0, 100, 100, getCam());
        myLayout.addPopupWidget(new Image(Assets.attackCardAssassin, Assets.invisible, 0, 0, 100, 100, false));
        addWidget(myLayout);

        popupLayout = new Layout(Assets.popupMenuBackground, 10, -130, 130, 80, getCam());
        MovementAnimator yAnimator = new MovementAnimator(popupLayout, -130, 10, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        popupLayout.addIncomingAnimator(yAnimator);
        MovementAnimator yAnimator2 = new MovementAnimator(popupLayout, 10, -130, 1, 0, Interpolation.ANTICIPATE_INTERPOLATOR, false, true);
        popupLayout.addOutgoingAnimator(yAnimator2);

        TextLabel settingsLabel = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, 40, 65, 50, 10, "Settings", BitmapFont.HAlignment.CENTER);
        popupLayout.addPopupWidget(settingsLabel);

        final Slider volumeSlider = new Slider(Assets.toggleButtonOff, Assets.toggleButtonOff, Settings.VOLUME, 5, 60, 120, 5, 3, 10);
        volumeSlider.setSliderListener(new SliderListener() {
            @Override
            public void onChange(float pos) {
                Settings.VOLUME = pos;
                Settings.setSound(pos);
                if (pos == 0) {
                    gpgs.unlockAchievement(CastleWars.SILENT_BUT_DEADLY);
                }
            }
        });
        popupLayout.addPopupWidget(volumeSlider);

        TextLabel originalAssetsLabel = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, 12, 55, 25, 3, "Original Textures", BitmapFont.HAlignment.CENTER);
        popupLayout.addPopupWidget(originalAssetsLabel);
        originalAssetsToggle = new ToggleButton(Assets.toggleButtonOn, Assets.toggleButtonOff, 16, 34, 16, 16, false);
        originalAssetsToggle.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(Widget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                originalAssetsToggle.turnOn();
                if (flameAssetsToggle != null) flameAssetsToggle.turnOff();
                if (japaneseAssetsToggle != null) japaneseAssetsToggle.turnOff();
                assetsType = 1;
            }
        });
        popupLayout.addPopupWidget(originalAssetsToggle);

        if (Settings.BOUGHT_FlAME_PACK) {
            TextLabel flameAssetsLabel = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, 54, 55, 25, 3, "Flame Textures", BitmapFont.HAlignment.CENTER);
            popupLayout.addPopupWidget(flameAssetsLabel);
            flameAssetsToggle = new ToggleButton(Assets.toggleButtonOn, Assets.toggleButtonOff, 59, 34, 16, 16, false);
            flameAssetsToggle.setButtonListener(new OnClickListener() {
                @Override
                public void onClick(Widget widget) {
                    Assets.buttonSound.play(Settings.VOLUME);
                    flameAssetsToggle.turnOn();
                    originalAssetsToggle.turnOff();
                    if (japaneseAssetsToggle != null) japaneseAssetsToggle.turnOff();
                    assetsType = 2;
                }
            });
            popupLayout.addPopupWidget(flameAssetsToggle);

            TextLabel flameCardsLabel = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, 12, 27, 25, 3, "Flame Cards In Game", BitmapFont.HAlignment.CENTER);
            popupLayout.addPopupWidget(flameCardsLabel);
            flameCardsToggle = new ToggleButton(Assets.toggleButtonOn, Assets.toggleButtonOff, 16, 7, 16, 16, false);
            if (wantsFlame) flameCardsToggle.turnOn(); //Appearance For Load Settings
            flameCardsToggle.setButtonListener(new OnClickListener() {
                @Override
                public void onClick(Widget widget) {
                    Assets.buttonSound.play(Settings.VOLUME);
                    if (flameCardsToggle.isOn()) {
                        wantsFlame = true;
                    } else {
                        wantsFlame = false;
                    }
                }
            });
            popupLayout.addPopupWidget(flameCardsToggle);
        }

        if (Settings.BOUGHT_JAPANESE_PACK) {
            TextLabel japaneseAssetsLabel = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, 96, 55, 25, 3, "Japanese Textures", BitmapFont.HAlignment.CENTER);
            popupLayout.addPopupWidget(japaneseAssetsLabel);

            japaneseAssetsToggle = new ToggleButton(Assets.toggleButtonOn, Assets.toggleButtonOff, 101, 34, 16, 16, false);
            japaneseAssetsToggle.setButtonListener(new OnClickListener() {
                @Override
                public void onClick(Widget widget) {
                    Assets.buttonSound.play(Settings.VOLUME);
                    japaneseAssetsToggle.turnOn();
                    originalAssetsToggle.turnOff();
                    if (flameAssetsToggle != null) flameAssetsToggle.turnOff();
                    assetsType = 3;
                }
            });
            popupLayout.addPopupWidget(japaneseAssetsToggle);

            TextLabel japaneseCardsLabel = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, 95, 27, 26, 3, "Japanese Cards In Game", BitmapFont.HAlignment.CENTER);
            popupLayout.addPopupWidget(japaneseCardsLabel);
            japaneseCardsToggle = new ToggleButton(Assets.toggleButtonOn, Assets.toggleButtonOff, 101, 7, 16, 16, false);
            if (wantsJapanese) japaneseCardsToggle.turnOn(); //Appearance For Load Settings
            japaneseCardsToggle.setButtonListener(new OnClickListener() {
                @Override
                public void onClick(Widget widget) {
                    Assets.buttonSound.play(Settings.VOLUME);
                    if (japaneseCardsToggle.isOn()) {
                        wantsJapanese = true;
                    }
                    if (!japaneseCardsToggle.isOn()) {
                        wantsJapanese = false;
                    }
                }
            });
            popupLayout.addPopupWidget(japaneseCardsToggle);
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

        ButtonMaterial okButton = new ButtonMaterial(Assets.okButton, 45, 10, Settings.BUTTON_HEIGHT, 40, 20);
        okButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(Widget widget) {
                Settings.setVolume(volumeSlider.getPosition());
                Settings.savePackSettings(assetsType, wantsFlame, wantsJapanese);
                Assets.buttonSound.play(Settings.VOLUME);
                settingsToggle--;
                popupLayout.moveOut();
            }
        });
        popupLayout.addPopupWidget(okButton);

        this.addPopupMenu(popupLayout);
        //endregion
    }

    public void getMenuMove() {
        popupLayout.moveIn();
    }

    public void getStoreMove() {
        storeLayout.moveIn();
    }

    private void createButtons() {
        //Create the buttons!
        playButton = new ButtonMaterial(Assets.playButton, ((MenuScreen.SCREEN_WIDTH / 2) * .35f) - 5f, 38, Settings.BUTTON_HEIGHT, 40, 20);
        playButton.addIncomingAnimator(new MovementAnimator(playButton, -20, playButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        playButton.moveIn();

        achievementButton = new ButtonMaterial(Assets.leaderboardButton, ((MenuScreen.SCREEN_WIDTH / 4) * 2.5f) - 5f, 38, Settings.BUTTON_HEIGHT, 40, 20);
        achievementButton.addIncomingAnimator(new MovementAnimator(achievementButton, -20, achievementButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        achievementButton.moveIn();

        storeButton = new ButtonMaterial(Assets.storeButton, ((MenuScreen.SCREEN_WIDTH / 4) * 2.5f) - 5f, 10, Settings.BUTTON_HEIGHT, 40, 20);
        storeButton.addIncomingAnimator(new MovementAnimator(storeButton, -20, storeButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        storeButton.moveIn();

        deckButton = new ButtonMaterial(Assets.buildDeckButton, ((MenuScreen.SCREEN_WIDTH / 2) * .35f) - 5f, 10, Settings.BUTTON_HEIGHT, 40, 20);
        deckButton.addIncomingAnimator(new MovementAnimator(deckButton, -20, deckButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        deckButton.moveIn();

        settingsButton = new ButtonMaterial(Assets.settingsButtonRound, ((MenuScreen.SCREEN_WIDTH)) - 22f, MenuScreen.SCREEN_HEIGHT - 22f, Settings.BUTTON_HEIGHT, 20, 20);
        settingsButton.addIncomingAnimator(new MovementAnimator(settingsButton, -20, settingsButton.getY(), 0.5f, 0, Interpolation.OVERSHOOT_INTERPOLATOR, false, true));
        settingsButton.moveIn();

        //Add the buttons to the ArrayList
        this.addWidget(playButton);
        this.addWidget(achievementButton);
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
            public void onClick(Widget widget) {
                menuInterface.playGame();
            }
        });

        achievementButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(Widget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                menuInterface.achievements();
            }
        });

        settingsButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(Widget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                if (settingsToggle == 0) {
                    settingsToggle++;
                    menuInterface.settings();
                } else {
                    settingsToggle--;
                    popupLayout.moveOut();
                }
            }
        });

        deckButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(Widget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                menuInterface.buildDeck();
            }
        });

        storeButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(Widget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                menuInterface.store();
            }
        });
    }

    private void createStore() {
        storeLayout = new Layout(Assets.blur, 0, -MenuScreen.SCREEN_HEIGHT, MenuScreen.SCREEN_WIDTH, MenuScreen.SCREEN_HEIGHT, getCam());
        storeLayout.addIncomingAnimator(new MovementAnimator(storeLayout, -MenuScreen.SCREEN_HEIGHT, 0, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true));
        storeLayout.addOutgoingAnimator(new MovementAnimator(storeLayout, 0, -MenuScreen.SCREEN_HEIGHT, 1, 0, Interpolation.ACCELERATE_INTERPOLATOR, false, true));

        final float AD_WIDTH = 60;
        float AD_HEIGHT = 80;

        storeScroll = new ScrollArea(Assets.invisible, 0, MenuScreen.SCREEN_HEIGHT / 2 - AD_HEIGHT / 5, MenuScreen.SCREEN_WIDTH, AD_HEIGHT, MenuScreen.SCREEN_WIDTH, AD_HEIGHT, ScrollArea.HORIZONTAL, 1, 40, AD_WIDTH, getCam());

        flameAd = new Image(Assets.flameAd, Assets.invisible, 0, 0, AD_WIDTH, AD_HEIGHT, false); //item 1
        japanAd = new Image(Assets.japaneseAd, Assets.invisible, 0, 0, AD_WIDTH, AD_HEIGHT, false); //item 2
        slotAd = new Image(Assets.extraSlotAd, Assets.invisible, 0, 0, AD_WIDTH, AD_HEIGHT, false); //item 3

        storeScroll.addPopupWidget(flameAd);
        storeScroll.addPopupWidget(japanAd);
        storeScroll.addPopupWidget(slotAd);
        storeScroll.slideToPosition(storeScroll.getPositionToCenter(0));

        storeLayout.addPopupWidget(storeScroll);

        ButtonMaterial buyButton = new ButtonMaterial(Assets.buyButton, MenuScreen.SCREEN_WIDTH / 2 - 20, MenuScreen.SCREEN_HEIGHT / 6, Settings.BUTTON_HEIGHT, 40, 15);
        buyButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(Widget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                if (currentItem == 0) {
                    if (!Settings.BOUGHT_FlAME_PACK) {
                        menuInterface.buyItem(Settings.FIRE_SKU);
                    }
                } else if (currentItem == 1) {
                    if (!Settings.BOUGHT_JAPANESE_PACK) {
                        menuInterface.buyItem(Settings.JAPANESE_SKU);
                    }
                } else if (currentItem == 2) {
                    if (Settings.EXTRA_CARD_SLOT_1) {
                        if (!Settings.EXTRA_CARD_SLOT_2) {
                            menuInterface.buyItem(Settings.EXTRA_CARD_SLOT_2_ID);
                        }
                    } else {
                        if (!Settings.EXTRA_CARD_SLOT_1) {
                            menuInterface.buyItem(Settings.EXTRA_CARD_SLOT_1_ID);
                        }
                    }
                }
            }
        });
        storeLayout.addPopupWidget(buyButton);

        ButtonMaterial leftButton = new ButtonMaterial(Assets.leftArrow, 2, MenuScreen.SCREEN_HEIGHT / 2 - 10, Settings.BUTTON_HEIGHT, 20, 20);
        leftButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(Widget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                currentItem += 1;
                if (currentItem >= 2) currentItem = 2;
                storeScroll.slideToPosition(storeScroll.getPositionToCenter(currentItem));
            }
        });
        storeLayout.addPopupWidget(leftButton);

        ButtonMaterial rightButton = new ButtonMaterial(Assets.rightArrow, MenuScreen.SCREEN_WIDTH - 22, MenuScreen.SCREEN_HEIGHT / 2 - 10, Settings.BUTTON_HEIGHT, 20, 20);
        rightButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(Widget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                currentItem -= 1;
                if (currentItem <= 0) currentItem = 0;
                storeScroll.slideToPosition(storeScroll.getPositionToCenter(currentItem));

            }
        });
        storeLayout.addPopupWidget(rightButton);


        ButtonMaterial okButton = new ButtonMaterial(Assets.okButtonRound, 2, 2, Settings.BUTTON_HEIGHT, 20, 20);
        okButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(Widget widget) {
                Assets.buttonSound.play(Settings.VOLUME);
                storeLayout.moveOut();
            }
        });
        storeLayout.addPopupWidget(okButton);

        this.addPopupMenu(storeLayout);
    }

    public void soldChecker() {
        Image soldImage = new Image(Assets.sold, Assets.invisible, 0, 0, 60, 80, false);
        boolean added = false;
        if (currentItem == 0 && Settings.BOUGHT_FlAME_PACK) {
            if (!added) {
                this.addWidget(soldImage);
                added = true;
            }
        } else if (currentItem == 1 && Settings.BOUGHT_JAPANESE_PACK) {
            if (!added) {
                this.addWidget(soldImage);
                added = true;
            }
        } else if (currentItem == 2 && Settings.EXTRA_CARD_SLOT_1 && Settings.EXTRA_CARD_SLOT_2) {
            if (!added) {
                this.addWidget(soldImage);
                added = true;
            }
        } else {
            if (added) {
                this.removeWidget(soldImage);
                added = false;
            }
        }
    }

}
