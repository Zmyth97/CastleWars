package com.desitum.castleWars.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.data.CardActions;
import com.desitum.castleWars.data.ComputerAI;
import com.desitum.castleWars.data.Resources;
import com.desitum.castleWars.data.Settings;
import com.desitum.castleWars.libraries.animation.ColorEffects;
import com.desitum.castleWars.libraries.animation.MovementAnimator;
import com.desitum.castleWars.libraries.effects.FlipEffect;
import com.desitum.castleWars.libraries.interpolation.Interpolation;
import com.desitum.castleWars.libraries.menu.OnClickListener;
import com.desitum.castleWars.libraries.menu.PopupButtonMaterial;
import com.desitum.castleWars.libraries.menu.PopupImage;
import com.desitum.castleWars.libraries.menu.PopupMenu;
import com.desitum.castleWars.libraries.menu.PopupTextLabel;
import com.desitum.castleWars.libraries.menu.PopupToggleButton;
import com.desitum.castleWars.libraries.menu.PopupWidget;
import com.desitum.castleWars.libraries.world.KodyWorld;
import com.desitum.castleWars.objects.Card;
import com.desitum.castleWars.objects.Cloud;
import com.desitum.castleWars.objects.Deck;
import com.desitum.castleWars.objects.Player;
import com.desitum.castleWars.packs.FlamePack;
import com.desitum.castleWars.packs.JapanesePack;
import com.desitum.castleWars.screens.GameScreen;
import com.desitum.castleWars.screens.MenuScreen;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Zmyth97 on 2/25/2015.
 * can be used by Zmyth97 and people in [Zmyth97}]
 */
public class GameWorld extends KodyWorld implements GameInterface {

    public static final int PLAYER = 0;
    public static final int PLAYER2 = 1;
    public static final float DRAW_PILE_X = MenuScreen.SCREEN_WIDTH / 2 - Card.CARD_WIDTH - 1.25f;
    public static final float DRAW_PILE_Y = MenuScreen.SCREEN_HEIGHT - Card.CARD_HEIGHT - 2.5f;
    public static final float DISCARD_PILE_X = MenuScreen.SCREEN_WIDTH / 2 + 1.25f;
    public static final float DISCARD_PILE_Y = MenuScreen.SCREEN_HEIGHT - Card.CARD_HEIGHT - 2.5f;
    public static final float CARD_SPACING = 0.5f;
    public static final float CARDS_Y = 5;
    public static final int EASY_DIFFICULTY = 0;
    public static final int NORMAL_DIFFICULTY = 1;
    private static final float FADE_IN_DURATION = 1f;
    private static final float FADE_DELAY = 04f;
    private static final float FADE_OUT_DURATION = 0.3f;
    private static final float CHANGE_TEXT_WIDTH = 40;
    private static final float CHANGE_TEXT_HEIGHT = 6;
    private static final float MENU_TEXT_WIDTH = 15;
    private static final float MENU_TEXT_HEIGHT = 6;
    private static Color buildColor = new Color(.122f, 0f, .616f, 1);
    private static Color attackColor = new Color(.855f, 0f, .102f, 1);
    private static Color magicColor = new Color(.035f, .722f, 0, 1);
    private static Color castleColor = new Color(0.278f, 0.278f, 0.322f, 1);
    private int playerTurn;
    private Player player1;
    private Player player2;
    private ComputerAI ai;
    private PopupTextLabel playerBuildersLabel;
    private PopupTextLabel playerSoldiersLabel;
    private PopupTextLabel playerWizardsLabel;
    private PopupTextLabel playerStoneLabel;
    private PopupTextLabel playerWeaponLabel;
    private PopupTextLabel playerGemLabel;
    private PopupTextLabel playerCastleLabel;
    private PopupTextLabel playerWallLabel;
    private PopupTextLabel computerBuildersLabel;
    private PopupTextLabel computerSoldiersLabel;
    private PopupTextLabel computerWizardsLabel;
    private PopupTextLabel computerStoneLabel;
    private PopupTextLabel computerWeaponLabel;
    private PopupTextLabel computerGemLabel;
    private PopupTextLabel computerCastleLabel;
    private PopupTextLabel computerWallLabel;
    private PopupTextLabel playerBuildersLabelChange;
    private PopupTextLabel playerSoldiersLabelChange;
    private PopupTextLabel playerWizardsLabelChange;
    private PopupTextLabel playerStoneLabelChange;
    private PopupTextLabel playerWeaponLabelChange;
    private PopupTextLabel playerGemLabelChange;
    private PopupTextLabel playerCastleLabelChange;
    private PopupTextLabel playerWallLabelChange;
    private PopupTextLabel computerBuildersLabelChange;
    private PopupTextLabel computerSoldiersLabelChange;
    private PopupTextLabel computerWizardsLabelChange;
    private PopupTextLabel computerStoneLabelChange;
    private PopupTextLabel computerWeaponLabelChange;
    private PopupTextLabel computerGemLabelChange;
    private PopupTextLabel computerCastleLabelChange;
    private PopupTextLabel computerWallLabelChange;
    private PopupToggleButton discardToggle;
    private int difficulty;
    private Deck deck;
    private Resources myResources;
    private CardActions cardActions;
    private FlamePack flamePack;
    private JapanesePack japanesePack;
    private float computerDelay;
    private ArrayList<Cloud> cloudList;


