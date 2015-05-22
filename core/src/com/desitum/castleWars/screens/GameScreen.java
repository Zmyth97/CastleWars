package com.desitum.castleWars.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.desitum.castleWars.CastleWars;
import com.desitum.castleWars.GooglePlayServicesInterface;

/**
 * Created by Zmyth97 on 5/18/2015.
 */
public class GameScreen implements Screen {

    private CastleWars castleWars;
    private GooglePlayServicesInterface gpgs;

    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Viewport viewport;

    public static final int GAME_BEFORE = 3;
    public static final int GAME_RUNNING = 4;
    public static final int GAME_PAUSED = 5;
    public static final int GAME_OVER = 6;
    public static final int GAME_OVER_WITH_TRANSITION = 7;

    public GameScreen(GooglePlayServicesInterface googlePlayServicesInterface, CastleWars cw) {
        batch = new SpriteBatch();
        cam = new OrthographicCamera(MenuScreen.SCREEN_WIDTH, MenuScreen.SCREEN_HEIGHT);
        cam.position.set(MenuScreen.SCREEN_WIDTH/2, MenuScreen.SCREEN_HEIGHT/2, 0);
        //the viewport object will handle camera's attributes
        //the aspect provided (worldWidth/worldHeight) will be kept
        viewport = new FitViewport(MenuScreen.SCREEN_WIDTH, MenuScreen.SCREEN_HEIGHT, cam);

        castleWars = cw;
        gpgs = googlePlayServicesInterface;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
