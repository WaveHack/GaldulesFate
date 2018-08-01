package com.mengstudios.galdulesfate.item.tool;

import com.badlogic.gdx.math.Polygon;
import com.mengstudios.galdulesfate.entity.Entity;
import com.mengstudios.galdulesfate.entity.mob.Mob;
import com.mengstudios.galdulesfate.item.Item;
import com.mengstudios.galdulesfate.screen.PlayScreen;

public class Tool extends Item {
    protected Polygon boundingPolygon;
    protected int level;
    protected float knockback;

    protected int damage;

    public Tool(PlayScreen playScreen) {
        super(playScreen);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        boundingPolygon.setPosition(x, y);
        if(swinging) {
            boundingPolygon.setRotation(rotation);
        }
    }

    public void use(Entity entity) {
        if(entity instanceof Mob) {
            ((Mob) entity).takeDamage(damage);
        }
    }

    public void initBoundingPolygon() {
        boundingPolygon = new Polygon(new float[]{0, 0, region.getRegionWidth(), 0, region.getRegionWidth(), region.getRegionHeight(), 0, region.getRegionHeight()});
    }

    public Polygon getBoundingPolygon() {
        return boundingPolygon;
    }
}
