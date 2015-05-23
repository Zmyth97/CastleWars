package com.desitum.castleWars.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.desitum.castleWars.objects.Card;
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

    public void draw(){
        gameCam.position.set(MenuScreen.SCREEN_WIDTH / 2, MenuScreen.SCREEN_HEIGHT / 2, 0);
        gameCam.update();
        gameBatch.setProjectionMatrix(gameCam.combined);

       //Draw stuff
    }

    public OrthographicCamera getCam() {
        return gameCam;
    }

    public void resetCam() {
        gameCam.position.set(MenuScreen.SCREEN_WIDTH / 2, MenuScreen.SCREEN_HEIGHT / 2, 0);
    }
}
