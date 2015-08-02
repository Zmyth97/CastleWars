package com.desitum.castleWars.libraries.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.desitum.castleWars.libraries.game_objects.GameObject;
import com.desitum.castleWars.libraries.styles.StyleBuilder;
import com.desitum.castleWars.libraries.ui.widgets.Button;
import com.desitum.castleWars.libraries.world.KodyWorld;

import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by Kody.VanRy on 7/24/2015.
 */
public class WorldBuilder {

    public static final String TEXTURE = "texture";
    public static final String X = "x";
    public static final String Y = "y";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    public static final String STYLE = "style";

    public static final String OBJECT_TYPE = "object type";
    public static final String GAME_OBJECT = "game object";
    public static final String WIDGET = "widget";
    public static final String BUTTON = "button";
    public static final String BUTTON_MATERIAL = "button material";
    public static final String EDIT_TEXT = "edit text";
    public static final String IMAGE = "image";
    public static final String MENU = "menu";

    public static void buildWorld(KodyWorld world, String jsonFile) {
        String json = Gdx.files.internal(jsonFile).readString();
        JSONObject worldJSON = new JSONObject(json);

        Iterator<String> worldObjects = worldJSON.keys();
        while (worldObjects.hasNext()) {
            String name = worldObjects.next();
            JSONObject worldObject = worldJSON.getJSONObject(name);

            if (worldObject.getString(OBJECT_TYPE).equals(GAME_OBJECT)) {
                addGameObject(world, worldObject, name);
            } else if (WIDGET.contains(worldObject.getString(OBJECT_TYPE))) {

            }
        }
    }

    private static void addGameObject(KodyWorld world, JSONObject jsonObject, String name) {
        GameObject gameObject = new GameObject(new Texture(jsonObject.getString(TEXTURE)),
                Float.parseFloat(jsonObject.getString(X)),
                Float.parseFloat(jsonObject.getString(Y)),
                Float.parseFloat(jsonObject.getString(WIDTH)),
                Float.parseFloat(jsonObject.getString(HEIGHT)));

        world.addGameObject(gameObject);
    }

    private static void addWidget (KodyWorld world, JSONObject jsonObject) {
        String widgetType = jsonObject.getString(OBJECT_TYPE);
        if (widgetType.equals(BUTTON)) {
            world.addWidget(buildButton(world, jsonObject));
        } else if (widgetType.equals(BUTTON_MATERIAL)) {
            world.addWidget(buildButton(world, jsonObject));
        } else if (widgetType.equals(EDIT_TEXT)) {
            world.addWidget(buildButton(world, jsonObject));
        } else if (widgetType.equals(IMAGE)) {
            world.addWidget(buildButton(world, jsonObject));
        } else if (widgetType.equals(MENU)) {
            world.addWidget(buildButton(world, jsonObject));
        } else if (widgetType.equals(BUTTON)) {
            world.addWidget(buildButton(world, jsonObject));
        } else if (widgetType.equals(BUTTON)) {
            world.addWidget(buildButton(world, jsonObject));
        }
    }

    private static Button buildButton(KodyWorld world, JSONObject jsonObject) {
        Button button = new Button(new Texture(jsonObject.getString(TEXTURE)),
                Float.parseFloat(jsonObject.getString(X)),
                Float.parseFloat(jsonObject.getString(Y)),
                Float.parseFloat(jsonObject.getString(WIDTH)),
                Float.parseFloat(jsonObject.getString(HEIGHT)));
        if (jsonObject.getJSONObject(STYLE) != null) {
//            button.setStyle(StyleBuilder.build(jsonObject.getJSONObject(STYLE)));
        }
        return button;
    }
}
