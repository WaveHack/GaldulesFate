package com.mengstudios.galdulesfate.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.item.Bar;
import com.mengstudios.galdulesfate.item.CopperBar;
import com.mengstudios.galdulesfate.item.CopperOre;
import com.mengstudios.galdulesfate.item.Ore;

public class FurnaceUi extends Ui {
    private Slider arrowProgress;

    private boolean justShown;
    private Ore ore;
    private Bar bar;

    private boolean smelting;

    public FurnaceUi(Hud hud) {
        super(hud);
        backgroundTexture = Assets.FURNACE_UI;
        x = GaldulesFate.WIDTH / 2 - backgroundTexture.getWidth() / 2;
        y = GaldulesFate.HEIGHT / 2;

        justShown = true;

        arrowProgress = new Slider();
        arrowProgress.setShowText(false);
        arrowProgress.setMinColor(Color.WHITE);
        arrowProgress.setMaxColor(Color.WHITE);
        arrowProgress.setBackFull(Assets.FURNACE_ARROW);
        arrowProgress.setMaxValue(2);
        arrowProgress.setValue(0);
        arrowProgress.setPosition(x + 88, y + 40);
    }

    @Override
    public void update(float delta) {
        arrowProgress.update(delta);

        justShown = false;

        if(smelting) {
            arrowProgress.changeValue(delta);
        }

        if(arrowProgress.getValue() >= arrowProgress.getMaxValue()) {
            arrowProgress.setValue(0);

            ore.changeCount(-1);
            if(ore.getCount() == 0) {
                ore = null;
                smelting = false;
                Assets.FURNACE_SOUND.stop();
            }

            if (bar == null) {
                bar = new CopperBar();
            } else {
                bar.changeCount(1);
            }
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if(ore != null) {
            ore.renderUi(batch, x + 10, y + 16);
        }
        if(bar != null) {
            bar.renderUi(batch, x + backgroundTexture.getWidth() - 64 - 10, y + 16);
        }
        arrowProgress.draw(batch);
    }

    @Override
    public void touchDown(int screenX, int screenY, int pointer, int button) {
        if(justShown)
            return;

        if(touchIn(screenX, screenY, x, y, backgroundTexture.getWidth(), backgroundTexture.getHeight())) {
            if(touchIn(screenX, screenY, x + backgroundTexture.getWidth() - 64 - 10, y + 16, 64, 64)) {
                if(bar != null && hud.getPlayScreen().getPlayer().getInventory().canHold(bar)) {
                    hud.getPlayScreen().getPlayer().getInventory().addResource(bar);
                    bar = null;
                }
            }
        } else if(touchIn(screenX, screenY, hud.getInventoryDisplay().getX(), hud.getInventoryDisplay().getY(),
                InventoryDisplay.WIDTH, InventoryDisplay.HEIGHT)) {
            if(hud.getInventoryDisplay().getTouchedItem(screenX, screenY) instanceof CopperOre) {
                try {
                    hud.getPlayScreen().getPlayer().getInventory().removeResource(CopperOre.class.newInstance(), 1);
                    if(ore == null) {
                        ore = new CopperOre();
                    } else {
                        ore.changeCount(1);
                    }
                    smelting = true;
                    Assets.FURNACE_SOUND.loop(1f);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
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