    private PopupMenu difficultyMenu;
    private PopupToggleButton easyButton;
    private PopupToggleButton normalButton;
    private PopupTextLabel easyLabel;
    private PopupTextLabel normalLabel;
    private PopupButtonMaterial okButton;

    public GameWorld(Viewport cam) {
        super();
        super.setCam(cam);

        player1 = new Player(this, (GameScreen.SCREEN_WIDTH / 2 - 50));
        player2 = new Player(this, (GameScreen.SCREEN_WIDTH / 2 + 25));

        playerTurn = PLAYER;
        computerDelay = Settings.COMPUTER_DELAY;

        deck = new Deck();
        for (Card card: deck.getCardList()) {
            card.setButtonListener(new OnClickListener() {
                @Override
                public void onClick(PopupWidget widget) {
                    Card card = (Card) widget;
                    onClickCard(card);
                }
            });
        }
        cardActions = new CardActions(this);
        flamePack = new FlamePack(this);
        japanesePack = new JapanesePack(this);
        myResources = new Resources(this);
        cloudList = new ArrayList<Cloud>();

        ai = new ComputerAI(this);

        setupSideMenus();

        buildDifficultyGUI();

        //Fill Both Players Hands At Start
        for (int i = 0; i < Settings.CARDS_DEALT; i++) {
            float cardX = MenuScreen.SCREEN_WIDTH/2 - ((Settings.CARDS_DEALT * Card.CARD_WIDTH) + ((Settings.CARDS_DEALT - 1) * CARD_SPACING))/2 + ((i * Card.CARD_WIDTH) + (i * CARD_SPACING));
            player1.getHand().addCardToHand(drawNewCard(cardX, CARDS_Y, i * 0.2f, false));
            player2.getHand().addCardToHand(drawNewCard(MenuScreen.SCREEN_WIDTH/2 - Card.CARD_WIDTH/2, -Card.CARD_HEIGHT, i * 0.2f + 0.1f, true));
        }
    }

    public void update(float delta) {

        for (Card c: deck.getCardList()) {
            c.update(delta);
        }

        super.update(delta);

        if (playerTurn == PLAYER2) {
            computerDelay -= delta;
            if (computerDelay < 0) {
                computerDelay = Settings.COMPUTER_DELAY;
                computerTurn();
            }
        }
        player1.update(delta);
        player2.update(delta);

        int randomCloudChance = (int)(Math.random() * 500);
        if(randomCloudChance == 1){
            makeCloud();
        }
        Iterator<Cloud> iter = cloudList.iterator();
        while (iter.hasNext()) {
            Cloud c = iter.next();
            c.update(delta);
            if (c.needsRemoval()) iter.remove();
        }

        playerBuildersLabel.setText(":" + myResources.getPlayerBuilders());
        playerSoldiersLabel.setText(":" + myResources.getPlayerSoldiers());
        playerWizardsLabel.setText(":" + myResources.getPlayerWizards());
        playerStoneLabel.setText(":" + myResources.getPlayerStones());
        playerWeaponLabel.setText(":" + myResources.getPlayerWeapons());
        playerGemLabel.setText(":" + myResources.getPlayerGems());
        playerCastleLabel.setText(":" + (int)  player1.getCastle().getHealth());
        playerWallLabel.setText(":" + (int)  player1.getCastle().getWall().getHealth());
        computerBuildersLabel.setText(":" + myResources.getComputerBuilders());
        computerSoldiersLabel.setText(":" + myResources.getComputerSoldiers());
        computerWizardsLabel.setText(":" + myResources.getComputerWizards());
        computerStoneLabel.setText(":" + myResources.getComputerStones());
        computerWeaponLabel.setText(":" + myResources.getComputerWeapons());
        computerGemLabel.setText(":" + myResources.getComputerGems());
        computerCastleLabel.setText(":" + (int) player2.getCastle().getHealth());
        computerWallLabel.setText(":" + (int)  player2.getCastle().getWall().getHealth());
    }

