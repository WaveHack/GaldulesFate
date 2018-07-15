package com.mengstudios.galdulesfate.entity.tile;

import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.world.World;

public class GrassTile extends Tile {

    public GrassTile(World world, float x, float y) {
        super(world);
        setRegion(Assets.GRASS_TILE);
        setBounds(x, y, Assets.GRASS_TILE.getWidth(), Assets.GRASS_TILE.getHeight());
    }

}
