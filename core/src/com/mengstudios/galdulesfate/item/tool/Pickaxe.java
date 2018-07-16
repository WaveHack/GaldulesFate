package com.mengstudios.galdulesfate.item.tool;

import com.badlogic.gdx.Gdx;
import com.mengstudios.galdulesfate.entity.Entity;
import com.mengstudios.galdulesfate.entity.mineralrock.MineralRock;

public class Pickaxe extends Tool {
    @Override
    public void use(Entity entity) {
        if(entity instanceof MineralRock) {
            if(level >= ((MineralRock) entity).getMinimumLevel()) {
                ((MineralRock) entity).mine();
            } else {
                Gdx.app.log("Pickaxe", "Pickaxe level not high enough!");
            }
        }
    }
}
