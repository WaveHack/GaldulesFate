package com.mengstudios.galdulesfate.hud.ui;

import com.badlogic.gdx.utils.Array;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.crafting.Recipe;
import com.mengstudios.galdulesfate.entity.Inventory;
import com.mengstudios.galdulesfate.hud.Hud;
import com.mengstudios.galdulesfate.hud.slider.ProgressBar;
import com.mengstudios.galdulesfate.item.resourceitem.food.CookedBacon;
import com.mengstudios.galdulesfate.item.resourceitem.food.CookedMeat;
import com.mengstudios.galdulesfate.item.resourceitem.food.RawBacon;
import com.mengstudios.galdulesfate.item.resourceitem.food.RawBeef;

public class CampfireUi extends RecipeUi {

    public CampfireUi(Hud hud) {
        super(hud, new Inventory(32));

        inventory.add(new CookedMeat());
        inventory.add(new CookedBacon());

        recipes = new Array<>();
        Recipe recipe = new Recipe();
        recipe.addCost(new RawBeef(), 1);
        recipe.setOutput(new CookedMeat());
        recipes.add(recipe);

        recipe = new Recipe();
        recipe.addCost(new RawBacon(), 1);
        recipe.setOutput(new CookedBacon());
        recipes.add(recipe);

        rowCount = 4;
        columnCount = 8;

        width = columnCount * 64;
        height = rowCount * 64;

        x = GaldulesFate.WIDTH / 2 - width / 2;
        y = GaldulesFate.HEIGHT / 2 - height / 2;

        progressBar = new ProgressBar(2);
        progressBar.setPosition(x + width / 2, y - 32);
    }

    @Override
    public void startRecipe() {
        super.startRecipe();
        if(!soundIsPlaying) {
            Assets.FURNACE_SOUND.loop();
            soundIsPlaying = true;
        }
    }

    @Override
    public void makeRecipe() {
        super.makeRecipe();
        Assets.FURNACE_SOUND.stop();
        soundIsPlaying = false;
    }

}
