package com.mengstudios.galdulesfate.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.crafting.Recipe;
import com.mengstudios.galdulesfate.entity.Inventory;
import com.mengstudios.galdulesfate.item.CopperBar;
import com.mengstudios.galdulesfate.item.OakWood;
import com.mengstudios.galdulesfate.item.Wood;
import com.mengstudios.galdulesfate.item.tool.CopperAxe;
import com.mengstudios.galdulesfate.item.tool.CopperPickaxe;

public class AnvilUi extends InventoryDisplay {
    private boolean justShown;
    private Array<Recipe> recipes;

    public AnvilUi(Hud hud) {
        super(hud, new Inventory(32));

        inventory.add(new CopperPickaxe());
        inventory.add(new CopperAxe());

        recipes = new Array<>();
        Recipe recipe = new Recipe();
        recipe.addCost(new CopperBar(), 5);
        recipe.addCost(new Wood(), 3);
        recipe.setOutput(new CopperPickaxe());
        recipes.add(recipe);

        recipe = new Recipe();
        recipe.addCost(new CopperBar(), 5);
        recipe.addCost(new Wood(), 3);
        recipe.setOutput(new CopperAxe());
        recipes.add(recipe);

        rowCount = 4;
        columnCount = 8;

        width = columnCount * 64;
        height = rowCount * 64;

        x = GaldulesFate.WIDTH / 2 - width / 2;
        y = GaldulesFate.HEIGHT / 2 - height / 2;
    }

    @Override
    public void update(float delta) {
        justShown = false;
    }

    @Override
    public void touchDown(int screenX, int screenY, int pointer, int button) {
        if(justShown)
            return;

        if(isTouched(screenX, screenY)) {
            if(getTouchedItem(screenX, screenY) != null) {
                int touchedIndex = getTouchedIndex(screenX, screenY);
                Gdx.app.log("AnvilUi", Boolean.toString(recipes.get(touchedIndex).canCraft(hud.getPlayScreen().getPlayer())));
                if(recipes.get(touchedIndex).canCraft(hud.getPlayScreen().getPlayer())) {
                    recipes.get(touchedIndex).craft(hud.getPlayScreen().getPlayer());
                    recipes.get(touchedIndex).deductCosts(hud.getPlayScreen().getPlayer());
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
