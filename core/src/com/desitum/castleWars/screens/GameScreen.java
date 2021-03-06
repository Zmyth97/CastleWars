package com.desitum.castleWars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.desitum.castleWars.CastleWars;
import com.desitum.castleWars.GooglePlayServicesInterface;
import com.desitum.castleWars.world.GameRenderer;
import com.desitum.castleWars.world.GameWorld;

/**
 * Created by Zmyth97 on 5/18/2015.
 */
public class GameScreen implements Screen {

    public static final float SCREEN_WIDTH = 150;
    public static final float SCREEN_HEIGHT = 100;

    private CastleWars castleWars;
    private GooglePlayServicesInterface gpgs;

    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Viewport viewport;

    private GameWorld gameWorld;
    private GameRenderer gameRenderer;


    public GameScreen(GooglePlayServicesInterface googlePlayServicesInterface, CastleWars cw) {
        batch = new SpriteBatch();
        cam = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
        cam.position.set(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);

        viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, cam);

        this.gpgs = googlePlayServicesInterface;

        gameWorld = new GameWorld(gpgs, viewport);
        gameRenderer = new GameRenderer(gameWorld, batch);

        castleWars = cw;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
        if (gameWorld.gameOver) {
            castleWars.setScreen(new MenuScreen(gpgs, castleWars)); //Do we need to make a new screen for it? Was the first one disposed?
        }
    }

    public void update(float delta) {
        gameWorld.update(delta);
    }

    public void draw() {
        Gdx.gl.glClearColor(0, 0, .196f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        gameRenderer.draw();
        batch.setProjectionMatrix(cam.combined);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
        System.out.println("I (GameScreen) disposed!");
    }
}
