package com.mengstudios.galdulesfate.hud.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mengstudios.galdulesfate.crafting.Recipe;
import com.mengstudios.galdulesfate.entity.Inventory;
import com.mengstudios.galdulesfate.hud.Hud;
import com.mengstudios.galdulesfate.item.Item;

public class RecipeUi extends InventoryDisplay {
    protected boolean justShown;
    protected Array<Recipe> recipes;
    protected int touchedItemIndex;

    public RecipeUi(Hud hud, Inventory inventory) {
        super(hud, inventory);
    }

    @Override
    public void update(float delta) {
        justShown = false;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);

        if(selectedItem != null) {
            drawRecipeCosts(batch);
        }
    }

    public void drawRecipeCosts(SpriteBatch batch) {
        Array<Item> costs = recipes.get(touchedItemIndex).getCosts();
        for(int i = 0; i < costs.size; i++) {
            costs.get(i).renderInventory(batch, x, y, -1, i);
        }
    }

    @Override
    public void touchDown(int screenX, int screenY, int pointer, int button) {
        if(justShown)
            return;

        if(isTouched(screenX, screenY)) {
            if(getTouchedItem(screenX, screenY) != null) {
                handleTouchedItem(screenX, screenY);
            } else {
                selectedItem = null;
            }
        } else {
            hide();
        }
    }

    public void handleTouchedItem(int screenX, int screenY) {
        touchedItemIndex = getTouchedIndex(screenX, screenY);
        if(selectedItem == getTouchedItem(screenX, screenY)) {
            if(recipes.get(touchedItemIndex).canCraft(hud.getPlayScreen().getPlayer())) {
                recipes.get(touchedItemIndex).craft(hud.getPlayScreen().getPlayer());
                recipes.get(touchedItemIndex).deductCosts(hud.getPlayScreen().getPlayer());
            }
        } else {
            selectedItem = getTouchedItem(screenX, screenY);
        }
        //Gdx.app.log("AnvilUi", Boolean.toString(recipes.get(touchedIndex).canCraft(hud.getPlayScreen().getPlayer())));
    }

    @Override
    public void show() {
        super.show();
        justShown = true;
    }
}
