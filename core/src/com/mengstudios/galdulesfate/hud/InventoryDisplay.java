package com.mengstudios.galdulesfate.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.entity.Player;
import com.mengstudios.galdulesfate.item.Item;

public class InventoryDisplay extends Ui {
    private Player player;

    private Item selectedItem;

    public InventoryDisplay(Hud hud) {
        super(hud);
        
        backgroundTexture = Assets.INVENTORY;
        
        x = GaldulesFate.WIDTH - Assets.INVENTORY.getWidth() - 20;
        y = GaldulesFate.HEIGHT - Assets.INVENTORY.getHeight() - 20;

        player = hud.getPlayScreen().getPlayer();
    }

    @Override
    public void update(float delta) {
        if(selectedItem != null) {
            selectedItem.update(delta);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(backgroundTexture, x, y);
        drawHighlight(batch);
        for(int i = 0; i < player.getInventory().getItems().length; i++) {
            if(player.getInventory().getItems()[i] == null)
                continue;

            player.getInventory().getItems()[i].renderInventory(
                    batch, x, y, (35 - i) / 9, i % 9);
        }
    }

    public void drawHighlight(SpriteBatch batch) {
        for(int i = 0; i < player.getInventory().getItems().length; i++) {
            if(selectedItem == player.getInventory().getItems()[i] && selectedItem != null) {
                batch.draw(Assets.HIGHLIGHT, x + i % 9 * 64, (float) (y + Math.floor((35 - i) / 9) * 64));
                break;
            }
        }
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void touchDown(int screenX, int screenY, int pointer, int button) {
        if(selectedItem != null) {
            selectedItem.setSwinging(true);
        }
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(screenX > x && screenX < x + backgroundTexture.getWidth()
                && screenY > y && screenY < y + backgroundTexture.getHeight()){
            for(int i = 0; i < 36; i++) {
                int row = i / 9;
                int column = i % 9;
                if(screenX > x + column * 64 && screenX < x + column * 64 + 64
                        && screenY > y + row * 64 && screenY < y + row * 64 + 64) {
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
