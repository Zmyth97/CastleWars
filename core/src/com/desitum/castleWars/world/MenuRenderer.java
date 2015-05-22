package com.desitum.castleWars.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.desitum.castleWars.libraries.menu.PopupButton;
import com.desitum.castleWars.libraries.menu.PopupWidget;
import com.desitum.castleWars.screens.MenuScreen;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public class MenuRenderer {
    private SpriteBatch menuBatch;
    private OrthographicCamera menuCamera;
    private MenuWorld world;

    public MenuRenderer(MenuWorld world, SpriteBatch batch) {
        this.world = world;
        this.menuBatch = batch;

        menuCamera = new OrthographicCamera(MenuScreen.SCREEN_WIDTH, MenuScreen.SCREEN_HEIGHT);
        menuCamera.position.set(MenuScreen.SCREEN_WIDTH / 2, MenuScreen.SCREEN_HEIGHT / 2, 0);
    }

    public void render() {
        menuCamera.update();
        menuBatch.setProjectionMatrix(menuCamera.combined);

        for (PopupWidget menuItem : this.world.getWidgets()) {
            menuItem.draw(menuBatch);
        }

    }

    public void resetCam() {
        menuCamera.position.set(MenuScreen.SCREEN_WIDTH / 2, MenuScreen.SCREEN_HEIGHT / 2, 0);
    }

    public OrthographicCamera getCam() {
        return menuCamera;
    }
}
