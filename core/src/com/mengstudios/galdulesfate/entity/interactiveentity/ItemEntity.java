package com.mengstudios.galdulesfate.entity.interactiveentity;

import com.mengstudios.galdulesfate.MathExtended;
import com.mengstudios.galdulesfate.item.Item;
import com.mengstudios.galdulesfate.item.resourceitem.ResourceItem;
import com.mengstudios.galdulesfate.world.World;

public class ItemEntity extends InteractiveEntity {
    Item item;

    public ItemEntity(World world, Item item, float x, float y) {
        super(world, x, y);
        solid = false;
        canFall = true;
        this.item = item;
        setRegion(item.getRegion());
        setBounds(x, y, item.getRegion().getRegionWidth(), item.getRegion().getRegionHeight());
        velocityX = Math.random() < 0.5 ? MathExtended.getFloatBetween(150, 300) : MathExtended.getFloatBetween(-300, -150);
        velocityY = Math.abs(velocityX);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if(velocityX > 60) {
            velocityX -= 6;
        } else if(velocityX < -60) {
            velocityX += 6;
        } else if(grounded){
            velocityX = 0;
        }
    }

    @Override
    public void touchDown() {
        if(item instanceof ResourceItem) {
            world.getPlayer().getInventory().addResource((ResourceItem) item);
        } else {
            world.getPlayer().getInventory().add(item);
        }
        remove();
    }
}
