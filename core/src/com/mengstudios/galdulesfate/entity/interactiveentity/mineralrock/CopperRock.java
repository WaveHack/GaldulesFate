package com.mengstudios.galdulesfate.entity.interactiveentity.mineralrock;

import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.item.resourceitem.CopperOre;
import com.mengstudios.galdulesfate.world.World;

public class CopperRock extends MineralRock {

    public CopperRock(World world, float x, float y) {
        super(world, x, y);

        texture = Assets.COPPER_ORE_ROCKS;
        dropOre = new CopperOre();
        setRegion(texture);
        setBounds(x, y, Assets.COPPER_ORE_ROCKS.getWidth(), Assets.COPPER_ORE_ROCKS.getHeight());
    }

}
