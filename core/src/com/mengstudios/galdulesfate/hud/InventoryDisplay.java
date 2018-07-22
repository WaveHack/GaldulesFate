package com.mengstudios.galdulesfate.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.entity.Player;
import com.mengstudios.galdulesfate.item.Item;

public class InventoryDisplay extends Ui {
    private Player player;

    private Item selectedItem;

    private static int rowCount = 4;
    private static int columnCount = 9;

    public static int WIDTH = columnCount * 64;
    public static int HEIGHT = rowCount * 64;

    public InventoryDisplay(Hud hud) {
        super(hud);
        
        backgroundTexture = Assets.BOX;
        
        x = GaldulesFate.WIDTH - WIDTH - 20;
        y = GaldulesFate.HEIGHT - HEIGHT - 20;

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
        batch.setColor(new Color().set(1f, 1f, 1f, 0.75f));
        for(int i = 0; i < rowCount; i++) {
            for(int j = 0; j < columnCount; j++) {
                batch.draw(backgroundTexture, x + j * 64, y + i * 64);
            }
        }
        batch.setColor(Color.WHITE);
        drawHighlight(batch);
        for(int i = 0; i < player.getInventory().getItems().length; i++) {
            if(player.getInventory().getItems()[i] == null)
                continue;

            player.getInventory().getItems()[i].renderInventory(
                    batch, x, y, (rowCount * columnCount - 1 - i) / columnCount, i % columnCount);
        }
    }

    public void drawHighlight(SpriteBatch batch) {
        for(int i = 0; i < player.getInventory().getItems().length; i++) {
            if(selectedItem == player.getInventory().getItems()[i] && selectedItem != null) {
                batch.draw(Assets.HIGHLIGHT, x + i % columnCount * 64, (float) (y + Math.floor((rowCount * columnCount - 1 - i) / columnCount) * 64));
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
        if(screenX > x && screenX < x + WIDTH
                && screenY > y && screenY < y + HEIGHT){
            for(int i = 0; i < 36; i++) {
                int row = i / columnCount;
                int column = i % columnCount;
                if(screenX > x + column * 64 && screenX < x + column * 64 + 64
                        && screenY > y + row * 64 && screenY < y + row * 64 + 64) {
                    Item selectedItemTemp = player.getInventory().getItems()[((rowCount - 1 - row) * columnCount + column)];
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
