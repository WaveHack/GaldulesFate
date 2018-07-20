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

    protected float maxValue;
    protected float value;

    protected Color sliderColor;
    protected Color minColor;
    protected Color maxColor;

    protected Text text;

    protected float TEXT_PADDING_X = -50;
    protected float TEXT_PADDING_Y = 2;

    protected boolean init = true;
    protected boolean showText = true;

    public Color lerp(Color color1, Color color2, float t) {
        float red = color1.r * (1 - t) + color2.r * t;
        float green = color1.g * (1 - t) + color2.g * t;
        float blue = color1.b * (1 - t) + color2.b * t;

        return new Color(red, green, blue, 1);
    }

    public void update(float delta) {
        if(init && showText) {
            text.setText(Integer.toString((int) value) + " / " + Integer.toString((int) maxValue));
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

        if(text != null) {
            text.setText(Integer.toString((int) value) + " / " + Integer.toString((int) maxValue));
        }
    }

    public void draw(SpriteBatch batch) {
        batch.setColor(sliderColor);
        batch.draw(back, x, y);
        batch.setColor(Color.WHITE);
        if(front != null) {
            batch.draw(front, x, y);
        }

        if(showText) {
            text.draw(batch);
        }
    }

    public void setValue(float value) {
        this.value = value;

        if(text != null) {
            text.setText(Integer.toString((int) value) + " / " + Integer.toString((int) maxValue));
        }
    }

    public void changeValue(float value) {
        this.value += value;
    }

    public float getValue() {
        return value;
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

        if(text != null) {
            text.setPosition(x + front.getWidth() + TEXT_PADDING_X, y + front.getHeight() + TEXT_PADDING_Y);
        }
    }

    public void setShowText(boolean showText) {
        this.showText = showText;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setMinColor(Color minColor) {
        this.minColor = minColor;
    }

    public void setMaxColor(Color maxColor) {
        this.maxColor = maxColor;
    }

    public void setBackFull(Texture backFull) {
        this.backFull = backFull;

        back = new TextureRegion(backFull);
    }
}
