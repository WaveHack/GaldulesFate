package com.mengstudios.galdulesfate.item.tool;

import com.badlogic.gdx.Gdx;
import com.mengstudios.galdulesfate.entity.Entity;
import com.mengstudios.galdulesfate.entity.interactiveentity.mineralrock.MineralRock;
import com.mengstudios.galdulesfate.screen.PlayScreen;

public class Pickaxe extends Tool {
    public Pickaxe(PlayScreen playScreen) {
        super(playScreen);
    }

    @Override
    public void use(Entity entity) {
        super.use(entity);
    }

    public void mine(MineralRock mineralRock, float delta) {
        if(level >= mineralRock.getMinimumLevel()) {
            mineralRock.mine(delta);
        } else {
            Gdx.app.log("Pickaxe", "Pickaxe level not high enough!");
        }
    }
}
