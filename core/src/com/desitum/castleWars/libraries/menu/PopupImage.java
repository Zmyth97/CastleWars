package com.desitum.castleWars.libraries.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by kody on 4/19/15.
 * can be used by kody and people in [Zack]
 */
public class PopupImage extends PopupWidget {
    private Texture downTexture;
    private Texture upTexture;

    private boolean beenDown;
    private boolean enabledClicking;

    private OnClickListener buttonListener;

    /**
     * PopupImage can have a highlighted area showing it is selected,
     * Extends PopupWidget and has all the animations you need
     *
     * @param upTexture       image to turn into a PopupImage
     * @param highlight       texture to use as a highlight
     * @param x               x position in relation to the parent (if applicable)
     * @param y               y position in relation to the parent (if applicable)
     * @param width           width of the PopupImage
     * @param height          height of the PopupImage
     * @param enabledClicking whether you can click on it to highlight it
     */
    public PopupImage(Texture upTexture, Texture highlight, float x, float y, float width, float height, boolean enabledClicking) {
        super(upTexture, width, height, x, y);

        this.upTexture = upTexture;
        this.downTexture = highlight;

        this.setSize(width, height);
        this.setPosition(x, y);

        this.setOriginCenter();

        this.enabledClicking = enabledClicking;
    }

    public void onClickDown() {
        if (enabledClicking && !beenDown) {
            beenDown = true;
        } else {
            beenDown = false;
        }
    }

    public void onClickUp(boolean clicked) {
        if (buttonListener != null && clicked && beenDown) {
            buttonListener.onClick(this);
        }
    }

    public void resetState() {
        this.setTexture(downTexture);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if (this.beenDown)
            batch.draw(downTexture, this.getX() + getWidth() / 2 - (getWidth() / 2) * getScaleX(), this.getY(), this.getWidth() * getScaleX(), this.getHeight());
    }

    public void setActive() {
        beenDown = true;
    }

    public void deactivate() {
        beenDown = false;
    }

    public void setButtonListener(OnClickListener buttonListener) {
        this.buttonListener = buttonListener;
    }
}
