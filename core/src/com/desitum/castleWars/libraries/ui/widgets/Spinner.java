package com.desitum.castleWars.libraries.ui.widgets;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.desitum.castleWars.libraries.CollisionDetection;
import com.desitum.castleWars.libraries.ui.listeners.OnClickListener;
import com.desitum.castleWars.libraries.ui.layout.Layout;

import java.util.ArrayList;

/**
 * Created by kody on 4/18/15.
 * can be used by kody and people in [Zack, Kody]
 */
public class Spinner extends Layout {

    private TextLabel label;

    private Texture background;

    private int value = 0;
    private int max;
    private int min;

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
     */
    public Spinner(Texture background, Texture buttonClickUp, Texture buttonClickDown, BitmapFont font, float x, float y, float width, float height, Camera camera) {
        super(background, 0, 0, background.getWidth(), background.getHeight(), camera);

        this.background = background;

        setPosition(x, y);
        this.setSize(width, height);

        Button clickUp = new Button(buttonClickUp, 0, height / 2, height / 2, height / 2);
        clickUp.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(Widget widget) {
                value += 1;
                if (value > max) value = max;
            }
        });
        addPopupWidget(clickUp);
        Button clickDown = new Button(buttonClickUp, 0, 0, height / 2, height / 2);
        clickDown.flip(false, true);
        clickDown.setButtonListener(new OnClickListener() {
            @Override
            public void onClick(Widget widget) {
                value -= 1;
                if (value < min) value = min;
            }
        });
        addPopupWidget(clickDown);

        label = new TextLabel(background, Color.BLACK, font, height / 2, 0, width - height / 2, height);
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
        if (Gdx.app.getType() == ApplicationType.Android)
            if (posMatters) {
                if (CollisionDetection.pointInRectangle(this.getBoundingRectangle(), mousePos)) {
                    value += amount;
                }
            }
    }

    /**
     * update animation and widgets and their animations associated with the Layout
     *
     * @param delta - time since last frame
     */
    public void update(float delta) {
        label.setText("" + value);

        super.update(delta);
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    @Override
    public ArrayList getChildren(boolean walk) {
        return new ArrayList<Widget>();
    }
}
