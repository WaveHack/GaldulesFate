package com.mengstudios.galdulesfate.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ui {
    Hud hud;
    Texture backgroundTexture;
    float x;
    float y;
    boolean hidden;
    boolean removed;

    public Ui(Hud hud) {
        this.hud = hud;
    }

    public void update(float delta) {

    }

    public void draw(SpriteBatch batch) {
        batch.draw(backgroundTexture, x, y);
    }

    public void remove() {
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void hide() {
        hidden = true;
    }

    public void show() {
        hidden = false;
    }

    public boolean isHidden() {
        return hidden;
    }

    public boolean touchIn(int screenX, int screenY, float x, float y, float width, float height) {
        return screenX > x && screenX < x + width && screenY > y && screenY < y + height;
    }

    public void touchDown(int screenX, int screenY, int pointer, int button) {

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
