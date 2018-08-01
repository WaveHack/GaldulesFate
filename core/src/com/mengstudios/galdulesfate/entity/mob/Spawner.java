package com.mengstudios.galdulesfate.entity.mob;

import com.badlogic.gdx.utils.Timer;
import com.mengstudios.galdulesfate.MathExtended;
import com.mengstudios.galdulesfate.entity.Entity;
import com.mengstudios.galdulesfate.world.World;

public class Spawner extends Entity{
    Mob.MobType mobType;
    boolean spawning = true;

    public Spawner(World world, float x, float y, Mob.MobType mobType) {
        super(world, x, y);
        solid = false;
        canFall = false;
        neverFlip = true;
        this.mobType = mobType;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if(spawning) {
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    if(!world.getEntityManager().canAddMob()) {
                        return;
                    }

                    switch (mobType) {
                        case PIG:
                            world.getEntityManager().addEntity(new Pig(world, getX(), getY()));
                            break;
                        case COW:
                            world.getEntityManager().addEntity(new Cow(world, getX(), getY()));
                            break;
                    }
                    spawning = true;
                }
            }, MathExtended.getFloatBetween(10, 30));
            spawning = false;
        }
    }
}
