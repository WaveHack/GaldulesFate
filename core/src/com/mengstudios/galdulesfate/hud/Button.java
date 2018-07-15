package com.mengstudios.galdulesfate.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.mengstudios.galdulesfate.Assets;

public class Button implements Disposable {
    protected Texture button_up;
    protected Texture button_down;

    protected Text text;
    protected String textString;

    protected float x;
    protected float y;
    protected float width; //@TODO Change width and height definitions
    protected float height;

    protected boolean pressed;

    public interface OnClickListener {
        void onClick();
    }

    OnClickListener onClickListener;

    public Button() {
        setTextures(Assets.BUTTON_UP, Assets.BUTTON_DOWN);

        text = new Text(Assets.BUTTON_FONT);
    }

    public void touchUp(int screenX, int screenY) {
        pressed = false;

        if(screenX > x && screenX < x + width
                && screenY > y && screenY < y + height) {
            release();
        }
    }

    public void touchDown(int screenX, int screenY) {
        if(screenX > x && screenX < x + width
                && screenY > y && screenY < y + height) {
            press();
        }
    }

    public void press() {
        pressed = true;
    }

    public void release() {
        pressed = false;

        if(onClickListener != null) {
            onClickListener.onClick();
        }
    }

    public void draw(SpriteBatch batch) {
        if(pressed) {
            batch.draw(button_down, x, y);
        } else {
            batch.draw(button_up, x, y);
        }

        if(text != null) {
            text.draw(batch);
        }
    }

    public void setText(String textString) {
        this.textString = textString;

        text.setText(textString);
        text.updateBounds();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;

        text.setPosition(x + width / 2 - getTextWidth() / 2, y + height / 2 + getTextHeight() / 2);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getTextWidth() {
        return text.getWidth();
    }

    public float getTextHeight() {
        return text.getHeight();
    }

    public void setTextures(Texture button_up, Texture button_down) {
        this.button_up = button_up;
        this.button_down = button_down;

        width = button_up.getWidth();
        height = button_up.getHeight();
    }

    @Override
    public void dispose() {
        button_up.dispose();
        button_down.dispose();
        text.dispose();
    }
}
