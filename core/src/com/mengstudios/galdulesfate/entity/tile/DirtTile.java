package com.mengstudios.galdulesfate.entity.tile;

import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.world.World;

public class DirtTile extends Tile {

    public DirtTile(World world, float x, float y) {
        super(world);
        setRegion(Assets.DIRT_TILE);
        setBounds(x, y, Assets.DIRT_TILE.getWidth(), Assets.DIRT_TILE.getHeight());
    }

}
