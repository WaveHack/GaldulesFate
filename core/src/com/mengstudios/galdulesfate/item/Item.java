package com.mengstudios.galdulesfate.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Item {
    protected Texture texture;

    protected boolean swinging;
    protected float rotation = 0;

    protected final float ROTATION_SPEED = 500;

    public void update(float delta) {
        if(swinging) {
            rotation += ROTATION_SPEED * delta;
        } else {
            rotation = 0;
        }
    }

    public void renderWorld(SpriteBatch batch, float x, float y, boolean clockwise) {
        if(swinging) {
            batch.draw(texture, x, y, 0, 0, texture.getWidth(), texture.getHeight(),
                    1, 1,
                    clockwise ? rotation : -rotation,
                    0, 0, texture.getWidth(), texture.getHeight(),
                    false, false);
        }
    }

    public boolean isSwinging() {
        return swinging;
    }

    public void setSwinging(boolean swinging) {
        this.swinging = swinging;
    }

    public void renderUi(SpriteBatch batch, float x, float y) {
        batch.draw(texture, x, y);
    }

    public void renderInventory(SpriteBatch batch, float inventoryX, float inventoryY, int row, int column) {
        batch.draw(texture, inventoryX + column * 64, inventoryY + row * 64);
    }

    public Texture getTexture() {
        return texture;
    }
}
