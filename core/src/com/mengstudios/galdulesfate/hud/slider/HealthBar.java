package com.mengstudios.galdulesfate.hud.slider;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.hud.Text;

public class HealthBar extends Slider {

    public HealthBar(float maxValue) {
        super(maxValue);

        value = maxValue;

        front = Assets.HEALTH_BAR_FRONT;
        backFull = Assets.HEALTH_BAR_BACK;
        back = new TextureRegion(backFull);

        minColor = new Color(Color.RED);
        maxColor = new Color(Color.FOREST);

        text = new Text(Assets.SLIDER_FONT);
    }

    public void setHealth(int value) {
        setValue(value);
    }

    public void changeHealth(int value) {
        changeValue(value);
    }

    public float getMaxHealth() {
        return maxValue;
    }
}
