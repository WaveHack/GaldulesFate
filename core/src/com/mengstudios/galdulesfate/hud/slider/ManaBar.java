package com.mengstudios.galdulesfate.hud.slider;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.hud.Text;

public class ManaBar extends Slider {

    public ManaBar(float maxValue) {
        super(maxValue);

        value = maxValue;

        front = Assets.MANA_BAR_FRONT;
        backFull = Assets.MANA_BAR_BACK;
        back = new TextureRegion(backFull);

        minColor = new Color(Color.valueOf("#262C73"));
        maxColor = new Color(Color.valueOf("#262C73"));

        text = new Text(Assets.SLIDER_FONT);
    }

    public void setMana(int value) {
        setValue(value);
    }

    public void changeMana(int value) {
        changeValue(value);
    }

    public float getMaxMana() {
        return maxValue;
    }
}
