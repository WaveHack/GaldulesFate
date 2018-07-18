package com.mengstudios.galdulesfate.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.entity.Player;
import com.mengstudios.galdulesfate.item.Item;

public class InventoryDisplay {
    private Hud hud;

    private Texture inventoryBackground = Assets.INVENTORY;

    private final float INVENTORY_X = GaldulesFate.WIDTH - Assets.INVENTORY.getWidth() - 20;
    private final float INVENTORY_Y = GaldulesFate.HEIGHT - Assets.INVENTORY.getHeight() - 20;

    private Player player;

    private Item selectedItem;

    public InventoryDisplay(Hud hud) {
        this.hud = hud;

        player = hud.getPlayScreen().getPlayer();
    }

    public void update(float delta) {
        if(selectedItem != null) {
            selectedItem.update(delta);
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(inventoryBackground, INVENTORY_X, INVENTORY_Y);
        drawHighlight(batch);
        for(int i = 0; i < player.getInventory().getItems().length; i++) {
            if(player.getInventory().getItems()[i] == null)
                continue;

            player.getInventory().getItems()[i].renderInventory(
                    batch, INVENTORY_X, INVENTORY_Y, (35 - i) / 9, i % 9);
        }
    }

    public void drawHighlight(SpriteBatch batch) {
        for(int i = 0; i < player.getInventory().getItems().length; i++) {
            if(selectedItem == player.getInventory().getItems()[i] && selectedItem != null) {
                batch.draw(Assets.HIGHLIGHT, INVENTORY_X + i % 9 * 64, (float) (INVENTORY_Y + Math.floor((35 - i) / 9) * 64));
                break;
            }
        }
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public float getX() {
        return INVENTORY_X;
    }

    public float getY() {
        return INVENTORY_Y;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(selectedItem != null) {
            selectedItem.setSwinging(true);
        }

        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(screenX > INVENTORY_X && screenX < INVENTORY_X + inventoryBackground.getWidth()
                && screenY > INVENTORY_Y && screenY < INVENTORY_Y + inventoryBackground.getHeight()){
            for(int i = 0; i < 36; i++) {
                int row = i / 9;
                int column = i % 9;
                if(screenX > INVENTORY_X + column * 64 && screenX < INVENTORY_X + column * 64 + 64
                        && screenY > INVENTORY_Y + row * 64 && screenY < INVENTORY_Y + row * 64 + 64) {
                    Item selectedItemTemp = player.getInventory().getItems()[((3 - row) * 9 + column)];
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
}
