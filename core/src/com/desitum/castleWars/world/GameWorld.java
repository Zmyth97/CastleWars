package com.desitum.castleWars.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.desitum.castleWars.CastleWars;
import com.desitum.castleWars.GooglePlayServicesInterface;
import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.data.CardActions;
import com.desitum.castleWars.data.ComputerAI;
import com.desitum.castleWars.data.Resources;
import com.desitum.castleWars.data.Settings;
import com.desitum.castleWars.libraries.animation.ColorEffects;
import com.desitum.castleWars.libraries.animation.MovementAnimator;
import com.desitum.castleWars.libraries.effects.FlipEffect;
import com.desitum.castleWars.libraries.interpolation.Interpolation;
import com.desitum.castleWars.libraries.ui.widgets.Image;
import com.desitum.castleWars.libraries.ui.layout.Layout;
import com.desitum.castleWars.libraries.ui.listeners.OnClickListener;
import com.desitum.castleWars.libraries.ui.widgets.ButtonMaterial;
import com.desitum.castleWars.libraries.ui.widgets.TextLabel;
import com.desitum.castleWars.libraries.ui.widgets.ToggleButton;
import com.desitum.castleWars.libraries.ui.widgets.Widget;
import com.desitum.castleWars.libraries.world.KodyWorld;
import com.desitum.castleWars.objects.Card;
import com.desitum.castleWars.objects.Cloud;
import com.desitum.castleWars.objects.Deck;
import com.desitum.castleWars.objects.Player;
import com.desitum.castleWars.screens.GameScreen;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Zmyth97 on 2/25/2015.
 * can be used by Zmyth97 and people in [Zmyth97}]
 */
public class GameWorld extends KodyWorld implements GameInterface {


    ////////////////////////////////////////////////
    public static final int PLAYER = 0;
    public static final int PLAYER2 = 1;
    ////////////////////////////////////////////////
    public static final float DRAW_PILE_X = GameScreen.SCREEN_WIDTH / 2 - Card.CARD_WIDTH - 1.25f;
    public static final float DRAW_PILE_Y = GameScreen.SCREEN_HEIGHT - Card.CARD_HEIGHT - 2.5f;
    public static final float DISCARD_PILE_X = GameScreen.SCREEN_WIDTH / 2 + 1.25f;
    public static final float DISCARD_PILE_Y = GameScreen.SCREEN_HEIGHT - Card.CARD_HEIGHT - 2.5f;
    public static final float CARD_SPACING = 0.5f;
    public static final float CARDS_Y = 2;
    private static final float FADE_IN_DURATION = 1f;
    private static final float FADE_DELAY = 04f;
    private static final float FADE_OUT_DURATION = 0.3f;
    private static final float CHANGE_TEXT_WIDTH = 40;
    private static final float CHANGE_TEXT_HEIGHT = 6;
    private static final float MENU_TEXT_WIDTH = 15;
    private static final float MENU_TEXT_HEIGHT = 6;
    public static boolean EASY_DIFFICULTY = false;
    public static boolean NORMAL_DIFFICULTY = false;
    public static boolean NETWORK_GAME;
    private static Color buildColor = new Color(.122f, 0f, .616f, 1);
    private static Color attackColor = new Color(.855f, 0f, .102f, 1);
    private static Color magicColor = new Color(.035f, .722f, 0, 1);
    private static Color castleColor = new Color(0.278f, 0.278f, 0.322f, 1);
    public boolean gameOver;
    private GooglePlayServicesInterface gpgs;
    private int playerTurn;
    private Player player1;
    private Player player2;
    private ComputerAI ai;
    private Card lastCardUsed;
    private int settingsToggle;
    private Layout settingsLayout;
    private TextLabel playerBuildersLabel;
    private TextLabel playerSoldiersLabel;
    private TextLabel playerWizardsLabel;
    private TextLabel playerStoneLabel;
    private TextLabel playerWeaponLabel;
    private TextLabel playerGemLabel;
    private TextLabel playerCastleLabel;
    private TextLabel playerWallLabel;
    private TextLabel computerBuildersLabel;
    private TextLabel computerSoldiersLabel;
    private TextLabel computerWizardsLabel;
    private TextLabel computerStoneLabel;
    private TextLabel computerWeaponLabel;
    private TextLabel computerGemLabel;
    private TextLabel computerCastleLabel;
    private TextLabel computerWallLabel;
    private TextLabel playerBuildersLabelChange;
    private TextLabel playerSoldiersLabelChange;
    private TextLabel playerWizardsLabelChange;
    private TextLabel playerStoneLabelChange;
    private TextLabel playerWeaponLabelChange;
    private TextLabel playerGemLabelChange;
    private TextLabel playerCastleLabelChange;
    private TextLabel playerWallLabelChange;
    private TextLabel computerBuildersLabelChange;
    private TextLabel computerSoldiersLabelChange;
    private TextLabel computerWizardsLabelChange;
    private TextLabel computerStoneLabelChange;
    private TextLabel computerWeaponLabelChange;
    private TextLabel computerGemLabelChange;
    private TextLabel computerCastleLabelChange;
    private TextLabel computerWallLabelChange;
    private ToggleButton discardToggle;
    private Deck deck;
    private Resources myResources;
    private CardActions cardActions;
    private float computerDelay;
    private ArrayList<Cloud> cloudList;
    private TextLabel playerLabel;
    private TextLabel computerLabel;
    private TextLabel aiDiscardLabel; //Looked Weird When the AI Moved a Card and it Did Nothing
    private float endTimer;
    private Layout difficultyLayout;
    private ToggleButton easyButton;
    private ToggleButton normalButton;

