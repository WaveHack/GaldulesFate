package com.mengstudios.galdulesfate.entity.tile;

import com.mengstudios.galdulesfate.entity.Entity;
import com.mengstudios.galdulesfate.world.World;

public class Tile extends Entity {

    public Tile(World world) {
        super(world);
    }

    public Tile(World world, float x, float y) {
        super(world, x, y);
    }
}
