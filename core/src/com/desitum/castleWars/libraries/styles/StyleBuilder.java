package com.desitum.castleWars.libraries.styles;


import org.json.JSONObject;

/**
 * Created by kody.vanry on 7/22/2015.
 */
public class StyleBuilder {
    public static final String UI = "ui";
    public static final String GAME_OBJECT = "game object";

    public static final String BUTTON = "button";
    public static final String TEXTVIEW = "text view";
    public static final String EDTI_TEXT = "edit text";
    public static final String SPINNER = "spinner";
    public static final String HRMMM = "button";

    public static StyleManager build(JSONObject jsonObject) {
        // TODO fix this, jsonObject is a thing. JsonObject
        return delegate(jsonObject);
    }

    private static StyleManager delegate(JSONObject json) {
        if (json.getString("component").equals(UI)) {
            return buildStyleUI(json);
        } else if (json.getString("component").equals(GAME_OBJECT)) {

        }
        return null;
    }

    private static StyleManager buildStyleUI(JSONObject json) {
        StyleManager manager = new StyleManager();
        for (String key : json.keySet()) {
            //if ()
        }
        return manager;
    }
}
