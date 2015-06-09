package com.desitum.castleWars.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.libraries.menu.PopupMenu;
import com.desitum.castleWars.libraries.menu.PopupWidget;
import com.desitum.castleWars.objects.Card;
import com.desitum.castleWars.objects.Cloud;
import com.desitum.castleWars.screens.GameScreen;
import com.desitum.castleWars.screens.MenuScreen;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public class GameRenderer {

    private SpriteBatch gameBatch;
    private OrthographicCamera gameCam;
    private GameWorld world;

    public GameRenderer(GameWorld world, SpriteBatch batch) {
        this.world = world;
        this.gameBatch = batch;

        gameCam = new OrthographicCamera(GameScreen.SCREEN_WIDTH, GameScreen.SCREEN_HEIGHT);
        gameCam.position.set(GameScreen.SCREEN_WIDTH / 2, GameScreen.SCREEN_WIDTH / 2, 0);
    }

    public void draw() {
        gameCam.position.set(MenuScreen.SCREEN_WIDTH / 2, MenuScreen.SCREEN_HEIGHT / 2, 0);
        gameCam.update();
        gameBatch.setProjectionMatrix(gameCam.combined);

        drawWorld();
        drawCards();

        for (PopupWidget widget : this.world.getWidgets()) {
            widget.draw(gameBatch);

            if (widget instanceof Card) {
                if (world.isDiscarding()) {
                    gameBatch.draw(Assets.discard, widget.getX(), widget.getY(), widget.getWidth(), widget.getHeight());
                }
            }
        }
        for (PopupMenu menu : this.world.getMenus()) {
            menu.draw(gameBatch);
        }

    }

    private void drawWorld() {
        gameBatch.draw(Assets.gameSky, 0, 0, GameScreen.SCREEN_WIDTH, GameScreen.SCREEN_HEIGHT);

        for(Cloud cloud: world.getCloudList()){
                cloud.draw(gameBatch);
        }

        world.getPlayer1().getCastle().draw(gameBatch);
        world.getPlayer2().getCastle().draw(gameBatch);
        gameBatch.draw(Assets.gameGround, 0, 0, GameScreen.SCREEN_WIDTH, (GameScreen.SCREEN_HEIGHT / 4 + 8));
    }

    private void drawCards() {
        gameBatch.draw(Assets.cardBack, GameWorld.DRAW_PILE_X, GameWorld.DRAW_PILE_Y, Card.CARD_WIDTH, Card.CARD_HEIGHT);
        for (int x = world.getDeck().getCardList().size() - 3; x < world.getDeck().getCardList().size(); x++) {
            Card card = world.getDeck().getCardList().get(x);
            if(card.getX() != GameWorld.DRAW_PILE_X) {
                card.draw(gameBatch);
            }
        }
        for (Card card : world.getPlayer2().getHand().getCardsInHand()) {
            card.draw(gameBatch);
        }
    }

    public OrthographicCamera getCam() {
        return gameCam;
    }

    public void resetCam() {
        gameCam.position.set(GameScreen.SCREEN_WIDTH / 2, GameScreen.SCREEN_HEIGHT / 2, 0);
    }
}
