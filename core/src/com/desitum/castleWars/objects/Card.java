package com.desitum.castleWars.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.Color;

import kody.libgdx.libraries.animation.ColorEffects;
import kody.libgdx.libraries.animation.MovementAnimator;

/**
 * Created by Zmyth97 on 5/18/2015.
 */
public class Card extends Sprite {

    private static final int BUILD = 1; //Red? Orange? Brown?
    private static final int ATTACK = 2; //Blue? Green? Yellow?
    private static final int MAGIC = 3; //Purple? Blue? Light Green?

    private int resourceType;
    private Color cardColor; //One for Brick Type, one for Weapon type, and one for Magic type
    private String cardName;
    private int cardCost;
    private Texture cardImage;
    private String cardAction;

    private ColorEffects colorChanger; //Fade from a lighter shade to more vibrant when you actually have enough resources
    private MovementAnimator movementAnimator; //For moving it from the deck and back, unless you have a different way you would like to do ^_^

    public Card(int resourceType, Color cardColor, String cardName, int cardCost, Texture cardImage, String cardAction){
        this.resourceType = resourceType;
        this.cardColor = cardColor;
        this.cardName = cardName;
        this.cardCost = cardCost;
        this.cardImage = cardImage;
        this.cardAction = cardAction;

    }

    public void update(float delta){
        //Make cards appear more vibrant if you have enough resources?
    }
}
