package com.desitum.castleWars.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.desitum.castleWars.data.Assets;
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

    private Player player1;
    private Player player2;

    private Deck deck;

    private Resources myResources;

    private PopupMenu playerBuildMenu;
    private PopupMenu playerAttackMenu;
    private PopupMenu playerMagicMenu;
    private PopupMenu playerCastleMenu;
    private PopupMenu computerBuildMenu;
    private PopupMenu computerAttackMenu;
    private PopupMenu computerMagicMenu;
    private PopupMenu computerCastleMenu;

    private static Color buildColor = new Color(12.2f, 0f, 61.6f, 1);
    private static Color attackColor = new Color(85.5f, 0f, 10.2f, 1);
    private static Color magicColor = new Color(3.5f, 72.2f, 0, 1);
    private static Color castleColor = new Color(1, 1, 1, 1);

    public static final float DRAW_PILE_X = 50;
    public static final float DRAW_PILE_Y = 80;
    public static final float DISCARD_PILE_X = 70;
    public static final float DISCARD_PILE_Y = 80;


    public GameWorld(Viewport cam) {
        super();
        super.setCam(cam);
        player1 = new Player();
        player2 = new Player();
        deck = new Deck();
        myResources = new Resources();


        //region Player Menus

        //Build Menu
        playerBuildMenu = new PopupMenu(Assets.menuArea, 10, -40, 20, 15);
        playerBuildMenu.setColor(buildColor);
        MovementAnimator playerBuildAnimator = new MovementAnimator(playerBuildMenu, 10, 80, 1, 0.2f, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        playerBuildAnimator.setControllingY(true);
        playerBuildMenu.addIncomingAnimator(playerBuildAnimator);
        this.addPopupMenu(playerBuildMenu);

        //Attack Menu
        playerAttackMenu = new PopupMenu(Assets.menuArea, 10, -40, 20, 15);
        playerAttackMenu.setColor(attackColor);
        MovementAnimator playerAttackAnimator = new MovementAnimator(playerAttackMenu, 10, 60, 1, 0.2f, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        playerAttackAnimator.setControllingY(true);
        playerAttackMenu.addIncomingAnimator(playerAttackAnimator);
        this.addPopupMenu(playerAttackMenu);

        //Magic Menu
        playerMagicMenu = new PopupMenu(Assets.menuArea, 10, -40, 20, 15);
        playerMagicMenu.setColor(magicColor);
        MovementAnimator playerMagicAnimator = new MovementAnimator(playerMagicMenu, 10, 40, 1, 0.2f, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        playerMagicAnimator.setControllingY(true);
        playerMagicMenu.addIncomingAnimator(playerMagicAnimator);
        this.addPopupMenu(playerMagicMenu);

        //Castle Menu
        playerCastleMenu = new PopupMenu(Assets.menuArea, 10, -40, 20, 15);
        playerCastleMenu.setColor(castleColor);
        MovementAnimator playerCastleAnimator = new MovementAnimator(playerCastleMenu, 10, 20, 1, 0.2f, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        playerCastleAnimator.setControllingY(true);
        playerCastleMenu.addIncomingAnimator(playerCastleAnimator);
        this.addPopupMenu(playerCastleMenu);

        //endregion

        //region Computer Menus

        //Build Menu
        computerBuildMenu = new PopupMenu(Assets.menuArea, 140 - Assets.menuArea.getWidth(), -40, 20, 15);
        computerBuildMenu.setColor(buildColor);
        MovementAnimator computerBuildAnimator = new MovementAnimator(computerBuildMenu, 140 - Assets.menuArea.getWidth(), 80, 1, 0.2f, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        computerBuildAnimator.setControllingY(true);
        computerBuildMenu.addIncomingAnimator(computerBuildAnimator);
        this.addPopupMenu(computerBuildMenu);

        //Attack Menu
        computerAttackMenu = new PopupMenu(Assets.menuArea, 140 - Assets.menuArea.getWidth(), -40, 20, 15);
        computerAttackMenu.setColor(attackColor);
        MovementAnimator computerAttackAnimator = new MovementAnimator(computerAttackMenu, 140 - Assets.menuArea.getWidth(), 60, 1, 0.2f, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        computerAttackAnimator.setControllingY(true);
        computerAttackMenu.addIncomingAnimator(computerAttackAnimator);
        this.addPopupMenu(computerAttackMenu);

        //magic Menu
        computerMagicMenu = new PopupMenu(Assets.menuArea, 140 - Assets.menuArea.getWidth(), -40, 20, 15);
        computerMagicMenu.setColor(magicColor);
        MovementAnimator computerMagicAnimator = new MovementAnimator(computerMagicMenu, 140 - Assets.menuArea.getWidth(), 40, 1, 0.2f, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        computerMagicAnimator.setControllingY(true);
        computerMagicMenu.addIncomingAnimator(computerMagicAnimator);
        this.addPopupMenu(computerMagicMenu);

        //Castle Menu
        computerCastleMenu = new PopupMenu(Assets.menuArea, 140 - Assets.menuArea.getWidth(), -40, 20, 15);
        computerCastleMenu.setColor(castleColor);
        MovementAnimator computerCastleAnimator = new MovementAnimator(computerCastleMenu, 140 - Assets.menuArea.getWidth(), 20, 1, 0.2f, Interpolation.DECELERATE_INTERPOLATOR, false, true);
        computerCastleAnimator.setControllingY(true);
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

    public void setGameMode() {

    }

    public void update(int state, OrthographicCamera cam, float delta) {

    }

    public void reset() {

    }

    @Override
    public void onClickCard(Card card, int cardID) {
        card.addMoveAnimtor((Animator) new MovementAnimator(card, card.getX(), DRAW_PILE_X, 0.5f, 0, Interpolation.DECELERATE_INTERPOLATOR, true, false));
        //insert call to card actions here
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
    public Player getPlayer1(){
        return player1;
    }

    @Override
    public Player getPlayer2(){
        return player2;
    }

    public Deck getDeck(){
        return deck;
    }
}