    public GameWorld(GooglePlayServicesInterface gpgs, Viewport cam) {
        super();
        super.setCam(cam);

        this.gpgs = gpgs;

        //gpgs.checkForPurchasesMade();
        if (Settings.EXTRA_CARD_SLOT_1) {
            Settings.CARDS_DEALT = 7;
        }
        if (Settings.EXTRA_CARD_SLOT_2 && Settings.EXTRA_CARD_SLOT_1) {
            Settings.CARDS_DEALT = 8;
        }

        player1 = new Player(this, (GameScreen.SCREEN_WIDTH / 2 - 50));
        player2 = new Player(this, (GameScreen.SCREEN_WIDTH / 2 + 25));

        playerTurn = PLAYER;
        computerDelay = Settings.COMPUTER_DELAY;

        deck = new Deck();
        for (Card card : deck.getCardList()) {
            card.setButtonListener(new OnClickListener() {
                @Override
                public void onClick(Widget widget) {
                    Card card = (Card) widget;
                    onClickCard(card);
                }
            });
        }
        cardActions = new CardActions(this);
        myResources = new Resources(this);
        cloudList = new ArrayList<Cloud>();

        ai = new ComputerAI(this);

        endTimer = 0;
        gameOver = false;

        playerLabel = new TextLabel(Assets.invisible, Color.WHITE, Assets.textFieldFont, 0, (GameScreen.SCREEN_HEIGHT / 4 + 1), 20, 5, "Player");
        computerLabel = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, GameScreen.SCREEN_WIDTH - 25, (GameScreen.SCREEN_HEIGHT / 4 + 1), 25, 5, "Computer");
        addWidgetToWorld(playerLabel);
        addWidgetToWorld(computerLabel);
        playerLabel.setFontColor(Color.BLACK);
        computerLabel.setFontColor(Color.BLACK);

