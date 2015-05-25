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
import com.desitum.castleWars.libraries.menu.PopupMenu;
import com.desitum.castleWars.libraries.menu.PopupWidget;
import com.desitum.castleWars.libraries.world.KodyWorld;
import com.desitum.castleWars.objects.Card;
import com.desitum.castleWars.objects.Deck;
import com.desitum.castleWars.objects.Player;
import com.desitum.castleWars.screens.MenuScreen;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public class GameWorld extends KodyWorld implements GameInterface {

    private int playerTurn;
    public static final int PLAYER = 0;
    public static final int PLAYER2 = 1;

    private Player player1;
    private Player player2;
    private ComputerAI ai;

    private Deck deck;

    private Resources myResources;
    private CardActions cardActions;

    private PopupMenu playerBuildMenu;
    private PopupMenu playerAttackMenu;
    private PopupMenu playerMagicMenu;
    private PopupMenu playerCastleMenu;
    private PopupMenu computerBuildMenu;
    private PopupMenu computerAttackMenu;
    private PopupMenu computerMagicMenu;
    private PopupMenu computerCastleMenu;

    private static Color buildColor = new Color(.122f, 0f, .616f, 1);
    private static Color attackColor = new Color(.855f, 0f, .102f, 1);
    private static Color magicColor = new Color(.035f, .722f, 0, 1);
    private static Color castleColor = new Color(1, 1, 1, 1);

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
        myResources = new Resources();
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

    public void setGameMode() {

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
        if (addToPlayer2 != null) player1.getHand().addCardToHand(drawNewCard(MenuScreen.SCREEN_WIDTH/2 - Card.CARD_WIDTH/2, -Card.CARD_HEIGHT, 0));

        for (Card c: deck.getCardList()) {
            c.update(delta);
        }

        super.update(delta);

        if (playerTurn == PLAYER2) {
            System.out.println("Computers turn: " + computerDelay);
            computerDelay -= delta;
            if (computerDelay < 0) {
                computerDelay = Settings.COMPUTER_DELAY;
                computerTurn();
            }
        }

        player1.update(delta);
        player2.update(delta);
    }

    private void switchTurns(int playerTurn) {
        this.playerTurn = playerTurn;

        if (getPlayerTurn() == PLAYER) {
            myResources.adjustPlayerStones(2 * myResources.getPlayerBuilders());
            myResources.adjustPlayerGems(2 * myResources.getPlayerWizards());
            myResources.adjustPlayerWeapons(2 * myResources.getPlayerSoldiers());
        } else {
            myResources.adjustComputerStones(2 * myResources.getComputerBuilders());
            myResources.adjustComputerGems(2 * myResources.getPlayerWizards());
            myResources.adjustComputerWeapons(2 * myResources.getComputerSoldiers());
        }
    }

    public void reset() {

    }

    private void computerTurn() {
        Card chosenCard = ai.processAI();
        processTurn(chosenCard);
        cardActions.doCardAction(chosenCard.getCardID());
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

        System.out.println("Card Used: " + card + "Card ID: " + card.getCardID());
        System.out.println("Player Weapons: " + myResources.getPlayerWeapons());
        System.out.println("Player Stones: " + myResources.getPlayerStones());
        System.out.println("Player Gems: " + myResources.getPlayerGems());
        System.out.println("Computer Weapons: " + myResources.getComputerWeapons());
        System.out.println("Computer Weapons: " + myResources.getComputerStones());
        System.out.println("Computer Weapons: " + myResources.getComputerGems());
        System.out.println("Player Builders: " + myResources.getPlayerBuilders());
        System.out.println("Player Wizards: " + myResources.getPlayerWizards());
        System.out.println("Player Soldiers: " + myResources.getPlayerSoldiers());
        System.out.println("Computer Builders: " + myResources.getComputerBuilders());
        System.out.println("Computer Wizards: " + myResources.getComputerWizards());
        System.out.println("Computer Soldiers: " + myResources.getComputerSoldiers());
        System.out.println("Player Castle: " + player1.getCastle().getHealth());
        System.out.println("Player Wall: " + player1.getCastle().getWall().getHealth());
        System.out.println("Computer Castle: " + player2.getCastle().getHealth());
        System.out.println("Computer Wall: " + player2.getCastle().getWall().getHealth());
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

    public Deck getDeck() {
        return deck;
    }

    private void setupSideMenus() {
        //region player menus
        playerBuildMenu = new PopupMenu(Assets.menuArea, 0, -40, 20, 15);
        playerBuildMenu.setColor(buildColor);
        MovementAnimator playerBuildAnimator = new MovementAnimator(playerBuildMenu, 0, 80, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        playerBuildMenu.addIncomingAnimator(playerBuildAnimator);
        this.addPopupMenu(playerBuildMenu);

        //Attack Popup Menu
        playerAttackMenu = new PopupMenu(Assets.menuArea, 0, -40, 20, 15);
        playerAttackMenu.setColor(attackColor);
        MovementAnimator playerAttackAnimator = new MovementAnimator(playerAttackMenu, 0, 65, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        playerAttackMenu.addIncomingAnimator(playerAttackAnimator);
        this.addPopupMenu(playerAttackMenu);

        //Magic Popup Menu
        playerMagicMenu = new PopupMenu(Assets.menuArea, 0, -40, 20, 15);
        playerMagicMenu.setColor(magicColor);
        MovementAnimator playerMagicAnimator = new MovementAnimator(playerMagicMenu, 0, 50, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        playerMagicMenu.addIncomingAnimator(playerMagicAnimator);
        this.addPopupMenu(playerMagicMenu);

        //Castle Popup Menu
        playerCastleMenu = new PopupMenu(Assets.menuArea, 0, -40, 20, 15);
        playerCastleMenu.setColor(castleColor);
        MovementAnimator playerCastleAnimator = new MovementAnimator(playerCastleMenu, 0, 35, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        playerCastleMenu.addIncomingAnimator(playerCastleAnimator);
        this.addPopupMenu(playerCastleMenu);

        //endregion

        //region Computer Menus

        //Build Popup Menu
        computerBuildMenu = new PopupMenu(Assets.menuArea, 130, -40, 20, 15);
        computerBuildMenu.setColor(buildColor);
        MovementAnimator computerBuildAnimator = new MovementAnimator(computerBuildMenu, 130, 80, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        computerBuildMenu.addIncomingAnimator(computerBuildAnimator);
        this.addPopupMenu(computerBuildMenu);

        //Attack Popup Menu
        computerAttackMenu = new PopupMenu(Assets.menuArea, 130, -40, 20, 15);
        computerAttackMenu.setColor(attackColor);
        MovementAnimator computerAttackAnimator = new MovementAnimator(computerAttackMenu, 130, 65, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        computerAttackMenu.addIncomingAnimator(computerAttackAnimator);
        this.addPopupMenu(computerAttackMenu);

        //magic Popup Menu
        computerMagicMenu = new PopupMenu(Assets.menuArea, 130, -40, 20, 15);
        computerMagicMenu.setColor(magicColor);
        MovementAnimator computerMagicAnimator = new MovementAnimator(computerMagicMenu, 130, 50, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        computerMagicMenu.addIncomingAnimator(computerMagicAnimator);
        this.addPopupMenu(computerMagicMenu);

        //Castle Popup Menu
        computerCastleMenu = new PopupMenu(Assets.menuArea, 130, -40, 20, 15);
        computerCastleMenu.setColor(castleColor);
        MovementAnimator computerCastleAnimator = new MovementAnimator(computerCastleMenu, 130, 35, 1, 0, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        computerCastleMenu.addIncomingAnimator(computerCastleAnimator);
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
