package com.desitum.castleWars.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.desitum.castleWars.screens.GameScreen;
import com.desitum.castleWars.world.GameWorld;

/**
 * Created by Zmyth97 on 6/7/2015.
 */
public class Cloud extends Sprite {

    private float yAmount;
    private float size;
    private float speed;

    private boolean remove;

    private GameWorld gw;

    public Cloud(GameWorld gw, Texture cloudTexture, float yAmount, float size, float speed){
        super(cloudTexture, 0, 0, cloudTexture.getWidth(), cloudTexture.getHeight());
        this.gw = gw;
        this.yAmount = yAmount;
        this.size = size;
        this.speed = speed;
        this.remove = false;

        this.setOriginCenter();
        this.setSize(cloudTexture.getWidth() / size, cloudTexture.getHeight() / size);
        this.setPosition(0 - (cloudTexture.getWidth() / size), yAmount);
    }

    public void update(float delta){
        if (this.getX() > GameScreen.SCREEN_WIDTH) {
            remove = true;
        } else {
            this.setX(getX() + speed * delta);
        }
    }

    public boolean needsRemoval() {
        return remove;
    }

    public void draw(SpriteBatch gameBatch) {
        super.draw(gameBatch);
    }
}
