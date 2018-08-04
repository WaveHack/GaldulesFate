package com.mengstudios.galdulesfate.hud.slider;

import com.badlogic.gdx.graphics.Color;
import com.mengstudios.galdulesfate.Assets;

public class ProgressBar extends Slider {
    public ProgressBar(float maxValue) {
        super(maxValue);

        setShowText(false);
        setMinColor(Color.WHITE);
        setMaxColor(Color.WHITE);
        setBackFull(Assets.PROGRESS_BAR_SHORT);
        setValue(0);
    }
}
