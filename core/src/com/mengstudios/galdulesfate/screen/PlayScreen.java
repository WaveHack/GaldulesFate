package com.mengstudios.galdulesfate.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.entity.Player;
import com.mengstudios.galdulesfate.hud.Hud;
import com.mengstudios.galdulesfate.world.World;

public class PlayScreen implements Screen, InputProcessor {
    private GaldulesFate game;

    private OrthographicCamera camera;

    private Hud hud;

    private World world;

    private static final int TOTAL_KEYS = 255;
    private boolean[] keysHeld = new boolean[TOTAL_KEYS];

    FPSLogger fpsLogger = new FPSLogger();

    public PlayScreen(GaldulesFate game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, GaldulesFate.WIDTH, GaldulesFate.HEIGHT);

        world = new World(this);

        hud = new Hud(this);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(hud);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void show() {

    }

    public void update(float delta) {
        for (int i = 0; i < keysHeld.length; i++) {
            if(keysHeld[i]) {
                keyHeld(i);
            }
        }

        hud.update(delta);

        camera.position.set(getPlayer().getX(), getPlayer().getY() + 100, 0);
        camera.update();

        world.update(delta);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl20.glClearColor(1, 1, 1, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        world.render(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.getCamera().combined);
        game.batch.begin();
        hud.draw(game.batch);
        game.batch.end();

        fpsLogger.log();
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

    @Override
    public boolean keyDown(int keycode) {
        float velocityX = 0;

        if(keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            velocityX -= 200;
            getPlayer().setVelocityX(velocityX);
        }
        if(keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            velocityX += 200;
            getPlayer().setVelocityX(velocityX);
        }
        if(keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            getPlayer().jump();
        }

        if(keycode == Input.Keys.NUM_1) {
            hud.getAbilityButtons().get(0).press();
        }
        if(keycode == Input.Keys.NUM_2) {
            hud.getAbilityButtons().get(1).press();
        }
        if(keycode == Input.Keys.NUM_3) {
            hud.getAbilityButtons().get(2).press();
        }
        if(keycode == Input.Keys.NUM_4) {
            hud.getAbilityButtons().get(3).press();
        }
        if(keycode == Input.Keys.NUM_5) {
            hud.getAbilityButtons().get(4).press();
        }
        if(keycode == Input.Keys.NUM_6) {
            hud.getAbilityButtons().get(5).press();
        }
        if(keycode == Input.Keys.NUM_7) {
            hud.getAbilityButtons().get(6).press();
        }
        if(keycode == Input.Keys.NUM_8) {
            hud.getAbilityButtons().get(7).press();
        }
        if(keycode == Input.Keys.NUM_9) {
            hud.getAbilityButtons().get(8).press();
        }
        if(keycode == Input.Keys.NUM_0) {
            hud.getAbilityButtons().get(9).press();
        }

        keysHeld[keycode] = true;

        return false;
    }

    public boolean keyHeld(int keycode) {
        float velocityX = 0;

        if(keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            velocityX -= 200;
            getPlayer().setVelocityX(velocityX);
        }
        if(keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            velocityX += 200;
            getPlayer().setVelocityX(velocityX);
        }
        if(keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            getPlayer().jump();
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.A ||  keycode == Input.Keys.LEFT || keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            getPlayer().setVelocityX(0);
        }

        if(keycode == Input.Keys.NUM_1) {
            hud.getAbilityButtons().get(0).release();
        }
        if(keycode == Input.Keys.NUM_2) {
            hud.getAbilityButtons().get(1).release();
        }
        if(keycode == Input.Keys.NUM_3) {
            hud.getAbilityButtons().get(2).release();
        }
        if(keycode == Input.Keys.NUM_4) {
            hud.getAbilityButtons().get(3).release();
        }
        if(keycode == Input.Keys.NUM_5) {
            hud.getAbilityButtons().get(4).release();
        }
        if(keycode == Input.Keys.NUM_6) {
            hud.getAbilityButtons().get(5).release();
        }
        if(keycode == Input.Keys.NUM_7) {
            hud.getAbilityButtons().get(6).release();
        }
        if(keycode == Input.Keys.NUM_8) {
            hud.getAbilityButtons().get(7).release();
        }
        if(keycode == Input.Keys.NUM_9) {
            hud.getAbilityButtons().get(8).release();
        }
        if(keycode == Input.Keys.NUM_0) {
            hud.getAbilityButtons().get(9).release();
        }

        keysHeld[keycode] = false;

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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 screenCoords = camera.unproject(new Vector3(screenX, screenY, 0));
        screenX = Math.round(screenCoords.x);
        screenY = Math.round(screenCoords.y);
        return false;
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

    public OrthographicCamera getCamera() {
        return camera;
    }

    public World getWorld() {
        return world;
    }

    public Player getPlayer() {
        return getWorld().getPlayer();
    }
}
