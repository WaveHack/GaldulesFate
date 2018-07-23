package com.mengstudios.galdulesfate.item.tool;

import com.badlogic.gdx.Gdx;
import com.mengstudios.galdulesfate.entity.interactiveentity.tree.Tree;

public class Axe extends Tool {
    public void chop(Tree tree, float delta) {
        if(level >= tree.getMinimumLevel()) {
            tree.chop(delta);
        } else {
            Gdx.app.log("Axe", "Axe level not high enough!");
        }
    }
}
