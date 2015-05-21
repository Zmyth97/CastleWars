package com.desitum.castleWars.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.desitum.castleWars.libraries.Drawing;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public class Assets {

    //Button Textures
    public static Texture playButtonUp;
    public static Texture playButtonDown;
    public static Texture settingsButtonUp;
    public static Texture settingsButtonDown;
    public static Texture cancelButtonUp;
    public static Texture cancelButtonDown;
    public static Texture okButtonUp;
    public static Texture okButtonDown;
    public static Texture saveButtonUp;
    public static Texture saveButtonDown;
    public static Texture exitButtonUp;
    public static Texture exitButtonDown;

    //Menu Textures
    public static Texture textCursor;
    public static Texture textFieldBackground;
    public static Texture menuBackground;

    //Misc Textures
    public static BitmapFont textFieldFont;

    public static void loadMenuTextures() {
        playButtonUp = new Texture("menu/play_button_up.png");
        playButtonDown = new Texture("menu/play_button_down.png");
        settingsButtonUp = new Texture("menu/settings_button_up.png");
        settingsButtonDown = new Texture("menu/settings_button_down.png");
        cancelButtonUp = new Texture("menu/cancel_button_up.png");
        cancelButtonDown = new Texture("menu/cancel_button_down.png");
        okButtonUp = new Texture("menu/ok_button_up.png");
        okButtonDown = new Texture("menu/ok_button_down.png");
        saveButtonUp = new Texture("menu/save_button_up.png");
        saveButtonDown = new Texture("menu/save_button_down.png");
        exitButtonUp = new Texture("menu/exit_button_up.png");
        exitButtonDown = new Texture("menu/exit_button_down.png");

        textFieldBackground = Drawing.getTextureRoundedRectangle(50, 50, 5, new Color(0.5f, 0.2f, 0.1f, 0.8f));
        menuBackground = new Texture("menu/menu_bg.png");

        textFieldFont = new BitmapFont(Gdx.files.internal("font/cartoon.fnt"), Gdx.files.internal("font/cartoon.png"), false);
        textFieldFont.setScale(0.15f);
        textFieldFont.setColor(Color.BLACK);
    }

    public static void loadGameTextures() {

    }

    public static void loadSounds(){

    }

    public static void dispose(){
        //TODO Add Disposals
    }
}
