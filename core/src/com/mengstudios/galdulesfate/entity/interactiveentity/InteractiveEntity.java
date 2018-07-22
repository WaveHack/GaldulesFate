package com.mengstudios.galdulesfate.entity.interactiveentity;

import com.mengstudios.galdulesfate.entity.Entity;
import com.mengstudios.galdulesfate.world.World;

public abstract class InteractiveEntity extends Entity {

    public InteractiveEntity(World world, float x, float y) {
        super(world, x, y);
    }

    public void create() {

    }

    public abstract void touchDown();

    public void touchHeld(float delta) {

    }
}
