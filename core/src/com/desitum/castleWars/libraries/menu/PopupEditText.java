package com.desitum.castleWars.libraries.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.desitum.castleWars.libraries.animation.ColorEffects;

/**
 * Created by kody on 5/23/15.
 * can be used by kody and people in [kody}]
 */
public class PopupEditText extends PopupWidget {

    private Texture backgroundTexture;
    private Color highlightColor;
    private BitmapFont font;

    private boolean beenDown;

    private OnClickListener buttonListener;

    private String text;
    private BitmapFont.HAlignment alignment;

    private float cursorBlink;
    private float cursorMaxBlink;
    private boolean cursorOn = false;

    private ColorEffects colorEffects;

    public PopupEditText(Texture backgroundTexture, Color highlightColor, BitmapFont font, float x, float y, float width, float height) {
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

        setupFontSize();
    }

    public PopupEditText(Texture backgroundTexture, Color highlightColor, BitmapFont font, float x, float y, float width, float height, String text) {
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

        setupFontSize();
    }

    public PopupEditText(Texture backgroundTexture, Color highlightColor, BitmapFont font, float x, float y, float width, float height, String text, BitmapFont.HAlignment hAlignment) {
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

        if (colorEffects != null) {
            colorEffects.update(delta);
            font.setColor(colorEffects.getCurrentColor());
        }

        cursorBlink -= delta;
        if (cursorBlink < 0) {
            cursorOn = !cursorOn;
            cursorBlink = cursorMaxBlink;
        }

        updateTextInput();
    }

    public void updateTextInput() {
        String toAppend = "";
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            toAppend = "a";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
            toAppend = "b";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            toAppend = "c";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            toAppend = "d";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            toAppend = "e";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            toAppend = "f";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
            toAppend = "g";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.H)) {
            toAppend = "h";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            toAppend = "i";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.J)) {
            toAppend = "j";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.K)) {
            toAppend = "k";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            toAppend = "l";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            toAppend = "m";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
            toAppend = "n";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            toAppend = "o";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            toAppend = "p";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            toAppend = "q";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            toAppend = "r";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            toAppend = "s";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            toAppend = "t";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.U)) {
            toAppend = "u";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.V)) {
            toAppend = "v";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            toAppend = "w";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            toAppend = "x";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.Y)) {
            toAppend = "y";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            toAppend = "z";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            toAppend = " ";
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)) {
            toAppend = "";
            text = text.substring(0, text.length() - 1);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_RIGHT)) {
            toAppend = toAppend.toUpperCase();
        }
        text += toAppend;
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
            font.drawWrapped(batch, text + "I", getTextX(), getTextY(), getWidth(), alignment);
        } else {
            font.drawWrapped(batch, text, getTextX(), getTextY(), getWidth(), alignment);
        }
    }

    @Override
    boolean scrollPosMatters() {
        return false;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText(String text) {
        return this.text;
    }
}

