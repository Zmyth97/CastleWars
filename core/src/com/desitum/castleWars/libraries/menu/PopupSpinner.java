package com.desitum.castleWars.libraries.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.desitum.castleWars.libraries.CollisionDetection;

/**
 * Created by kody on 4/18/15.
 * can be used by kody and people in [Zack, Kody]
 */
public class PopupSpinner extends PopupMenu {

    private PopupTextLabel label;

    private Texture background;

    private int value = 0;

    /**
     * Create new Popup Menu with a blank canvas
     * use PopupWidgets to create and add them to the PopupMenu
     * PopupWidgets will inherit all Animators from the PopupMenu, and can be overriden
     *
     * @param background background texture for the image
     * @param x          left x position of the PopupMenu
     * @param y          bottom y position of the PopupMenu
     * @param width      the width of the PopupMenu
     * @param height     the height of the PopupMenu
     */
    public PopupSpinner(Texture background, Texture buttonClickUp, Texture buttonClickDown, BitmapFont font, float x, float y, float width, float height) {
        super(background, 0, 0, background.getWidth(), background.getHeight());

        this.background = background;

        setPosition(x, y);
        this.setSize(width, height);

        PopupButton clickUp = new PopupButton(buttonClickUp, buttonClickDown, 0, height / 2, height / 2, height / 2);
        clickUp.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                value += 1;
                System.out.println("Clicked: " + value);
            }
        });
        addPopupWidget(clickUp);
        PopupButton clickDown = new PopupButton(buttonClickUp, buttonClickDown, 0, 0, height / 2, height / 2);
        clickDown.flip(false, true);
        clickDown.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(PopupWidget widget) {
                value -= 1;
            }
        });
        addPopupWidget(clickDown);

        label = new PopupTextLabel(background, Color.BLACK, font, height / 2, 0, width - height / 2, height);
        addPopupWidget(label);
    }

    /**
     * Updates information from the scroll wheel
     * all handled in KodyWorld
     *
     * @param amount     gives either 0, 1, or -1
     * @param mousePos   pos of the cursor
     * @param posMatters if your mouse position matters
     */
    public void udpateScrollInput(int amount, Vector3 mousePos, boolean posMatters) {
        if (posMatters) {
            if (CollisionDetection.pointInRectangle(this.getBoundingRectangle(), mousePos)) {
                value += amount;
            }
        }
    }

    /**
     * update animation and widgets and their animations associated with the PopupMenu
     *
     * @param delta - time since last frame
     */
    public void update(float delta) {
        label.setText("" + value);

        super.update(delta);
    }
}
