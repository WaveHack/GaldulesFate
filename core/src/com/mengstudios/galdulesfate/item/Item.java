package com.mengstudios.galdulesfate.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Item {
    protected Texture texture;

    public void renderInventory(SpriteBatch batch, float inventoryX, float inventoryY, int row, int column) {
        batch.draw(texture, inventoryX + column * 64, inventoryY + row * 64);
    }
}
