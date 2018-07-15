package com.mengstudios.galdulesfate.hud;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.screen.PlayScreen;

public class Hud implements InputProcessor {
    private PlayScreen playScreen;

    private OrthographicCamera camera;

    private StatusDisplay statusDisplay;
    private InventoryDisplay inventoryDisplay;

    public Hud(PlayScreen playScreen) {
        this.playScreen = playScreen;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, GaldulesFate.WIDTH, GaldulesFate.HEIGHT);

        statusDisplay = new StatusDisplay(this);
        inventoryDisplay = new InventoryDisplay(this);
    }

    public void update(float delta) {
        camera.update();

        statusDisplay.update(delta);
        inventoryDisplay.update(delta);
    }

    public void draw(SpriteBatch batch) {
        statusDisplay.draw(batch);
        inventoryDisplay.draw(batch);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Array<AbilityButton> getAbilityButtons() {
        return statusDisplay.getAbilityButtons();
    }

    public PlayScreen getPlayScreen() {
        return playScreen;
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

        statusDisplay.touchDown(screenX, screenY, pointer, button);
        inventoryDisplay.touchDown(screenX, screenY, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 screenCoords = camera.unproject(new Vector3(screenX, screenY, 0));
        screenX = Math.round(screenCoords.x);
        screenY = Math.round(screenCoords.y);

        statusDisplay.touchUp(screenX, screenY, pointer, button);
        inventoryDisplay.touchUp(screenX, screenY, pointer, button);
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
