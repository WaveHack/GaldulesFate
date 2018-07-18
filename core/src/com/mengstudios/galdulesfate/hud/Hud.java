package com.mengstudios.galdulesfate.hud;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.screen.PlayScreen;

public class Hud implements InputProcessor {
    private PlayScreen playScreen;

    private ExtendViewport viewport;
    private OrthographicCamera camera;

    private StatusDisplay statusDisplay;
    private InventoryDisplay inventoryDisplay;

    Array<Ui> uis;
    Array<Ui> uisToRemove;

    private MobileControls mobileControls;

    public Hud(PlayScreen playScreen) {
        this.playScreen = playScreen;

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        viewport = new ExtendViewport(GaldulesFate.WIDTH, GaldulesFate.HEIGHT, camera);

        statusDisplay = new StatusDisplay(this);
        inventoryDisplay = new InventoryDisplay(this);

        uis = new Array<>();
        uisToRemove = new Array<>();

        if(GaldulesFate.mobile) {
            mobileControls = new MobileControls(playScreen, viewport);
        }
    }

    public void update(float delta) {
        camera.update();

        statusDisplay.update(delta);
        inventoryDisplay.update(delta);
        if(GaldulesFate.mobile) {
            mobileControls.update(delta);
        }

        for(Ui ui: uis) {
            if(ui.isRemoved()) {
                uisToRemove.add(ui);
            }

            ui.update(delta);
        }
        uis.removeAll(uisToRemove, true);
        uisToRemove.clear();
    }

    public void draw(SpriteBatch batch) {
        statusDisplay.draw(batch);
        inventoryDisplay.draw(batch);
        for(Ui ui: uis) {
            if(ui.isHidden())
                continue;

            ui.draw(batch);
        }
        /*if(GaldulesFate.mobile) {
            mobileControls.draw(batch);
        }*/
    }

    public void addUi(Ui ui) {
        uis.add(ui);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Array<AbilityButton> getAbilityButtons() {
        return statusDisplay.getAbilityButtons();
    }

    public StatusDisplay getStatusDisplay() {
        return statusDisplay;
    }

    public InventoryDisplay getInventoryDisplay() {
        return inventoryDisplay;
    }

    public PlayScreen getPlayScreen() {
        return playScreen;
    }

    public ExtendViewport getViewport() {
        return viewport;
    }

    public MobileControls getMobileControls() {
        return mobileControls;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.NUM_1) {
            getAbilityButtons().get(0).press();
        }
        if(keycode == Input.Keys.NUM_2) {
            getAbilityButtons().get(1).press();
        }
        if(keycode == Input.Keys.NUM_3) {
            getAbilityButtons().get(2).press();
        }
        if(keycode == Input.Keys.NUM_4) {
            getAbilityButtons().get(3).press();
        }
        if(keycode == Input.Keys.NUM_5) {
            getAbilityButtons().get(4).press();
        }
        if(keycode == Input.Keys.NUM_6) {
            getAbilityButtons().get(5).press();
        }
        if(keycode == Input.Keys.NUM_7) {
            getAbilityButtons().get(6).press();
        }
        if(keycode == Input.Keys.NUM_8) {
            getAbilityButtons().get(7).press();
        }
        if(keycode == Input.Keys.NUM_9) {
            getAbilityButtons().get(8).press();
        }
        if(keycode == Input.Keys.NUM_0) {
            getAbilityButtons().get(9).press();
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.NUM_1) {
            getAbilityButtons().get(0).release();
        }
        if(keycode == Input.Keys.NUM_2) {
            getAbilityButtons().get(1).release();
        }
        if(keycode == Input.Keys.NUM_3) {
            getAbilityButtons().get(2).release();
        }
        if(keycode == Input.Keys.NUM_4) {
            getAbilityButtons().get(3).release();
        }
        if(keycode == Input.Keys.NUM_5) {
            getAbilityButtons().get(4).release();
        }
        if(keycode == Input.Keys.NUM_6) {
            getAbilityButtons().get(5).release();
        }
        if(keycode == Input.Keys.NUM_7) {
            getAbilityButtons().get(6).release();
        }
        if(keycode == Input.Keys.NUM_8) {
            getAbilityButtons().get(7).release();
        }
        if(keycode == Input.Keys.NUM_9) {
            getAbilityButtons().get(8).release();
        }
        if(keycode == Input.Keys.NUM_0) {
            getAbilityButtons().get(9).release();
        }

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
        for(Ui ui: uis) {
            if(ui.isHidden())
                continue;

            ui.touchDown(screenX, screenY, pointer, button);
        }
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
