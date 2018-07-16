package com.mengstudios.galdulesfate.entity.mineralrock;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.entity.Entity;
import com.mengstudios.galdulesfate.entity.ItemEntity;
import com.mengstudios.galdulesfate.item.tool.CopperOre;
import com.mengstudios.galdulesfate.world.World;

public class MineralRock extends Entity {
    protected Texture texture;
    protected int minimumLevel;
    protected boolean mined;

    public MineralRock(World world, float x, float y) {
        super(world, x, y);
        solid = false;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if(!mined) {
            setRegion(texture);
        } else {
            setRegion(Assets.EMPTY_ROCKS);
        }
    }

    public void mine() {
        if(mined)
            return;

        mined = true;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                mined = false;
                world.getEntities().add(new ItemEntity(world, new CopperOre(), getX() + 64, getY()));
            }
        }, 2f);
    }

    public int getMinimumLevel() {
        return minimumLevel;
    }
}
