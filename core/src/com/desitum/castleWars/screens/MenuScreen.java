package com.desitum.castleWars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.desitum.castleWars.data.Settings;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public class MenuScreen implements Screen {

    public static final float SCREEN_WIDTH = 10;
    public static final float SCREEN_HEIGHT = 15;

    public static int state = 1;
    public int score;

    private Viewport viewport;

    public static final int MENU_BEFORE_TRANSITION = 0;
    public static final int MENU_WAITING = 1;
    public static final int MENU_TRANSITION = 2;


    public static String PLAY = "play";
    public static String SCORE = "open_scores";
    public static String SETTINGS = "settings";

    private OrthographicCamera cam;
    private OrthographicCamera textCam;
    private SpriteBatch spriteBatch;

    private com.desitum.castleWars.world.MenuWorld menuWorld;
    private com.desitum.castleWars.world.GameWorld gameWorld;

    private com.desitum.castleWars.world.MenuRenderer menuRenderer;
    private com.desitum.castleWars.world.GameRenderer gameRenderer;

    private Vector3 touchPoint;

    private com.desitum.castleWars.GooglePlayServicesInterface gpgs;


    public MenuScreen(com.desitum.castleWars.GooglePlayServicesInterface gps) {
        score = 0;

        gpgs = gps;
        cam = new OrthographicCamera(SCREEN_WIDTH * 10, SCREEN_HEIGHT * 10);
        textCam = new OrthographicCamera(100, 150);
        cam.position.set(SCREEN_WIDTH * 10 / 2, SCREEN_HEIGHT * 10 / 2, 0);

        //the viewport object will handle camera's attributes
        //the aspect provided (worldWidth/worldHeight) will be kept
        viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, cam);

        spriteBatch = new SpriteBatch();

        menuWorld = new com.desitum.castleWars.world.MenuWorld();
        gameWorld = new com.desitum.castleWars.world.GameWorld();

        menuRenderer = new com.desitum.castleWars.world.MenuRenderer(menuWorld, spriteBatch);
        gameRenderer = new com.desitum.castleWars.world.GameRenderer(gameWorld, spriteBatch);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);



        if (Gdx.input.justTouched()) {
            if (state == MENU_WAITING || state == MENU_TRANSITION) {
                touchPoint = menuRenderer.getCam().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            } else if (state == GAME_BEFORE || state == GAME_RUNNING || state == GAME_PAUSED || state == GAME_OVER) {
                touchPoint = gameRenderer.getCam().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            }
            onClick();
        }

        update(delta);

        cam.update();
        spriteBatch.enableBlending();
        spriteBatch.begin();

        draw();

        spriteBatch.end();
    }

    private void onClick() {
        switch (state) {
            case MENU_WAITING:
                onClickMenuWaiting();
                break;
            case GAME_BEFORE:
                onClickGameBefore();
                break;
            case GAME_PAUSED:
                onClickGamePaused();
                break;
            case GAME_RUNNING:
                onClickGameRunning();
                break;
            case GAME_OVER:
                onClickGameOver();
                break;
        }
    }

    private void onClickMenuWaiting() {
        for (com.desitum.castleWars.objects.MenuButton mb : menuWorld.getMenuButtons()) {
            if (com.desitum.castleWars.libraries.CollisionDetection.pointInRectangle(mb.getBoundingRectangle(), touchPoint)) { // if touched a rectangle
                mb.onClick();
                if (mb.getCommand().equals(PLAY)) { // If the button was play
                    state = GAME_RUNNING;
                    GAME_MODE = REGULAR_MODE;
                } else if (mb.getCommand().equals(OTHER)) { // If the button was Endless Mode
                    state = GAME_RUNNING;
                    GAME_MODE = OTHER_MODE;
                } else if (mb.getCommand().equals(SCORE)) { // If the button was high scores

                } else if (mb.getCommand().equals(SOUND)) { // If the button was sound
                    Settings.volumeOn = !Settings.volumeOn; // toggle whether the volume is on
                    if (Settings.volumeOn) { // update the texture for the Sound Button
                        mb.setTexture(com.desitum.castleWars.data.Assets.menuButtonTexture); //No sound on texture yet!
                    } else {
                        mb.setTexture(com.desitum.castleWars.data.Assets.menuButtonTexture); //No sound off texture yet!
                    }
                }
            }
        }
    }

    private void onClickGameBefore() {
        state = GAME_RUNNING;
    }

    private void onClickGamePaused() {
        state = GAME_RUNNING;
    }

    private void onClickGameRunning() {

    }

    private void onClickGameOver() {
        //TODO Add in end game interface
    }

    private void update(float delta) {
        switch (state) {
            case MENU_BEFORE_TRANSITION:
                updateMenuBeforeTransition(delta);
                break;
            case MENU_WAITING:
                updateMenuWaiting(delta);
                break;
            case MENU_TRANSITION:
                updateMenuTransition(delta);
                break;
            case GAME_BEFORE:
                updateGameBefore(delta);
                break;
            case GAME_PAUSED:
                updateGamePaused(delta);
                break;
            case GAME_RUNNING:
                updateGameRunning(delta);
                break;
            case GAME_OVER:
                updateGameOver(delta);
                break;
            case GAME_OVER_WITH_TRANSITION:
                updateGameOverTransition(delta);
                break;
        }
    }

    private void updateMenuBeforeTransition(float delta) {
        menuWorld.update(delta);
    }

    private void updateMenuTransition(float delta) {
        menuWorld.update(delta);
    }

    private void updateMenuWaiting(float delta) {
        menuWorld.update(delta);
    }

    private void updateGameBefore(float delta) {
        gameWorld.update(state, gameRenderer.getCam(), delta);
    }

    private void updateGameRunning(float delta) {
        gameWorld.update(state, gameRenderer.getCam(), delta);
    }

    private void updateGamePaused(float delta) {

    }

    private void updateGameOver(float delta) {
        gameWorld.update(state, gameRenderer.getCam(), delta);
    }

    private void updateGameOverTransition(float delta) {

    }

    private void draw() {
        switch (state) {
            case MENU_BEFORE_TRANSITION:
                drawMenuBeforeTransition();
                break;
            case GAME_OVER:
                drawGameOver();
                break;
            case GAME_RUNNING:
                drawGameRunning();
                break;
            case MENU_WAITING:
                drawMenuWaiting();
                break;
            case MENU_TRANSITION:
                drawMenuTransition();
                break;
            case GAME_BEFORE:
                drawGameBefore();
                break;
            case GAME_PAUSED:
                drawGamePaused();
                break;
            case GAME_OVER_WITH_TRANSITION:
                drawGameOver();
                break;
        }
    }

    private void drawMenuBeforeTransition() {
        menuRenderer.render();
    }

    private void drawGamePaused() {
        gameRenderer.render();
    }

    private void drawGameBefore() {
        gameRenderer.render();

    }

    private void drawMenuTransition() {
        menuRenderer.render();
    }

    private void drawMenuWaiting() {
        menuRenderer.render();
    }

    private void drawGameRunning() {
        gameRenderer.render();
        spriteBatch.setProjectionMatrix(cam.combined);
    }

    private void drawGameOver() {
        gameRenderer.render();
        spriteBatch.setProjectionMatrix(cam.combined);

    }

    private void resetGame() {
        cam.position.set(SCREEN_WIDTH * 10 / 2, SCREEN_HEIGHT * 10 / 2, 0);
        gameWorld.reset();
        gameRenderer.resetCam();
        menuRenderer.resetCam();
    }

    @Override
    public void resize(int width, int height) {
        //notice that the method receives the entire screen size
        //the last argument tells the viewport to center the camera in the screen
        viewport.update(width, height, true);
    }


    @Override
    public void show() {

    }

    @Override
    public void hide() {
        if (state == GAME_RUNNING) {
            state = GAME_PAUSED;
        }
    }

    @Override
    public void pause() {
        if (state == GAME_RUNNING) {
            state = GAME_PAUSED;
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

}
