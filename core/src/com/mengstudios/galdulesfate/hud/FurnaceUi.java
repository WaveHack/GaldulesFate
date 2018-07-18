package com.mengstudios.galdulesfate.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.item.Bar;
import com.mengstudios.galdulesfate.item.CopperBar;
import com.mengstudios.galdulesfate.item.CopperOre;

public class FurnaceUi extends Ui {
    boolean justShown;
    Bar bar;

    public FurnaceUi(Hud hud) {
        super(hud);
        backgroundTexture = Assets.FURNACE_UI;
        x = GaldulesFate.WIDTH / 2 - backgroundTexture.getWidth() / 2;
        y = GaldulesFate.HEIGHT / 2;
        justShown = true;
    }

    @Override
    public void update(float delta) {
        justShown = false;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if(bar != null) {
            bar.renderUi(batch, x + backgroundTexture.getWidth() - 64 - 10, y + 16);
        }
    }

    @Override
    public void touchDown(int screenX, int screenY, int pointer, int button) {
        if(justShown)
            return;

        if(touchIn(screenX, screenY, x, y, backgroundTexture.getWidth(), backgroundTexture.getHeight())) {
            if(touchIn(screenX, screenY, x + backgroundTexture.getWidth() - 64 - 10, y + 16, 64, 64)) {
                if(bar != null) {
                    hud.getPlayScreen().getPlayer().getInventory().addResource(bar, bar.getCount());
                    bar = null;
                }
            }
        } else if(touchIn(screenX, screenY, hud.getInventoryDisplay().getX(), hud.getInventoryDisplay().getY(),
                Assets.INVENTORY.getWidth(), Assets.INVENTORY.getHeight())) {
            for(int i = 0; i < 36; i++) {
                int row = i / 9;
                int column = i % 9;
                if(screenX > hud.getInventoryDisplay().getX() + column * 64 && screenX < hud.getInventoryDisplay().getX() + column * 64 + 64
                        && screenY > hud.getInventoryDisplay().getY() + row * 64 && screenY < hud.getInventoryDisplay().getY() + row * 64 + 64) {
                    if(hud.getPlayScreen().getPlayer().getInventory().getItems()[((3 - row) * 9 + column)] instanceof CopperOre) {
                        try {
                            hud.getPlayScreen().getPlayer().getInventory().removeResource(CopperOre.class.newInstance(), 1);
                            if(bar == null) {
                                bar = new CopperBar();
                            } else {
                                bar.changeCount(1);
                            }
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            hide();
        }
    }

    @Override
    public void show() {
        super.show();
        justShown = true;
    }
}
