package com.desitum.castleWars.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.data.CardActions;
import com.desitum.castleWars.data.ComputerAI;
import com.desitum.castleWars.data.Resources;
import com.desitum.castleWars.data.Settings;
import com.desitum.castleWars.libraries.animation.MovementAnimator;
import com.desitum.castleWars.libraries.interpolation.Interpolation;
import com.desitum.castleWars.libraries.menu.OnClickListener;
import com.desitum.castleWars.libraries.menu.PopupImage;
import com.desitum.castleWars.libraries.menu.PopupMenu;
import com.desitum.castleWars.libraries.menu.PopupTextLabel;
import com.desitum.castleWars.libraries.menu.PopupWidget;
import com.desitum.castleWars.libraries.world.KodyWorld;
import com.desitum.castleWars.objects.Card;
import com.desitum.castleWars.objects.Deck;
import com.desitum.castleWars.objects.Player;
import com.desitum.castleWars.screens.MenuScreen;

/**
 * Created by Zmyth97 on 2/25/2015.
 * can be used by Zmyth97 and people in [Zmyth97}]
 */
public class GameWorld extends KodyWorld implements GameInterface {

    private int playerTurn;
    public static final int PLAYER = 0;
    public static final int PLAYER2 = 1;

    private int EASY_DIFFICULTY = 0;
    private int HARD_DIFFICULTY = 1;


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

    private int difficulty;

    private Deck deck;

    private Resources myResources;
    private CardActions cardActions;

    private static Color buildColor = new Color(.122f, 0f, .616f, 1);
    private static Color attackColor = new Color(.855f, 0f, .102f, 1);
    private static Color magicColor = new Color(.035f, .722f, 0, 1);
    private static Color castleColor = new Color(0.18f, 0.18f, 0.192f, 1);

    public static final float DRAW_PILE_X = MenuScreen.SCREEN_WIDTH/2 - Card.CARD_WIDTH - 1.25f;
    public static final float DRAW_PILE_Y = MenuScreen.SCREEN_HEIGHT - Card.CARD_HEIGHT - 2.5f;
    public static final float DISCARD_PILE_X = MenuScreen.SCREEN_WIDTH/2 + 1.25f;
    public static final float DISCARD_PILE_Y = MenuScreen.SCREEN_HEIGHT - Card.CARD_HEIGHT - 2.5f;
    public static final float CARD_SPACING = 2.5f;
    public static final float CARDS_Y = 5;

    private float computerDelay;

    public GameWorld(Viewport cam) {
        super();
        super.setCam(cam);
        playerTurn = PLAYER2;
        player1 = new Player(this);
        player2 = new Player(this);
        deck = new Deck();
        for (Card card: deck.getCardList()) {
            card.setButtonListener(new OnClickListener() {
                @Override
                public void onClick(PopupWidget widget) {
                    System.out.println("Clicked on");
                    Card card = (Card) widget;
                    onClickCard(card);
                }
            });
        }
        cardActions = new CardActions(this);
        myResources = new Resources(this);

        difficulty = 0;

        ai = new ComputerAI(this);
        computerDelay = Settings.COMPUTER_DELAY;

        setupSideMenus();

        //Fill Both Players Hands At Start
        for (int i = 0; i < Settings.CARDS_DEALT; i++) {
            float cardX = MenuScreen.SCREEN_WIDTH/2 - ((Settings.CARDS_DEALT * Card.CARD_WIDTH) + ((Settings.CARDS_DEALT - 1) * CARD_SPACING))/2 + ((i * Card.CARD_WIDTH) + (i * CARD_SPACING));
            player1.getHand().addCardToHand(drawNewCard(cardX, CARDS_Y, i * 0.2f));
            player2.getHand().addCardToHand(drawNewCard(MenuScreen.SCREEN_WIDTH/2 - Card.CARD_WIDTH/2, -Card.CARD_HEIGHT, i * 0.2f + 0.1f));
        }
    }

    public void update(float delta) {
        Card addToPlayer1 = null;
        Card addToPlayer2 = null;
        for (Card card : this.getDeck().getCardList()) {
            if (player1.containsCard(card)) {
                card.startOutgoingAnimators();
                player1.getHand().removeCardFromHand(card);
                addToPlayer1 = card;
                switchTurns(PLAYER2);
            } else if (player2.containsCard(card)) {
                card.startOutgoingAnimators();
                player2.getHand().removeCardFromHand(card);
                addToPlayer2 = card;
                switchTurns(PLAYER);
            }
        }

        if (addToPlayer1 != null) player1.getHand().addCardToHand(drawNewCard(addToPlayer1.getX(), addToPlayer1.getY(), 0));
        if (addToPlayer2 != null) player2.getHand().addCardToHand(drawNewCard(MenuScreen.SCREEN_WIDTH/2 - Card.CARD_WIDTH/2, -Card.CARD_HEIGHT, 0));

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

        playerBuildersLabel.setText(":" + myResources.getPlayerBuilders());
        playerSoldiersLabel.setText(":" + myResources.getPlayerSoldiers());
        playerWizardsLabel.setText(":" + myResources.getPlayerWizards());
        playerStoneLabel.setText(":" + myResources.getPlayerStones());
        playerWeaponLabel.setText(":" + myResources.getPlayerWeapons());
        playerGemLabel.setText(":" + myResources.getPlayerGems());
        playerCastleLabel.setText(":" +(int)  player1.getCastle().getHealth());
        playerWallLabel.setText(":" +(int)  player1.getCastle().getWall().getHealth());
        computerBuildersLabel.setText(":" + myResources.getPlayerBuilders());
        computerSoldiersLabel.setText(":" + myResources.getPlayerSoldiers());
        computerWizardsLabel.setText(":" + myResources.getPlayerWizards());
        computerStoneLabel.setText(":" + myResources.getPlayerStones());
        computerWeaponLabel.setText(":" + myResources.getPlayerWeapons());
        computerGemLabel.setText(":" + myResources.getPlayerGems());
        computerCastleLabel.setText(":" +(int)  player2.getCastle().getHealth());
        computerWallLabel.setText(":" +(int)  player2.getCastle().getWall().getHealth());
    }

