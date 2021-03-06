package com.mengstudios.galdulesfate.item.resourceitem;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.hud.Text;
import com.mengstudios.galdulesfate.item.Item;

public class ResourceItem extends Item {
    private Text text;
    protected int count = 1;

    public ResourceItem() {
        text = new Text(Assets.STACK_FONT);
        text.setText("1");
        text.updateBounds();
    }

    public ResourceItem(int count) {
        text = new Text(Assets.STACK_FONT);
        text.setText(Integer.toString(count));
        text.updateBounds();

        this.count = count;
    }

    public void remove(int count) {
        if(count > this.count) {
            this.count = 0;
        } else {
            this.count -= count;
        }
    }

    @Override
    public void renderUi(SpriteBatch batch, float x, float y) {
        super.renderUi(batch, x, y);
        if(count != 1) {
            text.setText(Integer.toString(count));
            text.setPosition(x + 4, y + text.getHeight() + 4);
            text.draw(batch);
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

    public void setCount(int count) {
        this.count = count;
    }

    public void changeCount(int count) {
        this.count += count;
    }

    public int getCount() {
        return count;
    }
}
