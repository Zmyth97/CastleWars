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
import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.data.Settings;
import com.desitum.castleWars.world.MenuInterface;
import com.desitum.castleWars.world.MenuRenderer;
import com.desitum.castleWars.world.MenuWorld;

/**
 * Created by Zmyth97 on 2/25/2015.
 * can be used by Zmyth97 and people in [Zmyth97}]
 */
public class MenuScreen implements Screen, MenuInterface {

    public static final float SCREEN_WIDTH = 150;
    public static final float SCREEN_HEIGHT = 100;


    private OrthographicCamera cam;
    private Viewport viewport;

    private SpriteBatch spriteBatch;
    private MenuWorld menuWorld;
    private MenuRenderer menuRenderer;

    private GooglePlayServicesInterface gpgs;
    private CastleWars castleWars;

    public MenuScreen(GooglePlayServicesInterface gps, CastleWars castleWars) {
        gpgs = gps;
        this.castleWars = castleWars;

        spriteBatch = new SpriteBatch();
        cam = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
        cam.position.set(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 0);
        viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, cam);

        menuWorld = new MenuWorld(viewport, this);
        menuRenderer = new MenuRenderer(menuWorld, spriteBatch);

        Assets.menuMusic.play();

    }

    private void update(float delta) {
        menuWorld.update(delta);
        Assets.menuMusic.setVolume(Settings.VOLUME);
    }

    private void draw() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        spriteBatch.begin();
        spriteBatch.draw(Assets.menuBackground, 0, 0, MenuScreen.SCREEN_WIDTH, MenuScreen.SCREEN_HEIGHT);
        menuRenderer.render();
        spriteBatch.end();
    }

    @Override
    public void playGame() {
        castleWars.setScreen(new GameScreen(gpgs, castleWars));
    }

    @Override
    public void settings() {
        menuWorld.getMenuMove();
    }

    @Override
    public void multiplayer() {
        //For when we add multiplayer
    }
    @Override
    public void leaderboard() {
        //For when we add leaderboards
    }
    @Override
    public void store() {
        //For when we add the store for in app purchases
    }
    @Override
    public void buildDeck() {
        castleWars.setScreen(new BuildScreen(castleWars));
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
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


    }
}
