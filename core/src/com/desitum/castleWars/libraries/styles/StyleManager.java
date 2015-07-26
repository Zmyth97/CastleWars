package com.desitum.castleWars.libraries.styles;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

/**
 * Created by Kody.VanRy on 7/25/2015.
 */
public class StyleManager {

    public static int STATE_UP = 0;
    public static int STATE_DOWN = 1;

    private HashMap<Integer, Texture> styles;
    private StateManager stateManager;

    public StyleManager () {
        styles = new HashMap<Integer, Texture>();
        stateManager = new StateManager();
    }

    public void setStateUpTexture (Texture texture) {
        if (texture != null) {
            stateManager.addState(STATE_UP);
        } else {
            stateManager.removeState(STATE_UP);
        }
        styles.put(STATE_UP, texture);
    }

    public void setStateDownTexture (Texture texture) {
        if (texture != null) {
            stateManager.addState(STATE_DOWN);
        } else {
            stateManager.removeState(STATE_DOWN);
        }
        styles.put(STATE_DOWN, texture);
    }

    public Texture getCurrentStateTexture() {
        return styles.get(stateManager.getState());
    }

    public Texture getStateTexture (int state) {
        return styles.get(state);
    }

    public void setState(int state) {
        stateManager.setState(state);
    }
}
