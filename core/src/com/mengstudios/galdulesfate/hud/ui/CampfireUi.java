package com.mengstudios.galdulesfate.hud.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.crafting.Recipe;
import com.mengstudios.galdulesfate.entity.Inventory;
import com.mengstudios.galdulesfate.hud.Hud;
import com.mengstudios.galdulesfate.item.Item;
import com.mengstudios.galdulesfate.item.resourceitem.food.CookedMeat;
import com.mengstudios.galdulesfate.item.resourceitem.food.RawBeef;

public class CampfireUi extends RecipeUi {

    public CampfireUi(Hud hud) {
        super(hud, new Inventory(32));

        inventory.add(new CookedMeat());

        recipes = new Array<>();
        Recipe recipe = new Recipe();
        recipe.addCost(new RawBeef(), 1);
        recipe.setOutput(new CookedMeat());
        recipes.add(recipe);

        rowCount = 4;
        columnCount = 8;

        width = columnCount * 64;
        height = rowCount * 64;

        x = GaldulesFate.WIDTH / 2 - width / 2;
        y = GaldulesFate.HEIGHT / 2 - height / 2;
    }

}