    private void switchTurns(int playerTurn) {
        this.playerTurn = playerTurn;

        if (getPlayerTurn() == PLAYER) {
            myResources.adjustPlayerStones(2 * myResources.getPlayerBuilders());
            myResources.adjustPlayerGems(2 * myResources.getPlayerWizards());
            myResources.adjustPlayerWeapons(2 * myResources.getPlayerSoldiers());
        } else {
            myResources.adjustComputerStones(2 * myResources.getComputerBuilders());
            myResources.adjustComputerGems(2 * myResources.getComputerWizards());
            myResources.adjustComputerWeapons(2 * myResources.getComputerSoldiers());
        }
    }

    private void computerTurn() {
        if(difficulty == EASY_DIFFICULTY){
            boolean usedCard = false;
            for (Card c: player2.getHand().getCardsInHand()) {
                if (c.isAvailable()) {
                    cardActions.doCardAction(c.getCardID());
                    processTurn(c);
                    usedCard = true;
                }
            }
            if (!usedCard) {
                Card c = player2.getHand().getCardsInHand().get(0);
                processTurn(c);
            }
        } else if(difficulty == HARD_DIFFICULTY) {
            Card chosenCard = ai.processAI();
            processTurn(chosenCard);
            cardActions.doCardAction(chosenCard.getCardID());
        }
    }

    @Override
    public void onClickCard(Card card) {
        System.out.println(card.isAvailable());
        System.out.println(playerTurn == PLAYER);
        if (card.isAvailable() && playerTurn == PLAYER) {
            cardActions.doCardAction(card.getCardID());
            processTurn(card);
        }
    }

    private void processTurn(Card card) {
        card.enable();
        deck.addCard(card);
    }

    private Card drawNewCard(float x, float y, float delay) {
        Card card = deck.drawCard();
        card.addIncomingAnimator(new MovementAnimator(card, DRAW_PILE_X, x, 0.5f, delay, Interpolation.ACCELERATE_INTERPOLATOR, true, false));
        card.addIncomingAnimator(new MovementAnimator(card, DRAW_PILE_Y, y, 0.5f, delay, Interpolation.DECELERATE_INTERPOLATOR, false, true));
        card.addOutgoingAnimator(new MovementAnimator(card, x, DISCARD_PILE_X, 0.5f, 0, Interpolation.ACCELERATE_INTERPOLATOR, true, false));
        card.addOutgoingAnimator(new MovementAnimator(card, y, DISCARD_PILE_Y, 0.5f, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true));
        card.startIncomingAnimators();
        return card;
    }

    @Override
    public Resources getResources() {
        return myResources;
    }

    @Override
    public int getPlayerTurn() {
        return PLAYER;
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
        MovementAnimator playerBuildAnimator = new MovementAnimator(playerBuildMenu, 0, 84, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        playerBuildMenu.addIncomingAnimator(playerBuildAnimator);
        playerBuildMenu.addPopupWidget(new PopupImage(Assets.hammer, Assets.invisible, 1, 9, 6, 6, false));
        playerBuildMenu.addPopupWidget(new PopupImage(Assets.stone, Assets.invisible, 1, 1, 6, 6, false));
        playerBuildersLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, 12, 6);
        playerBuildersLabel.setText(":" + myResources.getPlayerSoldiers());
        playerStoneLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, 12, 6);
        playerStoneLabel.setText(":" + myResources.getPlayerWeapons());
        playerBuildMenu.addPopupWidget(playerBuildersLabel);
        playerBuildMenu.addPopupWidget(playerStoneLabel);
        this.addPopupMenu(playerBuildMenu);

