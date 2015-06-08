package com.desitum.castleWars.libraries.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.desitum.castleWars.libraries.animation.ColorEffects;

import java.util.ArrayList;

/**
 * Created by kody on 5/23/15.
 * can be used by kody and people in [kody}]
 */
public class PopupTextLabel extends PopupWidget {

    private Texture backgroundTexture;
    private Color highlightColor;
    private BitmapFont font;

    private ArrayList<ColorEffects> colorEffects;

    private boolean beenDown;

    private OnClickListener buttonListener;

    private String text;
    private BitmapFont.HAlignment alignment;
    private Color currentColor;

    private float cursorBlink;
    private float cursorMaxBlink;
    private float fontSize;
    private boolean cursorOn = false;

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

        this.colorEffects = new ArrayList<ColorEffects>();

        this.currentColor = new Color(1, 1, 1, 1);

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

        this.colorEffects = new ArrayList<ColorEffects>();
        this.currentColor = new Color(1, 1, 1, 1);

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

        this.colorEffects = new ArrayList<ColorEffects>();
        this.currentColor = new Color(1, 1, 1, 1);

        setupFontSize();
    }

    public void startTextColorEffects() {
        for (ColorEffects effects : colorEffects) {
            effects.start(false);
        }
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

        this.fontSize = y;
    }

    public void setCursorBlink(float blinkTime) {
        this.cursorBlink = blinkTime;
        this.cursorMaxBlink = blinkTime;
    }

    public void onClickDown(Vector3 touchPos) {
        beenDown = true;
    }

    public void onClickUp(boolean clicked) {
        this.setTexture(backgroundTexture);
        if (buttonListener != null && clicked && beenDown) {
            buttonListener.onClick(this);
        }
        beenDown = false;
    }

    public void resetState() {
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        for (ColorEffects effects : colorEffects) {
            effects.update(delta);
            if (effects.isRunning()) {
                currentColor = effects.getCurrentColor();
            }
        }

        cursorBlink -= delta;
        if (cursorBlink < 0) {
            cursorOn = !cursorOn;
            cursorBlink = cursorMaxBlink;
        }
    }

    public void addFontColorChanger(ColorEffects colorEffects) {
        this.colorEffects.add(colorEffects);
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
        if (alignment.equals(BitmapFont.HAlignment.RIGHT)) {
            textX = getX() + getWidth() - font.getBounds(text).width;
        }
        return textX;
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
        font.setScale(fontSize);
        font.setColor(currentColor);
        if (font.getBounds(text).width > getWidth()) {
            font.drawWrapped(batch, text, getTextX(), getTextY(), getWidth(), alignment);
        } else {
            font.draw(batch, text, getTextX(), getTextY());
        }
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ColorEffects getColorEffect1() {
        return colorEffects.get(0);
    }
}

