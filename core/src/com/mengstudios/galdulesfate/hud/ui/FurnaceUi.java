package com.mengstudios.galdulesfate.hud.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.hud.Hud;
import com.mengstudios.galdulesfate.hud.slider.ProgressBar;
import com.mengstudios.galdulesfate.item.resourceitem.Bar;
import com.mengstudios.galdulesfate.item.resourceitem.CopperBar;
import com.mengstudios.galdulesfate.item.resourceitem.CopperOre;
import com.mengstudios.galdulesfate.item.resourceitem.Ore;

public class FurnaceUi extends Ui {
    private ProgressBar progressBar;

    private boolean justShown;
    private Ore ore;
    private Bar bar;

    private boolean smelting;
    private boolean soundIsPlaying;

    public FurnaceUi(Hud hud) {
        super(hud);
        backgroundTexture = Assets.FURNACE_UI;
        x = GaldulesFate.WIDTH / 2 - backgroundTexture.getWidth() / 2;
        y = GaldulesFate.HEIGHT / 2;

        justShown = true;

        progressBar = new ProgressBar(2);
        progressBar.setPosition(x + 86, y + 38);
    }

    @Override
    public void update(float delta) {
        progressBar.update(delta);

        justShown = false;

        if(smelting) {
            progressBar.changeValue(delta);
        }

        if(progressBar.hasFullProgress()) {
            progressBar.setValue(0);

            ore.changeCount(-1);
            if(ore.getCount() == 0) {
                ore = null;
                smelting = false;
                Assets.FURNACE_SOUND.stop();
                soundIsPlaying = false;
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
        progressBar.draw(batch);
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
        } else if(hud.getInventoryDisplay().isTouched(screenX, screenY)) {
            if(hud.getInventoryDisplay().getTouchedItem(screenX, screenY) instanceof CopperOre) {
                inputOre();
            }
        } else {
            hide();
        }
    }

    public void inputOre() {
        try {
            hud.getPlayScreen().getPlayer().getInventory().removeResource(CopperOre.class.newInstance(), 1);
            if(ore == null) {
                ore = new CopperOre();
            } else {
                ore.changeCount(1);
            }
            smelting = true;
            if(!soundIsPlaying) {
                Assets.FURNACE_SOUND.loop(0.8f);
                soundIsPlaying = true;
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void show() {
        super.show();
        justShown = true;
    }
}
