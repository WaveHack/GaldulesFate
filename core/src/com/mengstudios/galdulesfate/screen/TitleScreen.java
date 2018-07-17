package com.mengstudios.galdulesfate.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.hud.Button;
import com.mengstudios.galdulesfate.hud.Text;

public class TitleScreen implements Screen, InputProcessor {
    private GaldulesFate game;

    private OrthographicCamera camera;

    private final int titleSize = 72;

    private Texture backgroundTexture;

    private Text titleText;
    private Button playButton;
    private Button joinServerButton;
    private Button settingsButton;

    public TitleScreen(final GaldulesFate game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, GaldulesFate.WIDTH, GaldulesFate.HEIGHT);

        backgroundTexture = Assets.TITLE_SCREEN_BACKGROUND;

        titleText = new Text(Assets.TITLE_FONT);
        titleText.setText(GaldulesFate.TITLE);
        titleText.updateBounds();
        titleText.setPosition(GaldulesFate.WIDTH / 2 - titleText.getWidth() / 2, GaldulesFate.HEIGHT * 5 / 6);

        playButton = new Button();
        playButton.setText("play game");
        playButton.setPosition(GaldulesFate.WIDTH / 2 - playButton.getWidth() / 2, GaldulesFate.HEIGHT * 7 / 12);
        playButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick() {
                game.setScreen(new PlayScreen(game));
            }
        });

        joinServerButton = new Button();
        joinServerButton.setText("join server");
        joinServerButton.setPosition(GaldulesFate.WIDTH / 2 - joinServerButton.getWidth() / 2, GaldulesFate.HEIGHT * 5 / 12);
        joinServerButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick() {

            }
        });

        settingsButton = new Button();
        settingsButton.setText("settings");
        settingsButton.setPosition(GaldulesFate.WIDTH / 2 - settingsButton.getWidth() / 2, GaldulesFate.HEIGHT * 3 / 12);
        settingsButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick() {

            }
        });

        Gdx.input.setInputProcessor(this);

        Assets.BACKGROUD_MUSIC_1.setVolume(0.1f);
        Assets.BACKGROUD_MUSIC_1.play();
    }

    @Override
    public void show() {

    }

    public void update(float delta) {
        camera.update();
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl20.glClearColor(1, 1, 1, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0);
        titleText.draw(game.batch);
        playButton.draw(game.batch);
        joinServerButton.draw(game.batch);
        settingsButton.draw(game.batch);
        game.batch.end();
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
        backgroundTexture.dispose();
        titleText.dispose();
        playButton.dispose();
        joinServerButton.dispose();
        settingsButton.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 screenCoords = camera.unproject(new Vector3(screenX, screenY, 0));
        screenX = Math.round(screenCoords.x);
        screenY = Math.round(screenCoords.y);

        playButton.touchDown(screenX, screenY);
        joinServerButton.touchDown(screenX, screenY);
        settingsButton.touchDown(screenX, screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 screenCoords = camera.unproject(new Vector3(screenX, screenY, 0));
        screenX = Math.round(screenCoords.x);
        screenY = Math.round(screenCoords.y);

        playButton.touchUp(screenX, screenY);
        joinServerButton.touchUp(screenX, screenY);
        settingsButton.touchUp(screenX, screenY);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
