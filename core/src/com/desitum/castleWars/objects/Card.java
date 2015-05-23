package com.desitum.castleWars.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.desitum.castleWars.libraries.animation.Animator;
import com.desitum.castleWars.libraries.animation.ColorEffects;
import com.desitum.castleWars.libraries.animation.MovementAnimator;
import com.desitum.castleWars.world.GameInterface;

import java.util.ArrayList;


/**
 * Created by Zmyth97 on 5/18/2015.
 */
public class Card extends Sprite {

    public static final int BUILD = 1;
    public static final int ATTACK = 2;
    public static final int MAGIC = 3;

    private static Color regularColor = new Color(1f, 1f, 1f, 1);

    private static Color fadedColor = new Color(0.5f, 0.5f, 0.5f, 1);

    private ArrayList<Animator> animators;

    private int cardType;
    private Texture cardImage;
    private boolean available;
    private int cardCost;
    private int cardID;

    private ColorEffects colorChanger; //Fade from a lighter shade to more vibrant when you actually have enough resources
    private MovementAnimator movementAnimator;

    public Card(Texture cardImage, int cardType,   int cardID, int cardCost, float x, float y, GameInterface gi){
        super(cardImage, 0, 0, cardImage.getWidth(), cardImage.getHeight());
        available = false;
        this.cardImage = cardImage;
        this.cardType = cardType;
        this.cardCost = cardCost;
        this.cardID = cardID;
        this.setPosition(x, y);

        animators = new ArrayList<Animator>();
    }

    public void update(float delta){
        for (Animator anim: animators){
            anim.update(delta);
        }
    }

    public void fadeColor(){
        colorChanger = new ColorEffects(regularColor, fadedColor, 0.2f);
        colorChanger.setSprite(this, true, true);
        colorChanger.start(false);
    }

    public void restoreColor(){
        colorChanger = new ColorEffects(fadedColor, regularColor, 0.2f);
        colorChanger.setSprite(this, true, true);
        colorChanger.start(false);
    }

    public void addMoveAnimtor(Animator anim){
        this.animators.add(anim);
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
    }

    public void renderCard(SpriteBatch batch){
        batch.draw(cardImage, this.getX(), this.getY(), 14, 21);
    }
}
