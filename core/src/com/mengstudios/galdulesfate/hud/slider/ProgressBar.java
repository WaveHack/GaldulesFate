package com.mengstudios.galdulesfate.hud.slider;

import com.badlogic.gdx.graphics.Color;
import com.mengstudios.galdulesfate.Assets;

public class ProgressBar extends Slider {
    private boolean hasFullProgress;

    public ProgressBar(float maxValue) {
        super(maxValue);

        setShowText(false);
        setMinColor(Color.WHITE);
        setMaxColor(Color.WHITE);
        setBackFull(Assets.PROGRESS_BAR_SHORT_BACK);
        front = Assets.PROGRESS_BAR_SHORT_FRONT;
        setValue(0);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        hasFullProgress = value >= maxValue;
    }

    public boolean hasFullProgress() {
        return hasFullProgress;
    }
}