    private void switchTurns(int playerTurn) {
        this.playerTurn = playerTurn;
        discardToggle.turnOff();

        if (playerTurn == PLAYER) {
            myResources.addPlayerStones(2 * myResources.getPlayerBuilders());
            myResources.addPlayerGems(2 * myResources.getPlayerWizards());
            myResources.addPlayerWeapons(2 * myResources.getPlayerSoldiers());
        } else {
            myResources.addComputerStones(2 * myResources.getComputerBuilders());
            myResources.addComputerGems(2 * myResources.getComputerWizards());
            myResources.addComputerWeapons(2 * myResources.getComputerSoldiers());
        }
    }

    private void computerTurn() {
        Card c = null;
        if(difficulty == EASY_DIFFICULTY){
            boolean usedCard = false;
            for (Card card : player2.getHand().getCardsInHand()) {
                if (card.isAvailable()) {
                    c = card;
                    c.startOutgoingAnimators();
                    //If CARD HAS FINISHED MOVING AND IS IN THE DISCARD PILE, THEN DO BELOW
                    cardActions.doCardAction(card.getCardID());
                    player2.getHand().removeCardFromHand(c);
                    player2.getHand().addCardToHand(drawNewCard(MenuScreen.SCREEN_WIDTH / 2 - Card.CARD_WIDTH / 2, -Card.CARD_HEIGHT, 0, true));
                    deck.addCard(c);
                    disableCard(c);
                    usedCard = true;
                    break;
                }
            }
            if (!usedCard) {
                c = player2.getHand().getCardsInHand().get(0);
                disableCard(c);
            }
        } else if(difficulty == NORMAL_DIFFICULTY) {
            Card chosenCard = ai.processAI();
            chosenCard.startOutgoingAnimators();
            //If CARD HAS FINISHED MOVING AND IS IN THE DISCARD PILE, THEN DO BELOW
            if(!ai.isDiscarding()) {
                cardActions.doCardAction(chosenCard.getCardID());
            }
            player2.getHand().removeCardFromHand(chosenCard);
            player2.getHand().addCardToHand(drawNewCard(MenuScreen.SCREEN_WIDTH / 2 - Card.CARD_WIDTH / 2, - Card.CARD_HEIGHT, 0, true));
            deck.addCard(chosenCard);
            disableCard(chosenCard);
            c = chosenCard;
        }
        switchTurns(PLAYER);
    }

