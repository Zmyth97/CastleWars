package com.desitum.castleWars.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.desitum.castleWars.libraries.animation.Animator;
import com.desitum.castleWars.world.GameInterface;

import java.util.ArrayList;

import kody.libgdx.libraries.Colors;
import kody.libgdx.libraries.animation.ColorEffects;
import kody.libgdx.libraries.animation.MovementAnimator;

/**
 * Created by Zmyth97 on 5/18/2015.
 */
public class Card extends Sprite {

    private static final int BUILD = 1;
    private static final int ATTACK = 2;
    private static final int MAGIC = 3;

    private static Color buildColor = new Color(12.2f, 0f, 61.6f, 1);
    private static Color attackColor = new Color(85.5f, 0f, 10.2f, 1);
    private static Color magicColor = new Color(3.5f, 72.2f, 0, 1);

    private static Color fadedBuildColor = new Color(30.6f, 22.7f, 63.5f, 1);
    private static Color fadedAttackColor = new Color(88.2f, 23.5f, 31, 1);
    private static Color fadedMagicColor = new Color(22.7f, 74.5f, 20, 1);

    private ArrayList<Animator> animators;

    private int cardType;
    private Texture cardImage;

    private ColorEffects colorChanger; //Fade from a lighter shade to more vibrant when you actually have enough resources
    private MovementAnimator movementAnimator;

    public Card(Texture cardImage, int cardType, int cardID, int x, int y, GameInterface gi){
        super(cardImage, 0, 0, cardImage.getWidth(), cardImage.getHeight());

        this.cardImage = cardImage;
        this.cardType = cardType;


        if(cardType == BUILD){
            this.setColor(buildColor);
        } else if(cardType == ATTACK){
            this.setColor(attackColor);
        } else if(cardType == MAGIC){ //Yes I know I could use just an else statement, its nice to see its for Magic though
            this.setColor(magicColor);
        }

        this.setPosition(x, y);

        animators = new ArrayList<Animator>();
    }

    public void update(float delta){
        for (Animator anim: animators){
            anim.update(delta);
        }
    }
    public void fadeColor(){
        if(cardType == BUILD){
            colorChanger = new ColorEffects(buildColor, fadedBuildColor, 0.2f);
            colorChanger.start(false);
        } else if (cardType == ATTACK){
            colorChanger = new ColorEffects(attackColor, fadedAttackColor, 0.2f);
            colorChanger.start(false);
        } else if (cardType == MAGIC){ //Yes I know I could use just an else statement, its nice to see its for Magic though
            colorChanger = new ColorEffects(magicColor, fadedMagicColor, 0.2f);
            colorChanger.start(false);
        }
    }

    public void loveColor(){
        if(cardType == BUILD){
            colorChanger = new ColorEffects(fadedBuildColor, buildColor, 0.2f);
            colorChanger.start(false);
        } else if (cardType == ATTACK){
            colorChanger = new ColorEffects(fadedAttackColor, attackColor, 0.2f);
            colorChanger.start(false);
        } else if (cardType == MAGIC){ //Yes I know I could use just an else statement, its nice to see its for Magic though
            colorChanger = new ColorEffects(fadedMagicColor, magicColor, 0.2f);
            colorChanger.start(false);
        }
    }

    public void addMoveAnimtor(Animator anim){
        this.animators.add(anim);
    }
}
