package com.desitum.castleWars.libraries.styles;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kody.VanRy on 7/25/2015.
 */
public class StateManager {

    List<Integer> statesAvailable;
    private int state;

    public StateManager () {
        statesAvailable = new ArrayList<Integer>();
    }

    public void addState(int state) {
        if (!statesAvailable.contains(state)) {
            statesAvailable.add(state);
        }
    }

    public void removeState(int state) {
        if (statesAvailable.contains(state)) {
            statesAvailable.remove(statesAvailable.indexOf(state));
        }
    }

    public void setState(int state) {
        if (statesAvailable.contains(state)) {
            this.state = state;
        } else {
            if (state == StyleManager.STATE_DOWN) {
                state = StyleManager.STATE_UP;
            }
        }
    }

    public int getState() {
        return state;
    }
}