    @Override
    public void onClickCard(Card card) {
        if ((card.isAvailable() || isDiscarding()) && playerTurn == PLAYER) {
            card.startOutgoingAnimators();
            //If CARD HAS FINISHED MOVING AND IS IN THE DISCARD PILE, THEN DO BELOW
            if (!isDiscarding()) {
                cardActions.doCardAction(card.getCardID());
                player1.getHand().removeCardFromHand(card);
                int iRange = player1.getHand().getCardsInHand().size();
                for (int i = 0; i <= iRange; i++) {
                    float cardX = MenuScreen.SCREEN_WIDTH / 2 - ((Settings.CARDS_DEALT * Card.CARD_WIDTH) + ((Settings.CARDS_DEALT - 1) * CARD_SPACING)) / 2 + ((i * Card.CARD_WIDTH) + (i * CARD_SPACING));
                    if (i == player1.getHand().getCardsInHand().size()) {
                        player1.getHand().addCardToHand(drawNewCard(cardX, card.getY(), 0, false));
                    } else {
                        Card card1 = player1.getHand().getCardsInHand().get(i);
                        card1.clearAllAnimators();
                        card1.addIncomingAnimator(new MovementAnimator(card, card1.getX(), cardX, 1f, i * 0.1f, Interpolation.ACCELERATE_DECELERATE_INTERPOLATOR, true, false));
                        card1.addIncomingAnimator(new MovementAnimator(card, card1.getY(), CARDS_Y, 1f, i * 0.1f, Interpolation.DECELERATE_INTERPOLATOR, false, true));
                        card1.addOutgoingAnimator(new MovementAnimator(card, cardX, DISCARD_PILE_X, 1f, 0, Interpolation.ACCELERATE_INTERPOLATOR, true, false));
                        card1.addOutgoingAnimator(new MovementAnimator(card, card1.getY(), DISCARD_PILE_Y, 1f, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true));
                        card1.startIncomingAnimators();
                    }
                }
                deck.addCard(card);
            }
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
            card.startIncomingAnimators();
            card.setFlipEffect(new FlipEffect(card, Assets.cardBack, card.getTexture(), 1.0f, FlipEffect.HORIZONTAl));
        return card;
    }

    private void makeCloud(){
        int randomY = (int)(Math.random() * 28) + 60;
        int randomSize = (int)(Math.random() * 10) + 20;
        float randomSpeed = ((int) (Math.random() * 3.0f)) * 2.0f;
        int randomTexture = (int)(Math.random() * 2);

        if(randomTexture == 1){
             cloudList.add(new Cloud(this, Assets.cloud1, randomY, randomSize, randomSpeed));
        } else if (randomTexture == 2){
            cloudList.add(new Cloud(this, Assets.cloud2, randomY, randomSize, randomSpeed));
        } else {
            cloudList.add(new Cloud(this, Assets.cloud3, randomY, randomSize, randomSpeed));
        }

    }

    public ArrayList<Cloud> getCloudList(){
        return cloudList;
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
    public void removeWidgetFromWorld(PopupWidget widget) {
        super.removeWidget(widget);
    }

    @Override
    public void addWidgetToWorld(PopupWidget widget) {
        super.addWidget(widget);
    }

    public Deck getDeck() {
        return deck;
    }

    private void setupSideMenus() {
        //region player menus
        PopupMenu playerBuildMenu = new PopupMenu(Assets.menuArea, 0, -40, 20, 16);
        playerBuildMenu.setColor(buildColor);
        MovementAnimator playerBuildAnimator = new MovementAnimator(playerBuildMenu, 0, 85, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        playerBuildMenu.addIncomingAnimator(playerBuildAnimator);
        playerBuildMenu.addPopupWidget(new PopupImage(Assets.hammer, Assets.invisible, 1, 9, 6, 6, false));
        playerBuildMenu.addPopupWidget(new PopupImage(Assets.stone, Assets.invisible, 1, 1, 6, 6, false));
        playerBuildersLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        playerBuildersLabel.setText(":" + myResources.getPlayerSoldiers());
        playerStoneLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        playerStoneLabel.setText(":" + myResources.getPlayerWeapons());
        playerBuildMenu.addPopupWidget(playerBuildersLabel);
        playerBuildMenu.addPopupWidget(playerStoneLabel);
        this.addPopupMenu(playerBuildMenu);

        //Attack Popup Menu
        PopupMenu playerAttackMenu = new PopupMenu(Assets.menuArea, 0, -40, 20, 16);
        playerAttackMenu.setColor(attackColor);
        MovementAnimator playerAttackAnimator = new MovementAnimator(playerAttackMenu, 0, 67, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        playerAttackMenu.addIncomingAnimator(playerAttackAnimator);
        playerAttackMenu.addPopupWidget(new PopupImage(Assets.helmet, Assets.invisible, 1, 9, 6, 6, false));
        playerAttackMenu.addPopupWidget(new PopupImage(Assets.spear, Assets.invisible, 1, 1, 6, 6, false));
        playerSoldiersLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        playerSoldiersLabel.setText(":" + myResources.getPlayerSoldiers());
        playerWeaponLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        playerWeaponLabel.setText(":" + myResources.getPlayerWeapons());
        playerAttackMenu.addPopupWidget(playerSoldiersLabel);
        playerAttackMenu.addPopupWidget(playerWeaponLabel);
        this.addPopupMenu(playerAttackMenu);

        //Magic Popup Menu
        PopupMenu playerMagicMenu = new PopupMenu(Assets.menuArea, 0, -40, 20, 16);
        playerMagicMenu.setColor(magicColor);
        MovementAnimator playerMagicAnimator = new MovementAnimator(playerMagicMenu, 0, 50, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        playerMagicMenu.addIncomingAnimator(playerMagicAnimator);
        playerMagicMenu.addPopupWidget(new PopupImage(Assets.wand, Assets.invisible, 1, 9, 6, 6, false));
        playerMagicMenu.addPopupWidget(new PopupImage(Assets.gem, Assets.invisible, 1, 1, 6, 6, false));
        playerWizardsLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        playerWizardsLabel.setText(":" + myResources.getPlayerWizards());
        playerGemLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        playerGemLabel.setText(":" + myResources.getPlayerGems());
        playerMagicMenu.addPopupWidget(playerWizardsLabel);
        playerMagicMenu.addPopupWidget(playerGemLabel);
        this.addPopupMenu(playerMagicMenu);

        //Castle Popup Menu
        PopupMenu playerCastleMenu = new PopupMenu(Assets.menuArea, 0, -40, 20, 15);
        playerCastleMenu.setColor(castleColor);
        MovementAnimator playerCastleAnimator = new MovementAnimator(playerCastleMenu, 0, 33, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        playerCastleMenu.addIncomingAnimator(playerCastleAnimator);
        playerCastleMenu.addPopupWidget(new PopupImage(Assets.wand, Assets.invisible, 1, 9, 6, 6, false));
        playerCastleMenu.addPopupWidget(new PopupImage(Assets.gem, Assets.invisible, 1, 1, 6, 6, false));
        playerCastleLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        playerCastleLabel.setText(":" + (int)player1.getCastle().getHealth());
        playerWallLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        playerWallLabel.setText(":" + (int) player1.getCastle().getWall().getHealth());
        playerCastleMenu.addPopupWidget(playerCastleLabel);
        playerCastleMenu.addPopupWidget(playerWallLabel);
        this.addPopupMenu(playerCastleMenu);

        //endregion

        //region Computer Menus

        //Build Popup Menu
        PopupMenu computerBuildMenu = new PopupMenu(Assets.menuArea, 130, -40, 20, 15);
        computerBuildMenu.setColor(buildColor);
        MovementAnimator computerBuildAnimator = new MovementAnimator(computerBuildMenu, 130, 85, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        computerBuildMenu.addIncomingAnimator(computerBuildAnimator);
        computerBuildMenu.addPopupWidget(new PopupImage(Assets.hammer, Assets.invisible, 1, 9, 6, 6, false));
        computerBuildMenu.addPopupWidget(new PopupImage(Assets.stone, Assets.invisible, 1, 1, 6, 6, false));
        computerBuildersLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        computerBuildersLabel.setText(":" + myResources.getComputerSoldiers());
        computerStoneLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        computerStoneLabel.setText(":" + myResources.getComputerWeapons());
        computerBuildMenu.addPopupWidget(computerBuildersLabel);
        computerBuildMenu.addPopupWidget(computerStoneLabel);
        this.addPopupMenu(computerBuildMenu);

        //Attack Popup Menu
        PopupMenu computerAttackMenu = new PopupMenu(Assets.menuArea, 130, -40, 20, 15);
        computerAttackMenu.setColor(attackColor);
        MovementAnimator computerAttackAnimator = new MovementAnimator(computerAttackMenu, 130, 67, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        computerAttackMenu.addIncomingAnimator(computerAttackAnimator);
        computerAttackMenu.addPopupWidget(new PopupImage(Assets.helmet, Assets.invisible, 1, 8, 6, 6, false));
        computerAttackMenu.addPopupWidget(new PopupImage(Assets.spear, Assets.invisible, 1, 1, 6, 6, false));
        computerSoldiersLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        computerSoldiersLabel.setText(":" + myResources.getComputerSoldiers());
        computerWeaponLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        computerWeaponLabel.setText(":" + myResources.getComputerWeapons());
        computerAttackMenu.addPopupWidget(computerSoldiersLabel);
        computerAttackMenu.addPopupWidget(computerWeaponLabel);
        this.addPopupMenu(computerAttackMenu);

        //magic Popup Menu
        PopupMenu computerMagicMenu = new PopupMenu(Assets.menuArea, 130, -40, 20, 15);
        computerMagicMenu.setColor(magicColor);
        MovementAnimator computerMagicAnimator = new MovementAnimator(computerMagicMenu, 130, 50, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        computerMagicMenu.addIncomingAnimator(computerMagicAnimator);
        computerMagicMenu.addPopupWidget(new PopupImage(Assets.wand, Assets.invisible, 1, 9, 6, 6, false));
        computerMagicMenu.addPopupWidget(new PopupImage(Assets.gem, Assets.invisible, 1, 1, 6, 6, false));
        computerWizardsLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        computerWizardsLabel.setText(":" + myResources.getComputerWizards());
        computerGemLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        computerGemLabel.setText(":" + myResources.getComputerGems());
        computerMagicMenu.addPopupWidget(computerWizardsLabel);
        computerMagicMenu.addPopupWidget(computerGemLabel);
        this.addPopupMenu(computerMagicMenu);

        //Castle Popup Menu
        PopupMenu computerCastleMenu = new PopupMenu(Assets.menuArea, 130, -40, 20, 15);
        computerCastleMenu.setColor(castleColor);
        MovementAnimator computerCastleAnimator = new MovementAnimator(computerCastleMenu, 130, 33, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        computerCastleMenu.addIncomingAnimator(computerCastleAnimator);
        computerCastleMenu.addPopupWidget(new PopupImage(Assets.wand, Assets.invisible, 1, 9, 6, 6, false));
        computerCastleMenu.addPopupWidget(new PopupImage(Assets.gem, Assets.invisible, 1, 1, 6, 6, false));
        computerCastleLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        computerCastleLabel.setText(":" + (int) player2.getCastle().getHealth());
        computerWallLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, MENU_TEXT_WIDTH, MENU_TEXT_HEIGHT);
        computerWallLabel.setText(":" + (int) player2.getCastle().getWall().getHealth());
        computerCastleMenu.addPopupWidget(computerCastleLabel);
        computerCastleMenu.addPopupWidget(computerWallLabel);
        this.addPopupMenu(computerCastleMenu);
        //endregion

        playerBuildMenu.startIncomingAnimators();
        playerAttackMenu.startIncomingAnimators();
        playerMagicMenu.startIncomingAnimators();
        playerCastleMenu.startIncomingAnimators();
        computerBuildMenu.startIncomingAnimators();
        computerAttackMenu.startIncomingAnimators();
        computerMagicMenu.startIncomingAnimators();
        computerCastleMenu.startIncomingAnimators();

        float playerLabelX = 22;

        playerBuildersLabelChange = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, playerLabelX, playerBuildAnimator.getEndPos() + 9, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT);
        playerBuildersLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        playerBuildersLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        playerStoneLabelChange = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, 22, playerBuildAnimator.getEndPos() + 1, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT);
        playerStoneLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        playerStoneLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        playerSoldiersLabelChange = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, playerLabelX, playerAttackAnimator.getEndPos() + 9, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT);
        playerSoldiersLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        playerSoldiersLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        playerWeaponLabelChange = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, playerLabelX, playerAttackAnimator.getEndPos() + 1, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT);
        playerWeaponLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        playerWeaponLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        playerWizardsLabelChange = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, playerLabelX, playerMagicAnimator.getEndPos() + 9, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT);
        playerWizardsLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        playerWizardsLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        playerGemLabelChange = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, playerLabelX, playerMagicAnimator.getEndPos() + 1, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT);
        playerGemLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        playerGemLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        playerCastleLabelChange = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, playerLabelX, playerCastleAnimator.getEndPos() + 9, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT);
        playerCastleLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        playerCastleLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        playerWallLabelChange = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, playerLabelX, playerCastleAnimator.getEndPos() + 1, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT);
        playerWallLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        playerWallLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));

