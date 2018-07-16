package com.mengstudios.galdulesfate.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.screen.PlayScreen;

public class MobileControls {
    PlayScreen playScreen;

    Stage stage;

    private Touchpad touchpad;

    private final float DEAD_ZONE_RADIUS = 10;

    public MobileControls(PlayScreen playScreen, Viewport viewport) {
        this.playScreen = playScreen;

        stage = new Stage(viewport, playScreen.getGame().batch);

        Skin skin = new Skin();
        skin.add("touchpad_background", Assets.TOUCHPAD_BACKGROUND);
        skin.add("touchpad_knob", Assets.TOUCHPAD_KNOB);

        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
        touchpadStyle.background = skin.getDrawable("touchpad_background");
        touchpadStyle.knob = skin.getDrawable("touchpad_knob");

        touchpad = new Touchpad(DEAD_ZONE_RADIUS, touchpadStyle);
        touchpad.setBounds(25, 25, Assets.TOUCHPAD_BACKGROUND.getWidth(), Assets.TOUCHPAD_BACKGROUND.getHeight());
        stage.addActor(touchpad);
    }

    public void update(float delta) {
        playScreen.getWorld().getPlayer().setVelocityX(200 * touchpad.getKnobPercentX());
        if(touchpad.getKnobPercentY() > 0.5) {
            playScreen.getWorld().getPlayer().jump();
        }
    }

    public void draw(SpriteBatch batch) {
        stage.draw();
    }

    public Stage getStage() {
        return stage;
    }
}
