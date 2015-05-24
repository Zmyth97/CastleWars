package com.desitum.castleWars.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.data.CardActions;
import com.desitum.castleWars.libraries.animation.Animator;
import com.desitum.castleWars.libraries.animation.MovementAnimator;
import com.desitum.castleWars.libraries.interpolation.Interpolation;
import com.desitum.castleWars.libraries.menu.PopupMenu;
import com.desitum.castleWars.libraries.world.KodyWorld;
import com.desitum.castleWars.objects.Card;
import com.desitum.castleWars.objects.Deck;
import com.desitum.castleWars.objects.Player;
import com.desitum.castleWars.data.Resources;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public class GameWorld extends KodyWorld implements GameInterface {

    public static final int PLAYER = 0;
    public static final int PLAYER2 = 1;

    public static final int CARDSLOT1 = 1; //These Slots are the X coords to put the Cards
    public static final int CARDSLOT2 = 11;
    public static final int CARDSLOT3 = 21;
    public static final int CARDSLOT4 = 31;
    public static final int CARDSLOT5 = 41;
    public static final int CARDSLOT6 = 51;
    public static final int CARDSLOT7 = 61;
    public static final int CARDSLOT8 = 71;

    private boolean slot1Available;
    private boolean slot2Available;
    private boolean slot3Available;
    private boolean slot4Available;
    private boolean slot5Available;
    private boolean slot6Available;
    private boolean slot7Available;
    private boolean slot8Available;

    private GameInterface gi;

    private Player player1;
    private Player player2;

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

    public static final float DRAW_PILE_X = 60;
    public static final float DRAW_PILE_Y = 74;
    public static final float DISCARD_PILE_X = 76;
    public static final float DISCARD_PILE_Y = 74;


    public GameWorld(Viewport cam) {
        super();
        super.setCam(cam);
        player1 = new Player();
        player2 = new Player();
        deck = new Deck();
        cardActions = new CardActions(gi);
        myResources = new Resources();

        slot1Available = false;
        slot2Available = false;
        slot3Available = false;
        slot4Available = false;
        slot5Available = false;
        slot6Available = false;
        slot7Available = false;
        slot8Available = false;

        //region Player Menus

        //Build Popup Menu
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

        //Fill Both Players Hands At Start
        for (int cards = 0; cards < 8; cards++) {
            player1.getHand().addCardToHand(deck.getCardList().get(0));
            deck.getCardList().remove(0);
            player2.getHand().addCardToHand(deck.getCardList().get(0));
            deck.getCardList().remove(0);
        }

    }

    public void setGameMode() {

    }

    public void update(int state, OrthographicCamera cam, float delta) {
        for (Card card : this.getDeck().getCardList()) {
            card.update(delta);
        }
    }

    public void reset() {

    }

    @Override
    public void onClickCard(Card card, int cardID) {
        if (card.isAvailable()) {
            card.addMoveAnimtor((Animator) new MovementAnimator(card, card.getX(), DISCARD_PILE_X, 0.5f, 0, Interpolation.DECELERATE_INTERPOLATOR, true, false));
            //Once Card has Reached Draw Pile Then Do The Method Below
            processTurn(card, cardID);
        }
    }

    private void processTurn(Card card, int cardID) {
        cardActions.doCardAction(cardID);
        if (card.getX() == CARDSLOT1) {
            slot1Available = true;
        } else if (card.getX() == CARDSLOT2) {
            slot2Available = true;
        } else if (card.getX() == CARDSLOT3) {
            slot3Available = true;
        } else if (card.getX() == CARDSLOT4) {
            slot4Available = true;
        } else if (card.getX() == CARDSLOT5) {
            slot5Available = true;
        } else if (card.getX() == CARDSLOT6) {
            slot6Available = true;
        } else if (card.getX() == CARDSLOT7) {
            slot7Available = true;
        } else {
            slot8Available = true;
        }
        drawNewCard();


        if (gi.getPlayerTurn() == PLAYER) {
            for (int count = 0; count < myResources.getPlayerBuilders(); count++) {
                myResources.adjustPlayerStones(2);
            }
            for (int count = 0; count < myResources.getPlayerWizards(); count++) {
                myResources.adjustPlayerGems(2);
            }
            for (int count = 0; count < myResources.getPlayerSoldiers(); count++) {
                myResources.adjustPlayerWeapons(2);
            }
        } else {
            for (int count = 0; count < myResources.getComputerBuilders(); count++) {
                myResources.adjustComputerStones(2);
            }
            for (int count = 0; count < myResources.getComputerWizards(); count++) {
                myResources.adjustComputerGems(2);
            }
            for (int count = 0; count < myResources.getComputerSoldiers(); count++) {
                myResources.adjustComputerWeapons(2);
            }
        }


        System.out.println("Card Used: " + card + "Card ID: " + cardID);
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

    private void drawNewCard() {
        Card card = deck.drawCard();
        if (slot1Available) {
            card.addMoveAnimtor((Animator) new MovementAnimator(card, card.getX(), CARDSLOT1, 0.5f, 0, Interpolation.DECELERATE_INTERPOLATOR, true, false));
            slot1Available = false;
        } else if (slot2Available) {
            card.addMoveAnimtor((Animator) new MovementAnimator(card, card.getX(), CARDSLOT2, 0.5f, 0, Interpolation.DECELERATE_INTERPOLATOR, true, false));
            slot2Available = false;
        } else if (slot3Available) {
            card.addMoveAnimtor((Animator) new MovementAnimator(card, card.getX(), CARDSLOT3, 0.5f, 0, Interpolation.DECELERATE_INTERPOLATOR, true, false));
            slot3Available = false;
        } else if (slot4Available) {
            card.addMoveAnimtor((Animator) new MovementAnimator(card, card.getX(), CARDSLOT4, 0.5f, 0, Interpolation.DECELERATE_INTERPOLATOR, true, false));
            slot4Available = false;
        } else if (slot5Available) {
            card.addMoveAnimtor((Animator) new MovementAnimator(card, card.getX(), CARDSLOT5, 0.5f, 0, Interpolation.DECELERATE_INTERPOLATOR, true, false));
            slot5Available = false;
        } else if (slot6Available) {
            card.addMoveAnimtor((Animator) new MovementAnimator(card, card.getX(), CARDSLOT6, 0.5f, 0, Interpolation.DECELERATE_INTERPOLATOR, true, false));
            slot6Available = false;
        } else if (slot7Available) {
            card.addMoveAnimtor((Animator) new MovementAnimator(card, card.getX(), CARDSLOT7, 0.5f, 0, Interpolation.DECELERATE_INTERPOLATOR, true, false));
            slot7Available = false;
        } else {
            card.addMoveAnimtor((Animator) new MovementAnimator(card, card.getX(), CARDSLOT8, 0.5f, 0, Interpolation.DECELERATE_INTERPOLATOR, true, false));
            slot8Available = false;
        }
    }

    @Override
    public Resources getResources() {
        return myResources;
    }

    @Override
    public int getPlayerTurn() {
        return 0;
    }

    @Override
    public Player getPlayer1() {
        return player1;
    }

    @Override
    public Player getPlayer2() {
        return player2;
    }

    public Deck getDeck() {
        return deck;
    }
}
