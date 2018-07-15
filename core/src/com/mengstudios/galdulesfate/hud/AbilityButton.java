package com.mengstudios.galdulesfate.hud;

import com.mengstudios.galdulesfate.Assets;

public class AbilityButton extends Button {
    private static final float PADDING = 3;

    public AbilityButton() {
        setTextures(Assets.ABILITY_BUTTON_UP, Assets.ABILITY_BUTTON_DOWN);

        text = new Text(Assets.ABILITY_BUTTON_FONT);
    }

    @Override
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;

        text.setPosition(x + PADDING, y + getTextHeight() + PADDING);
    }

    public void setAbility() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick() {

            }
        });
    }
}
