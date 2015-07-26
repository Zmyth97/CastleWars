package com.desitum.castleWars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.desitum.castleWars.CastleWars;
import com.desitum.castleWars.GooglePlayServicesInterface;
import com.desitum.castleWars.data.Assets;
import com.desitum.castleWars.data.Settings;

import org.json.JSONObject;
import org.json.JSONString;

/**
 * Created by Zmyth97 on 2/25/2015.
 */
public class SplashScreen implements Screen {

    private OrthographicCamera cam;
    private SpriteBatch batch;
    private CastleWars game;
    private GooglePlayServicesInterface gps;

    private Sprite desitum;

    private boolean beenThrough = false;
    private boolean hasLoaded = false;
    private float timeElapsed = 0;

    public SplashScreen(GooglePlayServicesInterface gps, CastleWars game) {
        String myString = "{ 'hello' : 'json', 'hello2' : {'hello3' : 'json2'}}";
        JSONObject myJson = new JSONObject(myString);
        System.out.println(myJson.getString("hello2"));

        cam = new OrthographicCamera(MenuScreen.SCREEN_WIDTH, MenuScreen.SCREEN_HEIGHT);
        cam.position.set(MenuScreen.SCREEN_WIDTH / 2, MenuScreen.SCREEN_HEIGHT / 2, 0);
        batch = new SpriteBatch();

        this.gps = gps;
        this.game = game;

        Texture desitumTexture = new Texture(Gdx.files.internal("menu/desitum.png"));
        desitum = new Sprite(desitumTexture);
        desitum.setSize(100, 150);
        desitum.setX(MenuScreen.SCREEN_WIDTH / 2 - desitum.getWidth() / 2);
        desitum.setY(MenuScreen.SCREEN_HEIGHT / 2 - desitum.getHeight() / 2);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (timeElapsed > 3) {
            game.setScreen(new MenuScreen(gps, game));
        } else if (beenThrough && !hasLoaded) {
            hasLoaded = true;
            Assets.loadMenuTextures();
            Assets.loadGameTextures();
            Assets.loadSounds();
            Assets.loadCards();
            Settings.load();
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        cam.update();

        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        desitum.draw(batch);
        batch.end();
        timeElapsed += delta;
        beenThrough = true;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
