package com.desitum.castleWars.libraries.menu;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.libraries.animation.ColorEffects;
import com.desitum.castleWars.libraries.animation.ScaleAnimator;
import com.desitum.castleWars.libraries.interpolation.Interpolation;

/**
 * Created by kody on 4/19/15.
 * can be used by kody and people in []
 */
public class PopupButtonMaterial extends PopupWidget {
    private Texture upTexture;
    private Texture shadow;
    private Texture touchShadow;
    private Pixmap shadowMap;

    private boolean beenDown;

    private float z;

    private float originalX;
    private float originalY;

    private OnClickListener buttonListener;

    private Vector3 touchPos;
    private ScaleAnimator touchScale;
    private ColorEffects touchColor;

    public PopupButtonMaterial(Texture upTexture, float x, float y, float z, float width, float height) {
        super(upTexture, width, height, x, y);

        this.upTexture = upTexture;

        this.z = z;

        this.originalX = x;
        this.originalY = y;

        Texture shadowText = new Texture("shadow.png");
        touchShadow = Assets.invisible;
        this.shadow = shadowText;
        this.shadowMap = new Pixmap((int) width * 10, (int) height * 10, Pixmap.Format.RGBA8888);

        this.setSize(width, height);
        this.setPosition(x, y);

        this.setOriginCenter();

        touchPos = new Vector3(0, 0, 0);
        touchScale = new ScaleAnimator(1, 0, 1, Interpolation.LINEAR_INTERPOLATOR);
        touchColor = new ColorEffects(Color.GRAY, new Color(0.5f, 0.5f, 0.5f, 0), 1);
    }

    public void onClickDown(Vector3 pos) {
        this.touchPos.set(pos.x - getX(), pos.y - getY(), 0);

        setPosition(getX(), getY() - z * 0.4f);

        touchScale.start(false);
        touchColor.start(false);

        beenDown = true;
    }

    public void onClickUp(boolean clicked) {
        setPosition(originalX, originalY);
        this.setTexture(upTexture);
        if (buttonListener != null && clicked && beenDown) {
            buttonListener.onClick(this);
        }
        beenDown = false;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        touchScale.update(delta);
        touchColor.update(delta);
        if (touchScale.isRunning()) {
            shadowMap = new Pixmap((int) getWidth() * 10, (int) getHeight() * 10, Pixmap.Format.RGBA8888);
            shadowMap.setColor(touchColor.getCurrentColor());
            shadowMap.fillCircle((int) touchPos.x * 10, (int) (shadowMap.getHeight() - touchPos.y * 10), (int) (touchScale.getAmount() * getHeight() * 0.75f * 10));
            touchShadow = new Texture(shadowMap);
        }
    }

    public void setButtonListener(OnClickListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    public void draw(SpriteBatch batch) {
        Color c = batch.getColor();
        batch.setColor(getColor());
        batch.draw(shadow, getX() + z * 0.2f, getY() - z * 0.5f, getWidth(), getHeight());
        batch.setColor(c);
        super.draw(batch);
        batch.setColor(getColor());
        batch.draw(touchShadow, getX(), getY(), getWidth(), getHeight());
        batch.setColor(c);
    }

    @Override
    boolean scrollPosMatters() {
        return false;
    }

    public void setX(float x) {
        super.setX(x);
        originalX = x;
    }

    public void setY(float y) {
        super.setY(y);
        originalY = y;
    }
}
