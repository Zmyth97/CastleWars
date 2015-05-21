package com.desitum.castleWars.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.desitum.castleWars.world.GameInterface;

import java.util.ArrayList;

import kody.libgdx.libraries.animation.Animator;
import kody.libgdx.libraries.animation.ColorEffects;
import kody.libgdx.libraries.animation.MovementAnimator;

/**
 * Created by Zmyth97 on 5/18/2015.
 */
public class Card extends Sprite {

    private static final int BUILD = 1;
    private static final int ATTACK = 2;
    private static final int MAGIC = 3;

    private static Color regularColor = new Color(1f, 1f, 1f, 1);

    private static Color fadedColor = new Color(0.5f, 0.5f, 0.5f, 1);

    private ArrayList<Animator> animators;

    private int cardType;
    private Texture cardImage;

    private ColorEffects colorChanger; //Fade from a lighter shade to more vibrant when you actually have enough resources
    private MovementAnimator movementAnimator;

    public Card(Texture cardImage, int cardType, int cardID, int x, int y, GameInterface gi){
        super(cardImage, 0, 0, cardImage.getWidth(), cardImage.getHeight());

        this.cardImage = cardImage;
        this.cardType = cardType;

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
}
