package com.mengstudios.galdulesfate.hud.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.hud.Button;
import com.mengstudios.galdulesfate.hud.Hud;

public class Ribbon extends Ui {
    Array<Button> buttons;
    Button inventoryButton;
    InventoryDisplay inventoryDisplay;

    public Ribbon(Hud hud) {
        super(hud);

        inventoryDisplay = new InventoryDisplay(hud, hud.getPlayScreen().getPlayer().getInventory());

        buttons = new Array<>();

        inventoryButton = new Button();
        inventoryButton.setTextures(Assets.UI_BOX, Assets.UI_BOX);
        inventoryButton.setPosition(inventoryDisplay.getX() - 64, inventoryDisplay.getY() + inventoryDisplay.height - 64);
        inventoryButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick() {
                if(inventoryDisplay.isOpen()) {
                    inventoryDisplay.close();
                } else {
                    inventoryDisplay.open();
                }
            }
        });
        buttons.add(inventoryButton);
    }

    @Override
    public void update(float delta) {
        inventoryDisplay.update(delta);
    }

    @Override
    public void draw(SpriteBatch batch) {
        inventoryDisplay.draw(batch);
        batch.setColor(new Color().set(1, 1, 1, 0.5f));
        for(Button button: buttons) {
            button.draw(batch);
        }
        batch.setColor(Color.WHITE);
    }

    public void touchDown(int screenX, int screenY, int pointer, int button) {
        for(Button button1: buttons) {
            button1.touchDown(screenX, screenY);
        }
        inventoryDisplay.touchDown(screenX, screenY, pointer, button);
    }

    public void touchUp(int screenX, int screenY, int pointer, int button) {
        for(Button button1: buttons) {
            button1.touchUp(screenX, screenY);
        }
        inventoryDisplay.touchUp(screenX, screenY, pointer, button);
    }

    public InventoryDisplay getInventoryDisplay() {
        return inventoryDisplay;
    }
}