        aiDiscardLabel = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, DISCARD_PILE_X - 2, DISCARD_PILE_Y - 5, 20, 3, "Discarded");
        aiDiscardLabel.setFontColor(new Color(0, 0, 0, 0));
        addWidgetToWorld(aiDiscardLabel);

        setupSideMenus();
        setupSettingsMenu();
        buildDifficultyGUI();

        //Fill Both Players Hands At Start
        for (int i = 0; i < Settings.CARDS_DEALT; i++) {
            float cardX = (GameScreen.SCREEN_WIDTH / 2 + 5) - ((Settings.CARDS_DEALT * Card.CARD_WIDTH) + ((Settings.CARDS_DEALT - 1) * CARD_SPACING)) / 2 + ((i * Card.CARD_WIDTH) + (i * CARD_SPACING));
            player1.getHand().addCardToHand(drawNewCard(cardX, CARDS_Y, i * 0.2f, false));
            player2.getHand().addCardToHand(drawNewCard(GameScreen.SCREEN_WIDTH / 2 - Card.CARD_WIDTH / 2, -Card.CARD_HEIGHT, i * 0.2f + 0.1f, true));
        }

        gpgs.unlockAchievement(CastleWars.AN_ERA_BEGINS);
    }

    public void update(float delta) {

        for (Card c : deck.getCardList()) {
            c.update(delta);
        }

        super.update(delta);

        if (playerTurn == PLAYER2 && endTimer <= 0) {
            computerDelay -= delta;
            if (computerDelay < 0) {
                computerDelay = Settings.COMPUTER_DELAY;
                computerTurn();
            }
        }
        player1.update(delta);
        player2.update(delta);

        int randomCloudChance = (int) (Math.random() * 500);
        if (randomCloudChance == 1) {
            makeCloud();
        }
        Iterator<Cloud> iter = cloudList.iterator();
        while (iter.hasNext()) {
            Cloud c = iter.next();
            c.update(delta);
            if (c.needsRemoval()) {
                gpgs.unlockAchievement(CastleWars.CLOUD_WATCHER);
                iter.remove();
            }
        }

        if (endTimer > 0) {
            endTimer -= delta;
            if (endTimer < 0) {
                gameOver = true;
            }
        }
        playerBuildersLabel.setText(":" + myResources.getPlayerBuilders());
        playerSoldiersLabel.setText(":" + myResources.getPlayerSoldiers());
        playerWizardsLabel.setText(":" + myResources.getPlayerWizards());
        playerStoneLabel.setText(":" + myResources.getPlayerStones());
        playerWeaponLabel.setText(":" + myResources.getPlayerWeapons());
        playerGemLabel.setText(":" + myResources.getPlayerGems());
        playerCastleLabel.setText(":" + (int) player1.getCastle().getHealth());
        playerWallLabel.setText(":" + (int) player1.getCastle().getWall().getHealth());
        computerBuildersLabel.setText(":" + myResources.getComputerBuilders());
        computerSoldiersLabel.setText(":" + myResources.getComputerSoldiers());
        computerWizardsLabel.setText(":" + myResources.getComputerWizards());
        computerStoneLabel.setText(":" + myResources.getComputerStones());
        computerWeaponLabel.setText(":" + myResources.getComputerWeapons());
        computerGemLabel.setText(":" + myResources.getComputerGems());
        computerCastleLabel.setText(":" + (int) player2.getCastle().getHealth());
        computerWallLabel.setText(":" + (int) player2.getCastle().getWall().getHealth());
    }

    private void switchTurns(int playerTurn) {
        this.playerTurn = playerTurn;
        discardToggle.turnOff();

        if (playerTurn == PLAYER) {
            myResources.addPlayerStones(2 * myResources.getPlayerBuilders());
            myResources.addPlayerGems(2 * myResources.getPlayerWizards());
            myResources.addPlayerWeapons(2 * myResources.getPlayerSoldiers());
            playerLabel.setFontColor(Color.RED);
            computerLabel.setFontColor(Color.BLACK);
        } else {
            myResources.addComputerStones(2 * myResources.getComputerBuilders());
            myResources.addComputerGems(2 * myResources.getComputerWizards());
            myResources.addComputerWeapons(2 * myResources.getComputerSoldiers());
            computerLabel.setFontColor(Color.RED);
            playerLabel.setFontColor(Color.BLACK);
        }

    }

    private void computerTurn() {
        Card c;
        if (EASY_DIFFICULTY) {
            boolean usedCard = false;
            for (Card card : player2.getHand().getCardsInHand()) {
                if (card.isAvailable()) {
                    c = card;
                    c.moveOut();
                    cardActions.doCardAction(card.getCardID());
                    player2.getHand().removeCardFromHand(c);
                    player2.getHand().addCardToHand(drawNewCard(GameScreen.SCREEN_WIDTH / 2 - Card.CARD_WIDTH / 2, -Card.CARD_HEIGHT, 0, true));
                    disableCard(c);
                    usedCard = true;
                    deck.addCard(c);
                    break;
                }
            }
            if (!usedCard) {
                c = player2.getHand().getCardsInHand().get(0);
                disableCard(c);
            }
        } else if (NORMAL_DIFFICULTY) {
            Card chosenCard = ai.processAI();
            if (!ai.isDiscarding()) {
                cardActions.doCardAction(chosenCard.getCardID());
            } else {
                aiDiscardLabel.addFontColorChanger(new ColorEffects(new Color(0, 0, 0, 0), Color.BLACK, 1f, 0));
                aiDiscardLabel.addFontColorChanger(new ColorEffects(Color.BLACK, new Color(0, 0, 0, 0), 1f, 1f));
                aiDiscardLabel.startTextColorEffects();
            }
            chosenCard.moveOut();
            player2.getHand().removeCardFromHand(chosenCard);
            player2.getHand().addCardToHand(drawNewCard(GameScreen.SCREEN_WIDTH / 2 - Card.CARD_WIDTH / 2, -Card.CARD_HEIGHT, 0, true));
            deck.addCard(chosenCard);
            disableCard(chosenCard);
        }
        switchTurns(PLAYER);
    }

    @Override
    public void onClickCard(Card card) {
        if ((card.isAvailable() || isDiscarding()) && playerTurn == PLAYER) {
            card.moveOut();
            if (!isDiscarding()) {
                cardActions.doCardAction(card.getCardID());
                lastCardUsed = card;
            }
            player1.getHand().removeCardFromHand(card);
            int iRange = player1.getHand().getCardsInHand().size();
            for (int i = 0; i <= iRange; i++) {
                float cardX = (GameScreen.SCREEN_WIDTH / 2) + 5 - ((Settings.CARDS_DEALT * Card.CARD_WIDTH) + ((Settings.CARDS_DEALT - 1) * CARD_SPACING)) / 2 + ((i * Card.CARD_WIDTH) + (i * CARD_SPACING));
                if (i == player1.getHand().getCardsInHand().size()) {
                    player1.getHand().addCardToHand(drawNewCard(cardX, card.getY(), 0, false));
                } else {
                    Card card1 = player1.getHand().getCardsInHand().get(i);
                    card1.clearAllAnimators();
                    card1.addIncomingAnimator(new MovementAnimator(card, card1.getX(), cardX, 1f, i * 0.1f, Interpolation.ACCELERATE_DECELERATE_INTERPOLATOR, true, false));
                    card1.addIncomingAnimator(new MovementAnimator(card, card1.getY(), CARDS_Y, 1f, i * 0.1f, Interpolation.DECELERATE_INTERPOLATOR, false, true));
                    card1.addOutgoingAnimator(new MovementAnimator(card, cardX, DISCARD_PILE_X, 1f, 0, Interpolation.ACCELERATE_INTERPOLATOR, true, false));
                    card1.addOutgoingAnimator(new MovementAnimator(card, card1.getY(), DISCARD_PILE_Y, 1f, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true));
                    card1.moveIn();
                }
            }
            deck.addCard(card);
            disableCard(card);
            switchTurns(PLAYER2);
        }
    }

    private void disableCard(Card card) {
        card.disable();
    }

    private Card drawNewCard(float x, float y, float delay, boolean isComputer) { //Had to change to boolean for initial drawCards
        Card card = deck.drawCard(isComputer);
        card.clearAllAnimators();
        card.addIncomingAnimator(new MovementAnimator(card, DRAW_PILE_X, x, 1f, delay, Interpolation.ACCELERATE_INTERPOLATOR, true, false));
        card.addIncomingAnimator(new MovementAnimator(card, DRAW_PILE_Y, y, 1f, delay, Interpolation.DECELERATE_INTERPOLATOR, false, true));
        card.addOutgoingAnimator(new MovementAnimator(card, x, DISCARD_PILE_X, 1f, 0, Interpolation.ACCELERATE_INTERPOLATOR, true, false));
        card.addOutgoingAnimator(new MovementAnimator(card, y, DISCARD_PILE_Y, 1f, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true));
        card.moveIn();
        card.setFlipEffect(new FlipEffect(card, Assets.cardBack, card.getTexture(), 1.0f, FlipEffect.HORIZONTAl));
        return card;
    }

    private void makeCloud() {
        int randomY = (int) (Math.random() * 28) + 60;
        int randomSize = (int) (Math.random() * 10) + 20;
        float randomSpeed = ((int) (Math.random() * 3.0f)) * 2.0f;
        int randomTexture = (int) (Math.random() * 2);

        if (randomTexture == 1) {
            cloudList.add(new Cloud(this, Assets.cloud1, randomY, randomSize, randomSpeed));
        } else if (randomTexture == 2) {
            cloudList.add(new Cloud(this, Assets.cloud2, randomY, randomSize, randomSpeed));
        } else {
            cloudList.add(new Cloud(this, Assets.cloud3, randomY, randomSize, randomSpeed));
        }

    }

    @Override
    public void win() {
        gpgs.unlockAchievement(CastleWars.CASTLE_MASTER);
        if (lastCardUsed.getCardID() > 400 && lastCardUsed.getCardID() < 500) {
            gpgs.unlockAchievement(CastleWars.ELEMENTALIST);
        } else if (lastCardUsed.getCardID() > 500 && lastCardUsed.getCardID() < 600) {
            gpgs.unlockAchievement(CastleWars.JAPANESE_MASTER);
        }
        if (lastCardUsed.getCardID() == CardActions.TROJAN_HORSE) {
            gpgs.unlockAchievement(CastleWars.DIDNT_SEE_THAT_COMING);
        }
        if (Settings.ASSETS_TO_USE == 3) {
            gpgs.unlockAchievement(CastleWars.WORLD_CONQUEST);
        }
        TextLabel winLabel = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, GameScreen.SCREEN_WIDTH / 2 - 25, GameScreen.SCREEN_HEIGHT / 4 * 2.5f, 50, 10, "You Won!");
        addWidgetToWorld(winLabel);
        winLabel.addFontColorChanger(new ColorEffects(new Color(0, 0, 0, 0), Color.BLACK, 1f));
        winLabel.startTextColorEffects();
        endTimer = 5f;
    }

    @Override
    public void lose() {
        gpgs.unlockAchievement(CastleWars.CASTLE_MASTER);
        gpgs.unlockAchievement(CastleWars.PILLAGED);

        TextLabel loseLabel = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, GameScreen.SCREEN_WIDTH / 2 - 25, GameScreen.SCREEN_HEIGHT / 4 * 2.5f, 55, 10, "You Lost!");
        addWidgetToWorld(loseLabel);
        loseLabel.addFontColorChanger(new ColorEffects(new Color(0, 0, 0, 0), Color.BLACK, 1f));
        loseLabel.startTextColorEffects();
        endTimer = 5f;
    }

    public ArrayList<Cloud> getCloudList() {
        return cloudList;
    }

    @Override
    public void unlockAchievement(int achievement) {
        gpgs.unlockAchievement(achievement);
    }

    @Override
    public Resources getResources() {
        return myResources;
    }

    @Override
    public int getPlayerTurn() {
        return playerTurn;
    }

    @Override
    public Player getPlayer1() {
        return player1;
    }

    @Override
    public Player getPlayer2() {
        return player2;
    }

    @Override
    public void addCardToWorld(Card card) {
        addWidget(card);
    }

    @Override
    public void removeCardFromWorld(Card card) {
        removeWidget(card);
    }

    @Override
    public void removeWidgetFromWorld(Widget widget) {
        super.removeWidget(widget);
    }

    @Override
    public void addWidgetToWorld(Widget widget) {
        super.addWidget(widget);
    }

    public Deck getDeck() {
        return deck;
    }

    private void setupSideMenus() {
        //region player menus
        Layout playerBuildLayout = new Layout(Assets.menuArea, 0, -40, 20, 16, getCam());
        playerBuildLayout.setColor(buildColor);
        MovementAnimator playerBuildAnimator = new MovementAnimator(playerBuildLayout, 0, 85, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        playerBuildLayout.addIncomingAnimator(playerBuildAnimator);
        playerBuildLayout.addPopupWidget(new Image(Assets.hammer, Assets.invisible, 1, 9, 6, 6, false));
        playerBuildLayout.addPopupWidget(new Image(Assets.stone, Assets.invisible, 1, 1, 6, 6, false));
        playerBuildersLabel = new TextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        playerBuildersLabel.setText(":" + myResources.getPlayerSoldiers());
        playerStoneLabel = new TextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        playerStoneLabel.setText(":" + myResources.getPlayerWeapons());
        playerBuildLayout.addPopupWidget(playerBuildersLabel);
        playerBuildLayout.addPopupWidget(playerStoneLabel);
        this.addPopupMenu(playerBuildLayout);

        //Attack Popup Layout
        Layout playerAttackLayout = new Layout(Assets.menuArea, 0, -40, 20, 16, getCam());
        playerAttackLayout.setColor(attackColor);
        MovementAnimator playerAttackAnimator = new MovementAnimator(playerAttackLayout, 0, 67, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        playerAttackLayout.addIncomingAnimator(playerAttackAnimator);
        playerAttackLayout.addPopupWidget(new Image(Assets.helmet, Assets.invisible, 1, 9, 6, 6, false));
        playerAttackLayout.addPopupWidget(new Image(Assets.spear, Assets.invisible, 1, 1, 6, 6, false));
        playerSoldiersLabel = new TextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        playerSoldiersLabel.setText(":" + myResources.getPlayerSoldiers());
        playerWeaponLabel = new TextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        playerWeaponLabel.setText(":" + myResources.getPlayerWeapons());
        playerAttackLayout.addPopupWidget(playerSoldiersLabel);
        playerAttackLayout.addPopupWidget(playerWeaponLabel);
        this.addPopupMenu(playerAttackLayout);

        //Magic Popup Layout
        Layout playerMagicLayout = new Layout(Assets.menuArea, 0, -40, 20, 16, getCam());
        playerMagicLayout.setColor(magicColor);
        MovementAnimator playerMagicAnimator = new MovementAnimator(playerMagicLayout, 0, 50, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        playerMagicLayout.addIncomingAnimator(playerMagicAnimator);
        playerMagicLayout.addPopupWidget(new Image(Assets.wand, Assets.invisible, 1, 9, 6, 6, false));
        playerMagicLayout.addPopupWidget(new Image(Assets.gem, Assets.invisible, 1, 1, 6, 6, false));
        playerWizardsLabel = new TextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        playerWizardsLabel.setText(":" + myResources.getPlayerWizards());
        playerGemLabel = new TextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        playerGemLabel.setText(":" + myResources.getPlayerGems());
        playerMagicLayout.addPopupWidget(playerWizardsLabel);
        playerMagicLayout.addPopupWidget(playerGemLabel);
        this.addPopupMenu(playerMagicLayout);

        //Castle Popup Layout
        Layout playerCastleLayout = new Layout(Assets.menuArea, 0, -40, 20, 15, getCam());
        playerCastleLayout.setColor(castleColor);
        MovementAnimator playerCastleAnimator = new MovementAnimator(playerCastleLayout, 0, 33, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        playerCastleLayout.addIncomingAnimator(playerCastleAnimator);
        playerCastleLayout.addPopupWidget(new Image(Assets.castle, Assets.invisible, 2, 9, 5, 5, false));
        playerCastleLayout.addPopupWidget(new Image(Assets.wall, Assets.invisible, 3, 1, 3, 5, false));
        playerCastleLabel = new TextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        playerCastleLabel.setText(":" + (int) player1.getCastle().getHealth());
        playerWallLabel = new TextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        playerWallLabel.setText(":" + (int) player1.getCastle().getWall().getHealth());
        playerCastleLayout.addPopupWidget(playerCastleLabel);
        playerCastleLayout.addPopupWidget(playerWallLabel);
        this.addPopupMenu(playerCastleLayout);

        //endregion

        //region Computer Menus

        //Build Popup Layout
        Layout computerBuildLayout = new Layout(Assets.menuArea, 130, -40, 20, 15, getCam());
        computerBuildLayout.setColor(buildColor);
        MovementAnimator computerBuildAnimator = new MovementAnimator(computerBuildLayout, 130, 85, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        computerBuildLayout.addIncomingAnimator(computerBuildAnimator);
        computerBuildLayout.addPopupWidget(new Image(Assets.hammer, Assets.invisible, 1, 9, 6, 6, false));
        computerBuildLayout.addPopupWidget(new Image(Assets.stone, Assets.invisible, 1, 1, 6, 6, false));
        computerBuildersLabel = new TextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        computerBuildersLabel.setText(":" + myResources.getComputerSoldiers());
        computerStoneLabel = new TextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        computerStoneLabel.setText(":" + myResources.getComputerWeapons());
        computerBuildLayout.addPopupWidget(computerBuildersLabel);
        computerBuildLayout.addPopupWidget(computerStoneLabel);
        this.addPopupMenu(computerBuildLayout);

        //Attack Popup Layout
        Layout computerAttackLayout = new Layout(Assets.menuArea, 130, -40, 20, 15, getCam());
        computerAttackLayout.setColor(attackColor);
        MovementAnimator computerAttackAnimator = new MovementAnimator(computerAttackLayout, 130, 67, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        computerAttackLayout.addIncomingAnimator(computerAttackAnimator);
        computerAttackLayout.addPopupWidget(new Image(Assets.helmet, Assets.invisible, 1, 8, 6, 6, false));
        computerAttackLayout.addPopupWidget(new Image(Assets.spear, Assets.invisible, 1, 1, 6, 6, false));
        computerSoldiersLabel = new TextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        computerSoldiersLabel.setText(":" + myResources.getComputerSoldiers());
        computerWeaponLabel = new TextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        computerWeaponLabel.setText(":" + myResources.getComputerWeapons());
        computerAttackLayout.addPopupWidget(computerSoldiersLabel);
        computerAttackLayout.addPopupWidget(computerWeaponLabel);
        this.addPopupMenu(computerAttackLayout);

        //magic Popup Layout
        Layout computerMagicLayout = new Layout(Assets.menuArea, 130, -40, 20, 15, getCam());
        computerMagicLayout.setColor(magicColor);
        MovementAnimator computerMagicAnimator = new MovementAnimator(computerMagicLayout, 130, 50, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        computerMagicLayout.addIncomingAnimator(computerMagicAnimator);
        computerMagicLayout.addPopupWidget(new Image(Assets.wand, Assets.invisible, 1, 9, 6, 6, false));
        computerMagicLayout.addPopupWidget(new Image(Assets.gem, Assets.invisible, 1, 1, 6, 6, false));
        computerWizardsLabel = new TextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        computerWizardsLabel.setText(":" + myResources.getComputerWizards());
        computerGemLabel = new TextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        computerGemLabel.setText(":" + myResources.getComputerGems());
        computerMagicLayout.addPopupWidget(computerWizardsLabel);
        computerMagicLayout.addPopupWidget(computerGemLabel);
        this.addPopupMenu(computerMagicLayout);

        //Castle Popup Layout
        Layout computerCastleLayout = new Layout(Assets.menuArea, 130, -40, 20, 15, getCam());
        computerCastleLayout.setColor(castleColor);
        MovementAnimator computerCastleAnimator = new MovementAnimator(computerCastleLayout, 130, 33, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        computerCastleLayout.addIncomingAnimator(computerCastleAnimator);
        computerCastleLayout.addPopupWidget(new Image(Assets.castle, Assets.invisible, 2, 9, 5, 5, false));
        computerCastleLayout.addPopupWidget(new Image(Assets.wall, Assets.invisible, 3, 1, 3, 5, false));
        computerCastleLabel = new TextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        computerCastleLabel.setText(":" + (int) player2.getCastle().getHealth());
        computerWallLabel = new TextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        computerWallLabel.setText(":" + (int) player2.getCastle().getWall().getHealth());
        computerCastleLayout.addPopupWidget(computerCastleLabel);
        computerCastleLayout.addPopupWidget(computerWallLabel);
        this.addPopupMenu(computerCastleLayout);
        //endregion

        playerBuildLayout.moveIn();
        playerAttackLayout.moveIn();
        playerMagicLayout.moveIn();
        playerCastleLayout.moveIn();
        computerBuildLayout.moveIn();
        computerAttackLayout.moveIn();
        computerMagicLayout.moveIn();
        computerCastleLayout.moveIn();

        float playerLabelX = 22;

        playerBuildersLabelChange = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, playerLabelX, playerBuildAnimator.getEndPos() + 9, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT);
        playerBuildersLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        playerBuildersLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        playerStoneLabelChange = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, 22, playerBuildAnimator.getEndPos() + 1, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT);
        playerStoneLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        playerStoneLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        playerSoldiersLabelChange = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, playerLabelX, playerAttackAnimator.getEndPos() + 9, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT);
        playerSoldiersLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        playerSoldiersLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        playerWeaponLabelChange = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, playerLabelX, playerAttackAnimator.getEndPos() + 1, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT);
        playerWeaponLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        playerWeaponLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        playerWizardsLabelChange = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, playerLabelX, playerMagicAnimator.getEndPos() + 9, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT);
        playerWizardsLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        playerWizardsLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        playerGemLabelChange = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, playerLabelX, playerMagicAnimator.getEndPos() + 1, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT);
        playerGemLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        playerGemLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        playerCastleLabelChange = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, playerLabelX, playerCastleAnimator.getEndPos() + 9, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT);
        playerCastleLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        playerCastleLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        playerWallLabelChange = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, playerLabelX, playerCastleAnimator.getEndPos() + 1, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT);
        playerWallLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        playerWallLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));

        float computerLabelX = computerAttackLayout.getX() - CHANGE_TEXT_WIDTH - 2;

        computerBuildersLabelChange = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, computerLabelX, computerBuildAnimator.getEndPos() + 9, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT, "", BitmapFont.HAlignment.RIGHT);
        computerBuildersLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        computerBuildersLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        computerStoneLabelChange = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, computerLabelX, computerBuildAnimator.getEndPos() + 1, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT, "", BitmapFont.HAlignment.RIGHT);
        computerStoneLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        computerStoneLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        computerSoldiersLabelChange = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, computerLabelX, computerAttackAnimator.getEndPos() + 9, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT, "", BitmapFont.HAlignment.RIGHT);
        computerSoldiersLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        computerSoldiersLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        computerWeaponLabelChange = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, computerLabelX, computerAttackAnimator.getEndPos() + 1, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT, "", BitmapFont.HAlignment.RIGHT);
        computerWeaponLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        computerWeaponLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        computerWizardsLabelChange = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, computerLabelX, computerMagicAnimator.getEndPos() + 9, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT, "", BitmapFont.HAlignment.RIGHT);
        computerWizardsLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        computerWizardsLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        computerGemLabelChange = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, computerLabelX, computerMagicAnimator.getEndPos() + 1, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT, "", BitmapFont.HAlignment.RIGHT);
        computerGemLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        computerGemLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        computerCastleLabelChange = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, computerLabelX, computerCastleAnimator.getEndPos() + 9, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT, "", BitmapFont.HAlignment.RIGHT);
        computerCastleLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        computerCastleLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        computerWallLabelChange = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, computerLabelX, computerCastleAnimator.getEndPos() + 1, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT, "", BitmapFont.HAlignment.RIGHT);
        computerWallLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        computerWallLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));

        this.addWidget(playerBuildersLabelChange);
        this.addWidget(playerStoneLabelChange);
        this.addWidget(playerSoldiersLabelChange);
        this.addWidget(playerWeaponLabelChange);
        this.addWidget(playerWizardsLabelChange);
        this.addWidget(playerGemLabelChange);
        this.addWidget(playerCastleLabelChange);
        this.addWidget(playerWallLabelChange);

        this.addWidget(computerBuildersLabelChange);
        this.addWidget(computerStoneLabelChange);
        this.addWidget(computerSoldiersLabelChange);
        this.addWidget(computerWeaponLabelChange);
        this.addWidget(computerWizardsLabelChange);
        this.addWidget(computerGemLabelChange);
        this.addWidget(computerCastleLabelChange);
        this.addWidget(computerWallLabelChange);

        this.discardToggle = new ToggleButton(Assets.trashCanSelected, Assets.trashCan, 5, 12, 8, 12, false);
        this.addWidgetToWorld(discardToggle);

        ButtonMaterial settingsButton = new ButtonMaterial(Assets.exitButtonRound, 5, 2, Settings.BUTTON_HEIGHT, 8, 8);
        settingsButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(Widget widget) {
                if (settingsToggle == 0) {
                    settingsToggle++;
                    settingsLayout.moveIn();
                } else {
                    settingsToggle--;
                    settingsLayout.moveOut();
                }
            }
        });
        this.addWidgetToWorld(settingsButton);
    }

    private void setupSettingsMenu() {
        settingsLayout = new Layout(Assets.invisible, 10, -130, 130, 80, getCam());
        MovementAnimator yAnimator = new MovementAnimator(settingsLayout, -130, 10, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        settingsLayout.addIncomingAnimator(yAnimator);
        MovementAnimator yAnimator2 = new MovementAnimator(settingsLayout, 10, -130, 1, 0, Interpolation.ANTICIPATE_INTERPOLATOR, false, true);
        settingsLayout.addOutgoingAnimator(yAnimator2);

        ButtonMaterial okButton = new ButtonMaterial(Assets.okButtonRound, 40, 40, Settings.BUTTON_HEIGHT, 20, 20);
        okButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(Widget widget) {
                gameOver = true;
            }
        });
        settingsLayout.addPopupWidget(okButton);

        ButtonMaterial cancelButton = new ButtonMaterial(Assets.cancelButtonRound, 70, 40, Settings.BUTTON_HEIGHT, 20, 20);
        cancelButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(Widget widget) {
                settingsLayout.moveOut();
            }
        });
        settingsLayout.addPopupWidget(cancelButton);

        this.addPopupMenu(settingsLayout);
    }

    private void buildDifficultyGUI() {
        float menuWidth = (GameScreen.SCREEN_WIDTH * .75f);
        float menuHeight = (GameScreen.SCREEN_HEIGHT * .75f);
        difficultyLayout = new Layout(Assets.popupMenuBackground, (GameScreen.SCREEN_WIDTH / 2) - (GameScreen.SCREEN_WIDTH * .75f) / 2, -200, menuWidth, menuHeight, getCam());
        MovementAnimator yAnimator = new MovementAnimator(difficultyLayout, -200, (GameScreen.SCREEN_HEIGHT / 2) - (GameScreen.SCREEN_HEIGHT * .75f) / 3, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        difficultyLayout.addIncomingAnimator(yAnimator);
        MovementAnimator yAnimator2 = new MovementAnimator(difficultyLayout, (GameScreen.SCREEN_HEIGHT / 2) - (GameScreen.SCREEN_HEIGHT * .75f) / 3, -200, 1, 0, Interpolation.ANTICIPATE_INTERPOLATOR, false, true);
        difficultyLayout.addOutgoingAnimator(yAnimator2);


        easyButton = new ToggleButton(Assets.toggleButtonOn, Assets.toggleButtonOff, menuWidth / 6, (menuHeight / 3), menuWidth / 6, (menuWidth / 6), false);
        easyButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(Widget widget) {
                easyButton.turnOn();
                if (normalButton.isOn()) {
                    normalButton.turnOff();
                }
            }
        });
        normalButton = new ToggleButton(Assets.toggleButtonOn, Assets.toggleButtonOff, menuWidth / 4 * 2.5f, (menuHeight / 3), menuWidth / 6, (menuWidth / 6), false);
        normalButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(Widget widget) {
                normalButton.turnOn();
                if (easyButton.isOn()) {
                    easyButton.turnOff();
                }
            }

        });

        TextLabel easyLabel = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, menuWidth / 7, (menuHeight / 3) * 2, menuWidth / 4, (menuHeight / 8), "Easy");
        TextLabel normalLabel = new TextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, menuWidth / 4 * 2.3f, (menuHeight / 3) * 2, menuWidth / 3f, (menuHeight / 8), "Normal");

        ButtonMaterial okButton = new ButtonMaterial(Assets.okButton, menuWidth / 2 - (menuWidth / 4) / 1.75f, menuHeight / 4 - menuHeight / 6, Settings.BUTTON_HEIGHT, menuWidth / 4, menuHeight / 6);
        okButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(Widget widget) {
                if (easyButton.isOn()) {
                    EASY_DIFFICULTY = true;
                }
                {
                    NORMAL_DIFFICULTY = true;
                }
                difficultyLayout.moveOut();
                removeWidgetFromWorld(difficultyLayout);
            }
        });
        difficultyLayout.addPopupWidget(easyButton);
        difficultyLayout.addPopupWidget(normalButton);
        difficultyLayout.addPopupWidget(easyLabel);
        difficultyLayout.addPopupWidget(normalLabel);
        difficultyLayout.addPopupWidget(okButton);
        addPopupMenu(difficultyLayout);
        difficultyLayout.moveIn();
    }

    public void setPlayerBuildersLabelChangeText(int change) {
        if (change > 0) {
            playerBuildersLabelChange.setText("+" + change);
            playerBuildersLabelChange.startTextColorEffects();
        } else if (change < 0) {
            playerBuildersLabelChange.setText("" + change);
            playerBuildersLabelChange.startTextColorEffects();
        }
    }

    public void setPlayerStoneLabelChangeText(int change) {
        if (change > 0) {
            playerStoneLabelChange.setText("+" + change);
            playerStoneLabelChange.startTextColorEffects();
        } else if (change < 0) {
            playerStoneLabelChange.setText("" + change);
            playerStoneLabelChange.startTextColorEffects();
        }
    }

    public void setPlayerSoldiersLabelChangeText(int change) {
        if (change > 0) {
            playerSoldiersLabelChange.setText("+" + change);
            playerSoldiersLabelChange.startTextColorEffects();
        } else if (change < 0) {
            playerSoldiersLabelChange.setText("" + change);
            playerSoldiersLabelChange.startTextColorEffects();
        }
    }

    public void setPlayerWeaponLabelChangeText(int change) {
        if (change > 0) {
            playerWeaponLabelChange.setText("+" + change);
            playerWeaponLabelChange.startTextColorEffects();
        } else if (change < 0) {
            playerWeaponLabelChange.setText("" + change);
            playerWeaponLabelChange.startTextColorEffects();
        }
    }

    public void setPlayerWizardsLabelChangeText(int change) {
        if (change > 0) {
            playerWizardsLabelChange.setText("+" + change);
            playerWizardsLabelChange.startTextColorEffects();
        } else if (change < 0) {
            playerWizardsLabelChange.setText("" + change);
            playerWizardsLabelChange.startTextColorEffects();
        }
    }

    public void setPlayerGemLabelChangeText(int change) {
        if (change > 0) {
            playerGemLabelChange.setText("+" + change);
            playerGemLabelChange.startTextColorEffects();
        } else if (change < 0) {
            playerGemLabelChange.setText("" + change);
            playerGemLabelChange.startTextColorEffects();
        }
    }

    public void setPlayerCastleLabelChangeText(int change) {
        if (change > 0) {
            playerCastleLabelChange.setText("+" + change);
            playerCastleLabelChange.startTextColorEffects();
        } else if (change < 0) {
            playerCastleLabelChange.setText("" + change);
            playerCastleLabelChange.startTextColorEffects();
        }
    }

    public void setPlayerWallLabelChangeText(int change) {
        if (change > 0) {
            playerWallLabelChange.setText("+" + change);
            playerWallLabelChange.startTextColorEffects();
        } else if (change < 0) {
            playerWallLabelChange.setText("" + change);
            playerWallLabelChange.startTextColorEffects();
        }
    }

    public void setComputerBuildersLabelChangeText(int change) {
        if (change > 0) {
            computerBuildersLabelChange.setText("+" + change);
            computerBuildersLabelChange.startTextColorEffects();
        } else if (change < 0) {
            computerBuildersLabelChange.setText("" + change);
            computerBuildersLabelChange.startTextColorEffects();
        }
    }

    public void setComputerStoneLabelChangeText(int change) {
        if (change > 0) {
            computerStoneLabelChange.setText("+" + change);
            computerStoneLabelChange.startTextColorEffects();
        } else if (change < 0) {
            computerStoneLabelChange.setText("" + change);
            computerStoneLabelChange.startTextColorEffects();
        }
    }

    public void setComputerSoldiersLabelChangeText(int change) {
        if (change > 0) {
            computerSoldiersLabelChange.setText("+" + change);
            computerSoldiersLabelChange.startTextColorEffects();
        } else if (change < 0) {
            computerSoldiersLabelChange.setText("" + change);
            computerSoldiersLabelChange.startTextColorEffects();
        }
    }

    public void setComputerWeaponLabelChangeText(int change) {
        if (change > 0) {
            computerWeaponLabelChange.setText("+" + change);
            computerWeaponLabelChange.startTextColorEffects();
        } else if (change < 0) {
            computerWeaponLabelChange.setText("" + change);
            computerWeaponLabelChange.startTextColorEffects();
        }
    }

    public void setComputerWizardsLabelChangeText(int change) {
        if (change > 0) {
            computerWizardsLabelChange.setText("+" + change);
            computerWizardsLabelChange.startTextColorEffects();
        } else if (change < 0) {
            computerWizardsLabelChange.setText("" + change);
            computerWizardsLabelChange.startTextColorEffects();
        }
    }

    public void setComputerGemLabelChangeText(int change) {
        if (change > 0) {
            computerGemLabelChange.setText("+" + change);
            computerGemLabelChange.startTextColorEffects();
        } else if (change < 0) {
            computerGemLabelChange.setText("" + change);
            computerGemLabelChange.startTextColorEffects();
        }
    }

    public void setComputerCastleLabelChangeText(int change) {
        if (change > 0) {
            computerCastleLabelChange.setText("+" + change);
            computerCastleLabelChange.startTextColorEffects();
        } else if (change < 0) {
            computerCastleLabelChange.setText("" + change);
            computerCastleLabelChange.startTextColorEffects();
        }
    }

    public void setComputerWallLabelChangeText(int change) {
        if (change > 0) {
            computerWallLabelChange.setText("+" + change);
            computerWallLabelChange.startTextColorEffects();
        } else if (change < 0) {
            computerWallLabelChange.setText("" + change);
            computerWallLabelChange.startTextColorEffects();
        }
    }

    public boolean isDiscarding() {
        return discardToggle.isOn();
    }
}

