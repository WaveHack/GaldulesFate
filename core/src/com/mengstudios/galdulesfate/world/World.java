package com.mengstudios.galdulesfate.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.entity.EntityManager;
import com.mengstudios.galdulesfate.entity.mob.Player;
import com.mengstudios.galdulesfate.screen.PlayScreen;

public class World implements InputProcessor {
    private PlayScreen playScreen;

    private ExtendViewport viewport;
    private OrthographicCamera camera;

    private EntityManager entityManager;

    private WorldGenerator generator;

    public static final float GRAVITY = 8;

    private static final int TOTAL_KEYS = 255;
    private boolean[] keysHeld = new boolean[TOTAL_KEYS];
    private boolean touchHeld;
    private int touchHeldX;
    private int touchHeldY;

    private boolean created;

    public World(PlayScreen playScreen) {
        this.playScreen = playScreen;

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        viewport = new ExtendViewport(GaldulesFate.WIDTH, GaldulesFate.HEIGHT, camera);

        entityManager = new EntityManager(this);

        generator = new WorldGenerator(this);
    }

    public void create() {
        entityManager.create();
        created = true;
    }

    public void update(float delta) {
        handleHeldInput(delta);

        entityManager.update(delta);

        generator.update(delta);

        camera.position.set(getPlayer().getX() + getPlayer().getWidth() / 2, getPlayer().getY() + getPlayer().getHeight() / 2 + 50, 0);
        camera.update();
    }

    public void render(SpriteBatch batch) {
        batch.draw(Assets.SKY, camera.position.x - camera.viewportWidth / 2, camera.position.y - camera.viewportHeight / 2);

        entityManager.render(batch);
    }

    public boolean isCreated() {
        return created;
    }

    public PlayScreen getPlayScreen() {
        return playScreen;
    }

    public Player getPlayer() {
        return entityManager.getPlayer();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public ExtendViewport getViewport() {
        return viewport;
    }

    public boolean isTouchHeld() {
        return touchHeld;
    }

    public int getTouchHeldX() {
        return touchHeldX;
    }

    public int getTouchHeldY() {
        return touchHeldY;
    }

    private void handleHeldInput(float delta) {
        for (int i = 0; i < keysHeld.length; i++) {
            if(keysHeld[i]) {
                keyHeld(i);
            }
        }
        if(touchHeld) {
            touchHeld(Gdx.input.getX(), Gdx.input.getY(), delta);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        entityManager.keyDown(keycode);

        keysHeld[keycode] = true;

        return false;
    }

    private boolean keyHeld(int keycode) {
        entityManager.keyHeld(keycode);

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        entityManager.keyUp(keycode);

        keysHeld[keycode] = false;

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        entityManager.touchDown(screenX, screenY, pointer, button);

        Vector3 screenCoords = camera.unproject(new Vector3(screenX, screenY, 0));
        screenX = Math.round(screenCoords.x);
        screenY = Math.round(screenCoords.y);

        touchHeld = true;
        touchHeldX = screenX;
        touchHeldY = screenY;

        return false;
    }

    private void touchHeld(int screenX, int screenY, float delta) {
        Vector3 screenCoords = camera.unproject(new Vector3(screenX, screenY, 0));
        screenX = Math.round(screenCoords.x);
        screenY = Math.round(screenCoords.y);

        touchHeldX = screenX;
        touchHeldY = screenY;

        entityManager.touchHeld(screenX, screenY, delta);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 screenCoords = camera.unproject(new Vector3(screenX, screenY, 0));
        screenX = Math.round(screenCoords.x);
        screenY = Math.round(screenCoords.y);

        entityManager.touchUp(screenX, screenY, pointer, button);

        touchHeld = false;

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
}
