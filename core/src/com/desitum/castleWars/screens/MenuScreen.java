package com.desitum.castleWars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.data.Settings;

import kody.libgdx.libraries.animation.MovementAnimator;
import kody.libgdx.libraries.interpolation.Interpolation;
import kody.libgdx.libraries.menu.OnClickListener;
import kody.libgdx.libraries.menu.PopupButton;
import kody.libgdx.libraries.menu.PopupMenu;
import kody.libgdx.libraries.menu.PopupSlider;
import kody.libgdx.libraries.menu.PopupSliderListener;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public class MenuScreen implements Screen {

    public static final float SCREEN_WIDTH = 10;
    public static final float SCREEN_HEIGHT = 15;

    public static int state = 1;

    private Viewport viewport;

    public static final int MENU_BEFORE_TRANSITION = 0;
    public static final int MENU_WAITING = 1;
    public static final int MENU_TRANSITION = 2;
    public static final int SETTINGS_MENU = 3;

    private OrthographicCamera cam;
    private SpriteBatch spriteBatch;

    private PopupMenu popupMenu;

    private com.desitum.castleWars.world.MenuWorld menuWorld;

    private com.desitum.castleWars.world.MenuRenderer menuRenderer;

    private Vector3 touchPoint;

    private com.desitum.castleWars.GooglePlayServicesInterface gpgs; //Will be used for scoreboard popup later

    public MenuScreen(com.desitum.castleWars.GooglePlayServicesInterface gps) {
        gpgs = gps;
        cam = new OrthographicCamera(SCREEN_WIDTH * 10, SCREEN_HEIGHT * 10);
        cam.position.set(SCREEN_WIDTH * 10 / 2, SCREEN_HEIGHT * 10 / 2, 0);

        //the viewport object will handle camera's attributes
        //the aspect provided (worldWidth/worldHeight) will be kept
        viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, cam);

        spriteBatch = new SpriteBatch();

        menuWorld = new com.desitum.castleWars.world.MenuWorld();

        menuRenderer = new com.desitum.castleWars.world.MenuRenderer(menuWorld, spriteBatch);

        //region SettingsMenu
        // code to create the settings menu
        // do not delete or edit without permission first
        popupMenu = new PopupMenu(Assets.textFieldBackground, 10, -130, 130, 80);
        MovementAnimator yAnimator = new MovementAnimator(-130, 10, 1, Interpolation.DECELERATE_INTERPOLATOR);
        yAnimator.setControllingY(true);
        popupMenu.addIncomingAnimator(yAnimator);
        MovementAnimator yAnimator2 = new MovementAnimator(10, -130, 1, Interpolation.ANTICIPATE_INTERPOLATOR);
        yAnimator2.setControllingY(true);
        popupMenu.addOutgoingAnimator(yAnimator2);

        PopupButton cancelButton = new PopupButton(Assets.cancelButtonUp, Assets.cancelButtonDown, 5, 5, 57.5f, 15);
        cancelButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick() {
                popupMenu.moveOut();
                state = MENU_WAITING;

            }
        });
        popupMenu.addPopupWidget(cancelButton);

        final PopupSlider volumeSlider = new PopupSlider(Assets.textFieldBackground, Assets.textFieldBackground, 5, 60, 120, 5, 3, 10);
        volumeSlider.setSliderListener(new PopupSliderListener() {
            @Override
            public void onChange(float pos) {
                System.out.println(pos);
            }
        });
        popupMenu.addPopupWidget(volumeSlider);

        PopupButton okButton = new PopupButton(Assets.okButtonUp, Assets.okButtonDown, 67.5f, 5, 57.5f, 15);
        okButton.setButtonListener(new OnClickListener() {
            @Override
            public void onClick() {
                Settings.setVolume(volumeSlider.getPosition());
                popupMenu.moveOut();
                state = MENU_WAITING;

            }
        });
        popupMenu.addPopupWidget(okButton);
        //endregion
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);

        if (state == SETTINGS_MENU) {
            popupMenu.updateTouchInput(touchPoint, Gdx.input.isTouched());
        }
        if (Gdx.input.justTouched()) {
            if (state == MENU_WAITING || state == MENU_TRANSITION) {
                touchPoint = menuRenderer.getCam().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            }
            onClick();
        }

        update(delta);

        cam.update();
        spriteBatch.enableBlending();
        spriteBatch.begin();
        spriteBatch.draw(Assets.menuBackground, 0, 0, MenuScreen.SCREEN_WIDTH, MenuScreen.SCREEN_HEIGHT);
        //popupMenu.draw(spriteBatch);
        draw();

        spriteBatch.end();
    }

    private void onClick() {
        switch (state) {
            case MENU_WAITING:
                onClickMenuWaiting();
                break;
        }
    }

    private void onClickMenuWaiting() {
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
            case SETTINGS_MENU:
                updatePopupMenu(delta);
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

    private void updatePopupMenu(float delta){
        popupMenu.update(delta);
    }

    private void draw() {
        switch (state) {
            case MENU_BEFORE_TRANSITION:
                drawMenuBeforeTransition();
                break;
            case MENU_WAITING:
                drawMenuWaiting();
                break;
            case MENU_TRANSITION:
                drawMenuTransition();
                break;
            case SETTINGS_MENU:
                drawMenuTransition();
                break;
        }
    }

    private void drawMenuBeforeTransition() {
        menuRenderer.render();
    }

    private void drawMenuTransition() {
        menuRenderer.render();
    }

    private void drawMenuWaiting() {
        menuRenderer.render();
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

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

}
