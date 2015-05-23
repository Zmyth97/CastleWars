package com.desitum.castleWars.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.objects.Card;
import com.desitum.castleWars.screens.GameScreen;
import com.desitum.castleWars.screens.MenuScreen;

import java.awt.Menu;

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

    public void draw(){
        gameCam.position.set(MenuScreen.SCREEN_WIDTH / 2, MenuScreen.SCREEN_HEIGHT / 2, 0);
        gameCam.update();
        gameBatch.setProjectionMatrix(gameCam.combined);

       drawWorld();
    }

    private void drawWorld(){
        gameBatch.draw(Assets.gameSky, 0, 0, GameScreen.SCREEN_WIDTH, GameScreen.SCREEN_HEIGHT);
        gameBatch.draw(Assets.playerCastle, ((GameScreen.SCREEN_WIDTH/6)), 0,(GameScreen.SCREEN_WIDTH/4), (GameScreen.SCREEN_HEIGHT/2));
        gameBatch.draw(Assets.computerCastle, ((GameScreen.SCREEN_WIDTH/1.75f)), 0, (GameScreen.SCREEN_WIDTH/4), (GameScreen.SCREEN_HEIGHT/2));
        gameBatch.draw(Assets.gameGround, 0, 0, GameScreen.SCREEN_WIDTH, (GameScreen.SCREEN_HEIGHT/4));
    }
    public OrthographicCamera getCam() {
        return gameCam;
    }

    public void resetCam() {
        gameCam.position.set(GameScreen.SCREEN_WIDTH / 2, GameScreen.SCREEN_HEIGHT / 2, 0);
    }
}
