package com.desitum.castleWars.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

        gameCam = new OrthographicCamera(MenuScreen.SCREEN_WIDTH, MenuScreen.SCREEN_HEIGHT);
        gameCam.position.set(MenuScreen.SCREEN_WIDTH / 2, MenuScreen.SCREEN_WIDTH / 2, 0);
    }

    public void render() {
        gameCam.position.set(MenuScreen.SCREEN_WIDTH / 2, MenuScreen.SCREEN_HEIGHT / 2, 0);
        gameCam.update();
        gameBatch.setProjectionMatrix(gameCam.combined);

    }

    public OrthographicCamera getCam() {
        return gameCam;
    }

    public void resetCam() {
        gameCam.position.set(MenuScreen.SCREEN_WIDTH / 2, MenuScreen.SCREEN_HEIGHT / 2, 0);
    }
}
