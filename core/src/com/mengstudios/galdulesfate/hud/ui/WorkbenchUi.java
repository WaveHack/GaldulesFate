package com.mengstudios.galdulesfate.hud.ui;

import com.badlogic.gdx.utils.Array;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.crafting.Recipe;
import com.mengstudios.galdulesfate.entity.Inventory;
import com.mengstudios.galdulesfate.hud.Hud;
import com.mengstudios.galdulesfate.item.Campfire;
import com.mengstudios.galdulesfate.item.resourceitem.Wood;

public class WorkbenchUi extends RecipeUi {
    public WorkbenchUi(Hud hud) {
        super(hud, new Inventory(32));

        inventory.add(new Campfire(hud.getPlayScreen()));

        recipes = new Array<>();
        Recipe recipe = new Recipe();
        recipe.addCost(new Wood(), 2);
        recipe.setOutput(new Campfire(hud.getPlayScreen()));
        recipes.add(recipe);

        rowCount = 4;
        columnCount = 8;

        width = columnCount * 64;
        height = rowCount * 64;

        x = GaldulesFate.WIDTH / 2 - width / 2;
        y = GaldulesFate.HEIGHT / 2 - height / 2;
    }
}
