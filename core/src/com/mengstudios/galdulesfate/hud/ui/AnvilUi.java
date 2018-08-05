package com.mengstudios.galdulesfate.hud.ui;

import com.badlogic.gdx.utils.Array;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.crafting.Recipe;
import com.mengstudios.galdulesfate.entity.Inventory;
import com.mengstudios.galdulesfate.hud.Hud;
import com.mengstudios.galdulesfate.hud.slider.ProgressBar;
import com.mengstudios.galdulesfate.item.resourceitem.CopperBar;
import com.mengstudios.galdulesfate.item.resourceitem.Wood;
import com.mengstudios.galdulesfate.item.tool.CopperAxe;
import com.mengstudios.galdulesfate.item.tool.CopperPickaxe;
import com.mengstudios.galdulesfate.item.tool.weapon.CopperSword;

public class AnvilUi extends RecipeUi {

    public AnvilUi(Hud hud) {
        super(hud, new Inventory(32));

        inventory.add(new CopperPickaxe(hud.getPlayScreen()));
        inventory.add(new CopperAxe(hud.getPlayScreen()));
        inventory.add(new CopperSword(hud.getPlayScreen()));

        recipes = new Array<>();
        Recipe recipe = new Recipe();
        recipe.addCost(new CopperBar(), 5);
        recipe.addCost(new Wood(), 3);
        recipe.setOutput(new CopperPickaxe(hud.getPlayScreen()));
        recipes.add(recipe);

        recipe = new Recipe();
        recipe.addCost(new CopperBar(), 5);
        recipe.addCost(new Wood(), 3);
        recipe.setOutput(new CopperAxe(hud.getPlayScreen()));
        recipes.add(recipe);

        recipe = new Recipe();
        recipe.addCost(new CopperBar(), 8);
        recipe.setOutput(new CopperSword(hud.getPlayScreen()));
        recipes.add(recipe);

        rowCount = 4;
        columnCount = 8;

        width = columnCount * 64;
        height = rowCount * 64;

        x = GaldulesFate.WIDTH / 2 - width / 2;
        y = GaldulesFate.HEIGHT / 2 - height / 2;

        progressBar = new ProgressBar(2.5f);
        progressBar.setPosition(x + width / 2, y - 32);
    }

    @Override
    public void startRecipe() {
        super.startRecipe();
        if(!soundIsPlaying) {
            Assets.ANVIL_SOUND.loop();
            soundIsPlaying = true;
        }
    }

    @Override
    public void makeRecipe() {
        super.makeRecipe();
        Assets.ANVIL_SOUND.stop();
        soundIsPlaying = false;
    }
}
