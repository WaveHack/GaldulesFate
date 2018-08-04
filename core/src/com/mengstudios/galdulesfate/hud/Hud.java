package com.mengstudios.galdulesfate.hud;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.hud.ui.InventoryDisplay;
import com.mengstudios.galdulesfate.hud.ui.Ribbon;
import com.mengstudios.galdulesfate.hud.ui.StatusDisplay;
import com.mengstudios.galdulesfate.hud.ui.Ui;
import com.mengstudios.galdulesfate.screen.PlayScreen;

public class Hud implements InputProcessor {
    private PlayScreen playScreen;

    private ExtendViewport viewport;
    private OrthographicCamera camera;

    private Ribbon ribbon;
    private StatusDisplay statusDisplay;

    private Array<Ui> uis;
    private Array<Ui> uisToRemove;

    private MobileControls mobileControls;

    public Hud(PlayScreen playScreen) {
        this.playScreen = playScreen;

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        viewport = new ExtendViewport(GaldulesFate.WIDTH, GaldulesFate.HEIGHT, camera);

        ribbon = new Ribbon(this);
        statusDisplay = new StatusDisplay(this);

        uis = new Array<>();
        uisToRemove = new Array<>();

        if(GaldulesFate.mobile) {
            mobileControls = new MobileControls(playScreen, viewport);
        }
    }

    public void update(float delta) {
        camera.update();

        ribbon.update(delta);
        statusDisplay.update(delta);
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
        ribbon.draw(batch);
        statusDisplay.draw(batch);
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
        return ribbon.getInventoryDisplay();
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

        if(GaldulesFate.mobile) {
            if(mobileControls.isTouched(screenX, screenY)) {
                return true;
            }
        }

        ribbon.touchDown(screenX, screenY, pointer, button);
        statusDisplay.touchDown(screenX, screenY, pointer, button);
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

        ribbon.touchUp(screenX, screenY, pointer, button);
        statusDisplay.touchUp(screenX, screenY, pointer, button);
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
