package com.mengstudios.galdulesfate.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.entity.Inventory;
import com.mengstudios.galdulesfate.item.Item;

public class InventoryDisplay extends Ui {
    protected Inventory inventory;

    private Item selectedItem;

    protected int rowCount = 4;
    protected int columnCount = 9;

    protected int width = columnCount * 64;
    protected int height = rowCount * 64;

    public InventoryDisplay(Hud hud, Inventory inventory) {
        super(hud);
        
        backgroundTexture = Assets.BOX;
        
        x = GaldulesFate.WIDTH - width - 20;
        y = GaldulesFate.HEIGHT - height - 20;

        this.inventory = inventory;
    }

    @Override
    public void update(float delta) {
        if(selectedItem != null) {
            selectedItem.update(delta);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.setColor(new Color().set(1f, 1f, 1f, 0.75f));
        for(int i = 0; i < rowCount; i++) {
            for(int j = 0; j < columnCount; j++) {
                batch.draw(backgroundTexture, x + j * 64, y + i * 64);
            }
        }
        batch.setColor(Color.WHITE);
        drawHighlight(batch);
        for(int i = 0; i < inventory.getItems().length; i++) {
            if(inventory.getItems()[i] == null)
                continue;

            inventory.getItems()[i].renderInventory(
                    batch, x, y, (rowCount * columnCount - 1 - i) / columnCount, i % columnCount);
        }
    }

    public void drawHighlight(SpriteBatch batch) {
        for(int i = 0; i < inventory.getItems().length; i++) {
            if(selectedItem == inventory.getItems()[i] && selectedItem != null) {
                batch.draw(Assets.HIGHLIGHT, x + i % columnCount * 64, (float) (y + Math.floor((rowCount * columnCount - 1 - i) / columnCount) * 64));
                break;
            }
        }
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public Item getTouchedItem(int screenX, int screenY) {
        for(int i = 0; i < rowCount * columnCount; i++) {
            int row = i / columnCount;
            int column = i % columnCount;
            if (screenX > x + column * 64 && screenX < x + column * 64 + 64
                    && screenY > y + row * 64 && screenY < y + row * 64 + 64) {
                return inventory.getItems()[((rowCount - 1 - row) * columnCount + column)];
            }
        }
        return null;
    }

    public int getTouchedIndex(int screenX, int screenY) {
        for(int i = 0; i < rowCount * columnCount; i++) {
            int row = i / columnCount;
            int column = i % columnCount;
            if (screenX > x + column * 64 && screenX < x + column * 64 + 64
                    && screenY > y + row * 64 && screenY < y + row * 64 + 64) {
                return (3 - row) * columnCount + column;
            }
        }
        return 0;
    }

    public void touchDown(int screenX, int screenY, int pointer, int button) {
        if(selectedItem != null) {
            selectedItem.setSwinging(true);
        }
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(screenX > x && screenX < x + width
                && screenY > y && screenY < y + height){
            for(int i = 0; i < rowCount * columnCount; i++) {
                int row = i / columnCount;
                int column = i % columnCount;
                if(screenX > x + column * 64 && screenX < x + column * 64 + 64
                        && screenY > y + row * 64 && screenY < y + row * 64 + 64) {
                    Item selectedItemTemp = inventory.getItems()[((rowCount - 1 - row) * columnCount + column)];
                    if(selectedItemTemp == selectedItem) {
                        selectedItem = null;
                    } else {
                        selectedItem = selectedItemTemp;
                    }
                }
            }
        }

        if(selectedItem != null) {
            selectedItem.setSwinging(false);
        }

        return false;
    }
    
    public boolean isTouched(int screenX, int screenY) {
        return screenX > x && screenX < x + width
                && screenY > y && screenY < y + height;
    }
}
