package com.mengstudios.galdulesfate.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Slider {
    protected Texture front;
    protected Texture backFull;
    protected TextureRegion back;

    protected float x;
    protected float y;

    protected int maxValue;
    protected int value;

    protected Color sliderColor;
    protected Color minColor;
    protected Color maxColor;

    protected Text text;

    protected float TEXT_PADDING_X = -50;
    protected float TEXT_PADDING_Y = 2;

    protected boolean init = true;

    public Color lerp(Color color1, Color color2, float t) {
        float red = color1.r * (1 - t) + color2.r * t;
        float green = color1.g * (1 - t) + color2.g * t;
        float blue = color1.b * (1 - t) + color2.b * t;

        return new Color(red, green, blue, 1);
    }

    public void update(float delta) {
        if(init) {
            text.setText(Integer.toString(value) + " / " + Integer.toString(maxValue));
            text.updateBounds();
            init = false;
        }

        if(value < 0) {
            value = 0;
        }
        if(value > maxValue) {
            value = maxValue;
        }

        sliderColor = lerp(minColor, maxColor, (float) value / (float) maxValue);
        back.setRegionWidth(Math.round(((float) value / (float) maxValue) * backFull.getWidth()));

        text.setText(Integer.toString(value) + " / " + Integer.toString(maxValue));
    }

    public void draw(SpriteBatch batch) {
        batch.setColor(sliderColor);
        batch.draw(back, x, y);
        batch.setColor(Color.WHITE);
        batch.draw(front, x, y);

        text.draw(batch);
    }

    public void setValue(int value) {
        this.value = value;

        text.setText(Integer.toString(value) + " / " + Integer.toString(maxValue));
    }

    public void changeValue(int value) {
        this.value += value;
    }

    public float getWidth() {
        return front.getWidth();
    }

    public float getHeight() {
        return front.getHeight();
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;

        text.setPosition(x + front.getWidth() + TEXT_PADDING_X, y + front.getHeight() + TEXT_PADDING_Y);
    }
}
