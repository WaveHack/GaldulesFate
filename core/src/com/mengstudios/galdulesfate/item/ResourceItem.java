package com.mengstudios.galdulesfate.item;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.hud.Text;

public class ResourceItem extends Item {
    private Text text;
    private int count = 1;

    public ResourceItem() {
        text = new Text(Assets.STACK_FONT);
        text.setText("1");
        text.updateBounds();
    }

    public void remove(int count) {
        if(count > this.count) {
            this.count = 0;
        } else {
            this.count -= count;
        }
    }

    @Override
    public void renderInventory(SpriteBatch batch, float inventoryX, float inventoryY, int row, int column) {
        super.renderInventory(batch, inventoryX, inventoryY, row, column);
        if(count != 1) {
            text.setText(Integer.toString(count));
            text.setPosition(inventoryX + column * 64 + 4, inventoryY + row * 64 + text.getHeight() + 4);
            text.draw(batch);
        }
    }

    public void changeCount(int count) {
        this.count += count;
    }

    public int getCount() {
        return count;
    }
}
