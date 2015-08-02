package com.desitum.castleWars.libraries.ui.widgets;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.desitum.castleWars.libraries.CollisionDetection;
import com.desitum.castleWars.libraries.styles.StyleManager;
import com.desitum.castleWars.libraries.ui.listeners.OnClickListener;

import java.util.ArrayList;

/**
 * Created by kody on 4/19/15.
 * can be used by kody and people in []
 */
public class Button extends Widget {
    private StyleManager styleManager;

    private boolean beenDown;

    private OnClickListener buttonListener;

    public Button(Texture baseTexture, float x, float y, float width, float height) {
        super(baseTexture, width, height, x, y);

        styleManager = new StyleManager();
        styleManager.setStateUpTexture(baseTexture);

        this.setSize(width, height);
        this.setPosition(x, y);

        this.setOriginCenter();
    }

    private void onClickDown() {
        styleManager.setState(StyleManager.STATE_DOWN);
        this.setTexture(styleManager.getCurrentStateTexture());
        beenDown = true;
    }

    private void onClickUp(boolean clicked) {
        styleManager.setState(StyleManager.STATE_UP);
        if (beenDown) this.setTexture(styleManager.getCurrentStateTexture());
        if (buttonListener != null && clicked && beenDown) {
            buttonListener.onClick(this);
        }
        beenDown = false;
    }

    public void resetState() {
        styleManager.setState(StyleManager.STATE_UP);
        this.setTexture(styleManager.getCurrentStateTexture());
    }

    public void setButtonListener(OnClickListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    @Override
    boolean scrollPosMatters() {
        return false;
    }

    @Override
    public void updateTouchInput(Vector3 touchPos, boolean clickDown) {
        boolean clickInArea = CollisionDetection.pointInRectangle(getBoundingRectangle(), touchPos);
        if (clickInArea && clickDown) {
            onClickDown();
        } else if (clickInArea) {
            onClickUp(true);
        } else {
            onClickUp(false);
        }
    }

    @Override
    public ArrayList getChildren(boolean walk) {
        return new ArrayList<Widget>();
    }
}
