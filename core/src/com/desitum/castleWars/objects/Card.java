package com.desitum.castleWars.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.desitum.castleWars.libraries.animation.ColorEffects;
import com.desitum.castleWars.libraries.animation.MovementAnimator;
import com.desitum.castleWars.libraries.menu.PopupButton;


/**
 * Created by Zmyth97 on 5/18/2015.
 */
public class Card extends PopupButton {

    public static final float CARD_WIDTH = 14;
    public static final float CARD_HEIGHT = 21;

    public static final int BUILD = 1;
    public static final int ATTACK = 2;
    public static final int MAGIC = 3;

    private static Color regularColor = new Color(1f, 1f, 1f, 1);

    private static Color fadedColor = new Color(0.5f, 0.5f, 0.5f, 1);

    private int cardType;
    private boolean available;
    private int cardCost;
    private int cardID;

    private ColorEffects colorChanger; //Fade from a lighter shade to more vibrant when you actually have enough resources
    private MovementAnimator movementAnimator;

    public Card(Texture cardImage, int cardType,   int cardID, int cardCost, float x, float y){
        super(cardImage, cardImage, x, y, CARD_WIDTH, CARD_HEIGHT);
        available = true;
        this.cardType = cardType;
        this.cardCost = cardCost;
        this.cardID = cardID;
        this.setPosition(x, y);
    }

    public void update(float delta) {
        super.update(delta);
        if (colorChanger != null) colorChanger.update(delta);
    }
    public void fadeColor(){
        if (colorChanger != null && colorChanger.isRunning()) return;
        if (ColorEffects.colorsMatch(this.getColor(), fadedColor, 0.01f)) return;
        colorChanger = new ColorEffects(regularColor, fadedColor, 0.2f);
        colorChanger.setSprite(this, true, true);
        colorChanger.start(false);
    }

    public void restoreColor(){
        if (colorChanger != null && colorChanger.isRunning()) return;
        if (ColorEffects.colorsMatch(this.getColor(), regularColor, 0.01f)) return;
        colorChanger = new ColorEffects(fadedColor, regularColor, 0.2f);
        colorChanger.setSprite(this, true, true);
        colorChanger.start(false);
    }

    public int getCardCost() {
        return cardCost;
    }

    public int getCardType() {
        return cardType;
    }

    public int getCardID(){
        return cardID;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
        if (available) restoreColor();
        else fadeColor();
    }

    public void disable() {
        setAvailable(false);
    }

    public void enable() {
        setAvailable(true);
    }
}
