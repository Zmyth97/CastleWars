package com.desitum.castleWars.libraries.menu;


import com.badlogic.gdx.graphics.Texture;

/**
 * Created by kody on 4/19/15.
 * can be used by kody and people in []
 */
public class PopupButton extends PopupWidget {
    private Texture downTexture;
    private Texture upTexture;

    private boolean beenDown;

    private OnClickListener buttonListener;

    public PopupButton(Texture upTexture, Texture downTexture, float x, float y, float width, float height) {
        super(upTexture, width, height, x, y);

        this.upTexture = upTexture;
        this.downTexture = downTexture;

        this.setSize(width, height);
        this.setPosition(x, y);

        this.setOriginCenter();
    }

	public void onClickDown(){
        this.setTexture(downTexture);
        beenDown = true;
    }

    public void onClickUp(boolean clicked){
        if (beenDown) this.setTexture(upTexture);
        if (buttonListener != null && clicked && beenDown){
            buttonListener.onClick(this);
        }
        beenDown = false;
    }

    public void resetState(){
        this.setTexture(downTexture);
    }

    public void setButtonListener(OnClickListener buttonListener) {
        this.buttonListener = buttonListener;
    }
}
