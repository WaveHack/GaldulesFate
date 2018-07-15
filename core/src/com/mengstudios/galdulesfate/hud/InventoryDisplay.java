package com.mengstudios.galdulesfate.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.entity.Player;
import com.mengstudios.galdulesfate.item.Item;

public class InventoryDisplay {
    private Hud hud;

    private Texture inventoryBackground;

    private static final float INVENTORY_X = GaldulesFate.WIDTH - Assets.INVENTORY.getWidth() - 20;
    private static final float INVENTORY_Y = GaldulesFate.HEIGHT - Assets.INVENTORY.getHeight() - 20;

    private Player player;

    private Item selectedItem;

    public InventoryDisplay(Hud hud) {
        this.hud = hud;

        player = hud.getPlayScreen().getPlayer();

        inventoryBackground = Assets.INVENTORY;
    }

    public void update(float delta) {

    }

    public void draw(SpriteBatch batch) {
        batch.draw(inventoryBackground, INVENTORY_X, INVENTORY_Y);
        drawHighlight(batch);
        for(int i = 0; i < player.getInventory().getItems().size(); i++) {
            player.getInventory().getItems().get(i).renderInventory(
                    batch, INVENTORY_X, INVENTORY_Y, (27 - i) / 9, i % 9);
        }
    }

    public void drawHighlight(SpriteBatch batch) {
        for(int i = 0; i < player.getInventory().getItems().size(); i++) {
            if(selectedItem == player.getInventory().getItems().get(i)) {
                batch.draw(Assets.HIGHLIGHT, INVENTORY_X + i % 9 * 64, INVENTORY_Y + (27 - i) / 9 * 64);
            }
        }
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
                    if((3 - row) * 9 + column >= player.getInventory().getItems().size())
                        return false;
                    Item selectedItemTemp = player.getInventory().getItems().get((3 - row) * 9 + column);
                    if(selectedItemTemp == selectedItem) {
                        selectedItem = null;
                    } else {
                        selectedItem = selectedItemTemp;
                    }
                }
            }
        }
        return false;
    }
}