        float computerLabelX = computerAttackMenu.getX() - CHANGE_TEXT_WIDTH - 2;

        computerBuildersLabelChange = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, computerLabelX, computerBuildAnimator.getEndPos() + 1, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT, "", BitmapFont.HAlignment.RIGHT);
        computerBuildersLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        computerBuildersLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        computerStoneLabelChange = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, computerLabelX, computerBuildAnimator.getEndPos() + 1, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT, "", BitmapFont.HAlignment.RIGHT);
        computerStoneLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        computerStoneLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        computerSoldiersLabelChange = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, computerLabelX, computerAttackAnimator.getEndPos() + 9, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT, "", BitmapFont.HAlignment.RIGHT);
        computerSoldiersLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        computerSoldiersLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        computerWeaponLabelChange = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, computerLabelX, computerAttackAnimator.getEndPos() + 1, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT, "", BitmapFont.HAlignment.RIGHT);
        computerWeaponLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        computerWeaponLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        computerWizardsLabelChange = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, computerLabelX, computerMagicAnimator.getEndPos() + 9, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT, "", BitmapFont.HAlignment.RIGHT);
        computerWizardsLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        computerWizardsLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        computerGemLabelChange = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, computerLabelX, computerMagicAnimator.getEndPos() + 1, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT, "", BitmapFont.HAlignment.RIGHT);
        computerGemLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        computerGemLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        computerCastleLabelChange = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, computerLabelX, computerCastleAnimator.getEndPos() + 9, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT, "", BitmapFont.HAlignment.RIGHT);
        computerCastleLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 0), new Color(1, 1, 1, 1), FADE_IN_DURATION));
        computerCastleLabelChange.addFontColorChanger(new ColorEffects(new Color(1, 1, 1, 1), new Color(1, 1, 1, 0), FADE_DELAY, FADE_OUT_DURATION));
        computerWallLabelChange = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, computerLabelX, computerCastleAnimator.getEndPos() + 1, CHANGE_TEXT_WIDTH, CHANGE_TEXT_HEIGHT, "", BitmapFont.HAlignment.RIGHT);
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

        this.discardToggle = new PopupToggleButton(Assets.trashCanSelected, Assets.trashCan, 5, 5, 10, 15, false);

        this.addWidgetToWorld(discardToggle);
    }

    private void buildDifficultyGUI(){
        float menuWidth = (GameScreen.SCREEN_WIDTH * .75f);
        float menuHeight = (GameScreen.SCREEN_HEIGHT * .75f);
        difficultyMenu = new PopupMenu(Assets.popupMenuBackground,(GameScreen.SCREEN_WIDTH/2) - (GameScreen.SCREEN_WIDTH * .75f)/2, -200, menuWidth, menuHeight);
        MovementAnimator yAnimator = new MovementAnimator(difficultyMenu, -200, (GameScreen.SCREEN_HEIGHT/2) - (GameScreen.SCREEN_HEIGHT * .75f)/3, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        difficultyMenu.addIncomingAnimator(yAnimator);
        MovementAnimator yAnimator2 = new MovementAnimator(difficultyMenu, (GameScreen.SCREEN_HEIGHT/2) - (GameScreen.SCREEN_HEIGHT * .75f)/3, -200, 1, 0, Interpolation.ANTICIPATE_INTERPOLATOR, false, true);
        difficultyMenu.addOutgoingAnimator(yAnimator2);


        easyButton = new PopupToggleButton(Assets.toggleButtonOn, Assets.toggleButtonOff, menuWidth/6, (menuHeight/3), menuWidth/6, (menuWidth/6), false);
        easyButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                easyButton.turnOn();
                if(normalButton.isOn()){
                    normalButton.turnOff();
                }}
        });
        normalButton = new PopupToggleButton(Assets.toggleButtonOn, Assets.toggleButtonOff, menuWidth/4 * 2.5f, (menuHeight/3), menuWidth/6, (menuWidth/6), false);
        normalButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                    normalButton.turnOn();
                    if(easyButton.isOn()){
                        easyButton.turnOff();
                    }             }

        });

        easyLabel = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, menuWidth/7, (menuHeight/3) *2, menuWidth/4, (menuHeight/8), "Easy");
        normalLabel = new PopupTextLabel(Assets.invisible, Color.BLACK, Assets.textFieldFont, menuWidth/4 * 2.3f, (menuHeight/3)*2, menuWidth/3f, (menuHeight/8), "Normal");
        okButton = new PopupButtonMaterial(Assets.okButton, menuWidth / 2 - (menuWidth / 4) / 1.75f, menuHeight / 4 - menuHeight / 6, 2, menuWidth / 4, menuHeight / 6);
        okButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                if (easyButton.isOn()) {
                    difficulty = EASY_DIFFICULTY;
                } {
                    difficulty = NORMAL_DIFFICULTY;
                }
                difficultyMenu.startOutgoingAnimators();
                removeWidgetFromWorld(difficultyMenu);
            }
        });
        difficultyMenu.addPopupWidget(easyButton);
        difficultyMenu.addPopupWidget(normalButton);
        difficultyMenu.addPopupWidget(easyLabel);
        difficultyMenu.addPopupWidget(normalLabel);
        difficultyMenu.addPopupWidget(okButton);
        addPopupMenu(difficultyMenu);
        difficultyMenu.startIncomingAnimators();
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

    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }
}