        //Attack Popup Menu
        PopupMenu playerAttackMenu = new PopupMenu(Assets.menuArea, 0, -40, 20, 16);
        playerAttackMenu.setColor(attackColor);
        MovementAnimator playerAttackAnimator = new MovementAnimator(playerAttackMenu, 0, 67, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        playerAttackMenu.addIncomingAnimator(playerAttackAnimator);
        playerAttackMenu.addPopupWidget(new PopupImage(Assets.spear, Assets.invisible, 1, 9, 6, 6, false));
        playerAttackMenu.addPopupWidget(new PopupImage(Assets.steak, Assets.invisible, 1, 1, 6, 6, false));
        playerSoldiersLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, 12, 6);
        playerSoldiersLabel.setText(":" + myResources.getPlayerSoldiers());
        playerWeaponLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, 12, 6);
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
        playerWizardsLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, 12, 6);
        playerWizardsLabel.setText(":" + myResources.getPlayerWizards());
        playerGemLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, 12, 6);
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
        playerCastleLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, 12, 6);
        playerCastleLabel.setText(":" + (int)player1.getCastle().getHealth());
        playerWallLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, 12, 6);
        playerWallLabel.setText(":" + (int) player1.getCastle().getWall().getHealth());
        playerCastleMenu.addPopupWidget(playerCastleLabel);
        playerCastleMenu.addPopupWidget(playerWallLabel);
        this.addPopupMenu(playerCastleMenu);

        //endregion

        //region Computer Menus

        //Build Popup Menu
        PopupMenu computerBuildMenu = new PopupMenu(Assets.menuArea, 130, -40, 20, 15);
        computerBuildMenu.setColor(buildColor);
        MovementAnimator computerBuildAnimator = new MovementAnimator(computerBuildMenu, 130, 80, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        computerBuildMenu.addIncomingAnimator(computerBuildAnimator);
        computerBuildMenu.addPopupWidget(new PopupImage(Assets.hammer, Assets.invisible, 1, 9, 6, 6, false));
        computerBuildMenu.addPopupWidget(new PopupImage(Assets.stone, Assets.invisible, 1, 1, 6, 6, false));
        computerBuildersLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, 12, 6);
        computerBuildersLabel.setText(":" + myResources.getComputerSoldiers());
        computerStoneLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, 12, 6);
        computerStoneLabel.setText(":" + myResources.getComputerWeapons());
        computerBuildMenu.addPopupWidget(computerBuildersLabel);
        computerBuildMenu.addPopupWidget(computerStoneLabel);
        this.addPopupMenu(computerBuildMenu);

        //Attack Popup Menu
        PopupMenu computerAttackMenu = new PopupMenu(Assets.menuArea, 130, -40, 20, 15);
        computerAttackMenu.setColor(attackColor);
        MovementAnimator computerAttackAnimator = new MovementAnimator(computerAttackMenu, 130, 65, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        computerAttackMenu.addIncomingAnimator(computerAttackAnimator);
        computerAttackMenu.addPopupWidget(new PopupImage(Assets.spear, Assets.invisible, 1, 9, 6, 6, false));
        computerAttackMenu.addPopupWidget(new PopupImage(Assets.steak, Assets.invisible, 1, 1, 6, 6, false));
        computerSoldiersLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, 12, 6);
        computerSoldiersLabel.setText(":" + myResources.getComputerSoldiers());
        computerWeaponLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, 12, 6);
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
        computerWizardsLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, 12, 6);
        computerWizardsLabel.setText(":" + myResources.getComputerWizards());
        computerGemLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, 12, 6);
        computerGemLabel.setText(":" + myResources.getComputerGems());
        computerMagicMenu.addPopupWidget(computerWizardsLabel);
        computerMagicMenu.addPopupWidget(computerGemLabel);
        this.addPopupMenu(computerMagicMenu);

        //Castle Popup Menu
        PopupMenu computerCastleMenu = new PopupMenu(Assets.menuArea, 130, -40, 20, 15);
        computerCastleMenu.setColor(castleColor);
        MovementAnimator computerCastleAnimator = new MovementAnimator(computerCastleMenu, 130, 35, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        computerCastleMenu.addIncomingAnimator(computerCastleAnimator);
        computerCastleMenu.addPopupWidget(new PopupImage(Assets.wand, Assets.invisible, 1, 9, 6, 6, false));
        computerCastleMenu.addPopupWidget(new PopupImage(Assets.gem, Assets.invisible, 1, 1, 6, 6, false));
        computerCastleLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 9, 12, 6);
        computerCastleLabel.setText(":" + (int) player2.getCastle().getHealth());
        computerWallLabel = new PopupTextLabel(Assets.invisible, new Color(0, 0, 0, 0), Assets.textFieldFont, 8, 1, 12, 6);
        computerWallLabel.setText(":" + (int) player2.getCastle().getWall().getHealth());
        computerCastleMenu.addPopupWidget(computerCastleLabel);
        computerCastleMenu.addPopupWidget(computerWallLabel);
        this.addPopupMenu(computerCastleMenu);
        //endregion

        playerBuildMenu.moveIn();
        playerAttackMenu.moveIn();
        playerMagicMenu.moveIn();
        playerCastleMenu.moveIn();
        computerBuildMenu.moveIn();
        computerAttackMenu.moveIn();
        computerMagicMenu.moveIn();
        computerCastleMenu.moveIn();
    }
}
