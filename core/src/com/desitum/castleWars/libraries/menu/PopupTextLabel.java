package com.desitum.castleWars.libraries.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.desitum.castleWars.libraries.animation.Animator;
import com.desitum.castleWars.libraries.animation.ColorEffects;

import java.util.ArrayList;

/**
 * Created by kody on 5/23/15.
 * can be used by kody and people in [kody}]
 */
public class PopupTextLabel extends PopupWidget{

    private Texture backgroundTexture;
    private Color highlightColor;
    private BitmapFont font;

    private ArrayList<Animator> comingInAnimators;
    private ArrayList<Animator> goingOutAnimators;

    private boolean beenDown;

    private OnClickListener buttonListener;

    private String text;
    private BitmapFont.HAlignment alignment;

    private float cursorBlink;
    private float cursorMaxBlink;
    private boolean cursorOn = false;

    private ColorEffects colorEffects;

    public PopupTextLabel(Texture backgroundTexture, Color highlightColor, BitmapFont font, float x, float y, float width, float height) {
        super(backgroundTexture, width, height, x, y);

        this.backgroundTexture = backgroundTexture;
        this.highlightColor = highlightColor;
        this.font = font;
        text = "";
        this.alignment = BitmapFont.HAlignment.LEFT;
        cursorBlink = 0.5f;
        cursorMaxBlink = 0.5f;

        this.setSize(width, height);
        this.setPosition(x, y);

        this.setOriginCenter();

        this.comingInAnimators = new ArrayList<Animator>();
        this.goingOutAnimators = new ArrayList<Animator>();

        setupFontSize();
    }

    public PopupTextLabel(Texture backgroundTexture, Color highlightColor, BitmapFont font, float x, float y, float width, float height, String text) {
        super(backgroundTexture, width, height, x, y);

        this.backgroundTexture = backgroundTexture;
        this.highlightColor = highlightColor;
        this.font = font;
        this.text = text;
        this.alignment = BitmapFont.HAlignment.LEFT;
        cursorBlink = 0.5f;
        cursorMaxBlink = 0.5f;

        this.setSize(width, height);
        this.setPosition(x, y);

        this.setOriginCenter();

        this.comingInAnimators = new ArrayList<Animator>();
        this.goingOutAnimators = new ArrayList<Animator>();

        setupFontSize();
    }

    public PopupTextLabel(Texture backgroundTexture, Color highlightColor, BitmapFont font, float x, float y, float width, float height, String text, BitmapFont.HAlignment hAlignment) {
        super(backgroundTexture, width, height, x, y);

        this.backgroundTexture = backgroundTexture;
        this.highlightColor = highlightColor;
        this.font = font;
        this.text = text;
        this.alignment = hAlignment;
        cursorBlink = 0.5f;
        cursorMaxBlink = 0.5f;

        this.setSize(width, height);
        this.setPosition(x, y);

        this.setOriginCenter();

        this.comingInAnimators = new ArrayList<Animator>();
        this.goingOutAnimators = new ArrayList<Animator>();

        setupFontSize();
    }

    private void setupFontSize() {
        float x = 0;
        float y = 0;
        float z = 0;
        while (x < getHeight() * 0.8f) {
            y = z;
            z += 0.01f;
            font.setScale(z);
            x = font.getBounds("THE QUICK BROWN FOX JUMPED OVER THE LAZY DOG 123456789").height;
        }
        System.out.println(y);
    }

    public void setCursorBlink(float blinkTime) {
        this.cursorBlink = blinkTime;
        this.cursorMaxBlink = blinkTime;
    }

    public void onClickDown(Vector3 touchPos){
        beenDown = true;
    }

    public void onClickUp(boolean clicked){
        this.setTexture(backgroundTexture);
        if (buttonListener != null && clicked && beenDown){
            buttonListener.onClick(this);
        }
        beenDown = false;
    }

    public void resetState(){
    }

    @Override
    public void update(float delta){
        for (Animator anim: comingInAnimators){
            anim.update(delta);
        }

        for (Animator anim: goingOutAnimators){
            anim.update(delta);
        }

        if (colorEffects != null) {
            colorEffects.update(delta);
            font.setColor(colorEffects.getCurrentColor());
        }

        cursorBlink -= delta;
        if (cursorBlink < 0) {
            cursorOn = !cursorOn;
            cursorBlink = cursorMaxBlink;
        }
    }

    @Override
    public void addIncomingAnimator(Animator anim){
        anim.setSprite(this, anim.updateX(), anim.updateY());
        this.comingInAnimators.add(anim);
    }

    @Override
    public void addOutgoingAnimator(Animator anim){
        anim.setSprite(this, anim.updateX(), anim.updateY());
        this.goingOutAnimators.add(anim);
    }

    @Override
    public void startIncomingAnimators(){
        for (Animator anim: comingInAnimators){
            anim.start(false);
        }
    }

    @Override
    public void startOutgoingAnimators(){
        for (Animator anim: goingOutAnimators){
            anim.start(false);
        }
    }

    public void addFontColorChanger(ColorEffects colorEffects) {
        this.colorEffects = colorEffects;
    }

    public void setButtonListener(OnClickListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    private float getTextY() {
        float textY = getY();
        textY = getY() + getHeight() * 0.9f;
        return textY;
    }

    private float getTextX() {
        float textX = getX();
        return textX;
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if (cursorOn) {
            font.drawWrapped(batch, text, getTextX(), getTextY(), getWidth(), alignment);
        } else {
            font.drawWrapped(batch, text, getTextX(), getTextY(), getWidth(), alignment);
        }
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText(String text) {
        return this.text;
    }
}

