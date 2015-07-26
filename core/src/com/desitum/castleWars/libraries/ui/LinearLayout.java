package com.desitum.castleWars.libraries.ui;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Kody.VanRy on 7/25/2015.
 */
public class LinearLayout extends Layout {
    /**
     * Create new Popup Layout with a blank canvas
     * use PopupWidgets to create and add them to the Layout
     * PopupWidgets will inherit all Animators from the Layout, and can be overriden
     *
     * @param background background texture for the image
     * @param x          left x position of the Layout
     * @param y          bottom y position of the Layout
     * @param width      the width of the Layout
     * @param height     the height of the Layout
     * @param camera
     */
    public LinearLayout(Texture background, float x, float y, float width, float height, Camera camera) {
        super(background, x, y, width, height, camera);
    }
}
